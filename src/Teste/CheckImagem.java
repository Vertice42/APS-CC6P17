package Teste;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;


public class CheckImagem {
    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public static void main(String[] args) {
        // Carregar a imagem
        String imagePath = "src/resources/pexels-heitorverdifotos-2169434.jpg";
        Mat image = Imgcodecs.imread(imagePath);

        if (image.empty()) {
            System.out.println("Erro ao carregar a imagem!");
            return;
        }

        // Carregar o classificador de rostos
        CascadeClassifier faceDetector = new CascadeClassifier("src/data/haarcascades/haarcascade_frontalface_default.xml");
        CascadeClassifier eyeDetector = new CascadeClassifier("src/data/haarcascades/haarcascade_eye.xml");

        // Detectar rostos na imagem
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);

        for (Rect rect : faceDetections.toArray()) {
            // Desenhar retângulo ao redor do rosto detectado
            Imgproc.rectangle(image, new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));

            // Obter região do rosto
            Mat faceROI = image.submat(rect);

            // Detectar olhos dentro da região do rosto
            MatOfRect eyeDetections = new MatOfRect();
            eyeDetector.detectMultiScale(faceROI, eyeDetections);

            Point eye1 = null, eye2 = null;
            for (Rect eyeRect : eyeDetections.toArray()) {
                Point eyeCenter = new Point(rect.x + eyeRect.x + eyeRect.width / 2, rect.y + eyeRect.y + eyeRect.height / 2);
                Imgproc.circle(image, eyeCenter, 5, new Scalar(255, 0, 0), 2);

                if (eye1 == null) {
                    eye1 = eyeCenter;
                } else {
                    eye2 = eyeCenter;
                }
            }

            // Calcular a distância entre os olhos
            if (eye1 != null && eye2 != null) {
                double distance = Math.sqrt(Math.pow(eye2.x - eye1.x, 2) + Math.pow(eye2.y - eye1.y, 2));
                System.out.println("Distância entre os olhos: " + distance);
            }
        }

        // Salvar a imagem com anotações
        Imgcodecs.imwrite("caminho/para/salvar/resultado.jpg", image);
    }
}
