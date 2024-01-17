#ifndef COMPUTERGAME_H
#define COMPUTERGAME_H

#include "Product.h"
#include <string>

using namespace std;

class ComputerGame: public Product{
    private:
        string gamePublisher;
    public:
        ComputerGame();
        ComputerGame(int id, const string& name, double price, const string& category, int volume, const string& pub);
        void display_product_info() override;

        // Setter
        void setPublisher(const string& pub);

        // Getter
        string getPublisher() const;
};


#endif // COMPUTERGAME_H