package Modelo;

import org.opencv.core.Point;
import org.opencv.core.Rect;

import java.util.ArrayList;
import java.util.List;

public class Caracteristicas {
    private Rect faceRectangle;
    private List<Point> Desc_Olhos;
    private Point Desc_Nariz;


    public Caracteristicas() {
        Desc_Olhos = new ArrayList<>();
    }

    public void setFaceRectangle(Rect rect) {
        this.faceRectangle = rect;
    }

    public void addEye(Point eye) {
        if (Desc_Olhos.size() < 2) Desc_Olhos.add(eye); // Apenas dois olhos
    }

    public void setDesc_Nariz(Point desc_Nariz) {
        this.Desc_Nariz = desc_Nariz;
    }

    public Rect getFaceRectangle() {
        return faceRectangle;
    }

    public List<Point> getDesc_Olhos() {
        return Desc_Olhos;
    }

    public Point getDesc_Nariz() {
        return Desc_Nariz;
    }

}
