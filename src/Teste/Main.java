package Teste;


import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.face.LBPHFaceRecognizer;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;

import static org.opencv.core.CvType.CV_32SC1;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgproc.Imgproc.COLOR_BGRA2GRAY;




public class Main {

    public void trainPhotos() {
//        File directory = new File("C:\\photos\\");
//        FilenameFilter filter = (File dir, String name1) -> name1.endsWith(".jpg") || name1.endsWith(".png");
//
//        File[] files = directory.listFiles(filter);
//        opencv_core.MatVector photos = new opencv_core.MatVector(files.length);
//        Mat labels = new Mat(files.length, 1, CV_32SC1);
//        IntBuffer labelsBuffer = labels.createBuffer();
//
//        int counter = 0;
//        for (File image : files) {
//            Mat photo = imread(image.getAbsolutePath(), COLOR_BGRA2GRAY);
//            int idP = Integer.parseInt(image.getName().split("\\.")[1]);
//            opencv_imgproc.resize(photo, photo, new Size(160, 160));
//
//            photos.put(counter, photo);
//            labelsBuffer.put(counter, idP);
//            counter++;
//        }
//
//        LBPHFaceRecognizer lbph = LBPHFaceRecognizer.create(1, 8, 8, 8, 12);
//        lbph.train(photos, labels);
//        lbph.save("C:\\photos\\classifierLBPH.yml");
    }
    public static void main(String[] args) {
        new Main().trainPhotos();
    }
}
