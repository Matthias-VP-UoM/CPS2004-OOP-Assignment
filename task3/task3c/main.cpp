#include <iostream>
#include <fstream>
#include <filesystem>
#include <vector>

#include "Stock.h"

#include "Book.h"
#include "Clothing.h"
#include "ComputerGame.h"
#include "Electronics.h"

using namespace std;
namespace fs = std::filesystem;

void copyFile(const string& sourceFileName, const string& destFileName){
    ofstream(sourceFileName).put('x');
    fs::copy_file(sourceFileName, destFileName);

    cout << ifstream(destFileName).rdbuf() << endl;

    cout << "File has been copied successfully!" << endl;
}

void loadStock(Stock* &stock, const string& filename) {
    std::fstream inFile;
    inFile.open(filename, std::ios::out | std::ios::binary);
    if (!inFile) {
        std::cerr << "Error: Could not open file for reading.\n";
        return;
    }

    // Deserialize the stock
    if(inFile.read((char*)(&stock), sizeof(stock))){
        cout << "Stock Viewer App:" << endl;
        cout << "--------------------------------" << endl;
        stock->read_stock_list();
    }
}


int main(int, char**){
    Stock* stock = new Stock();
    copyFile(fs::current_path().parent_path().parent_path().string()+"/task3a_b/state_stock.bin", "state_stock.bin");

    loadStock(stock, "state_stock.bin");

    /* std::cout << "Current path is " << fs::current_path().parent_path().parent_path() << '\n';
    for (fs::path p : {"build/state_stock.bin", "/", "/var/tmp/."}){
        std::cout << "The parent path of " << p << " is " << p.parent_path() << '\n';
    } */


    return 0;
}
