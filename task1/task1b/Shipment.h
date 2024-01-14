#ifndef SHIPMENT_H
#define SHIPMENT_H

#include <string>
#include <vector>

#include "Stock.h"
#include "StockItem.h"
#include "Customer.h"
#include "Transport.h"

using namespace std;

class Shipment{
    protected:
        int shipmentID;
        string shipmentStatus, dispatchDate;
        vector<StockItem> itemsToShip;
        Customer shipmentCustomer;
        Transport* shipmentTransport;

    public:
        Shipment();
        Shipment(int id, Customer customer);
        double calculate_total_cost(int monthNum);
        void dispatch(const string& dateTime);
        void print_items_list();
        virtual void display_shipment_details();
        string stateTransportType(double distance);

        void addItemToList(StockItem item);
        void updateItemInList(StockItem item, int pos);
        void removeItemFromlist(int pos);

        // Setters
        void setID(int id);
        void setStatus(const string& status);
        void setDate(const string& date);
        void setTransport(Transport* vehicle);


        // Getters
        int getID() const;
        string getStatus() const;
        string getDate() const;
        Customer getCustomer() const;
        StockItem getItem(int pos) const;
        Transport* getTransport() const;
};



#endif //SHIPMENT_H