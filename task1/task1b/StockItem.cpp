#include "StockItem.h"
#include <iostream>

StockItem::StockItem(){}

StockItem::StockItem(int quantity, Product* product): itemQuantity(quantity), itemProduct(product){}

// Setters
void StockItem::setQuantity(int quantity){
    itemQuantity = quantity;
}

void StockItem::setProduct(Product* product){
    itemProduct = product;
}

// Getters
int StockItem::getQuantity() const{
    return itemQuantity;
}

Product* StockItem::getProduct() const{
    return itemProduct;
}

// Other Methods
void StockItem::updateQuantity(int newQuantity){
    itemQuantity += newQuantity;
}