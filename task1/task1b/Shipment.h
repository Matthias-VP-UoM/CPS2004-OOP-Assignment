#ifndef SHIPMENT_H
#define SHIPMENT_H

#include <string>
#include <vector>

#include "Stock.h"
#include "StockItem.h"
#include "Customer.h"
#include "Transport.h"
#include "Packaging.h"

using namespace std;

class Shipment{
    protected:
        int shipmentID;
        string shipmentStatus, dispatchDate, orderDate;
        double shipmentDistance;
        vector<StockItem> itemsToShip;
        Customer shipmentCustomer;
        Transport* shipmentTransport;
        Packaging shipmentPackaging;

    public:
        Shipment();
        Shipment(int id, Customer customer, const string& date, Packaging pack);
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
        void setDate(const string& date);
        void setCustomer(Customer customer);
        void setTransport(Transport* vehicle);
        void setPackaging(Packaging pack);
        void setDistance(double distance);

        // Getters
        int getID() const;
        string getStatus() const;
        string getOrderDate() const;
        string getDispatchDate() const;
        Customer getCustomer() const;
        StockItem getItem(int pos) const;
        Transport* getTransport() const;
        Packaging getPackaging() const;
        double getDistance() const;
};



#endif //SHIPMENT_H