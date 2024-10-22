package Modelo;

import org.opencv.core.Core;
import org.opencv.core.Mat;
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

        running = true;
        Mat frame = new Mat();
        while (running) {
            if (camera.read(frame)) {
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

    public void saveImage(String fileName) {
        String filePath = "src/resources/" + fileName;
        if (ImgSalva != null) {
            try {
                ImageIO.write(ImgSalva, "png", new File(filePath));
                System.out.println("Imagem Salva em " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Erro ao Salvar Imagem!!");
        }
    }
}