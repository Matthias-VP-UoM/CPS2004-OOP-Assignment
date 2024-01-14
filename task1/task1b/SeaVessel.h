#ifndef SEAVESSEL_H
#define SEAVESSEL_H

#include "Transport.h"
#include <string>
#include <vector>

using namespace std;

class SeaVessel: public Transport{
    private:
        vector<string> routeList;
        string route;
    
    public:
        SeaVessel();
        SeaVessel(int id, const string& type, double speed, double cost);
        void display_transport_details() override;

        void updateRouteinList(const string& route, int pos);

        // Setters
        void setRoute(const string& route);
        void addToList(const string& route);

        // Getter
        string getRoute(int pos) const;
        //struct motorWayList getMotorwayList() const;
};


#endif // SEAVESSEL_H