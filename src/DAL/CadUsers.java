package DAL;

import Modelo.Estatico;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CadUsers {
    public String mensagem = "";

    public Boolean Salvar(String nome, int privilegios, String departamento) {

        try{

            Connection con = Conexao.getConnection();
            String sql = "INSERT INTO caduser (Nome, Privilegios, Setor) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setInt(2, privilegios);
            ps.setString(3, departamento);
            ps.execute();
            mensagem = "Usuário cadastrado com sucesso!";
            ps.close();
            con.close();
            return true;
        } catch (Exception e) {
            mensagem = "Erro ao cadastrar usuário: " + e.getMessage();
            System.out.print(e.getMessage());
            return false;
        }
    }
    public int BuscarUserSalvo(String nome){

        int idUser = 0;
        try {
            Connection con = null;
            con = Conexao.getConnection();
            String Query = "SELECT idUser FROM caduser WHERE Nome = ? LIMIT 1";
            PreparedStatement stmt = con.prepareStatement(Query);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // Se o ResultSet tiver pelo menos um registro
                 idUser = rs.getInt("idUser");

            }
        } catch (Exception e) {
            mensagem = "Erro ao buscar usuário: " + e.getMessage();

        }
        return idUser;
    }
    public String BuscarUserId(int idUser){

        String Nome = "";
        try {
            Connection con = null;
            con = Conexao.getConnection();
            String Query = "SELECT Nome,Privilegios FROM caduser WHERE idUser = ? LIMIT 1";
            PreparedStatement stmt = con.prepareStatement(Query);
            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // Se o ResultSet tiver pelo menos um registro
                Nome = rs.getString("Nome");
                Estatico.Priv = rs.getInt("Privilegios");
                System.out.print(Nome);
            }
        } catch (Exception e) {
            mensagem = "Erro ao buscar usuário: " + e.getMessage();
        }
        return Nome;
    }
}

