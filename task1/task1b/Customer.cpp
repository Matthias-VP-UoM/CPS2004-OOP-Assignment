#include "Customer.h"
#include <iostream>


Customer::Customer(){}

Customer::Customer(int id, string name, string road, string town, string postCode): customerID(id), customerName(name), customerRoad(road), customerTown(town), customerPostCode(postCode){}

void Customer::display_customer_info(){
    cout << "Customer ID: " << getID() << endl;
    cout << "Customer Name: " << getName() << endl;
    cout << "Customer Address: " << getRoad() << ", " << getTown() << ", " << getPostCode() << endl;
}

// Setters
void Customer::setID(int id){
    customerID = id;
}

void Customer::setName(const string& name){
    customerName = name;
}

void Customer::setRoad(const string& road){
    customerRoad = road;
}

void Customer::setTown(const string& town){
    customerTown = town;
}

void Customer::setPostCode(const string& postCode){
    customerPostCode = postCode;
}

// Getters
int Customer::getID() const{
    return customerID;
}

string Customer::getName() const{
    return customerName;
}

string Customer::getRoad() const{
    return customerRoad;
}

string Customer::getTown() const{
    return customerTown;
}

string Customer::getPostCode() const{
    return customerPostCode;
}