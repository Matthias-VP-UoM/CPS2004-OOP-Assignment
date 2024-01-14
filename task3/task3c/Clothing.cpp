#include "Clothing.h"
#include <iostream>

Clothing::Clothing(){}

Clothing::Clothing(int id, const string& name, double price, const string& category, int volume, double w, double l, const string& size): Product(id, name, price, category, volume), width(w), length(l), sizeClassification(size) {}

void Clothing::display_product_info() {
    Product::display_product_info();
    cout << "Width: " << width << endl;
    cout << "Length: " << length << endl;
    cout << "Size Classification: " << sizeClassification << endl;
}

// Setters
void Clothing::setWidth(double w){
    width = w;
}

void Clothing::setLength(double l){
    length = l;
}

void Clothing::setSize(const string& size){
    sizeClassification = size;
}


// Getters
double Clothing::getWidth() const {
    return width;
}

double Clothing::getLength() const {
    return length;
}

string Clothing::getSize() const {
    return sizeClassification;
}