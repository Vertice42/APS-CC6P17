package Modelo;

import DAL.CadUsers;
import DAL.Caracteristicas;


public class Controle {
    public String mensagem;

    public void SalvarUser(String nome, int privilegios , String departamento, String Olhos, String Nariz) {
        this.mensagem = "";
        CadUsers cad = new CadUsers();
        if (cad.Salvar(nome, privilegios, departamento)) {
            this.mensagem = cad.mensagem;
            int iduser = cad.BuscarUserSalvo(nome);
            if (iduser != 0) {
                Caracteristicas car = new Caracteristicas();
                if (!car.SalvarCaracteristicas(iduser, Olhos, Nariz)) {
                    this.mensagem = car.mensagem;
                }
            } else {
                this.mensagem = cad.mensagem;
            }
        } else {
            this.mensagem = cad.mensagem;
        }

    }

}
