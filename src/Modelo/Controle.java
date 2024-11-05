package Modelo;

import DAL.Autenticacao;

import javax.swing.*;
import java.util.ArrayList;

public class Controle {
    public String mensagem;

//    public void SalvarUser(String nome, int privilegios , String departamento, ArrayList histograma) {
//        this.mensagem = "";
//        Validacao validacao = new Validacao();
//        String json = validacao.ConverterJson(histograma);
//        mensagem = validacao.mensagem;
//        if(this.mensagem != "") {
//            this.mensagem = validacao.mensagem;
//            return;
//        }
//        DAL.CadUsers cadUsers = new DAL.CadUsers();
//        cadUsers.Salvar(nome, privilegios, departamento);
//        int id = cadUsers.UltimoUser();
//        if(id == 0) {
//            this.mensagem = cadUsers.mensagem;
//            return;
//        }
//        DAL.Autenticacao autenticacao = new DAL.Autenticacao();
//        autenticacao.SalvarHisto(id, json);
//        this.mensagem = cadUsers.mensagem;
//    }
//    public void Login(){
//        OpenCan openCan = new OpenCan();
//        openCan.ConverterParaCinza();
//        CalcularHistograma calcularHistograma = new CalcularHistograma();
//        ArrayList<int[]> histogramas1 = calcularHistograma.Calc();
//        String json = "";
//        Autenticacao autenticacao = new Autenticacao();
//        json = autenticacao.BuscarHisto(3);
//        if(json == "") {
//            this.mensagem = autenticacao.mensagem;
//            return;
//        }
//        Validacao validacao = new Validacao();
//        ArrayList<int[]> histogramas2 = validacao.ConverterArray(json);
//        if(histogramas2.size() == 0) {
//            this.mensagem = validacao.mensagem;
//            return;
//        }
//
//        Boolean n1 = calcularHistograma.comparaHistograma(histogramas1, histogramas2);
//        JOptionPane.showMessageDialog(null, n1);
//    }
}
