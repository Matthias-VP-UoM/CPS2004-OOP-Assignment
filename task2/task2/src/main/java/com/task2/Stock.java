package com.task2;

import java.io.Serializable;
import java.util.ArrayList;

public class Stock implements Serializable{
    private static final long serialVersionUID = 1L;

    protected ArrayList<StockItem> items = new ArrayList<StockItem>();

    public Stock(){}

    public void addItem(StockItem item){
        items.add(item);
    }

    public void updateItem(StockItem item, int pos){
        items.set(pos, item);
    }

    public void removeItem(StockItem item){
        items.remove(item);
    }

    public boolean checkIfEmptyList(){
        return items.isEmpty();
    }

    public void read_stock_list(){
        for (int i = 0; i < items.size(); i++){
            print_item_details(i);
        }
    }

    public void print_item_details(int pos){
        try {
            if (items.get(pos).quantity > 0){
                Product prodAtPos = items.get(pos).getProduct();
                System.out.println("Item " + pos + ":");
                System.out.println("Product Details:");
                prodAtPos.display_product_info();
                System.out.println("Product Quantity in Stock: " + items.get(pos).getQuantity());
                System.out.println();
            }else{
                System.out.println("There is no item in the position given!");
            }
        }catch (Exception e){
            System.out.println();
        }
    }

    public int calculate_product_quantity(StockItem item){
        return item.getQuantity();
    }

    public StockItem getItem(int pos){
        return items.get(pos);
    }
}
