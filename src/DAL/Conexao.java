package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConnection() throws SQLException {
        String user = "david";
        String senha = "pfb#2011";
        String url = "jdbc:mysql://localhost:3306/cc6p17?user=" + user + "&password=" + senha;
        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(url);
        } catch (ClassNotFoundException err) {
            err.printStackTrace();
        }
        return c;
    }

    public static void main(String[] args) {
        try {
            Connection c = Conexao.getConnection();
            System.out.println("Conexão realizada com sucesso!");
            c.close();
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }
}
