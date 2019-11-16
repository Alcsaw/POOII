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
    private ConversationStateEnum state;
    private Order order;
    private int lastMessageId;
    private Category category;
    private Product product;
    private OrderProduct orderProduct;
    
    public Conversation() {
        this.state = ConversationStateEnum.GREETING;
        this.order = new Order();
    }

    public Conversation(ConversationStateEnum state, int lastMessageId) {
        this.state = state;
        this.lastMessageId = lastMessageId;
    }
    
    public static boolean conversationAlreadyExists(ArrayList<Conversation> convs, String clientName) {
        for(Conversation c : convs) {
            if(c.getOrder() != null) {
                if(c.getOrder().getClient() != null) {
                    if(c.getOrder().getClient().getName() != null) {
                        if(c.getOrder().getClient().getName().equals(clientName)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public void handleClientMessage(TelegramMessage message, Bot bot) throws SQLException, ClassNotFoundException {
        DAO<Category> categDAO = new DAO<Category>();
        DAO<Product> prodDAO = new DAO<Product>();
        DAO<Client> cliDAO = new DAO<Client>();
        
        if(null != this.state) switch (this.state) {
            case GREETING:
                order.setClient(cliDAO.getByNameOrDescription(Client.class, message.getText()));
                bot.sendMessage(message.getSenderId(), Constants.GREETING_MSG);
                bot.sendMessage(message.getSenderId(), Constants.CATEGORY_MSG);
                this.state = ConversationStateEnum.PRODUCT_CHOICE;
                break;
            case PRODUCT_CHOICE:
                this.category = categDAO.getByNameOrDescription(Category.class, message.getText());
                bot.sendMessage(message.getSenderId(), Constants.PRODUCT_MSG);
                this.state = ConversationStateEnum.QUANTITY_CHOICE;
                break;
            case QUANTITY_CHOICE:
                this.product = prodDAO.getByNameOrDescription(Product.class, message.getText());
                bot.sendMessage(message.getSenderId(), Constants.QUANTITY_MSG);
                this.state = ConversationStateEnum.COMMENTS;
                break;
            case COMMENTS:
                OrderProduct op = new OrderProduct();
                op.setOrder(order);
                op.setProduct(product);
                op.setQuantity(Integer.parseInt(message.getText()));
                this.order.addOrderProduct(op);
                bot.sendMessage(message.getSenderId(), Constants.COMMENT_MSG);
                this.state = ConversationStateEnum.ANOTHER_PRODUCT;
                break;
            case ANOTHER_PRODUCT:
                this.order.getOrderProducts().get(this.order.getOrderProducts().size() - 1).setComment(message.getText());
                bot.sendMessage(message.getSenderId(), Constants.ADD_PROD_MSG);
                this.state = ConversationStateEnum.THANKS;
                break;
            case THANKS:
                if(message.getText().equals("Sim")) {
                    this.state = ConversationStateEnum.GREETING;
                } else {
                    bot.sendMessage(message.getSenderId(), Constants.ADD_PROD_MSG);
                }
                break;
            default:
                break;
        }
    }

    public int getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(int lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    
    
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
    public void addProductToOrder(OrderProduct op) {
        this.order.addOrderProduct(op);
    }

    public ConversationStateEnum getState() {
        return state;
    }

    public void setState(ConversationStateEnum state) {
        this.state = state;
    }

    
    
}
