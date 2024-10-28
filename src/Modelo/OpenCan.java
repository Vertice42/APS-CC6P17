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

    public void openWebcam(JLabel imageLabel) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera = new VideoCapture(0);
        Mat frame = new Mat();
        if (!camera.isOpened()) {
            System.out.println("Camera não Localizada!");
            return;
        }
        CascadeClassifier Rosto = new CascadeClassifier("src/data/haarcascades/haarcascade_frontalface_default.xml");
        CascadeClassifier Olhos = new CascadeClassifier("src/data/haarcascades/haarcascade_eye.xml");
        running = true;

        while (running) {
            if (camera.read(frame)) {
                Mat cinza = new Mat();
                Imgproc.cvtColor(frame, cinza, Imgproc.COLOR_BGR2GRAY);

                MatOfRect deteccoesDeRostos = new MatOfRect();
                Rosto.detectMultiScale(cinza, deteccoesDeRostos, 1.1, 3, Objdetect.CASCADE_SCALE_IMAGE, new Size(30, 30), new Size());
                MatOfRect deteccoesDeOlhos = new MatOfRect();

                if (deteccoesDeRostos.toArray().length > 0) {
                    Rect retangulo = deteccoesDeRostos.toArray()[0];
                    Mat regiaoDoRosto = cinza.submat(retangulo);
                    Olhos.detectMultiScale(regiaoDoRosto, deteccoesDeOlhos, 1.1, 5, Objdetect.CASCADE_SCALE_IMAGE, new Size(30, 30), new Size());

                    int olhosDetectados = 0;
                    for (Rect olho : deteccoesDeOlhos.toArray()) {
                        if (olhosDetectados >= 2) break;
                        Point centroDoOlho = new Point(retangulo.x + olho.x + (double) olho.width / 2, retangulo.y + olho.y + (double) olho.height / 2);
                        Imgproc.circle(frame, centroDoOlho, 5, new Scalar(255, 0, 0), 2);
                        olhosDetectados++;
                    }

                    Imgproc.rectangle(frame, new Point(retangulo.x, retangulo.y), new Point(retangulo.x + retangulo.width, retangulo.y + retangulo.height), new Scalar(0, 255, 0));
                }

                BufferedImage bufferedImage = matToBufferedImage(frame);
                BufferedImage resizedImage = CentralizarImg(bufferedImage, imageLabel.getWidth(), imageLabel.getHeight());
                ImageIcon image = new ImageIcon(resizedImage);
                imageLabel.setIcon(image);
                imageLabel.repaint();
                ImgSalva = matToBufferedImage(frame);
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


    // Metodo que eu utilizo para redimensionar a imagem
    private BufferedImage CentralizarImg(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image imgProcesse = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage imgGerada = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = imgGerada.createGraphics();
        g2d.drawImage(imgProcesse, 0, 0, null);
        g2d.dispose();
        return imgGerada;
    }

    public boolean saveRosto(String fileName) {
        if (ImgSalva == null) {
            System.out.println("Erro ao Salvar Imagem!!");
            return false;
        }

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat image = bufferedImageToMat(ImgSalva);
        CascadeClassifier faceDetector = new CascadeClassifier("src/data/haarcascades/haarcascade_frontalface_default.xml");
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);

        if (faceDetections.toArray().length == 0) {
            System.out.println("Nenhum rosto detectado!");
            return false;
        }

        for (Rect rect : faceDetections.toArray()) {
            Mat face = new Mat(image, rect);
            Mat resizedFace = new Mat();
            Size size = new Size(160, 160);
            Imgproc.resize(face, resizedFace, size);

            BufferedImage faceImage = matToBufferedImage(resizedFace);
            try {
                ImageIO.write(faceImage, "png", new File("src/resources/" + fileName));
                System.out.println("Rosto salvo em src/resources/" + fileName);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }
}