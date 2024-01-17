#include "ComputerGame.h"
#include <iostream>

ComputerGame::ComputerGame(){}

ComputerGame::ComputerGame(int id, const string& name, double price, const string& category, int volume, const string& pub): Product(id, name, price, category, volume), gamePublisher(pub) {}

void ComputerGame::display_product_info(){
    Product::display_product_info();
    cout << "Game Publisher: " << getPublisher() << endl;
}

// Setter
void ComputerGame::setPublisher(const string& pub){
    gamePublisher = pub;
}

string ComputerGame::getPublisher() const{
    return gamePublisher;
}