#include "ComputerGame.h"
#include <iostream>

ComputerGame::ComputerGame(){}

ComputerGame::ComputerGame(int id, const string& name, double price, const string& category, int volume): Product(id, name, price, category, volume) {}