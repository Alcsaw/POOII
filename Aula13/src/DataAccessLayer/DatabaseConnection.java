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
public class DatabaseConnection {
    private String driver = "org.gjt.mm.mysql.Driver";
    private String url = "jdbc:mysql://localhost/" + "aula13-ifdm";
    private String usuario = "root";
    private String senha = "";
    private Connection connection;
    
    public DatabaseConnection() throws ClassNotFoundException, SQLException {
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
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }
    
    public PreparedStatement preparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }
}
