package Modelo;

import org.opencv.core.*;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;

public class ProcessarImg {
    private Net detectorDeRostos;
    private CascadeClassifier detectorDeOlhos;
    private String imgProcessada;

    public ProcessarImg() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String modelPath = "src/data/yunet.onnx";
        File modelFile = new File(modelPath);
        if (!modelFile.exists()) {
            throw new RuntimeException("Modelo ONNX não encontrado: " + modelPath);
        }
        try {
            detectorDeRostos = Dnn.readNetFromONNX(modelPath);
        } catch (CvException e) {
            System.err.println("Erro ao carregar o modelo ONNX: " + e.getMessage());
            return;
        }
        detectorDeOlhos = new CascadeClassifier("src/data/face_recognition_sface_2021dec_int8.onnx");
        imgProcessada = "src/resources/captured_image_c16b105d-4ed2-4d7e-9ffe-6233fe2201e5.png";
    }

    public void processarImg() {
        Mat quadro = Imgcodecs.imread(imgProcessada);

        if (quadro.empty()) {
            System.out.println("Erro ao carregar a imagem");
            return;
        }

        Mat blob = Dnn.blobFromImage(quadro, 1.0, new Size(320, 320), new Scalar(104, 177, 123), false, false);
        detectorDeRostos.setInput(blob);
        Mat deteccoes = detectorDeRostos.forward();

        for (int i = 0; i < deteccoes.size(2); i++) {
            double confidence = deteccoes.get(0, i)[2];
            if (confidence > 0.5) {
                int x1 = (int) (deteccoes.get(0, i)[3] * quadro.cols());
                int y1 = (int) (deteccoes.get(0, i)[4] * quadro.rows());
                int x2 = (int) (deteccoes.get(0, i)[5] * quadro.cols());
                int y2 = (int) (deteccoes.get(0, i)[6] * quadro.rows());
                Rect retangulo = new Rect(new Point(x1, y1), new Point(x2, y2));
                Imgproc.rectangle(quadro, retangulo, new Scalar(0, 255, 0));

                Mat regiaoDoRosto = quadro.submat(retangulo);
                Mat cinza = new Mat();
                Imgproc.cvtColor(regiaoDoRosto, cinza, Imgproc.COLOR_BGR2GRAY);
                MatOfRect deteccoesDeOlhos = new MatOfRect();
                detectorDeOlhos.detectMultiScale(cinza, deteccoesDeOlhos, 1.1, 3, 0, new Size(10, 10), new Size());
                for (Rect olho : deteccoesDeOlhos.toArray()) {
                    Point centroDoOlho = new Point(retangulo.x + olho.x + (double) olho.width / 2, retangulo.y + olho.y + (double) olho.height / 2);
                    Imgproc.circle(quadro, centroDoOlho, 5, new Scalar(255, 0, 0), 2);
                }
            }
        }
        HighGui.imshow("Detecção de Rostos", quadro);
        HighGui.waitKey(0);
        HighGui.destroyAllWindows();
    }
}