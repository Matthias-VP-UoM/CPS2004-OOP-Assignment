#include "Product.h"
#include <iostream>

Product::Product(){}

Product::Product(int id, const string& name, double price, const string& category, int volume): productID(id), productName(name), productPrice(price), productCategory(category), productVolume(volume){}

// Shopping_Items::~Shopping_Items(){
//     //displayInfo();
// }

double Product::calculate_discount(int quantity, int purchaseMonth){
    // Define discount rules based on quantity and purchase month
    double discount = 0.0;

    if (quantity > 10 && purchaseMonth == 12){
        discount = 0.15;  // 15% discount for more than 10 items purchased in December
    }else if (quantity >= 15 && purchaseMonth == 11){
        discount = 0.2;
    }else if (quantity > 5){
        discount = 0.1;   // 10% discount for more than 5 items purchased
    }

    return discount;
}

void Product::display_product_info(){
    cout << "Product ID: " << getID() << endl;
    cout << "Product Name: " << getName() << endl;
    cout << "Product Price: " << getPrice() << endl;
    cout << "Product Category: " << getCategory() << endl;
    cout << "Product Volume: " << getVolume() << endl;
}

// Setters
void Product::setID(int id){
    productID = id;
}

void Product::setName(const string& name){
    productName = name;
}

void Product::setPrice(double price){
    productPrice = price;
}

void Product::setCategory(const string& category){
    productCategory = category;
}

void Product::setVolume(int volume){
    productVolume = volume;
}

// Getters
int Product::getID() const{
    return productID;
}

string Product::getName() const{
    return productName;
}

double Product::getPrice() const{
    return productPrice;
}

string Product::getCategory() const{
    return productCategory;
}

int Product::getVolume() const{
    return productVolume;
}
