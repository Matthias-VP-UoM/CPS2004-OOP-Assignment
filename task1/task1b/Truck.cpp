#include "Truck.h"
#include <iostream>
#include <string.h>

Truck::Truck(){}

Truck::Truck(int id, const string& type, double speed, double cost): Transport(id, type, speed, cost) {}

void Truck::display_transport_details() {
    Transport::display_transport_details();
    cout << "Motorway List: " << endl;
    for(int i = 0; i < motorwayList.size(); i++){
        cout << motorwayList.at(i) << endl;
    }
}

void Truck::updateMotorwayinList(const string& motorway, int pos){
    motorwayList.at(pos) = motorway;
}


// Setters
void Truck::setMotorway(const string& motorway){
    this->motorway = motorway;
}

void Truck::addToList(const string& motorway){
    motorwayList.push_back(motorway);
}


// Getters
string Truck::getRoute(int pos) const{
    string motorwayAtPos = motorwayList.at(pos);
    return motorwayAtPos;
}