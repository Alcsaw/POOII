package view;

import DataAccessLayer.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import com.opencsv.*;
import java.io.FileWriter;
import java.io.IOException;
import org.json.*;

public class Trabalho1ChatBotLancheria {

    public static void main(String[] args) throws InterruptedException {
        try {
            // TODO code application logic here
            DAO<Product> productDAO = new DAO<Product>();
            DAO<Category> categoryDAO = new DAO<Category>();
            DAO<Client> clientDAO = new DAO<Client>();
            DAO<Order> orderDAO = new DAO<Order>();
            DAO<OrderProduct> opDAO = new DAO<OrderProduct>();
            
            Bot bot = new Bot("1035768730:AAG1d_Mw78fuKv-8cXjhK00v_bDlmnJdUKo");
            
            // Teste da importação para o excel, gera o arquivo na raiz do projeto
            ArrayList<Order> orders = orderDAO.get(Order.class);
            CSVWriter writer;
            try {
                writer = new CSVWriter(new FileWriter("./pedidos.csv"),',');
                writer.writeNext(new String[] { "Id", "Data", "Finalizado", "Entregue", "Nome do cliente" });
                for (Order or : orders) {
                    writer.writeNext(or.toCSVString());
                }
                writer.close();
                orders.clear();
            } catch (IOException ex) {
                Logger.getLogger(Trabalho1ChatBotLancheria.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Codigo do bot
            int updateID = 1;
            int cont = 0;
            ArrayList<Conversation> conversations = new ArrayList<Conversation>();
            while(updateID != 0) {
                cont++;
                System.out.println(cont);
                String message = bot.receiveMessage(updateID + 1);
                System.out.println(message);
                
                // Lista de conversas ativas
                ArrayList<TelegramMessage> msgs = bot.parseMessage(message);
                for(TelegramMessage tm : msgs) {
                    // Procura cliente no banco
                    // se não existe, cria e salva
                    // adiciona cliente na lista de conversas 
                    // salva mensagem do cliente na lista de conversas 
                    // envia resposta para ultima mensagem da lista
                    Client client = clientDAO.getById(Client.class, Integer.parseInt(tm.getSenderId()));
                    if(client == null) {
                        client = new Client();
                        client.setId(Integer.parseInt(tm.getSenderId()));
                        client.setName(tm.getSenderFirstName() + " " + tm.getSenderLastName());
                        clientDAO.insert(client);
                    }
                    Conversation conversation = Conversation.findExistingConversation(conversations, client);
                    if(conversation == null) {
                        conversation = new Conversation();
                        conversation.setClient(client);
                    } 
                    conversation.addMessage(tm);
                    conversations.add(conversation);
                    conversation.respond(bot);
                }
                
                // Reseta o updateID para continuar ouvindo
                updateID = msgs.isEmpty() ? 1 : msgs.get(msgs.size() - 1).getUpdateId();
                // Pausa por 1 segundo entre iterações
                Thread.sleep(1000);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Trabalho1ChatBotLancheria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Trabalho1ChatBotLancheria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
