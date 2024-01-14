package com.task1c;

import java.io.Serializable;

public class Customer implements Serializable{
    private static final long serialVersionUID = 1L;

    protected int customerID;
    protected String customerName;
    protected String customerRoad, customerTown, customerPostCode;

    public Customer(){}

    public Customer(int id, String name, String road, String town, String postCode){
        customerID = id;
        customerName = name;
        customerRoad = road;
        customerTown = town;
        customerPostCode = postCode;
    }

    public String toString(){
        String info = "";

        info += "Customer ID: " + getID() + "\n";
        info += "Customer Name: " + getName() + "\n";
        info += "Customer Address: " + getRoad() + ", " + getTown() + ", " + getPostCode() + "\n";

        return info;
    }

    // Setters
    public void setID(int id){
        customerID = id;
    }

    public void setName(String name){
        customerName = name;
    }

    public void setRoad(String road){
        customerRoad = road;
    }

    public void setTown(String town){
        customerTown = town;
    }

    public void setPostCode(String postCode){
        customerPostCode = postCode;
    }

    // Getters
    public int getID(){
        return customerID;
    }

    public String getName(){
        return customerName;
    }

    public String getRoad(){
        return customerRoad;
    }

    public String getTown(){
        return customerTown;
    }

    public String getPostCode(){
        return customerPostCode;
    }
}
