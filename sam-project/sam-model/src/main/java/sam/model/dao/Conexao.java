
package sam.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
    
    private final String URL = "jdbc:localhost://localhost:3306/**nomedobancodedados**";
    private final String usuario = "root";
    private final String senha = "admin";
    
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, usuario, senha);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado!");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados!");
            throw new RuntimeException(e);
        }
    }
    
    public static void fechar(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Conexao() {
    }

    public String getURL() {
        return URL;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }
    
}
