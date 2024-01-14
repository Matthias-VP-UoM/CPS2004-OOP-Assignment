#ifndef AIRCRAFT_H
#define AIRCRAFT_H

#include "Transport.h"
#include <string>
#include <vector>

using namespace std;


class Aircraft: public Transport{
    private:
        vector<string> airSpaceList;
        string airSpace;
    
    public:
        Aircraft();
        Aircraft(int id, const string& type, double speed, double cost);
        void display_transport_details() override;

        void updateAirSpaceinList(const string& airSpace, int pos);

        // Setters
        void setAirSpace(const string& airSpace);
        void addToList(const string& airSpace);

        // Getter
        string getRoute(int pos) const;
        //struct motorWayList getMotorwayList() const;
};

#endif // AIRCRAFT_H