package Modelo;

public class Controle {
    public String mensagem;

    public void SalvarUser(String nome, int privilegios , String departamento, String img) {
        DAL.CadUsers cadUsers = new DAL.CadUsers();
        cadUsers.Salvar(nome, privilegios, departamento, img);
        this.mensagem = cadUsers.mensagem;
    }
}
