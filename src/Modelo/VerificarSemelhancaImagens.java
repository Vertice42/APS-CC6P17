package Modelo;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class VerificarSemelhancaImagens {

    public Boolean HistogramaCalculo(String file1, String file2){
        boolean semelhante = false;
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat image1 = Imgcodecs.imread(file1);
        Mat image2 = Imgcodecs.imread(file2);
        if (image1.empty()) {
            System.out.println("Não foi possível carregar as imagens!");
            return false;
        }

        Mat hist1 = CalcularHistograma(image1);
        Mat hist2 = CalcularHistograma(image2);
        double correlacao = Imgproc.compareHist(hist1, hist2, Imgproc.HISTCMP_CORREL);

        semelhante = correlacao >= 0.9;

        return semelhante;
    }


    private static Mat CalcularHistograma(Mat image) {
        Mat hsvImage = new Mat();
        Imgproc.cvtColor(image, hsvImage, Imgproc.COLOR_BGR2HSV);

        Mat hist = new Mat();
        MatOfInt histSize = new MatOfInt(50);
        MatOfFloat ranges = new MatOfFloat(0f, 256f);
        MatOfInt channels = new MatOfInt(0);

        Imgproc.calcHist(
                java.util.Collections.singletonList(hsvImage),
                channels,
                new Mat(),
                hist,
                histSize,
                ranges
        );
        Core.normalize(hist, hist, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        return hist;
    }

}
