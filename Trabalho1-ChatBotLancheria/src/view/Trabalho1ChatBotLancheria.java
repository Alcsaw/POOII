package view;

import DataAccessLayer.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 *
 * @author alcsaw
 */
public class Trabalho1ChatBotLancheria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            DAO<Product> productDAO = new DAO<Product>();
            DAO<Category> categoryDAO = new DAO<Category>();
            DAO<Client> clientDAO = new DAO<Client>();
            
            ArrayList<Product> prods = productDAO.getProductsFromCategory(new Category(3, "Cachorros quentes"));
            for(Product p : prods) {
                System.out.println(p.getId());
                System.out.println(p.getDescription());
                System.out.println(p.getPrice());
                System.out.println(p.getCategory().getDescription());
                System.out.println("===============");
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Trabalho1ChatBotLancheria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Trabalho1ChatBotLancheria.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
}
