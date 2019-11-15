/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;
import java.util.ArrayList;

/**
 *
 * @author erico
 */
public class Order {
    private int id;
    private Client client;
    private ArrayList<Product> products;
    private DateFormat date;
    private boolean done;
    private boolean delivered;
    private String observation;

    public Order() {
        
    }
    
    public Order(int id, Client client, ArrayList<Product> products, DateFormat date, boolean isProduced, boolean isDelivered, String observation) {
        this.id = id;
        this.client = client;
        this.products = products;
        this.date = date;
        this.done = isProduced;
        this.delivered = isDelivered;
        this.observation = observation;
    }

    public double calculateTotalPrice() {
        double sum = 0;
        for(Product pr : products) {
            sum += pr.getPrice();
        }
        return sum;
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

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public DateFormat getDate() {
        return date;
    }

    public void setDate(DateFormat date) {
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

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
    
    
}
