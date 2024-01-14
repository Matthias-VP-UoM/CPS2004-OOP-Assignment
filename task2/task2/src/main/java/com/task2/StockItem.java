package com.task2;

import java.io.Serializable;

public class StockItem implements Serializable{
    private static final long serialVersionUID = 1L;

    protected int quantity;
    protected Product product;

    public StockItem(){}

    public StockItem(int quantity, Product product){
        this.quantity = quantity;
        this.product = product;
    }

    // Setters
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setProduct(Product product){
        this.product = product;
    }

    // Getters
    public int getQuantity(){
        return quantity;
    }

    public Product getProduct(){
        return product;
    }

    // Other Methods
    public void updateQuantity(int newQuantity){
        quantity = newQuantity;
    }
}
