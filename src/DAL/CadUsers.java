package DAL;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CadUsers {
    public String mensagem = "";

    public void Salvar(String nome, int privilegios, String departamento, String img) {
        File file = BuscarImg(img);
        if(file == null) {
            mensagem = "Imagem não encontrada!";
            return;
        }
        try  (InputStream inputStream = new FileInputStream(file)){

            Connection con = Conexao.getConnection();
            String sql = "INSERT INTO users (Nome, Privilegios, Departamento, Foto) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setInt(2, privilegios);
            ps.setString(3, departamento);
            ps.setBlob(4, (Blob) file);
            ps.execute();
            mensagem = "Usuário cadastrado com sucesso!";
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
}

