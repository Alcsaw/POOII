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
        
        // Escolhe a resposta adequada
        String responseMsg = "";
        if(lastBotMsg == null) {
            responseMsg = Constants.GREETING_MSG + "\n" + Constants.CategoryMsg();
        } else if(lastBotMsg.getText().contains("categoria")) {
            Category categ = new DAO<Category>().getById(Category.class, Integer.parseInt(lastClientMsg.getText()));
            responseMsg = categ == null ? Constants.DIDNT_UNDERSTAND_CATEGORY : Constants.ProductsMsg(categ);
        } else if(lastBotMsg.getText().contains("Por favor, escolha o produto")) {
            Product prod = new DAO<Product>().getById(Product.class, Integer.parseInt(lastClientMsg.getText()));
            responseMsg = prod == null ? Constants.DIDNT_UNDERSTAND_PRODUCT : Constants.QUANTITY_MSG;
        } else if(lastBotMsg.getText().contains("Quantas unidades")) {
            responseMsg = Constants.COMMENT_MSG;
        } else if(lastBotMsg.getText().contains("Possui alguma observação quanto à este produto?")) {
            responseMsg = Constants.ADD_PROD_MSG;
        } else if(lastBotMsg.getText().contains(Constants.DIDNT_UNDERSTAND_CATEGORY)) {
            Category categ = new DAO<Category>().getById(Category.class, Integer.parseInt(lastClientMsg.getText()));
            responseMsg = categ == null ? Constants.DIDNT_UNDERSTAND_CATEGORY : Constants.ProductsMsg(categ);
        } else if(lastBotMsg.getText().contains(Constants.DIDNT_UNDERSTAND_PRODUCT)) {
            Product prod = new DAO<Product>().getById(Product.class, Integer.parseInt(lastClientMsg.getText()));
            responseMsg = prod == null ? Constants.DIDNT_UNDERSTAND_PRODUCT : Constants.QUANTITY_MSG;
        } else if(lastBotMsg.getText().contains("Deseja adicionar mais algum produto?")) {
            if(lastClientMsg.getText().toLowerCase().equals("sim")) {
                responseMsg = Constants.CategoryMsg();
            } else {
                responseMsg = Constants.THANK_MSG;
                createOrder();
                this.messages = new ArrayList<TelegramMessage>();
            }
        } 
        
        bot.sendMessage(this.client.getId() + "", responseMsg);
        response.setText(responseMsg);
        this.messages.add(response);
    }
    
    public void createOrder() throws ClassNotFoundException, SQLException {
        DAO<Category> cDAO = new DAO<Category>();
        DAO<Product> pDAO = new DAO<Product>();
        DAO<Order> oDAO = new DAO<Order>();
        DAO<OrderProduct> opDAO = new DAO<OrderProduct>();
        
        // Cria a order que será inserida no banco
        Order order = new Order();
        order.setDelivered(false);
        order.setDone(true);
        order.setClient(this.client);
        
        // Cria a primeira orderProduct
        OrderProduct op = new OrderProduct();
        op.setOrder(order);
        order.addOrderProduct(op);
        
        // Remove mensagens erradas do cliente
        ArrayList<TelegramMessage> filteredMsgs = new ArrayList<TelegramMessage>();
        for(int i = 0; i < messages.size()-1; i++) {
            String curMsgTxt = messages.get(i).getText();
            String nextMsgTxt = messages.get(i + 1).getText();
            if(!nextMsgTxt.contains(Constants.DIDNT_UNDERSTAND_CATEGORY) && !nextMsgTxt.contains(Constants.DIDNT_UNDERSTAND_PRODUCT)
                && !curMsgTxt.contains(Constants.DIDNT_UNDERSTAND_CATEGORY) && !curMsgTxt.contains(Constants.DIDNT_UNDERSTAND_PRODUCT)) {
                filteredMsgs.add(messages.get(i));
            }
        }
        this.messages = filteredMsgs;
        for(TelegramMessage tm : messages) {
            System.out.println(tm.getText());
        }
        
        // Itera por cada mensagem
        for(int i = 0; i < messages.size() - 1; i++) {
            
            TelegramMessage curMsg = messages.get(i);
            TelegramMessage nextMsg = messages.get(i+1);
            
            // Se mensagem atual é o bot perguntando o produto
            if(curMsg.getText().contains("Por favor, escolha o produto")) {
                // Encontra o produto no banco
                Product selectedProd = pDAO.getById(Product.class, Integer.parseInt(nextMsg.getText()));
                // Pega ultima orderProduct no objeto order
                OrderProduct curOP = order.getOrderProducts().get(order.getOrderProducts().size() - 1);
                
                // Seta informações de quantidade, produto, etc.
                if(curOP.getProduct() == null) {
                    curOP.setProduct(selectedProd);
                    curOP.setQuantity(Integer.parseInt(messages.get(i+3).getText()));
                    curOP.setComment(messages.get(i+5).getText());
                } else {
                    OrderProduct opNew = new OrderProduct();
                    opNew.setProduct(selectedProd);
                    opNew.setQuantity(Integer.parseInt(messages.get(i+3).getText()));
                    opNew.setComment(messages.get(i+5).getText());
                    order.addOrderProduct(opNew);
                }
            }
        }
        
        // Faz o insert no banco da order e orderProducts
        order.getOrderProducts().remove(0);
        oDAO.insert(order);
        ArrayList<Order> allOrders = oDAO.get(Order.class);
        order.setId(allOrders.get(allOrders.size() - 1).getId());
        for(OrderProduct op3: order.getOrderProducts()) {
            op3.setOrder(order);
            opDAO.insert(op3);
        }
        
        
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
