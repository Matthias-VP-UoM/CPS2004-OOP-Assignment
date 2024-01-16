#ifndef STOCK_H
#define STOCK_H

#include "Product.h"
#include "StockItem.h"
#include <string>
#include <vector>

#define MAXITEMS 300

using namespace std;

class Stock{
    protected:
        vector<StockItem> items;
    public:
        Stock();

        void addItem(StockItem item);
        void updateItem(StockItem item, int pos);
        void removeItem(int pos);
        bool checkIfEmptyList();
        void read_stock_list();
        void print_item_details(int pos);
        StockItem getItem(int pos) const;
        int calculate_product_quantity(StockItem item) const;
};


#endif //STOCK_H