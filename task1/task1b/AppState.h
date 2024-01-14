#include <iostream>
#include <fstream>

#include "Stock.h"
#include "Transport.h"
#include "Packaging.h"

using namespace std;

class AppState{
    protected:
        Stock* stockState = new Stock();
        Transport* transportList[150];
        Packaging* packagingList[300];

    public:
        // Setter methods
        void setStock(Stock* stock);
        void setTransportList(Transport** transports);
        void setPackagingList(Packaging** packages);

        // Getter methods
        Stock *getStock();
        Transport **getTransportList();
        Packaging **getPackagingList();

        // Methods for serialization and deserialization
        void save(const string& filename) const;
        void load(const string& filename);
};