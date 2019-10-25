package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m98567
 */
public class DataAccessObject {
    private String driver = "org.gjt.mm.mysql.Driver";
    private String db_name = "poo2";
    private String db_URI = "jdbc:mysql://localhost/" + db_name;
    private String db_user = "root";
    private String db_password = "";
    private Connection connection;
    
    public DataAccessObject() throws ClassNotFoundException, SQLException {
        //carregar o driver para o Java
        Class.forName(driver);
        //abrir a conexão
        connection = (Connection) DriverManager.getConnection(db_URI, db_user, db_password);
    }
    
    public void testConnection() {
        try {
            if (connection.isValid(5)) {
                System.out.println("Succesfully connected to the database");
            } else {
                System.out.println("Connection is closed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
}
