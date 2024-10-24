package Modelo;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

public class CompareImages {


    public void CompararImagem(String url1){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);


        // Carregar o classificador Haar para face e olhos
        CascadeClassifier faceCascade = new CascadeClassifier("src/data/haarcascades/haarcascade_frontalface_default.xml");
        CascadeClassifier eyeCascade = new CascadeClassifier("src/data/haarcascades/haarcascade_eye.xml");

        // Carregar a imagem salva e a imagem capturada
        Mat image1 = Imgcodecs.imread("src/resources/captured_image_ed759930-e0bf-481f-a762-e689503e2ac7.png");
        Mat image2 = Imgcodecs.imread(url1);

        // Detectar a face e os olhos em ambas as imagens
        Point[] landmarksImage1 = detectLandmarks(image1, faceCascade, eyeCascade);
        Point[] landmarksImage2 = detectLandmarks(image2, faceCascade, eyeCascade);

        if (landmarksImage1 == null || landmarksImage2 == null) {
            System.out.println("Não foi possível detectar as características faciais nas imagens.");
            return;
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
        if (Math.abs(distanceEyesImage1 - distanceEyesImage2) < 10 &&
                Math.abs(distanceNoseImage1 - distanceNoseImage2) < 10) {
            System.out.println("As características faciais são semelhantes.");
        } else {
            System.out.println("As características faciais são diferentes.");
        }
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
}
