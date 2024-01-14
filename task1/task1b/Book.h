#ifndef BOOK_H
#define BOOK_H

#include "Product.h"
#include <string>

using namespace std;

class Book: public Product{
    private:
        string IBAN;
        string genre;
        string author;
    
    public:
        Book();
        Book(int id, const string& name, double price, const string& category, int volume, const string& i, const string& g, const string& a);
        void display_product_info() override;

        // Setters
        void setIBAN(const string& i);
        void setGenre(const string& g);
        void setAuthor(const string& a);

        // Getters
        string getIBAN() const;
        string getGenre() const;
        string getAuthor() const;
};


#endif // BOOK_H