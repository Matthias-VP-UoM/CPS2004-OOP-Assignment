#ifndef COMPUTERGAME_H
#define COMPUTERGAME_H

#include "Product.h"
#include <string>

using namespace std;

class ComputerGame: public Product{
    private:
    
    public:
        ComputerGame();
        ComputerGame(int id, const string& name, double price, const string& category, int volume);
};


#endif // COMPUTERGAME_H