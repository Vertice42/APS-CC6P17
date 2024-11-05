package DAL;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Autenticacao {
    public String mensagem;

    public void SalvarHisto(int idUser, String json) {
        this.mensagem = "";
        try {
            Connection con = Conexao.getConnection();
            String sql = "INSERT INTO histograma (idUser, histogram_data) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, idUser);
            ps.setString(2, json);
            ps.execute();
            mensagem = "Histórico salvo com sucesso!";
            ps.close();
            con.close();
        } catch (Exception e) {
            mensagem = "Erro ao salvar histórico: " + e.getMessage();
            System.out.print(e.getMessage());
        }

    }
    public String BuscarHisto(int idUser) {
        this.mensagem = "";
        String json = "";
        try {
            Connection con = Conexao.getConnection();
            String sql = "SELECT histogram_data FROM histograma WHERE idUser = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUser);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                json = rs.getString("histogram_data");
            }
            ps.close();
            con.close();
        } catch (Exception e) {
            mensagem = "Erro ao buscar histórico: " + e.getMessage();
            System.out.print(e.getMessage());
        }
        return json;
    }
}
