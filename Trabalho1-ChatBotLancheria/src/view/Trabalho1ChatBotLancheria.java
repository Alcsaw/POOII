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
        
        // Thread para o bot
        new Thread(new Runnable() {
            public void run() {
                Bot bot = new Bot("1035768730:AAG1d_Mw78fuKv-8cXjhK00v_bDlmnJdUKo");
                try {
                    bot.startListening();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Trabalho1ChatBotLancheria.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Trabalho1ChatBotLancheria.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Trabalho1ChatBotLancheria.class.getName()).log(Level.SEVERE, null, ex);
                }
              }
        }).start();
        
        // Thread para a interface
        new Thread(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        }).start();
    }
}
