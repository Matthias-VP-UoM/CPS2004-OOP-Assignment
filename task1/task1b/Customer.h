#ifndef CUSTOMER_H
#define CUSTOMER_H

#include <string>

using namespace std;

class Customer{
    protected:
        int customerID;
        string customerName;
        string customerRoad, customerTown, customerPostCode;
    public:
        Customer();
        Customer(int id, string name, string road, string town, string postCode);
        void display_customer_info();

        // Setters
        void setID(int id);
        void setName(const string& name);
        void setRoad(const string& road);
        void setTown(const string& town);
        void setPostCode(const string& postCode);

        // Getters
        int getID() const;
        string getName() const;
        string getRoad() const;
        string getTown() const;
        string getPostCode() const;

};

#endif // CUSTOMER_H