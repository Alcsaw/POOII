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
            ArrayList<Conversation> conversations = new ArrayList<Conversation>();
            while(updateID != 0) {
                cont++;
                System.out.println(cont);
                String message = bot.receiveMessage(updateID + 1);
                System.out.println(message);
                ArrayList<TelegramMessage> msgs = bot.parseMessage(message);
                for(TelegramMessage tm : msgs) {
                    // Verifica se cliente existe, adicionando no banco caso necessario
                    System.out.println(tm.getText());
                    if(!Conversation.conversationAlreadyExists(conversations, tm.getSenderFirstName() + " " + tm.getSenderLastName())) {
                        Conversation newConv = new Conversation();
                        newConv.getOrder().setClient(clientDAO.getByNameOrDescription(Client.class, tm.getText()));
                        conversations.add(newConv);
                        newConv.handleClientMessage(tm, bot);
                    }
                }
                updateID = msgs.isEmpty() ? 1 : msgs.get(msgs.size() - 1).getUpdateId();
                Thread.sleep(1000);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Trabalho1ChatBotLancheria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Trabalho1ChatBotLancheria.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
}
