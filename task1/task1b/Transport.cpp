#include "Transport.h"
#include <iostream>

Transport::Transport(){}

Transport::Transport(int id, const string& type, double speed, double cost): vehicleID(id), vehicleType(type), transportationSpeed(speed), transportationCostPerKm(cost){}


double Transport::calculate_delivery_cost(double distance){
    // Calculate delivery cost based on transportation cost per km and distance
    return transportationCostPerKm * distance;
}

double Transport::calculate_delivery_time(double distance){
    // Calculate estimated delivery time based on transportation speed and distance
    return distance / transportationSpeed;
}


void Transport::display_transport_details(){
    cout << "Vehicle ID: " << getID() << endl;
    cout << "Vehicle Type: " << getType() << endl;
    cout << "Vehicle Transportation Speed: " << getSpeed() << endl;
    cout << "Vehicle Transportation Cost/km: " << getCost() << endl;
}


// Setters
void Transport::setID(int id){
    vehicleID = id;
}

void Transport::setType(const string& type){
    vehicleType = type;
}

void Transport::setSpeed(double speed){
    transportationSpeed = speed;
}

void Transport::setCost(double cost){
    transportationCostPerKm = cost;
}

// Getters
int Transport::getID() const{
    return vehicleID;
}

string Transport::getType() const{
    return vehicleType;
}

double Transport::getSpeed() const{
    return transportationSpeed;
}

double Transport::getCost() const{
    return transportationCostPerKm;
}