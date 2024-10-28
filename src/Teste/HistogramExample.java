package Teste;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.imgproc.Imgproc;

public class HistogramExample {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // Suponha que 'image' seja uma imagem carregada como Mat
        Mat image = new Mat();


        // Converter a imagem para o espaço de cores HSV
        Mat hsvImage = new Mat();
        Imgproc.cvtColor(image, hsvImage, Imgproc.COLOR_BGR2HSV);

        // Definir os parâmetros do histograma
        Mat hist = new Mat();
        MatOfInt histSize = new MatOfInt(50); // Número de bins
        MatOfFloat ranges = new MatOfFloat(0f, 256f); // Intervalo de intensidades
        MatOfInt channels = new MatOfInt(0); // Canal a ser considerado (0 para H, 1 para S, 2 para V)

        // Calcular o histograma
        Imgproc.calcHist(
                java.util.Collections.singletonList(hsvImage),
                channels,
                new Mat(),
                hist,
                histSize,
                ranges
        );

        // Normalizar o histograma
        Core.normalize(hist, hist, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        // 'hist' agora é uma matriz que representa o histograma da imagem
        System.out.println("Histograma: " + hist.dump());
    }
}
