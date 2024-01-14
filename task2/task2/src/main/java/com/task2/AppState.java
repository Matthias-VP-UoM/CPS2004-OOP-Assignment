package com.task2;

import java.io.*;
import java.util.ArrayList;

public class AppState {
    private Stock stockState;
    private ArrayList<Packaging> packagingList;
    private ArrayList<Transport> transportList;
    private ArrayList<Customer> customerList;

    // Setter methods
    public void setStock(Stock stock) {
        stockState = stock;
    }

    public void setPackagingList(ArrayList<Packaging> packages) {
        packagingList = packages;
    }

    public void setTransportList(ArrayList<Transport> transports) {
        transportList = transports;
    }

    public void setCustomerList(ArrayList<Customer> customers) {
        customerList = customers;
    }

    // Getter methods
    public Stock getStock(){
        return stockState;
    }
    
    public ArrayList<Packaging> getPackagingList() {
        return packagingList;
    }

    public ArrayList<Transport> getTransportList() {
        return transportList;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    // Example method for serialization
    public void save(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            // Serialize your data structures here
            // For example: out.writeObject(member_variable);
            out.writeObject(stockState);

            // Serialize the lists
            out.writeObject(getPackagingList());
            out.writeObject(getTransportList());
            out.writeObject(getCustomerList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Example method for deserialization
    public void load(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            // Deserialize your data structures here
            // For example: member_variable = (DataType) in.readObject();
            stockState = (Stock) in.readObject();

            // Deserialize the lists
            setPackagingList((ArrayList<Packaging>) in.readObject());
            setTransportList((ArrayList<Transport>) in.readObject());
            setCustomerList((ArrayList<Customer>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
