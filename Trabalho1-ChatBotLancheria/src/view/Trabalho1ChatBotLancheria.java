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
            bot.startListening();
            // Teste da importação para o excel, gera o arquivo na raiz do projeto
//            ArrayList<Order> orders = orderDAO.get(Order.class);
//            CSVWriter writer;
//            try {
//                writer = new CSVWriter(new FileWriter("./pedidos.csv"),',');
//                writer.writeNext(new String[] { "Id", "Data", "Finalizado", "Entregue", "Nome do cliente" });
//                for (Order or : orders) {
//                    writer.writeNext(or.toCSVString());
//                }
//                writer.close();
//                orders.clear();
//            } catch (IOException ex) {
//                Logger.getLogger(Trabalho1ChatBotLancheria.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Trabalho1ChatBotLancheria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Trabalho1ChatBotLancheria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
