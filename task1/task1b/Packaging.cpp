#include "Packaging.h"
#include <iostream>

Packaging::Packaging(){}

Packaging::Packaging(int ID, double cost, const string& mat, int cap): packagingID(ID), packagingCost(cost), material(mat), capacity(cap){}

void Packaging::display_package_details(){
    cout << "Packaging ID: " << getID() << endl; 
    cout << "Packaging Cost: " << getCost() << endl;
    cout << "Packaging Material: " << getMaterial() << endl;
    cout << "Packaging Capacity: " << getCapacity() << endl;
}

// Setters
void Packaging::setID(int ID){
    packagingID = ID;
}

void Packaging::setCost(double cost){
    packagingCost = cost;
}

void Packaging::setMaterial(const string& mat){
    material = mat;
}

void Packaging::setCapacity(int cap){
    capacity = cap;
}

// Getters
int Packaging::getID() const{
    return packagingID;
}

double Packaging::getCost() const{
    return packagingCost;
}

string Packaging::getMaterial() const{
    return material;
}

int Packaging::getCapacity() const{
    return capacity;
}
