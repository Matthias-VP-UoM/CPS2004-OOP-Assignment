#include "Shipment.h"
#include <iostream>

Shipment::Shipment(){}

Shipment::Shipment(int id, Customer customer): shipmentID(id), shipmentStatus("Not Dispatched"), shipmentCustomer(customer){}

void Shipment::print_items_list(){
    for (int i = 0; i < itemsToShip.size(); i++){
        Product* prodAtPos = itemsToShip.at(i).getProduct();
        cout << "Item " << i << ":" << endl;
        cout << "Product Details:" << endl;
        prodAtPos->display_product_info();
        cout << "Product Quantity Ordered: " << itemsToShip.at(i).getQuantity() << endl;
        cout << "" << endl;
    }
}

void Shipment::display_shipment_details(){
    cout << "Order ID: " << shipmentID << endl;
    cout << "Customer: " << shipmentCustomer.getName() << endl;
    
    print_items_list();

    cout << "Transport Used: " << shipmentTransport->getType() << endl;
}

void Shipment::dispatch(const string& dateTime){
    // Perform the dispatch logic
    if (shipmentStatus != "Dispatched"){
        shipmentStatus = "Dispatched";
        dispatchDate = dateTime;
        // Additional dispatch logic if needed
    }
}

void Shipment::addItemToList(StockItem item){
        itemsToShip.push_back(item);
    }

void Shipment::updateItemInList(StockItem item, int pos){
    itemsToShip.at(pos) = item;
}

void Shipment::removeItemFromlist(int pos){
    itemsToShip.erase(itemsToShip.begin() + pos);
}

double Shipment::calculate_total_cost(int monthNum){
    double totalCost = 0.0;

    for(int i = 0; i < itemsToShip.size(); i++){
        StockItem currentItem = itemsToShip.at(i);
        int purchaseQuantity = currentItem.getQuantity();
        Product* purchaseProduct = currentItem.getProduct();
        double itemCost = (purchaseQuantity * purchaseProduct->getPrice()) - purchaseProduct->calculate_discount(purchaseQuantity, monthNum);
        totalCost += itemCost;
    }

    return totalCost;
}

string Shipment::stateTransportType(double distance){
    string vehicleTypeToUse = "";
    if (distance > 0 && distance <= 25){
        vehicleTypeToUse = "Truck";
    }else if (distance > 25 && distance <= 500){
        vehicleTypeToUse = "Sea Vessel";
    }else if (distance > 500){
        vehicleTypeToUse = "Aircraft";
    }

    return vehicleTypeToUse;
}

// Setters
void Shipment::setID(int id){
    shipmentID = id;
}

void Shipment::setStatus(const string& status){
    shipmentStatus = status;
}

void Shipment::setDate(const string& date){
    dispatchDate = date;
}

void Shipment::setTransport(Transport* vehicle){
    shipmentTransport = vehicle;
}

// Getters
int Shipment::getID() const{
    return shipmentID;
}

string Shipment::getStatus() const{
    return shipmentStatus;
}

string Shipment::getDate() const{
    return dispatchDate;
}

Customer Shipment::getCustomer() const{
    return shipmentCustomer;
}

StockItem Shipment::getItem(int pos) const{
    return itemsToShip.at(pos);
}

Transport* Shipment::getTransport() const{
    return shipmentTransport;
}
