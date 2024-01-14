#ifndef ELECTRONICS_H
#define ELECTRONICS_H

#include "Product.h"
#include <string>

using namespace std;

class Electronics: public Product{
    public:
        Electronics();
        Electronics(int id, const string& name, double price, const string& category, int volume);
};


#endif // ELECTRONICS_H