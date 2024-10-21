package Teste;
import Apresentacao.frmPrincipal;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.util.UUID;

public class MainTeste {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public static void main(String[] args) {

        // Inicializar a captura de vídeo da webcam
        VideoCapture captura = new VideoCapture(0);
        if (!captura.isOpened()) {
            System.out.println("Erro ao abrir a webcam");
            return;
        }

        // Carregar o classificador de rostos / Olhos
        CascadeClassifier detectorDeRostos = new CascadeClassifier();
        CascadeClassifier detectorDeOlhos = new CascadeClassifier();
        detectorDeRostos.load("src/data/haarcascades/haarcascade_frontalface_alt.xml");
        detectorDeOlhos.load("src/data/haarcascades/haarcascade_eye.xml");
        Mat quadro = new Mat();
        Mat cinza = new Mat();

        while (captura.read(quadro)) {

            Imgproc.cvtColor(quadro, cinza, Imgproc.COLOR_BGR2GRAY);
            MatOfRect deteccoesDeRostos = new MatOfRect();
            detectorDeRostos.detectMultiScale(cinza, deteccoesDeRostos, 1.1, 3, Objdetect.CASCADE_SCALE_IMAGE, new Size(30, 30), new Size());

            // Detectar olhos
            MatOfRect deteccoesDeOlhos = new MatOfRect();
            for (Rect retangulo : deteccoesDeRostos.toArray()) {
                Mat regiaoDoRosto = cinza.submat(retangulo);
                detectorDeOlhos.detectMultiScale(regiaoDoRosto, deteccoesDeOlhos);
                for (Rect olho : deteccoesDeOlhos.toArray()) {
                    Point centroDoOlho = new Point(retangulo.x + olho.x +  (double) olho.width / 2, retangulo.y + olho.y + (double) olho.height / 2);
                    Imgproc.circle(quadro, centroDoOlho, 5, new Scalar(255, 0, 0), 2);
                }
            }


            for (Rect retangulo : deteccoesDeRostos.toArray()) {
                Imgproc.rectangle(quadro, new Point(retangulo.x, retangulo.y), new Point(retangulo.x + retangulo.width, retangulo.y + retangulo.height), new Scalar(0, 255, 0));
            }


            HighGui.imshow("Detecção de Rostos", quadro);



            if (HighGui.waitKey(10) == 'Q') {
                frmPrincipal frm = new frmPrincipal();
                frm.setVisible(true);
                break;
            }

        }

        captura.release();
        HighGui.destroyAllWindows();
        // Salvar a imagem com os rostos detectados
        String nomeDoArquivo = "src/resources/rostos_detectados_" + UUID.randomUUID() + ".png";
        Imgcodecs.imwrite(nomeDoArquivo, quadro);

        System.out.println("Rostos detectados em " + nomeDoArquivo);
    }
}
