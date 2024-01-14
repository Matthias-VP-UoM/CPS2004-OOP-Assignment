package com.task1c;

import java.util.ArrayList;


public class SeaVessel extends Transport{
    private ArrayList<String> routeList = new ArrayList<String>();
    private String route;

    public SeaVessel(){

    }

    public SeaVessel(int id, String type, double speed, double cost){
        super(id, type, speed, cost);
    }

    @Override
    public void display_vehicle_details(){
        super.display_vehicle_details();
        System.out.println("Route List:");
        for (int i = 0; i < routeList.size(); i++){
            System.out.println(routeList.get(i));
        }
    }

    // Setters
    public void setRoute(String route){
        this.route = route;
    }

    public void addToList(String route){
        routeList.add(route);
    }

    public void updateRouteinList(String route, int pos){
        routeList.set(pos, route);
    }

    // Getters
    public String getRoute(int pos){
        String routeAtPos = routeList.get(pos);
        return routeAtPos;
    }
}
