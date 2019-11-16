/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author erico
 */
import DataAccessLayer.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
import util.*;
public class Conversation {
    private ArrayList<TelegramMessage> messages;
    private Client client;
    public Conversation() {
        this.messages = new ArrayList<TelegramMessage>();
        this.client = new Client();
    }

    public Conversation(ArrayList<TelegramMessage> messages, Client client) {
        this.messages = messages;
        this.client = client;
    }
    
    public static Conversation findExistingConversation(ArrayList<Conversation> conversations, Client c) {
        for(Conversation conv : conversations) {
            if(conv.getClient().getId() == c.getId()) {
                return conv;
            }
        }
        return null;
    }
    
    public void respond(Bot bot) throws ClassNotFoundException, SQLException {
        // Mensagem que será enviada pelo bot
        TelegramMessage response = new TelegramMessage();
        response.setMessageId(-1);
        response.setSenderFirstName("Bot");
        response.setSenderLastName("Bot");
        response.setSenderId("-1");
        response.setUpdateId(1);
        
        // pega a ultima msg do bot
        // pega a ultima msg do cliente
        // baseado nelas, decide qual mensagem enviar
        // monta mensagem a ser enviada (resposta) e salva no historico
        // envia mensagem
        // trata caso tenha concluido a conversa
        TelegramMessage lastBotMsg = this.messages.size() > 2 ? this.messages.get(this.messages.size() - 2) : null;
        TelegramMessage lastClientMsg = this.messages.get(this.messages.size() - 1);
        
        // Choose response
        String responseMsg = "";
        if(lastBotMsg == null) {
            responseMsg = Constants.GREETING_MSG + "\n" + Constants.CategoryMsg();
        } else if(lastBotMsg.getText().contains("categoria")) {
            // envia produtos
            DAO<Category> dao = new DAO<Category>();
            responseMsg = Constants.ProductsMsg(dao.getByNameOrDescription(Category.class, "Pizzas"));
        } else if(lastBotMsg.getText().contains("Por favor, escolha o produto")) {
            // pergunta quantidade
            responseMsg = Constants.QUANTITY_MSG;
        } else if(lastBotMsg.getText().contains("Quantas unidades")) {
            // pergunta observacao
            responseMsg = Constants.COMMENT_MSG;
        } else if(lastBotMsg.getText().contains("Possui alguma observação quanto à este produto?")) {
            // pergunta mais algum produto
            responseMsg = Constants.ADD_PROD_MSG;
        } else if(lastBotMsg.getText().contains("Deseja adicionar mais algum produto?")) {
            // se sim, manda mensagem de categorias
            // senao, manda mensagem de término, gera order e salva no banco
            if(lastClientMsg.getText().toLowerCase().equals("sim")) {
                responseMsg = Constants.CategoryMsg();
            } else {
                responseMsg = Constants.THANK_MSG;
                // Ler todo o historico de mensagens, gerar a order e salvar no banco
                // Após, apagar o historico de mensagens para que o cliente possa fazer novoos pedidos
            }
        } 
        
        bot.sendMessage(this.client.getId() + "", responseMsg);
        response.setText(responseMsg);
        this.messages.add(response);
    }
    
    public void addMessage(TelegramMessage msg) {
        this.messages.add(msg);
    }

    public ArrayList<TelegramMessage> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<TelegramMessage> messages) {
        this.messages = messages;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    
}
