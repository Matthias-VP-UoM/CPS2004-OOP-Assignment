#ifndef PACKAGING_H
#define PACKAGING_H

#include <string>

using namespace std;

class Packaging{
    protected:
        int packagingID;
        double packagingCost;
        string material;
        int capacity;
        //double width, length, height;
    public:
        Packaging();
        Packaging(int ID, double cost, const string& mat, int cap);
        virtual void display_package_details();

        // Setters
        void setID(int ID);
        void setCost(double cost);
        void setMaterial(const string& mat);
        void setCapacity(int cap);

        // Getters
        int getID() const;
        double getCost() const;
        string getMaterial() const;
        int getCapacity() const;
};


#endif //PACKAGING_H