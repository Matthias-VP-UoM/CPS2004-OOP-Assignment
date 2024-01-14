#include "Stock.h"
#include <iostream>


Stock::Stock(){}

void Stock::addItem(StockItem item){
    items.push_back(item);
}

void Stock::updateItem(StockItem item, int pos){
    items.at(pos) = item;
}

void Stock::removeItem(int pos){
    items.erase(items.begin() + pos);
}

bool Stock::checkIfEmptyList(){
    return items.empty();
}


void Stock::read_stock_list(){
    for (int i = 0; i < MAXITEMS; i++){
        print_item_details(i);
    }
}


void Stock::print_item_details(int pos){
    try {
        if (items.at(pos).getQuantity() > 0){
            Product* prodAtPos = items.at(pos).getProduct();
            cout << "Item " << pos << ":" << endl;
            cout << "Product Details:" << endl;
            prodAtPos->display_product_info();
            cout << "Product Quantity in Stock: " << items.at(pos).getQuantity() << endl;
            cout << "" << endl;
        }else{
            cout << "There is no item in the position given!" << endl;
        }
    }catch (const exception& e){
        cout << "" << endl;
    } 
}


StockItem Stock::getItem(int pos) const{
    return items.at(pos);
}

int Stock::calculate_product_quantity(StockItem item) const{
    return item.getQuantity();
}
