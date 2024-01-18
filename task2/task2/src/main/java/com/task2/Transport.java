package com.task2;

import java.io.Serializable;

public abstract class Transport implements Serializable{
    protected static final long serialVersionUID = 1L;

    protected int vehicleID;
    protected String vehicleType;
    protected double transportationSpeed;
    protected double transportationCostPerKm;

    public Transport(){

    }

    public Transport(int id, String type, double speed, double cost){
        vehicleID = id;
        vehicleType = type;
        transportationSpeed = speed;
        transportationCostPerKm = cost;
    }

    public abstract String getRoute(int pos);

    public double calculate_delivery_cost(double distance){
        return transportationSpeed * distance;
    }

    public double calculate_delivery_time(double distance){
        return distance/transportationSpeed;
    }

    public void display_vehicle_details(){
        System.out.println("Vehicle ID: " + getID());
        System.out.println("Vehicle Type: " + getType());
        System.out.println("Vehicle Transportation Speed: " + getSpeed());
        System.out.println("Vehicle Transportation Cost/km: " + getCost());
    }

    // Setters
    public void setID(int id){
        vehicleID = id;
    }

    public void setType(String type){
        vehicleType = type;
    }

    public void setSpeed(double speed){
        transportationSpeed = speed;
    }

    public void setCost(double cost){
        transportationCostPerKm = cost;
    }

    // Getters
    public int getID(){
        return vehicleID;
    }

    public String getType(){
        return vehicleType;
    }

    public double getSpeed(){
        return transportationSpeed;
    }

    public double getCost(){
        return transportationCostPerKm;
    }
}
