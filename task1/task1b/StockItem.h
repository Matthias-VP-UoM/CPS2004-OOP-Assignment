#ifndef STOCKITEM_H
#define STOCKITEM_H


#include "Product.h"
#include <string>
#include <vector>

class StockItem {
    int itemQuantity;
    Product* itemProduct;

    public:
        StockItem();

        StockItem(int quantity, Product* product);

        // Setters
        void setQuantity(int quantity);
        void setProduct(Product* product);

        // Getters
        int getQuantity() const;
        Product* getProduct() const;

        // Other Methods
        void updateQuantity(int newQuantity);
};

#endif //STOCKITEM_H