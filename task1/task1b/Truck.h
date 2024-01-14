#ifndef TRUCK_H
#define TRUCK_H

#include "Transport.h"
#include <string>
#include <vector>

using namespace std;

class Truck: public Transport{
    private:
        vector<string> motorwayList;
        string motorway;
    
    public:
        Truck();
        Truck(int id, const string& type, double speed, double cost);
        void display_transport_details() override;

        // Setters
        void setMotorway(const string& motorway);
        void addToList(const string& motorway);

        void updateMotorwayinList(const string& motorway, int pos);

        // Getter
        string getRoute(int pos) const;
        //struct motorWayList getMotorwayList() const;
};

#endif // TRUCK_H