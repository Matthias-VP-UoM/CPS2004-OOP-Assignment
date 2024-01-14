package com.task2;

import java.io.Serializable;

public class Packaging implements Serializable{
    private static final long serialVersionUID = 1L;

    protected int packagingID;
    protected double packagingCost;
    protected String material;
    protected int capacity;

    public Packaging(){
        
    }

    public Packaging(int id, double cost, String mat, int cap){
        packagingID = id;
        packagingCost = cost;
        material = mat;
        capacity = cap;
    }

    public void display_package_details(){
        System.out.println("Packaging ID: "+getID());
        System.out.println("Packaging Cost: "+getCost());
        System.out.println("Packaging Material: "+getMaterial());
        System.out.println("Packaging Capacity: "+getCapacity());
    }

    // Setters
    public void setID(int id){
        packagingID = id;
    }

    public void setCost(double cost){
        packagingCost = cost;
    }

    public void setMaterial(String mat){
        material = mat;
    }

    public void setCapacity(int cap){
        capacity = cap;
    }

    // Getters
    public int getID(){
        return packagingID;
    }

    public double getCost(){
        return packagingCost;
    }

    public String getMaterial(){
        return material;
    }

    public int getCapacity(){
        return capacity;
    }
}
