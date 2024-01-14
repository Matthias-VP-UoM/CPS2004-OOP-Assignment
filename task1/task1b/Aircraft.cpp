#include "Aircraft.h"
#include <iostream>
#include <string.h>

Aircraft::Aircraft(){}

Aircraft::Aircraft(int id, const string& type, double speed, double cost): Transport(id, type, speed, cost) {}

void Aircraft::display_transport_details() {
    Transport::display_transport_details();
    cout << "Air Space List: " << endl;
    for(int i = 0; i < airSpaceList.size(); i++){
        cout << airSpaceList.at(i) << endl;
    }
}

void Aircraft::updateAirSpaceinList(const string& airSpace, int pos){
    airSpaceList.at(pos) = airSpace;
}


// Setters
void Aircraft::setAirSpace(const string& airSpace){
    this->airSpace = airSpace;
}

void Aircraft::addToList(const string& airSpace){
    airSpaceList.push_back(airSpace);
}


// Getters
string Aircraft::getRoute(int pos) const{
    string airSpaceAtPos = airSpaceList.at(pos);
    return airSpaceAtPos;
}