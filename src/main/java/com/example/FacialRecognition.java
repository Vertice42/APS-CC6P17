package com.example;

import java.net.URISyntaxException;
import java.nio.file.Paths;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.FaceRecognizerSF;
import org.opencv.objdetect.Objdetect;

public class FacialRecognition {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public void saveImage(MatOfRect facesDetected, Mat loadedImage, String targetPath) {
        Rect[] facesArray = facesDetected.toArray();
        if (facesArray.length > 0) {
            for (Rect face : facesArray) {
                Imgproc.rectangle(loadedImage, face.tl(), face.br(),
                        new Scalar(0, 0, 255), 1);
            }
        }
        Imgcodecs.imwrite(targetPath, loadedImage);
    }

    private String getResFile(String name){
        try {
            return Paths.get(FacialRecognition.class.getClassLoader()
            .getResource(name).toURI()).toAbsolutePath().toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    MatOfRect detectFaces(Mat loadedImage) {
        MatOfRect facesDetected = new MatOfRect();
        int minFaceSize = Math.round(loadedImage.rows() * 0.1f);

        CascadeClassifier cascadeClassifier = new CascadeClassifier();
        cascadeClassifier.load(getResFile("models/haarcascades/haarcascade_frontalface_alt.xml")); 
        cascadeClassifier.detectMultiScale(loadedImage,
                facesDetected,
                1.1,
                3,
                Objdetect.CASCADE_SCALE_IMAGE,
                new Size(minFaceSize, minFaceSize),
                new Size());
        return facesDetected;
    }

    boolean recognizeFace(String imageA, String imageB) {
        Mat img1 = Imgcodecs.imread(imageA);
        Mat img2 = Imgcodecs.imread(imageB);

        var faceRecognizer = FaceRecognizerSF
        .create(getResFile("models/face_recognition_sface_2021dec.onnx"), "");

        Mat aligned_face1 = new Mat();
        Mat aligned_face2 = new Mat();

        MatOfRect face1box = detectFaces(img1);
        MatOfRect face2box = detectFaces(img2);

        faceRecognizer.alignCrop(img1, face1box.row(0), aligned_face1);
        faceRecognizer.alignCrop(img2, face2box.row(0), aligned_face2);

        Mat feature1 = new Mat();
        Mat feature2 = new Mat();

        faceRecognizer.feature(aligned_face1, feature1);
        feature1 = feature1.clone();
        faceRecognizer.feature(aligned_face2, feature2);
        feature2 = feature2.clone();

        final double COS_SCORE = faceRecognizer.match(feature1, feature2, FaceRecognizerSF.FR_COSINE);
        final double L2_SCORE = faceRecognizer.match(feature1, feature2, FaceRecognizerSF.FR_NORM_L2);
        System.out.println("cos_score = " + COS_SCORE + " L2_score = " + L2_SCORE);

        if (COS_SCORE >= 0.363d || L2_SCORE <= 1.128d) {
            return true;
        }
        return false;

    }
}