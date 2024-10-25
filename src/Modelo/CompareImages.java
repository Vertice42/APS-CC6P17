package Modelo;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

import javax.swing.*;
import java.io.File;

public class CompareImages {
    private Boolean isSimilar;

    public Boolean CompararImagem(String url1){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        isSimilar = false;

        // Carregar o classificador Haar para face e olhos
        CascadeClassifier ClassRosto = new CascadeClassifier("src/data/haarcascades/haarcascade_frontalface_default.xml");
        CascadeClassifier ClassOlhos = new CascadeClassifier("src/data/haarcascades/haarcascade_eye.xml");

        // Carregar a imagem salva e a imagem capturada
        Mat image1 = Imgcodecs.imread("src/resources/captured_image_ecb1feaf-d3eb-47e0-88d3-a1b6fa48e9fc.png");
        Mat image2 = Imgcodecs.imread(url1);

        // Detectar a face e os olhos em ambas as imagens
        Point[] landmarksImage1 = detectLandmarks(image1, ClassRosto, ClassOlhos);
        Point[] landmarksImage2 = detectLandmarks(image2, ClassRosto, ClassOlhos);

        if (landmarksImage1 == null || landmarksImage2 == null) {
            JOptionPane.showMessageDialog(null, "Não foi possível detectar as características faciais nas imagens.");
            return isSimilar = false;
        }

        // Calcular as distâncias Euclidianas entre os pontos (ex.: entre os olhos e nariz)
        double distanceEyesImage1 = calculateDistance(landmarksImage1[0], landmarksImage1[1]); // Olho esquerdo e direito
        double distanceEyesImage2 = calculateDistance(landmarksImage2[0], landmarksImage2[1]);

        double distanceNoseImage1 = calculateDistance(landmarksImage1[0], landmarksImage1[2]); // Olho esquerdo e nariz
        double distanceNoseImage2 = calculateDistance(landmarksImage2[0], landmarksImage2[2]);

        // Comparar as distâncias entre as imagens
        System.out.println("Distância entre os olhos na imagem 1: " + distanceEyesImage1);
        System.out.println("Distância entre os olhos na imagem 2: " + distanceEyesImage2);

        System.out.println("Distância entre o olho esquerdo e o nariz na imagem 1: " + distanceNoseImage1);
        System.out.println("Distância entre o olho esquerdo e o nariz na imagem 2: " + distanceNoseImage2);

        // Definir um threshold de semelhança
        if (Math.abs(distanceEyesImage1 - distanceEyesImage2) < 10 && Math.abs(distanceNoseImage1 - distanceNoseImage2) < 10) {
            isSimilar = true;
            JOptionPane.showMessageDialog(null, "Imagens similares.");
        } else {
            isSimilar = false;
            JOptionPane.showMessageDialog(null, "Imagens diferentes.");
        }
        return  isSimilar;
    }

    public static Point[] detectLandmarks(Mat image, CascadeClassifier faceCascade, CascadeClassifier eyeCascade) {
        // Detectar a face
        MatOfRect faces = new MatOfRect();
        faceCascade.detectMultiScale(image, faces);

        if (faces.empty()) {
            return null; // Não encontrou face
        }

        // Pegar a primeira face encontrada
        Rect face = faces.toArray()[0];

        // Detectar os olhos dentro da região da face
        Mat faceROI = image.submat(face);
        MatOfRect eyes = new MatOfRect();
        eyeCascade.detectMultiScale(faceROI, eyes);

        Rect[] eyesArray = eyes.toArray();
        if (eyesArray.length < 2) {
            return null; // Não encontrou os dois olhos
        }

        // Pegar as coordenadas dos olhos e do nariz (hipoteticamente o centro da face)
        Point eye1 = new Point(face.x + eyesArray[0].x + eyesArray[0].width / 2, face.y + eyesArray[0].y + eyesArray[0].height / 2);
        Point eye2 = new Point(face.x + eyesArray[1].x + eyesArray[1].width / 2, face.y + eyesArray[1].y + eyesArray[1].height / 2);

        // O nariz pode ser aproximado como o ponto central entre os olhos e a base da face
        Point nose = new Point(face.x + face.width / 2, face.y + (int) (face.height * 0.6));

        // Retornar os pontos dos olhos e nariz
        return new Point[]{eye1, eye2, nose};
    }

    public static double calculateDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
    public Boolean VerificarImagemSalva(String imgSalva){
        Boolean SaveImage = false;
        File file = new File(imgSalva);

        if (file.exists() && !file.isDirectory()) {
            Mat image = Imgcodecs.imread(imgSalva);
            if (image.empty()) {
                SaveImage = false;
                System.out.println("Erro ao carregar a imagem.");
            } else {
                SaveImage = true;
                System.out.println("Imagem carregada com sucesso.");
            }
        } else {
            SaveImage = false;
            System.out.println("Arquivo não encontrado: " + imgSalva);
        }
        return SaveImage;
    }
}
