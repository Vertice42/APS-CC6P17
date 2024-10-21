package Modelo;

import jdk.jfr.Label;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class OpenCan {

    private volatile boolean running = false;
    private BufferedImage currentImage;

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
                ImageIcon image = new ImageIcon(matToBufferedImage(frame));
                imageLabel.setIcon(image);
                imageLabel.repaint();
            }
        }

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
    public void saveImage(String fileName) {
        String filePath = "src/resources/" + fileName;
        if (currentImage != null) {
            try {
                ImageIO.write(currentImage, "png", new File(filePath));
                System.out.println("Imagem Salva em " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Erro ao Salvar Imagem!!");
        }
    }
}