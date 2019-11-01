package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m98567
 */
public class Conexao {
    private String driver = "org.gjt.mm.mysql.Driver";
    private String url = "jdbc:mysql://localhost/" + "poo2";
    private String usuario = "root";
    private String senha = "";
    private Connection connection;
    
    public Conexao() throws ClassNotFoundException, SQLException {
        Class.forName(driver);  //carregar o driver para o Java
        connection = (Connection) DriverManager.getConnection(url, usuario, senha); //abrir a conexão
    }
    
    public void testConnection() {
        try {
            if (connection.isValid(5)) {
                System.out.println("Succesfully connected to the database");
            } else {
                System.out.println("Connection is closed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
    
    public PreparedStatement preparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }
}
