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
            DAO<Order> orderDAO = new DAO<Order>();
            DAO<OrderProduct> opDAO = new DAO<OrderProduct>();
            
            ArrayList<Order> orders = orderDAO.get(Order.class);
            Product prod = productDAO.get(Product.class).get(3);
            Order order = orders.get(5);
            OrderProduct op = new OrderProduct();
            op.setOrder(order);
            op.setProduct(prod);
            op.setQuantity(22);
            op.setComment("fodase");
            
            opDAO.insert(op);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Trabalho1ChatBotLancheria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Trabalho1ChatBotLancheria.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
}
