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
    fs::copy_file(sourceFileName, destFileName);

    //cout << ifstream(destFileName).rdbuf() << endl;

    cout << "File has been copied successfully!" << endl;
}

void loadStock(Stock* &stock, const string& filename) {
    std::fstream inFile;
    inFile.open(filename, std::ios::in | std::ios::out | std::ios::binary);
    if (!inFile) {
        std::cerr << "Error: Could not open file for reading.\n";
        return;
    }

    // Deserialize the stock
    if(inFile.read((char*)(&stock), sizeof(Stock))){
        cout << "Stock Viewer App:" << endl;
        cout << "--------------------------------" << endl;
        stock->read_stock_list();
    }
}


int main(int, char**){
    Stock* stock = new Stock();
    // Checking if save file exists in build directory
    ifstream file;
    file.open("state_stock.bin");
    if (!file){
        copyFile(fs::current_path().parent_path().parent_path().string()+"/task3a_b/state_stock.bin", "state_stock.bin");
    }else{
        cout << "File exists" << endl;
    }
    file.close();

    loadStock(stock, "state_stock.bin");

    return 0;
}
