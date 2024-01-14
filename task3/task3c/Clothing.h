#ifndef CLOTHING_H
#define CLOTHING_H

#include "Product.h"
#include <string>

using namespace std;

class Clothing: public Product{
    private:
        double width;
        double length;
        string sizeClassification;
    
    public:
        Clothing();
        Clothing(int id, const string& name, double price, const string& category, int volume, double w, double l, const string& size);
        void display_product_info() override;

        // Setters
        void setWidth(double w);
        void setLength(double l);
        void setSize(const string& size);

        // Getters
        double getWidth() const;
        double getLength() const;
        string getSize() const;
};

#endif // CLOTHING_H