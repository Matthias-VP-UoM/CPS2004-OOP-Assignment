#include "Electronics.h"
#include <iostream>

Electronics::Electronics(){}

Electronics::Electronics(int id, const string& name, double price, const string& category, int volume): Product(id, name, price, category, volume) {}