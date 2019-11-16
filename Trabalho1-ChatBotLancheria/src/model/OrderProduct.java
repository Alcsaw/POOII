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
public class OrderProduct {
    private Product product;
    private Order order;
    private int quantity;
    private String comment;

    public OrderProduct() {
        this.product = new Product();
    }

    public OrderProduct(Product product, Order order, int quantity, String comment) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.comment = comment;
    }
    
    public double getTotalPrice() {
        return this.product.getPrice() * this.quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    
}
