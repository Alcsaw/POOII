/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;

/**
 *
 * @author erico
 */
public class Order {
    private int id;
    private Client client;
    private ArrayList<OrderProduct> orderProducts;
    private Date date;
    private boolean done;
    private boolean delivered;

    public Order() {
        
    }
    
    public Order(int id, Client client, ArrayList<OrderProduct> orderProducts, Date date, boolean done, boolean delivered) {
        this.id = id;
        this.client = client;
        this.orderProducts = orderProducts;
        this.date = date;
        this.done = done;
        this.delivered = delivered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(ArrayList<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
    
    
    
}
