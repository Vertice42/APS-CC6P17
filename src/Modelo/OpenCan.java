package Modelo;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
import org.opencv.videoio.VideoCapture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class OpenCan {

    private volatile boolean running = false;
    private BufferedImage ImgSalva;


    public void openWebcam(JLabel imageLabel) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera = new VideoCapture(0);
        Mat frame = new Mat();

        // Carregar o modelo de detecção de rosto (DNN)
        Net faceNet = Dnn.readNetFromCaffe("src/data/deploy.prototxt", "src/data/pose_iter_440000.caffemodel");
        if (!camera.isOpened()) {
            System.out.println("Camera não localizada!");
            return;
        }

        running = true;

        while (running) {
            if (camera.read(frame)) {
                // Converter imagem para RGB para a DNN
                Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2RGB);
                Mat blob = Dnn.blobFromImage(frame, 1.0, new Size(300, 300), new Scalar(104, 177, 123), false, false);

                // Usar o modelo de DNN para detectar rosto
                faceNet.setInput(blob);
                Mat detections = faceNet.forward();

                // Loop para verificar se há algum rosto detectado
                for (int i = 0; i < detections.size(2); i++) {
                    double confidence = detections.get(0, 0)[0];
                    if (confidence > 0.5) {  // Limite de confiança para detectar rosto
                        int x1 = (int) (detections.get(0, 0)[0] * frame.width());
                        int y1 = (int) (detections.get(0, 0)[0] * frame.height());
                        int x2 = (int) (detections.get(0, 0)[0] * frame.width());
                        int y2 = (int) (detections.get(0, 0)[0] * frame.height());

                        // Cortar a imagem do rosto detectado
                        Rect faceRect = new Rect(new Point(x1, y1), new Point(x2, y2));
                        Mat face = new Mat(frame, faceRect);

                        // Gerar embedding do rosto (por exemplo, usando FaceNet)
                        Mat faceBlob = Dnn.blobFromImage(face, 1.0 / 255.0, new Size(160, 160), new Scalar(0, 0, 0), true, false);
                        Net embeddingNet = Dnn.readNetFromTensorflow("path/to/facenet.pb");  // Modelo de embeddings
                        embeddingNet.setInput(faceBlob);
                        Mat embedding = embeddingNet.forward();


                    }
                }

                // Exibir a imagem capturada com possíveis marcadores de rosto
                BufferedImage bufferedImage = matToBufferedImage(frame);
                BufferedImage resizedImage = CentralizarImg(bufferedImage, imageLabel.getWidth(), imageLabel.getHeight());
                ImageIcon image = new ImageIcon(resizedImage);
                imageLabel.setIcon(image);
                imageLabel.repaint();
            }
        }

        camera.release();
    }



//    public void openWebcam(JLabel imageLabel) {
//
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        VideoCapture camera = new VideoCapture(0);
//        Mat frame = new Mat();
//        if (!camera.isOpened()) {
//            System.out.println("Camera não Localizada!");
//            return;
//        }
//
//        running = true;
//
//        while (running) {
//            if (camera.read(frame)) {
//                Mat cinza = new Mat();
//                Imgproc.cvtColor(frame, cinza, Imgproc.COLOR_BGR2GRAY);
//
//                BufferedImage bufferedImage = matToBufferedImage(frame);
//                BufferedImage resizedImage = CentralizarImg(bufferedImage, imageLabel.getWidth(), imageLabel.getHeight());
//                ImageIcon image = new ImageIcon(resizedImage);
//                imageLabel.setIcon(image);
//                imageLabel.repaint();
//
//                //SALVAR IMAGEM < POREM NÃO ESTÁ SENDO USADA NO MOMENTO
//
//  //            ImgSalva = matToBufferedImage(frame);
//            }
//        }
//
//        camera.release();
//
//    }

    public void stopWebcam() {
        running = false;

    }

    private BufferedImage matToBufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] buffer = new byte[bufferSize];
        mat.get(0, 0, buffer);
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
        return image;
    }




    public void SaveRosto(String fileNamePrefix) {
    if (ImgSalva == null) {
        System.out.println("Erro ao Salvar Imagem!!");
        return;
    }
    try {
        File outputfile = new File("src/resources/in/" + fileNamePrefix);
        ImageIO.write(ImgSalva, "png", outputfile);
    } catch (IOException e) {
        System.out.println("Erro ao Salvar Imagem!!");
        return;
    }

    }

    // Metodo que eu utilizo para redimensionar a imagem
    private BufferedImage CentralizarImg(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image imgProcesse = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage imgGerada = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = imgGerada.createGraphics();
        g2d.drawImage(imgProcesse, 0, 0, null);
        g2d.dispose();
        return imgGerada;
    }

    public Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }
    public void ConverterParaCinza() {
        File directory = new File("src/resources/in/");
        File[] filesArray = directory.listFiles();
        try{
            for (File file : Objects.requireNonNull(filesArray)) {
                BufferedImage image = ImageIO.read(file);
                Mat mat = bufferedImageToMat(image);
                Mat cinza = new Mat();
                Imgproc.cvtColor(mat, cinza, Imgproc.COLOR_BGR2GRAY);
                BufferedImage imgCinza = matToBufferedImage(cinza);
                File outputfile = new File("src/resources/out/" + file.getName());
                ImageIO.write(imgCinza, "png", outputfile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}