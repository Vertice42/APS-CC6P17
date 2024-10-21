package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost/cc6p17?user=sa&password=pfb#2011&useSSL=true&serverTimezone=UTC";
        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(url);
        } catch (ClassNotFoundException err) {
            err.printStackTrace();
        }
        return c;
    }
    public static void main(String args[]) {
        try {
            Connection c = Conexao.getConnection();
            System.out.println("Conectado!");
            c.close();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }
}
