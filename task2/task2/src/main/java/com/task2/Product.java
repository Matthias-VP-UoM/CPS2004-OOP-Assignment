package com.task2;

import java.io.Serializable;

public class Product implements Serializable{
    private static final long serialVersionUID = 1L;

    protected int productID;
    protected String productName;
    protected double productPrice;
    protected String productCategory;
    protected int productVolume;

    public Product(){

    }

    public Product(int id, String name, double price, String category, int volume){
        productID = id;
        productName = name;
        productPrice = price;
        productCategory = category;
        productVolume = volume;
    }

    public double calculate_discount(int quantity, int purchaseMonth){
        // Define discount rules based on quantity and purchase month
        double discount = 0.0;

        if (quantity > 10 && purchaseMonth == 12){
            discount = 0.15;  // 15% discount for more than 10 items purchased in December
        }else if (quantity >= 15 && purchaseMonth == 11){
            discount = 0.2;
        }else if (quantity > 5){
            discount = 0.18;   // 10% discount for more than 5 items purchased
        }else{
            discount = 0.1;
        }

        return discount;
    }

    public void display_product_info(){
        System.out.println("Product ID: "+getID());
        System.out.println("Product Name: "+getName());
        System.out.println("Product Price: "+getPrice());
        System.out.println("Product Category: "+getCategory());
        System.out.println("Product Volume: "+getVolume());
    }

    // Setters
    public void setID(int id){
        productID = id;
    }

    public void setName(String name){
        productName = name;
    }

    public void setPrice(double price){
        productPrice = price;
    }

    public void setCategory(String category){
        productCategory = category;
    }

    public void setVolume(int volume){
        productVolume = volume;
    }

    // Getters
    public int getID(){
        return productID;
    }

    public String getName(){
        return productName;
    }

    public double getPrice(){
        return productPrice;
    }

    public String getCategory(){
        return productCategory;
    }

    public int getVolume(){
        return productVolume;
    }
}
