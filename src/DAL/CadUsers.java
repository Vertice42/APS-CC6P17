package DAL;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CadUsers {
    public String mensagem = "";

    public void Salvar(String nome, int privilegios, String departamento) {

        try{

            Connection con = Conexao.getConnection();
            String sql = "INSERT INTO users (Nome, Privilegios, Departamento) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setInt(2, privilegios);
            ps.setString(3, departamento);
            ps.execute();
            mensagem = "Usuário cadastrado com sucesso!";
            ps.close();
            con.close();
        } catch (Exception e) {
            mensagem = "Erro ao cadastrar usuário: " + e.getMessage();
            System.out.print(e.getMessage());
        }
    }

    private File BuscarImg(String Diretorio) {
        Diretorio = "src/resources/" + Diretorio;
        File file = new File(Diretorio);
        if(!file.exists()) {
            return null;
        }
        return file;
    }
    public int UltimoUser() {
        int id = 0;
        try {
            Connection con = Conexao.getConnection();
            String sql = "SELECT MAX(idUser) as idUser FROM users";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            if(ps.getResultSet().next()){
                id = ps.getResultSet().getInt("idUser");
            }
        } catch (Exception e) {
            mensagem = "Erro ao buscar usuário: " + e.getMessage();
            System.out.print(e.getMessage());
        }
        return id;
    }
}

