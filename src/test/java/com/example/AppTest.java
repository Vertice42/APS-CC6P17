package com.example;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.junit.Test;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private String getRadomImagePath(String lastPerson) {
        Path parentDirPath = Paths.get("src/main/resources/lfw").toAbsolutePath();
        File parentDir = parentDirPath.toFile();
        File[] directories = parentDir.listFiles(File::isDirectory);
    
        if (directories != null && directories.length > 0) {
            Random random = new Random();
            File randomDir;
            int randomIndex;
            String lastPersonDir = lastPerson != null ? lastPerson.toLowerCase() : "";
    
            do {
                randomIndex = random.nextInt(directories.length);
                randomDir = directories[randomIndex];
            } while (directories.length > 1 && randomDir.getName().toLowerCase().equals(lastPersonDir));
    
            File[] files = randomDir.listFiles();
            return files[random.nextInt(files.length)].getAbsolutePath();
        }
    
        System.out.println("Nenhum diretório encontrado no caminho especificado.");
        return null;
    }
    

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {

        var faceRecognizer = new FacialRecognition();
        var imageA = getRadomImagePath(null);
        var imageB = getRadomImagePath(null);
        System.out.println("getRadomImagePath imageA: " + imageA);
        System.out.println("getRadomImagePath imageA: " + imageB);
        faceRecognizer.detectFaces(Imgcodecs.imread(imageB));

        assertTrue(faceRecognizer.recognizeFace(imageA, imageA));
        assertFalse(faceRecognizer.recognizeFace(imageA, imageB));
    }
}
