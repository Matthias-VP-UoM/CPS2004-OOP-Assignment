#ifndef STOCKITEM_H
#define STOCKITEM_H


#include "Product.h"
#include <string>
#include <vector>

class StockItem {
    int quantity;
    Product* product;

    public:
        StockItem(){}

        StockItem(int quantity, Product* product){
            this->quantity = quantity;
            this->product = product;
        }

        // Setters
        void setQuantity(int quantity){
            this->quantity = quantity;
        }

        void setProduct(Product* product){
            this->product = product;
        }

        // Getters
        int getQuantity(){
            return quantity;
        }

        Product* getProduct(){
            return product;
        }

        // Other Methods
        void updateQuantity(int newQuantity){
            quantity += newQuantity;
        }
};

#endif //STOCKITEM_H