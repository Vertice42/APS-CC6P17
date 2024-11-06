package DAL;

import org.opencv.core.Point;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Caracteristicas {
    public String mensagem;

    public Boolean SalvarCaracteristicas(int idUser, String Olhos, String Nariz){
        this.mensagem = "";
        try {
            Connection con = Conexao.getConnection();
            String sql = "INSERT INTO caracteristicas (IdUsuario, Olhos, Nariz) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUser);
            ps.setString(2, Olhos);
            ps.setString(3, Nariz);
            ps.execute();
            ps.close();
            con.close();
            return true;
        } catch (Exception e) {
            this.mensagem = e.getMessage();
            return false;
        }
    }
    public void CompararCaracteristicas(Point Olhos, Point Nariz){
        this.mensagem = "";
        try {
            Connection con = null;
            con = Conexao.getConnection();
            String Query = "SELECT IdUsuario,Olhos,Nariz FROM caracteristicas";
            PreparedStatement stmt = con.prepareStatement(Query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("IdUsuario");
                // Se o ResultSet tiver pelo menos um registro
                System.out.print("Olhos Atual: " + Olhos.toString() + "\n");
                System.out.print("Nariz Atual: " + Nariz.toString() + "\n");
                Point OlhosBD = stringToPoint(rs.getString("Olhos"));
                Point NarizBD = stringToPoint(rs.getString("Nariz"));
                System.out.print("Olhos BD: " + OlhosBD.toString() + "\n");
                System.out.print("Nariz BD: " + NarizBD.toString() + "\n");
                double distanciaOlhos = Math.sqrt(Math.pow(Olhos.x - OlhosBD.x, 2) + Math.pow(Olhos.y - OlhosBD.y, 2));
                double distanciaNariz = Math.sqrt(Math.pow(Nariz.x - NarizBD.x, 2) + Math.pow(Nariz.y - NarizBD.y, 2));
                if (distanciaOlhos < 40 && distanciaNariz < 40) {
                    mensagem = "Usuário encontrado!";
                    break;
                }
                mensagem = "Usuário não Liberado!";
            }
        } catch (Exception e) {
            mensagem = "Erro ao buscar usuário: " + e.getMessage();

        }
    }
    private static Point stringToPoint(String str) {
        str = str.replace("{", "").replace("}", ""); // Remove { and }
        String[] parts = str.split(",");
        double x = Double.parseDouble(parts[0].trim());
        double y = Double.parseDouble(parts[1].trim());
        return new Point(x, y);
    }
}
