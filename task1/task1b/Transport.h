#ifndef TRANSPORT_H
#define TRANSPORT_H

#include <string>

using namespace std;

class Transport{
    protected:
        int vehicleID;
        string vehicleType;
        double transportationSpeed;
        double transportationCostPerKm;

    public:
        Transport();
        Transport(int id, const string& type, double speed, double cost);
        double calculate_delivery_cost(double distance);
        double calculate_delivery_time(double distance);
        virtual void display_transport_details();

        // Setters
        void setID(int id);
        void setType(const string& type);
        void setSpeed(double speed);
        void setCost(double cost);

        // Getters
        int getID() const;
        string getType() const;
        double getSpeed() const;
        double getCost() const;

        //virtual string getRoute(int pos) const = 0;
};


#endif //TRANSPORT_H