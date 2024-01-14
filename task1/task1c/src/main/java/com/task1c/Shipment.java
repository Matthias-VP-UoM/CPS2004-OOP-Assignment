package com.task1c;

import java.io.Serializable;
import java.util.ArrayList;

public class Shipment implements Serializable{
    private static final long serialVersionUID = 1L;

    private int shipmentID;
    private String shipmentStatus, dispatchDate;
    private ArrayList<StockItem> itemsToShip = new ArrayList<StockItem>();
    private Customer shipmentCustomer;
    private Transport shipmentTransport;

    public Shipment(){
        shipmentStatus = "Not Dispatched";
    }

    public Shipment(int id, Customer customer){
        shipmentID = id;
        shipmentStatus = "Not Dispatched";
        shipmentCustomer = customer;
    }

    public void display_shipment_details(){
        System.out.println("Order ID: " + shipmentID);
        System.out.println("Customer: " + shipmentCustomer);
        
        print_items_list();

        System.out.println("Transport Used: " + shipmentTransport);
    }

    public void print_items_list(){
        for (int i = 0; i < itemsToShip.size(); i++){
            Product prodAtPos = itemsToShip.get(i).getProduct();
            System.out.println("Item " + i + ":");
            System.out.println("Product Details:");
            prodAtPos.display_product_info();
            System.out.println("Product Quantity Ordered: " + itemsToShip.get(i).getQuantity());
            System.out.println();
        }
    }

    public void dispatch(String date){
        // Perform the dispatch logic
        if (shipmentStatus.equals("Dispatched")){
            shipmentStatus = "Dispatched";
            dispatchDate = date;
        }
    }

    public String stateTransportType(double distance){
        String vehicleTypeToUse = "";
        if (distance > 0 && distance <= 25){
            vehicleTypeToUse = "Truck";
        }else if (distance > 25 && distance <= 500){
            vehicleTypeToUse = "Sea Vessel";
        }else if (distance > 500){
            vehicleTypeToUse = "Aircraft";
        }

        return vehicleTypeToUse;
    }

    public void addItemToList(StockItem item){
        itemsToShip.add(item);
    }

    public void updateItemInList(StockItem item, int pos){
        itemsToShip.set(pos, item);
    }

    public void removeItemFromlist(int pos){
        itemsToShip.remove(pos);
    }

    public double calculateTotalCost(int monthNum){
        double totalCost = 0.0;

        for(int i = 0; i < itemsToShip.size(); i++){
            StockItem currentItem = itemsToShip.get(i);
            int purchaseQuantity = currentItem.getQuantity();
            Product purchaseProduct = currentItem.getProduct();
            double itemCost = (purchaseQuantity * purchaseProduct.getPrice()) - purchaseProduct.calculate_discount(purchaseQuantity, monthNum);
            totalCost += itemCost;
        }

        return totalCost;
    }


    // Setters
    public void setID(int id){
        shipmentID = id;
    }

    public void setDate(String date){
        dispatchDate = date;
    }

    public void setCustomer(Customer customer){
        shipmentCustomer = customer;
    }

    public void setTransport(Transport vehicle){
        shipmentTransport = vehicle;
    }

    // Getters
    public int getID(){
        return shipmentID;
    }

    public String getDate(){
        return dispatchDate;
    }

    public String getStatus(){
        return shipmentStatus;
    }

    public Customer getCustomer(){
        return shipmentCustomer;
    }

    public StockItem getItem(int pos){
        return itemsToShip.get(pos);
    }

    public Transport getTransport(){
        return shipmentTransport;
    }
}
