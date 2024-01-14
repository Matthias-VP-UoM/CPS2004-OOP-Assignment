package com.task2;

import java.util.ArrayList;

public class Truck extends Transport{
    private ArrayList<String> motorwayList = new ArrayList<String>();
    private String motorway;

    public Truck(){

    }

    public Truck(int id, String type, double speed, double cost){
        super(id, type, speed, cost);
    }

    @Override
    public void display_vehicle_details(){
        super.display_vehicle_details();
        System.out.println("Motorway List:");
        for (int i = 0; i < motorwayList.size(); i++){
            System.out.println(motorwayList.get(i));
        }
    }

    // Setters
    public void setMotorway(String motorway){
        this.motorway = motorway;
    }

    public void addToList(String motorway){
        motorwayList.add(motorway);
    }

    public void updateMotorwayinList(String motorway, int pos){
        motorwayList.set(pos, motorway);
    }

    // Getters
    public String getRoute(int pos){
        String motorwayAtPos = motorwayList.get(pos);
        return motorwayAtPos;
    }

}
