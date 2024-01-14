package com.task3a_b;

import java.util.ArrayList;

public class Aircraft extends Transport{
    ArrayList<String> airSpaceList = new ArrayList<String>();
    private String airSpace;

    public Aircraft(){

    }

    public Aircraft(int id, String type, double speed, double cost){
        super(id, type, speed, cost);
    }

    @Override
    public void display_vehicle_details(){
        super.display_vehicle_details();
        System.out.println("Air Space List:");
        for (int i = 0; i < airSpaceList.size(); i++){
            System.out.println(airSpaceList.get(i));
        }
    }

    // Setters
    public void setAirSpace(String airSpace){
        this.airSpace = airSpace;
    }

    public void addToList(String airSpace){
        airSpaceList.add(airSpace);
    }

    public void updateAirSpaceinList(String airSpace, int pos){
        airSpaceList.set(pos, airSpace);
    }

    // Getters
    public String getRoute(int pos){
        String airSpaceAtPos = airSpaceList.get(pos);
        return airSpaceAtPos;
    }
}
