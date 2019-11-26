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
    private String date;
    private Double totalPrice;
    private boolean done;
    private boolean delivered;

    public Order() {
        this.client = new Client();
        this.orderProducts = new ArrayList<OrderProduct>();
    }
    
    public Order(int id, Client client, ArrayList<OrderProduct> orderProducts, String date, boolean done, boolean delivered) {
        this.id = id;
        this.client = client;
        this.orderProducts = orderProducts;
        this.date = date;
        double price = 0;
        for (OrderProduct orderProduct : orderProducts) {
            price += orderProduct.getTotalPrice();
        }
        this.totalPrice = price;
        this.done = done;
        this.delivered = delivered;
    }
    
    public void addOrderProduct(OrderProduct op) {
        this.orderProducts.add(op);
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
    
    public String getTotalPriceString() {
        return Double.toString(totalPrice).replace(".", ",");
    }

    public void setTotalPrice() {
        double price = 0;
        for (OrderProduct orderProduct : orderProducts) {
            price += orderProduct.getTotalPrice();
        }
        this.totalPrice = price;
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
    
    public String[] toCSVString() {
        return new String[] {this.id + "", this.date, this.done + "", this.delivered + "", this.client.getName()};
    }
    
}
