package com.task3a_b;

import java.io.Serializable;
import java.util.ArrayList;

public class Shipment implements Serializable, ShipmentInterface{
    private static final long serialVersionUID = 1L;

    private int shipmentID;
    private String shipmentStatus, dispatchDate, orderDate;
    private ArrayList<StockItem> itemsToShip = new ArrayList<StockItem>();
    private Customer shipmentCustomer;
    private Transport shipmentTransport;

    public Shipment(){
        shipmentStatus = "Not Dispatched";
    }

    public Shipment(int id, Customer customer, String date){
        shipmentID = id;
        shipmentStatus = "Not Dispatched";
        orderDate = date;
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

    public void dispatch(String currentDateTime){
        // Perform the dispatch logic
        if (shipmentStatus.equals("Dispatched")){
            shipmentStatus = "Dispatched";
            dispatchDate = currentDateTime;
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
            double itemCost = (purchaseQuantity * purchaseProduct.getPrice()) * (1 - purchaseProduct.calculate_discount(purchaseQuantity, monthNum));
            totalCost += itemCost;
        }

        return totalCost;
    }

    public String getDeliveryPlan(){
        return null;
    }


    // Setters
    public void setID(int id){
        shipmentID = id;
    }

    public void setDate(String date){
        orderDate = date;
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

    public String getOrderDate(){
        return orderDate;
    }

    public String getDispatchDate(){
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

    public ArrayList<StockItem> getList(){
        return itemsToShip;
    }

    public int getListSize(){
        return itemsToShip.size();
    }

    public int getOrderedQuantity(int pos){
        return itemsToShip.get(pos).getQuantity();
    }

    public Product getOrderedProduct(int pos){
        return itemsToShip.get(pos).getProduct();
    }
}
