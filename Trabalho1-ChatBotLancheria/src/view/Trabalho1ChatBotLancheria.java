package view;

import DataAccessLayer.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import org.json.*;

/**
 *
 * @author alcsaw
 */
public class Trabalho1ChatBotLancheria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        try {
            // TODO code application logic here
            DAO<Product> productDAO = new DAO<Product>();
            DAO<Category> categoryDAO = new DAO<Category>();
            DAO<Client> clientDAO = new DAO<Client>();
            DAO<Order> orderDAO = new DAO<Order>();
            DAO<OrderProduct> opDAO = new DAO<OrderProduct>();
            
            Bot bot = new Bot("1035768730:AAG1d_Mw78fuKv-8cXjhK00v_bDlmnJdUKo");
            int updateID = 1;
            int cont = 0;
            while(updateID != 0) {
                cont++;
                System.out.println(cont);
                String message = bot.receiveMessage(updateID + 1);
                System.out.println(message);
                ArrayList<TelegramMessage> msgs = bot.parseMessage(message);
                for(TelegramMessage tm : msgs) {
                    System.out.println("Msg Id: "+tm.getMessageId());
                    System.out.println("Sender id: " + tm.getSenderId());
                    System.out.println("Sender First: " + tm.getSenderFirstName());
                    System.out.println("Sender Last: " + tm.getSenderLastName());
                    System.out.println("UpdateID: " + tm.getUpdateId());
                    System.out.println("Text: " + tm.getText());
                    if(tm.getText().equals("Oi")) {
                        System.out.println("Vai mandar msg");
                        bot.sendMessage(tm.getSenderId(), "Buenas");
                    }
                }
                updateID = msgs.isEmpty() ? 1 : msgs.get(msgs.size() - 1).getUpdateId();
                System.out.println("============ updateId: " + updateID);
                Thread.sleep(1000);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Trabalho1ChatBotLancheria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Trabalho1ChatBotLancheria.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
}
