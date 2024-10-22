package Modelo;
import Apresentacao.frmCadastro;
import Apresentacao.frmLogin;

public class Main {

    public static void main(String[] args) {
//        frmLogin frm = new frmLogin();
//        frm.setVisible(true);
//        frmCadastro frm = new frmCadastro();
//        frm.setVisible(true);
        ProcessarImg processarImg = new ProcessarImg();
        processarImg.processarImg();
    }
}