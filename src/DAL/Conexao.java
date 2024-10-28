package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConnection() throws SQLException {
        String user = "root";
        String senha = "";
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


}
