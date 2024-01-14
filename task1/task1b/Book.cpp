#include "Book.h"
#include <iostream>

Book::Book(){}

Book::Book(int id, const string& name, double price, const string& category, int volume, const string& i, const string& g, const string& a): Product(id, name, price, category, volume), IBAN(i), genre(g), author(a) {}

void Book::display_product_info() {
    Product::display_product_info();
    cout << "IBAN: " << IBAN << endl;
    cout << "Book Genre: " << genre << endl;
    cout << "Author: " << author << endl;
}

// Setters
void Book::setIBAN(const string& i){
    IBAN = i;
}

void Book::setGenre(const string& g){
    genre = g;
}

void Book::setAuthor(const string& a){
    author = a;
}

// Getters
string Book::getIBAN() const{
    return IBAN;
}

string Book::getGenre() const{
    return genre;
}

string Book::getAuthor() const{
    return author;
}