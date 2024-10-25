package Modelo;

import org.opencv.core.*;
import org.opencv.core.Point;
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

public class OpenCan {

    private volatile boolean running = false;
    private BufferedImage ImgSalva;

//    public void openWebcam(JLabel imageLabel) {
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        VideoCapture camera = new VideoCapture(0);
//        if (!camera.isOpened()) {
//            System.out.println("Camera não Localizada!");
//            return;
//        }
//
//        running = true;
//        Mat frame = new Mat();
//        while (running) {
//            if (camera.read(frame)) {
//                ImageIcon image = new ImageIcon(matToBufferedImage(frame));
//                imageLabel.setIcon(image);
//                imageLabel.repaint();
//            }
//        }
//
//        camera.release();
//    }

    public void openWebcam(JLabel imageLabel) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera = new VideoCapture(0);
        if (!camera.isOpened()) {
            System.out.println("Camera não Localizada!");
            return;
        }
        CascadeClassifier Rosto = new CascadeClassifier("src/data/haarcascades/haarcascade_frontalface_default.xml");
        CascadeClassifier Olhos = new CascadeClassifier("src/data/haarcascades/haarcascade_eye.xml");
        running = true;
        Mat frame = new Mat();
        while (running) {
            if (camera.read(frame)) {

                Mat cinza = new Mat();
                Imgproc.cvtColor(frame, cinza, Imgproc.COLOR_BGR2GRAY);
                MatOfRect deteccoesDeRostos = new MatOfRect();
                Rosto.detectMultiScale(cinza, deteccoesDeRostos, 1.1, 3, Objdetect.CASCADE_SCALE_IMAGE, new Size(30, 30), new Size());
                MatOfRect deteccoesDeOlhos = new MatOfRect();

                for (Rect retangulo : deteccoesDeRostos.toArray()) {

                    Mat regiaoDoRosto = cinza.submat(retangulo);
                    Olhos.detectMultiScale(regiaoDoRosto, deteccoesDeOlhos, 1.1, 5, Objdetect.CASCADE_SCALE_IMAGE, new Size(30, 30), new Size());

                    for (Rect olho : deteccoesDeOlhos.toArray()) {
                        org.opencv.core.Point centroDoOlho = new org.opencv.core.Point(retangulo.x + olho.x +  (double) olho.width / 2, retangulo.y + olho.y + (double) olho.height / 2);
                        Imgproc.circle(frame, centroDoOlho, 5, new Scalar(255, 0, 0), 2);
                    }
                }


                for (Rect retangulo : deteccoesDeRostos.toArray()) {
                    Imgproc.rectangle(frame, new org.opencv.core.Point(retangulo.x, retangulo.y), new Point(retangulo.x + retangulo.width, retangulo.y + retangulo.height), new Scalar(0, 255, 0));
                }

                BufferedImage bufferedImage = matToBufferedImage(frame);
                BufferedImage resizedImage = CentralizarImg(bufferedImage, imageLabel.getWidth(), imageLabel.getHeight());
                ImageIcon image = new ImageIcon(resizedImage);
                imageLabel.setIcon(image);
                imageLabel.repaint();
            }
        }

        ImgSalva = matToBufferedImage(frame);
        camera.release();

    }

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


    // Metodo que eu utilizo para redimensionar a imagem
    private BufferedImage CentralizarImg(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image imgProcesse = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage imgGerada = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = imgGerada.createGraphics();
        g2d.drawImage(imgProcesse, 0, 0, null);
        g2d.dispose();
        return imgGerada;
    }

    public Boolean saveImage(String fileName) {
        Boolean salvo = true;
        String filePath = "src/resources/" + fileName;
        if (ImgSalva != null) {
            try {
                ImageIO.write(ImgSalva, "png", new File(filePath));
                System.out.println("Imagem Salva em " + filePath);
                salvo = true;
            } catch (IOException e) {
                e.printStackTrace();
                salvo = false;
            }
        } else {
            System.out.println("Erro ao Salvar Imagem!!");
        }
        return salvo;
    }
}