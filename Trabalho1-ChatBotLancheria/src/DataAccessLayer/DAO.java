package DataAccessLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import model.*;

/**
 *
 * @author m98567
 * 
 * Generic Class for Data Access Objects
 */
public class DAO<T> {
    
    private DatabaseConnection connection;
    
    public DAO() throws ClassNotFoundException, SQLException {
        this.connection = new DatabaseConnection();
    }

}
