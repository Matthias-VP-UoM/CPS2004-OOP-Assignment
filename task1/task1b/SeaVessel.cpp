#include "SeaVessel.h"
#include <iostream>
#include <string.h>

SeaVessel::SeaVessel(){}

SeaVessel::SeaVessel(int id, const string& type, double speed, double cost): Transport(id, type, speed, cost) {}

void SeaVessel::display_transport_details() {
    Transport::display_transport_details();
    cout << "Route List: " << endl;
    for(int i = 0; i < routeList.size(); i++){
        cout << routeList.at(i) << endl;
    }
}

void SeaVessel::updateRouteinList(const string& route, int pos){
    routeList.at(pos) = route;
}


// Setters
void SeaVessel::setRoute(const string& route){
    this->route = route;
}

void SeaVessel::addToList(const string& route){
    routeList.push_back(route);
}


// Getters
string SeaVessel::getRoute(int pos) const{
    string routeAtPos = routeList.at(pos);
    return routeAtPos;
}