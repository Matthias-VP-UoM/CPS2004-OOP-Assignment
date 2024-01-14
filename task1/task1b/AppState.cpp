#include "AppState.h"

// Setters
void AppState::setStock(Stock* stock) {
    stockState = stock;
}

void AppState::setTransportList(Transport** transports) {
    for (int i = 0; i < 150; i++){
        transportList[i] = transports[i];
    }
}

void AppState::setPackagingList(Packaging** packages) {
    for (int i = 0; i < 300; i++){
        packagingList[i] = packages[i];
    }
} 


// Getters
Stock *AppState::getStock(){
    return stockState;
}

Transport **AppState::getTransportList() {
    return transportList;
}

Packaging **AppState::getPackagingList(){
    return packagingList;
}


void AppState::save(const std::string& filename) const {
    std::ofstream outFile(filename, std::ios::binary);
    if (!outFile) {
        std::cerr << "Error: Could not open file for writing.\n";
        return;
    }

    outFile.write(reinterpret_cast<const char*>(&stockState), sizeof(stockState));

    size_t transportSize = 150;
    outFile.write(reinterpret_cast<const char*>(&transportSize), sizeof(transportSize));
    outFile.write(reinterpret_cast<const char*>(transportList), transportSize * sizeof(Transport));

    size_t packagingSize = 300;
    outFile.write(reinterpret_cast<const char*>(&packagingSize), sizeof(packagingSize));
    outFile.write(reinterpret_cast<const char*>(packagingList), packagingSize * sizeof(Packaging));
        

    // Serialize your data structures here
    // For example, write each member variable to the file
    // outFile.write(reinterpret_cast<const char*>(&member_variable), sizeof(member_variable));
}

void AppState::load(const std::string& filename) {
    std::ifstream inFile(filename, std::ios::binary);
    if (!inFile) {
        cerr << "Error: Could not open file for reading.\n";
        return;
    }

    inFile.read(reinterpret_cast<char*>(&stockState), sizeof(stockState));

    size_t transportSize;
    inFile.read(reinterpret_cast<char*>(&transportSize), sizeof(transportSize));
    //transportList.resize(transportSize);

    if (transportSize < 150) {
        for (int i = transportSize; i < 150; ++i) {
            delete transportList[i];  // Free memory for removed elements
        }
    }

    inFile.read(reinterpret_cast<char*>(transportList), transportSize * sizeof(Transport));

    size_t packagingSize;
    inFile.read(reinterpret_cast<char*>(&packagingSize), sizeof(packagingSize));
    //transportList.resize(transportSize);

    if (packagingSize < 300) {
        for (int i = packagingSize; i < 300; ++i) {
            delete packagingList[i];  // Free memory for removed elements
        }
    }

    inFile.read(reinterpret_cast<char*>(packagingList), packagingSize * sizeof(Packaging));


    // Deserialize your data structures here
    // For example, read each member variable from the file
    // inFile.read(reinterpret_cast<char*>(&member_variable), sizeof(member_variable));
}