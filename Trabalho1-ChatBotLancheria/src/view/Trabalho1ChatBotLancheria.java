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
            ArrayList<Order> orders = orderDAO.get(Order.class);
            for(Order o : orders) {
                System.out.println("Id: " + o.getId());
                System.out.println("Date: " + o.getDate());
                System.out.println("Done: " + o.isDone());
                System.out.println("Delivered: "+o.isDelivered());
                System.out.println("Client id: "+o.getClient().getId());
                System.out.println("Client name: "+o.getClient().getName());
                for(OrderProduct op : o.getOrderProducts()) {
                    System.out.println("----Prod id: "+op.getProduct().getId());
                    System.out.println("----Prod desc: "+op.getProduct().getDescription());
                    System.out.println("----has cat: "+(op.getProduct().getCategory() != null));
                    System.out.println("----Qntde: "+op.getQuantity());
                    System.out.println("----Price: "+op.getTotalPrice());
                    System.out.println("----Obs: "+op.getComment());
                    System.out.println("----is circular: "+ (op.getOrder() != null));
                }
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Trabalho1ChatBotLancheria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Trabalho1ChatBotLancheria.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
}
