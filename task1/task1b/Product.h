#ifndef PRODUCT_H
#define PRODUCT_H

#include <string>

using namespace std;

class Product{
    protected:
        int productID;
        string productName;
        double productPrice;
        string productCategory;
        int productVolume;

    public:
        Product();
        Product(int id, const string& name, double price, const string& category, int volume);
        double calculate_discount(int quantity, int purchaseMonth);
        virtual void display_product_info();

        // Setters
        void setID(int id);
        void setName(const string& name);
        void setPrice(double price);
        void setCategory(const string& category);
        void setVolume(int volume);


        // Getters
        int getID() const;
        string getName() const;
        double getPrice() const;
        string getCategory() const;
        int getVolume() const;
};



#endif //PRODUCT_H