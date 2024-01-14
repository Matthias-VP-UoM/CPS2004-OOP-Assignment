#include <iostream>
#include <fstream>
#include <vector>

#include "Stock.h"

#include "Book.h"
#include "Clothing.h"
#include "ComputerGame.h"
#include "Electronics.h"

using namespace std;


bool copyFile(const string& sourceFileName, const string& destFileName){
    ifstream sourceFile(sourceFileName, ios::binary);
    ofstream destFile(destFileName, ios::binary);

    if (!sourceFile.is_open() || !destFile.is_open()){
        cerr << "Error opening source file." << endl;
        return false;
    }

    destFile << sourceFile.rdbuf();

    if (!sourceFile.good() || !destFile.good()){
        cout << "Error occurred during file copy!" << endl;
        return false;
    }

    cout << "File has been copied successfully!" << endl;
    return true;
}

void loadStock(Stock* &stock, const string& filename) {
    std::ifstream inFile(filename, std::ios::binary);
    if (!inFile) {
        std::cerr << "Error: Could not open file for reading.\n";
        return;
    }

    // Deserialize the stock
    inFile.read(reinterpret_cast<char*>(&stock), sizeof(stock));
}


int main(int, char**){
    Stock* stock = new Stock();
    copyFile("", "");

    loadStock(stock, "");

    cout << "Stock Viewer App:" << endl;
    cout << "--------------------------------" << endl;
    stock->read_stock_list();


    return 0;
}
