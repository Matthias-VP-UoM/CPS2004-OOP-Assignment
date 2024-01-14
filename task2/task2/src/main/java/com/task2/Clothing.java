package com.task2;

public class Clothing extends Product{
    private double width;
    private double length;
    private String sizeClassification;

    public Clothing(){

    }

    public Clothing(int id, String name, double price, String category, int volume, double w, double l, String size){
        super(id, name, price, category, volume);
        width = w;
        length = l;
        sizeClassification = size;
    }

    @Override
    public void display_product_info(){
        super.display_product_info();
        System.out.println("Width: "+getWidth());
        System.out.println("Length: "+getLength());
        System.out.println("Size Classification: "+getSize());
    }

    // Setters
    public void setWidth(double w){
        width = w;
    }

    public void setLength(double l){
        length = l;
    }

    public void setSize(String size){
        sizeClassification = size;
    }

    // Getters
    public double getWidth(){
        return width;
    }

    public double getLength(){
        return length;
    }

    public String getSize(){
        return sizeClassification;
    }
}
