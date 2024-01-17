#include <iostream>
#include <fstream>
#include <vector>

#include "AppState.h"
#include "Stock.h"
#include "Packaging.h"

#include "Aircraft.h"
#include "Book.h"
#include "Clothing.h"
#include "ComputerGame.h"
#include "Electronics.h"
#include "SeaVessel.h"
#include "Truck.h"
#include "Shipment.h"
#include "Customer.h"


using namespace std;


Stock stock;
vector<Transport*> vehiclesList;
vector<Packaging> packagesList;
vector<Customer> customersList;
vector<Shipment> ordersList;


void AddObject(int choiceEntered){
    if (choiceEntered == 2){
        int id;
        double speed, cost;
        string category;
        int volume;
        char categoryChoice;

        cout << "Enter vehicle ID: ";
        cin >> id;
        cout << "Enter vehicle speed: ";
        cin >> speed;
        cout << "Enter vehicle transportation cost per km: ";
        cin >> cost;

        bool validChoice = false;

        do{
            cout << "A. Aircraft" << endl;
            cout << "B. SeaVessel" << endl;
            cout << "C. Truck" << endl;
            cout << "Enter choice of category: ";
            cin >> categoryChoice;

            switch(toupper(categoryChoice)){
                case 'A': category = "Aircraft"; validChoice = true; break;
                case 'B': category = "Sea Vessel"; validChoice = true; break;
                case 'C': category = "Truck"; validChoice = true; break;
                default: cout << "Invalid choice of category: " << endl;
            }
        }while(!validChoice);

        if (category.compare("Aircraft") == 0){
            Aircraft* ac = new Aircraft(id, "Aircraft", speed, cost);
            bool isFinished = false;
            do{
                string ans;
                cout << "Do you want to add in an airspace: ";
                cin >> ans;

                if (toupper(ans[0]) == 'Y'){
                    isFinished = false;
                    string air_space;
                    cout << "Enter an air space: ";
                    cin >> air_space;
                    ac->setAirSpace(air_space);
                    ac->addToList(air_space);
                }else if (toupper(ans[0]) == 'N'){
                    isFinished = true;
                }
            }while(!isFinished);
            vehiclesList.push_back(ac);
        }else if (category.compare("Sea Vessel") == 0){
            SeaVessel* sv = new SeaVessel(id, "Sea Vessel", speed, cost);
            bool isFinished = false;
            do{
                string ans;
                cout << "Do you want to add in a route: ";
                cin >> ans;

                if (toupper(ans[0]) == 'Y'){
                    isFinished = false;
                    string sea_route;
                    cout << "Enter a route: ";
                    cin >> sea_route;
                    sv->setRoute(sea_route);
                    sv->addToList(sea_route);
                }else if (toupper(ans[0]) == 'N'){
                    isFinished = true;
                }
            }while(!isFinished);
            vehiclesList.push_back(sv);
        }else if (category.compare("Truck") == 0){
            Truck* t = new Truck(id, "Truck", speed, cost);
            bool isFinished = false;
            do{
                string ans;
                cout << "Do you want to add in a motorway: ";
                cin >> ans;

                if (toupper(ans[0]) == 'Y'){
                    isFinished = false;
                    string motorway;
                    cout << "Enter a motorway: ";
                    cin >> motorway;
                    t->setMotorway(motorway);
                    t->addToList(motorway);
                }else if (toupper(ans[0]) == 'N'){
                    isFinished = true;
                }
            }while(!isFinished);
            vehiclesList.push_back(t);
        }
    }else if (choiceEntered == 1){
        int id;
        string name;
        double price;
        string category;
        int volume;
        char categoryChoice;
        int quantity;

        Product* prod;

        cout << "Enter product ID: ";
        cin >> id;
        cout << "Enter name of product: ";
        cin >> name;
        cout << "Enter product price: ";
        cin >> price;
        cout << "Enter product volume: ";
        cin >> volume;
        cout << "Enter quantity of product: ";
        cin >> quantity;

        bool validChoice = false;

        do{
            cout << "A. Book" << endl;
            cout << "B. Clothing" << endl;
            cout << "C. Computer Game" << endl;
            cout << "D. Electronics" << endl;
            cout << "Enter choice of category: ";
            cin >> categoryChoice;

            switch(toupper(categoryChoice)){
                case 'A': category = "Book"; validChoice = true; break;
                case 'B': category = "Clothing"; validChoice = true; break;
                case 'C': category = "Computer Game"; validChoice = true; break;
                case 'D': category = "Electronics"; validChoice = true; break;
                default: cout << "Invalid choice of category!" << endl;
            }
        }while(!validChoice);

        if (category.compare("Book") == 0){
            string iban, genre, author;
            cout << "Enter book IBAN: ";
            cin >> iban;
            cout << "Enter book genre: ";
            cin >> genre;
            cout << "Enter book author: ";
            cin >> author;
            Book* book = new Book(id, name, price, "Book", volume, iban, genre, author);

            StockItem item;
            item.setQuantity(quantity);
            item.setProduct(book);

            stock.addItem(item);
        }else if (category.compare("Clothing") == 0){
            double width, length;
            string size;
            cout << "Enter width: ";
            cin >> width;
            cout << "Enter length: ";
            cin >> length;
            cout << "Enter size: ";
            cin >> size;
            Clothing* cloth = new Clothing(id, name, price, "Category", volume, width, length, size);
            
            StockItem item;
            item.setQuantity(quantity);
            item.setProduct(cloth);

            stock.addItem(item);
        }else if (category.compare("Computer Game") == 0){
            string publisher;
            cout << "Enter game publisher: ";
            cin >> publisher;
            ComputerGame* cg = new ComputerGame(id, name, price, "Computer Game", volume, publisher);
            
            StockItem item;
            item.setQuantity(quantity);
            item.setProduct(cg);

            stock.addItem(item);
        }else if (category.compare("Electronics") == 0){
            Electronics* electronic = new Electronics(id, name, price, "Electronics", volume);
            
            StockItem item;
            item.setQuantity(quantity);
            item.setProduct(electronic);

            stock.addItem(item);
        }
    }else if (choiceEntered == 3){
        int id;
        double cost;
        string material;
        int capacity;
        char materialChoice;

        cout << "Enter packaging ID: ";
        cin >> id;
        cout << "Enter packaging cost: ";
        cin >> cost;
        cout << "Enter capacity: ";
        cin >> capacity;

        bool validChoice = false;

        do{
            cout << "A. Padded Envelope" << endl;
            cout << "B. Bubble Wrap" << endl;
            cout << "C. Cardboard Box" << endl;
            cout << "D. Wooded Box" << endl;
            cout << "E. Vacuum Packaging" << endl;
            cout << "Enter choice of category: ";
            cin >> materialChoice;

            switch(toupper(materialChoice)){
                case 'A': material = "Padded Envelope"; validChoice = true; break;
                case 'B': material = "Bubble Wrap"; validChoice = true; break;
                case 'C': material = "Cardboard"; validChoice = true; break;
                case 'D': material = "Wood"; validChoice = true; break;
                case 'E': material = "Vacuum"; validChoice = true; break;
                default: cout << "Invalid choice of material!" << endl;
            }
        }while(!validChoice);

        Packaging package(id, cost, material, capacity);
    }else if (choiceEntered == 4){
        int id;

        bool validID = false;

        do{
            cout << "Enter customer ID: " << endl;
            cin >> id;

            if (!customersList.empty()){
                for (int i = 0; i < customersList.size(); i++){
                    if (id == customersList.at(i).getID()){
                        validID = false;
                        cout << "Customer with same ID already exists! Please enter another one!" << endl;
                        break;
                    }
                }

                validID = true;
            }else{
                validID = true;
            }
        }while (!validID);

        string name, road, town, postCode;

        cout << "Enter name: ";
        cin >> name;
        cout << "Enter road: ";
        cin >> road;
        cout << "Enter town: ";
        cin >> town;
        cout << "Enter post code: ";
        cin >> postCode;

        Customer cust(id, name, road, town, postCode);
        customersList.push_back(cust);
    }else if (choiceEntered == 5){
        int orderID = 0;

        bool validID = false;

        do{
            cout << "Enter order ID: ";
            cin >> orderID;

            if (!ordersList.empty()){
                for (int i = 0; i < ordersList.size(); i++){
                    if (orderID == ordersList.at(i).getID()){
                        validID = false;
                        cout << "Order with same ID already exists! Please enter another one!" << endl;
                        break;
                    }
                }

                validID = true;
            }else{
                validID = true;
            }
        }while (!validID);

        for (int i = 0; i < customersList.size(); i++){
            customersList.at(i).display_customer_info();
        }

        int custID;
        cout << "Enter ID of customer that ordered: ";
        cin >> custID;

        int counter = 0, posOfCust = 0;
        bool isFound = false;
        do{
            if (customersList.at(counter).getID() == custID){
                isFound = true;
                posOfCust = counter;
            }else{
                isFound = false;
                counter++;
            }
        }while (!isFound);

        if (!isFound){
            cout << "ID of customer could not be found! Please try again!" << endl;
        }else{
            Customer custOrder = customersList.at(posOfCust);
            stock.read_stock_list();

            bool isValid = false;

            do{
                int prodIndex, orderQuantity;
                cout << "Enter index of product to order (from 0): ";
                cin >> prodIndex;

                cout << "Enter quantity to order for the product you chose: ";
                cin >> orderQuantity;

                StockItem i = stock.getItem(prodIndex);
                StockItem *p = &i;
                if (p != NULL && orderQuantity <= stock.calculate_product_quantity(i)){
                    StockItem itemOrdered(orderQuantity, i.getProduct());
                    Shipment ship(orderID, custOrder);
                    ship.addItemToList(itemOrdered);
                    stock.getItem(prodIndex).updateQuantity(-orderQuantity);
                    

                    /*if (stock.getItem(prodIndex).getQuantity() == 0){
                        stock.removeItem(stock.getItem(prodIndex));
                    }*/

                    isValid = true;
                    double distance;

                    cout << "Enter distance between customer and warehouse: ";
                    cin >> distance;

                    string transportType = ship.stateTransportType(distance);

                    for (int i = 0; i < vehiclesList.size(); i++){
                        if (transportType.compare("Truck") == 0){
                            if (vehiclesList.at(i)->getType().compare("Truck") == 0){
                                Truck* currentVehicle = (Truck*) vehiclesList.at(i);
                                int countVeh = 0;
                                bool isSearched = false;

                                while(!isSearched){
                                    string route = currentVehicle->getRoute(countVeh);
                                    if (route.find(custOrder.getTown()) != string::npos){
                                        ship.setTransport(currentVehicle);
                                        isSearched = true;
                                    }else{
                                        countVeh++;
                                    }
                                }
                                break;
                            }
                        }else if (transportType.compare("Sea Vessel") == 0){
                            if (vehiclesList.at(i)->getType().compare("SeaVessel") == 0){
                                SeaVessel* currentVehicle = (SeaVessel*) vehiclesList.at(i);
                                int countVeh = 0;
                                bool isSearched = false;

                                while(!isSearched){
                                    string route = currentVehicle->getRoute(countVeh);
                                    if (route.find(custOrder.getTown()) != string::npos){
                                        ship.setTransport(currentVehicle);
                                        isSearched = true;
                                    }else{
                                        countVeh++;
                                    }
                                }
                                break;
                            }
                        }else if (transportType.compare("Aircraft") == 0){
                            if (vehiclesList.at(i)->getType().compare("Aircraft") == 0){
                                Aircraft* currentVehicle = (Aircraft*) vehiclesList.at(i);
                                int countVeh = 0;
                                bool isSearched = false;

                                while(!isSearched){
                                    string route = currentVehicle->getRoute(countVeh);
                                    if (route.find(custOrder.getTown()) != string::npos){
                                        ship.setTransport(currentVehicle);
                                        isSearched = true;
                                    }else{
                                        countVeh++;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    ordersList.push_back(ship);

                    cout << "Item has been put to cart!" << endl;
                }else{
                    isValid = false;
                    cout << "Please try again!" << endl;
                }
            }while (!isValid);
        }
    }
}

void ReadObject(int choiceEntered){
    if (choiceEntered == 1){
        int readChoice = 0;
        bool validOption = false;

        if (!stock.checkIfEmptyList()){
            do{
                cout << "1. Read entire list of items in stock" << endl;
                cout << "2. Read one item in stock" << endl;
                cout << "Enter your choice here: ";
                cin >> readChoice;

                switch(readChoice){
                    case 1: stock.read_stock_list(); validOption = true; break;
                    case 2:{
                        int posToRead;
                        cout << "Enter position of item to search in array: ";
                        cin >> posToRead;
                        stock.print_item_details(posToRead);
                        validOption = true;
                    } break;
                    default: cout << "Invalid option! Please try again!" << endl;
                }
            }while (!validOption);
        }else{
            cout << "List of Products is empty!" << endl;
        }
    }else if (choiceEntered == 2){
        int readChoice = 0;
        bool validOption = false;

        if (!vehiclesList.empty()){
            do{
                cout << "1. Read entire list of vehicles" << endl;
                cout << "2. Read one vehicle" << endl;
                cout << "Enter your choice here: ";
                cin >> readChoice;

                switch(readChoice){
                    case 1: {
                        for (int i = 0; i < vehiclesList.size(); i++){
                            vehiclesList[i]->display_transport_details();
                        }
                        validOption = true;
                    } break;
                    case 2:{
                        int posToRead;
                        cout << "Enter position of vehicle to search in array: ";
                        cin >> posToRead;
                        try{
                            int count = 0;
                            do{
                                if (count == posToRead){
                                    vehiclesList[count]->display_transport_details();
                                }else{
                                    count++;
                                }
                            }while (count < vehiclesList.size());
                        }catch(const exception& e){
                            cout << "Vehicle does not exist in list!" << endl;
                        }
                        validOption = true;
                    } break;
                    default: cout << "Invalid option! Please try again!" << endl;
                }
            }while (!validOption);
        }else{
            cout << "List of Vehicles is empty!" << endl;
        }
    }else if (choiceEntered == 3){
        int readChoice = 0;
        bool validOption = false;

        if (!packagesList.empty()){
            do{
                cout << "1. Read entire list of packages" << endl;
                cout << "2. Read one package" << endl;
                cout << "Enter your choice here: ";
                cin >> readChoice;

                switch(readChoice){
                    case 1: {
                        for (int i = 0; i < packagesList.size(); i++){
                            packagesList[i].display_package_details();
                        }
                    } break;
                    case 2:{
                        int posToRead;
                        cout << "Enter position of package to search in array: ";
                        cin >> posToRead;
                        try{
                            int count = 0;
                            do{
                                if (count == posToRead){
                                    packagesList[count].display_package_details();
                                }else{
                                    count++;
                                }
                            }while (count < packagesList.size());
                        }catch(const exception& e){
                            cout << "Package does not exist in list!" << endl;
                        }
                    } break;
                    default: cout << "Invalid option! Please try again!" << endl;
                }
            }while (!validOption);
        }else{
            cout << "List of Packages is empty!" << endl;
        }
    }else if (choiceEntered == 4){
        if (!customersList.empty()){
            int readChoice = 0;
            bool validOption = false;

            do{
                cout << "1. Read entire list of customers" << endl;
                cout << "2. Read one customer" << endl;
                cout << "Enter your choice here: ";
                cin >> readChoice;

                switch(readChoice){
                    case 1: {
                        for (int i = 0; i < customersList.size(); i++){
                            cout << "Customer " << (i+1) << ":" << endl;
                            customersList.at(i).display_customer_info();
                            cout << "" << endl;
                            validOption = true;
                        }
                    } break;
                    case 2:{
                        int posToRead;
                        cout << "Enter position of customer to search in array: ";
                        cin >> posToRead;
                        try{
                            int count = 0;
                            do{
                                if (count == posToRead){
                                    customersList.at(count).display_customer_info();
                                    break;
                                }else{
                                    count++;
                                }
                            }while (count < customersList.size());
                        }catch(const exception& e){
                            cout << "Customer does not exist in list!" << endl;
                        }
                        validOption = true;
                    } break;
                    default: cout << "Invalid option! Please try again!" << endl;
                }
            }while (!validOption);
        }else{
            cout << "List of Customers is empty!" << endl;
        }
    }else if (choiceEntered == 5){
        if (!ordersList.empty()){
            int readChoice = 0;
            bool validOption = false;

            do{
                cout << "1. Read entire list of orders" << endl;
                cout << "2. Read one order" << endl;
                cout << "Enter your choice here: ";
                cin >> readChoice;

                switch(readChoice){
                    case 1: {
                        for (int i = 0; i < ordersList.size(); i++){
                            cout << "Order " << (i+1) << ":" << endl;
                            ordersList.at(i).display_shipment_details();
                            cout << "" << endl;
                            validOption = true;
                        }
                    } break;
                    case 2:{
                        int posToRead;
                        cout << "Enter position of order to search in array: ";
                        cin >> posToRead;
                        try{
                            int count = 0;
                            do{
                                if (count == posToRead){
                                    ordersList.at(count).display_shipment_details();
                                    break;
                                }else{
                                    count++;
                                }
                            }while (count < packagesList.size());
                        }catch(const exception& e){
                            cout << "Order does not exist in list!" << endl;
                        }
                        validOption = true;
                    } break;
                    default: cout << "Invalid option! Please try again!" << endl;
                }
            }while (!validOption);
        }else{
            cout << "List of Orders is empty!" << endl;
        }
    }
}

void UpdateObject(int choiceEntered){
    if (choiceEntered == 1){
        int posToUpdate;
        try{
            cout << "Enter position of item to update in array: ";
            cin >> posToUpdate;

            string name;
            double price;
            int volume, quantity;

            StockItem itemAtPos = stock.getItem(posToUpdate);
            Product* prodAtPos = itemAtPos.getProduct();

            cout << "Original Product Name (enter same value to keep same name): " << prodAtPos->getName() << endl;
            cout << "Enter new name of product: ";
            cin >> name;
            if (name.length() != 0){
                prodAtPos->setName(name);
            }
            

            cout << "Original Product Price: " << prodAtPos->getPrice() << endl;
            cout << "Enter new product price (enter same value to keep same price): ";
            cin >> price;
            if (to_string(price).length() != 0){
                prodAtPos->setPrice(price);
            }
            

            cout << "Original Product Volume: " << prodAtPos->getVolume() << endl;
            cout << "Enter new product volume (enter same value to keep same volume): ";
            cin >> volume;
            if (to_string(volume).length() != 0){
                prodAtPos->setVolume(volume);
            }

            cout << "Original Item Quantity: " << itemAtPos.getQuantity() << endl;
            cout << "Enter new item quantity (enter same value to keep same quantity): ";
            cin >> quantity;
            if (to_string(quantity).length() != 0){
                itemAtPos.setQuantity(quantity);
            }

            itemAtPos.setProduct(prodAtPos);
            stock.updateItem(itemAtPos, posToUpdate);

            if (prodAtPos->getCategory().compare("Book") == 0){
                Book* b = (Book*) prodAtPos;

                string IBAN;
                cout << "Original IBAN: " << b->getIBAN() << endl;
                cout << "Enter new IBAN (enter same value to keep same IBAN): ";
                cin >> IBAN;
                if (IBAN.length() != 0){
                    b->setIBAN(IBAN);
                }

                string genre;
                cout << "Original Genre: " << b->getGenre() << endl;
                cout << "Enter new genre (enter same value to keep same genre): ";
                cin >> genre;
                if (genre.length() != 0){
                    b->setGenre(genre);
                }

                string author;
                cout << "Original Author: " << b->getAuthor() << endl;
                cout << "Enter new author (enter same value to keep same author): ";
                cin >> author;
                if (author.length() != 0){
                    b->setAuthor(author);
                }

                itemAtPos.setProduct(b);
                stock.updateItem(itemAtPos, posToUpdate);
            }else if (prodAtPos->getCategory().compare("Clothing") == 0){
                Clothing* c = (Clothing*) prodAtPos;

                double width;
                cout << "Original Width: " << c->getWidth() << endl;
                cout << "Enter new width (enter same value to keep same width): ";
                cin >> width;
                string widthString = to_string(width);
                if (widthString.length() != 0){
                    c->setWidth(width);
                }

                double length;
                cout << "Original Length: " << c->getLength() << endl;
                cout << "Enter new length (enter same value to keep same length): ";
                cin >> length;
                string lengthString = to_string(length);
                if (lengthString.length() != 0){
                    c->setLength(length);
                }

                string size;
                cout << "Original Size: " << c->getSize() << endl;
                cout << "Enter new size (enter same value to keep same size): ";
                cin >> size;
                if (size.length() != 0){
                    c->setSize(size);
                }

                itemAtPos.setProduct(c);
                stock.updateItem(itemAtPos, posToUpdate);
            }else if (prodAtPos->getCategory().compare("Computer Game") == 0){
                ComputerGame* cg = (ComputerGame*) prodAtPos;
                string publisher;
                cout << "Original Game Publisher: " << cg->getPublisher() << endl;
                cout << "Enter new publisher (enter same value to keep same size): ";
                cin >> publisher;
                if (publisher.length() != 0){
                    cg->setPublisher(publisher);
                }
            }
        }catch(const exception& e){
            cout << "Item not found!" << endl;
        }

    }else if (choiceEntered == 2){
        int posToUpdate;
        try{
            cout << "Enter position of vehicle to update in array: ";
            cin >> posToUpdate;

            string route;
            double speed, cost;

            Aircraft* acAtPos = new Aircraft();
            SeaVessel* svAtPos = new SeaVessel();
            Truck* tAtPos = new Truck();

            if(vehiclesList.at(posToUpdate)->getType().compare("Aircraft") == 0){
                cout << "Original Vehicle Transportation Speed: " << vehiclesList.at(posToUpdate)->getSpeed() << endl;
                cout << "Enter new vehicle speed (enter same value to keep same speed): ";
                cin >> speed;
                string speedString = to_string(speed);
                if (speedString.length() != 0){
                    vehiclesList.at(posToUpdate)->setSpeed(speed);
                }
                

                cout << "Original Vehicle Transportation Cost/km: " << vehiclesList.at(posToUpdate)->getCost() << endl;
                cout << "Enter new vehicle cost (enter same value to keep same cost): ";
                cin >> cost;
                string costString = to_string(cost);
                if (costString.length() != 0){
                    vehiclesList.at(posToUpdate)->setCost(cost);
                }
            }else if(vehiclesList.at(posToUpdate)->getType().compare("SeaVessel") == 0){
                cout << "Original Vehicle Transportation Speed: " << vehiclesList.at(posToUpdate)->getSpeed() << endl;
                cout << "Enter new vehicle speed (enter same value to keep same speed): ";
                cin >> speed;
                string speedString = to_string(speed);
                if (speedString.length() != 0){
                    vehiclesList.at(posToUpdate)->setSpeed(speed);
                }
                

                cout << "Original Vehicle Transportation Cost/km: " << vehiclesList.at(posToUpdate)->getCost() << endl;
                cout << "Enter new vehicle cost (enter same value to keep same cost): ";
                cin >> cost;
                string costString = to_string(cost);
                if (costString.length() != 0){
                    vehiclesList.at(posToUpdate)->setCost(cost);
                }
            }else if(vehiclesList.at(posToUpdate)->getType().compare("SeaVessel") == 0){
                cout << "Original Vehicle Transportation Speed: " << vehiclesList.at(posToUpdate)->getSpeed() << endl;
                cout << "Enter new vehicle speed (enter same value to keep same speed): ";
                cin >> speed;
                string speedString = to_string(speed);
                if (speedString.length() != 0){
                    vehiclesList.at(posToUpdate)->setSpeed(speed);
                }
                

                cout << "Original Vehicle Transportation Cost/km: " << vehiclesList.at(posToUpdate)->getCost() << endl;
                cout << "Enter new vehicle cost (enter same value to keep same cost): ";
                cin >> cost;
                string costString = to_string(cost);
                if (costString.length() != 0){
                    vehiclesList.at(posToUpdate)->setCost(cost);
                }
            }

            int readChoice = 0;
            bool validOption = false;

            do{
                cout << "1. Update existing air space/route/motorway" << endl;
                cout << "2. Add air space/route/motorway" << endl;
                cout << "Enter your choice here: ";
                cin >> readChoice;

                switch(readChoice){
                    case 1: {
                        if(vehiclesList.at(posToUpdate)->getType().compare("Aircraft") == 0){
                            acAtPos = (Aircraft*) vehiclesList.at(posToUpdate);
                            cout << "Enter new air space (enter same value to keep same air space): " << endl;
                            cin >> route;
                            acAtPos->updateAirSpaceinList(route, posToUpdate);
                            vehiclesList.at(posToUpdate) = acAtPos;
                        }else if(vehiclesList.at(posToUpdate)->getType().compare("Sea Vessel") == 0){
                            svAtPos = (SeaVessel*) vehiclesList.at(posToUpdate);
                            cout << "Enter new route (enter same value to keep same route): ";
                            cin >> route;
                            svAtPos->updateRouteinList(route, posToUpdate);
                            vehiclesList.at(posToUpdate) = svAtPos;
                        }else if(vehiclesList.at(posToUpdate)->getType().compare("Truck") == 0){
                            tAtPos = (Truck*) vehiclesList.at(posToUpdate);
                            cout << "Enter new motorway (enter same value to keep same motorway): ";
                            cin >> route;
                            tAtPos->updateMotorwayinList(route, posToUpdate);
                            vehiclesList.at(posToUpdate) = tAtPos;
                        }
                        validOption = true;
                    } break;
                    case 2:{
                        if(vehiclesList.at(posToUpdate)->getType().compare("Aircraft") == 0){
                            acAtPos = (Aircraft*) vehiclesList.at(posToUpdate);
                            cout << "Enter new air space: ";
                            cin >> route;
                            acAtPos->setAirSpace(route);
                            acAtPos->addToList(route);
                            vehiclesList.at(posToUpdate) = acAtPos;
                        }else if(vehiclesList.at(posToUpdate)->getType().compare("Sea Vessel") == 0){
                            svAtPos = (SeaVessel*) vehiclesList.at(posToUpdate);
                            cout << "Enter new route (enter same value to keep same route): ";
                            cin >> route;
                            svAtPos->setRoute(route);
                            svAtPos->addToList(route);
                            vehiclesList.at(posToUpdate) = svAtPos;
                        }else if(vehiclesList.at(posToUpdate)->getType().compare("Truck") == 0){
                            tAtPos = (Truck*) vehiclesList.at(posToUpdate);
                            cout << "Enter new motorway (enter same value to keep same motorway): ";
                            cin >> route;
                            tAtPos->setMotorway(route);
                            tAtPos->addToList(route);
                            vehiclesList.at(posToUpdate) = tAtPos;
                        }
                        validOption = true;
                    } break;
                    default: cout << "Invalid option! Please try again!" << endl;
                }
            }while (!validOption);
        }catch (const exception& e){
            cout << "Vehicle not found!" << endl;
        }
        
    }else if (choiceEntered == 3){
        int posToUpdate;
        try{
            cout << "Enter position of package to update in array: ";
            cin >> posToUpdate;

            Packaging packageAtPos = packagesList.at(posToUpdate);

            string material;
            cout << "Original Packaging Material: " << packageAtPos.getMaterial() << endl;
            cout << ("Enter new packaging material (enter same value to keep same material): ");
            cin >> material;
            if (material.length() != 0){
                packageAtPos.setMaterial(material);
            }

            double cost;
            cout << "Original Packaging Cost: " << packageAtPos.getCost() << endl;
            cout << "Enter new packaging cost (enter same value to keep same cost): ";
            cin >> cost;
            string costString = to_string(cost);
            if (costString.length() != 0){
                packageAtPos.setCost(cost);
            }

            int capacity;
            cout << "Original Packaging Capacity: " << packageAtPos.getCapacity() << endl;
            cout << "Enter new product capacity (enter same value to keep same capacity): ";
            cin >> capacity;
            string capacityString = to_string(capacity);
            if (capacityString.length() != 0){
                packageAtPos.setCapacity(capacity);
            }
            packagesList.at(posToUpdate) = packageAtPos;
        }catch (const exception& e){
            cout << "Package not found!" << endl;
        }
    }else if (choiceEntered == 4){
        int posToUpdate;
        try{
            cout << "Enter position of customer to update in array: ";
            cin >> posToUpdate;

            Customer custAtPos = customersList.at(posToUpdate);
            
            string name;
            cout << "Original Customer Name: " << custAtPos.getName() << endl;
            cout << "Enter new name (enter same value to keep same name): ";
            cin >> name;
            if (name.length() != 0){
                custAtPos.setName(name);
            }

            string road;
            cout << "Original Customer Road: " << custAtPos.getRoad() << endl;
            cout << "Enter new road (enter same value to keep same cost): ";
            cin >> road;
            if (road.length() != 0){
                custAtPos.setRoad(road);
            }

            string town;
            cout << "Original Customer Town: " << custAtPos.getTown() << endl;
            cout << "Enter new town (enter same value to keep same cost): ";
            cin >> town;
            if (town.length() != 0){
                custAtPos.setTown(town);
            }

            string postCode;

            cout << "Original Customer Post Code: " << custAtPos.getPostCode() << endl;
            cout << "Enter new post code (enter same value to keep same cost): ";
            cin >> postCode;
            if (postCode.length() != 0){
                custAtPos.setPostCode(postCode);
            }
            customersList.at(posToUpdate) = custAtPos;
        }catch(const exception& e){
            cout << "Customer not found!" << endl;
        }
    }else if (choiceEntered == 5){
        int posToUpdate;
        cout << "Enter position of order to update in array: ";
        cin >> posToUpdate;

        int orderID = ordersList.at(posToUpdate).getID();

        cout << "Original Customer: " << ordersList.at(posToUpdate).getCustomer().getName() << endl;

        for (int i = 0; i < customersList.size(); i++){
            customersList.at(i).display_customer_info();
        }

        int custID;
        cout << "Enter new ID of customer that ordered (enter same value to keep current customer): ";
        cin >> custID;

        int counter = 0, posOfCust = 0;
        bool isFound = false;
        do{
            if (customersList.at(counter).getID() == custID){
                isFound = true;
                posOfCust = counter;
            }else{
                isFound = false;
                counter++;
            }
        }while (!isFound);

        if (!isFound){
            cout << "ID of customer could not be found! Please try again!" << endl;
        }else{
            Customer custOrder = customersList.at(posOfCust);
            stock.read_stock_list();

            bool isValid = false;

            do{
                cout << "Current List of Items Ordered: " << endl;
                ordersList.at(posToUpdate).print_items_list();

                int selection;
                bool validSelection = false;

                do{
                    cout << "1. Update existing item in list" << endl;
                    cout << "2. Add item to list" << endl;
                    cout << "3. Remove item from list" << endl;
                    cout << "Enter choice here: " << endl;
                    cin >> selection;

                    switch (selection) {
                        case 1:{
                            validSelection = true;
                            
                            int prodIndex;
                            cout << "Enter index of product to update (from 0): ";
                            cin >> prodIndex;

                            StockItem itemChosen = ordersList.at(posToUpdate).getItem(prodIndex);

                            int originalQuantity = itemChosen.getQuantity();

                            int orderQuantity;
                            cout << "Original Product Quantity: "+originalQuantity<< endl;
                            cout << "Enter new quantity (enter same value to keep quantity): ";
                            cin >> orderQuantity;

                            StockItem *p = &itemChosen;

                            if (p != NULL && orderQuantity <= stock.calculate_product_quantity(stock.getItem(prodIndex))){
                                StockItem itemOrdered(orderQuantity, itemChosen.getProduct());
                                Shipment ship(orderID, custOrder);
                                ship.updateItemInList(itemOrdered, posToUpdate);
                                if (orderQuantity < originalQuantity || orderQuantity > originalQuantity)
                                    stock.getItem(prodIndex).updateQuantity(orderQuantity - originalQuantity);
                                

                                /*if (stock.getItem(prodIndex).getQuantity() == 0){
                                    stock.removeItem(stock.getItem(prodIndex));
                                }*/

                                isValid = true;

                                ordersList.at(posToUpdate) = ship;

                                cout << "Item has been updated!" << endl;
                            }else{
                                isValid = false;
                                cout << "Please try again!" << endl;
                            }
                        } break;
                        case 2: {
                            validSelection = true;

                            int prodIndex;
                            cout << "Enter index of product to order (from 0): ";
                            cin >> prodIndex;

                            int orderQuantity;
                            cout << "Enter quantity to order for the product you chose: ";
                            cin >> orderQuantity;

                            StockItem i = stock.getItem(prodIndex);
                            StockItem *p2 = &i;

                            if (p2 != NULL && orderQuantity <= stock.calculate_product_quantity(i)){
                                StockItem itemOrdered(orderQuantity, i.getProduct());
                                Shipment ship(orderID, custOrder);
                                ship.addItemToList(itemOrdered);
                                stock.getItem(prodIndex).updateQuantity(-orderQuantity);
                                

                                /*if (stock.getItem(prodIndex).getQuantity() == 0){
                                    stock.removeItem(stock.getItem(prodIndex));
                                }*/

                                isValid = true;

                                double distance;
                                cout << "Enter distance between customer and warehouse: ";
                                cin >> distance;

                                string transportType = ship.stateTransportType(distance);

                                for (int i = 0; i < vehiclesList.size(); i++){
                                    if (transportType.compare("Truck") == 0){
                                        if (vehiclesList.at(posToUpdate)->getType().compare("Truck") == 0){
                                            Truck* currentVehicle = (Truck*) vehiclesList.at(i);
                                            int countVeh = 0;
                                            bool isSearched = false;

                                            while(!isSearched){
                                                string route = currentVehicle->getRoute(countVeh);
                                                if (route.find(custOrder.getTown()) != string::npos){
                                                    ship.setTransport(currentVehicle);
                                                    isSearched = true;
                                                }else{
                                                    countVeh++;
                                                }
                                            }
                                            break;
                                        }
                                    }else if (transportType.compare("Sea Vessel") == 0){
                                        if (vehiclesList.at(posToUpdate)->getType().compare("Sea Vessel") == 0){
                                            SeaVessel* currentVehicle = (SeaVessel*) vehiclesList.at(i);
                                            int countVeh = 0;
                                            bool isSearched = false;

                                            while(!isSearched){
                                                string route = currentVehicle->getRoute(countVeh);
                                                if (route.find(custOrder.getTown()) != string::npos){
                                                    ship.setTransport(currentVehicle);
                                                    isSearched = true;
                                                }else{
                                                    countVeh++;
                                                }
                                            }
                                            break;
                                        }
                                    }else if (transportType.compare("Aircraft") == 0){
                                        if (vehiclesList.at(posToUpdate)->getType().compare("Aircraft") == 0){
                                            Aircraft* currentVehicle = (Aircraft*) vehiclesList.at(i);
                                            int countVeh = 0;
                                            bool isSearched = false;

                                            while(!isSearched){
                                                string route = currentVehicle->getRoute(countVeh);
                                                if (route.find(custOrder.getTown()) != string::npos){
                                                    ship.setTransport(currentVehicle);
                                                    isSearched = true;
                                                }else{
                                                    countVeh++;
                                                }
                                            }
                                            break;
                                        }
                                    }
                                }
                                ordersList.push_back(ship);

                                cout << "Item has been put to cart!" << endl;;
                            }else{
                                isValid = false;
                                cout << "Please try again!" << endl;
                            }
                        } break;
                        case 3: {
                            int posToRemove;
                            cout << "Enter position of item to remove (from 0): ";
                            cin >> posToRemove;
                            ordersList.at(posToUpdate).removeItemFromlist(posToRemove);
                            validSelection = true;
                        }
                        default: cout << "Invalid option!" << endl; validSelection = false; break;
                    }
                }while (!validSelection);
            }while (!isValid);
        }
    }
}

void DeleteObject(int choiceEntered){
    int posToRemove;
    if (choiceEntered == 1){
        cout << "Enter position of item to remove (from 0): ";
        cin >> posToRemove;
        stock.removeItem(posToRemove);
    }else if (choiceEntered == 2){
        cout << "Enter position of vehicle to remove (from 0): ";
        cin >> posToRemove;
        vehiclesList.erase(vehiclesList.begin() + posToRemove);
    }else if (choiceEntered == 3){
        cout << "Enter position of package to remove (from 0): ";
        cin >> posToRemove;
        packagesList.erase(packagesList.begin() + posToRemove);
    }else if (choiceEntered == 4){
        cout << "Enter position of customer to remove (from 0): ";
        cin >> posToRemove;
        customersList.erase(customersList.begin() + posToRemove);
    }else if (choiceEntered == 5){
        cout << "Enter position of order to remove (from 0): ";
        cin >> posToRemove;
        ordersList.erase(ordersList.begin() + posToRemove);
    }
}

// Method for saving (serialization)
void save2(const string& filename) {
    std::ofstream outFile(filename.c_str(), std::ios::out | std::ios::binary | std::ios::app);

    if (!outFile.is_open()) {
        std::cerr << "Error: Could not open file for writing." << endl;
    }else{
        // Serialize the stock
        outFile.write((char*)(&stock), sizeof(Stock));

        // Serialize the vehiclesList
        size_t transportSize = vehiclesList.size();
        outFile.write(reinterpret_cast<const char*>(&transportSize), sizeof(transportSize));
        outFile.write(reinterpret_cast<const char*>(vehiclesList.data()), transportSize * sizeof(Transport));

        // Serialize the packagesList
        size_t packageSize = packagesList.size();
        outFile.write(reinterpret_cast<const char*>(&packageSize), sizeof(packageSize));
        outFile.write(reinterpret_cast<const char*>(packagesList.data()), packageSize * sizeof(Packaging));

        // Serialize the customersList
        size_t customerSize = customersList.size();
        outFile.write(reinterpret_cast<const char*>(&customerSize), sizeof(customerSize));
        outFile.write(reinterpret_cast<const char*>(customersList.data()), customerSize * sizeof(Customer));

        // Serialize the ordersList
        size_t orderSize = ordersList.size();
        outFile.write(reinterpret_cast<const char*>(&orderSize), sizeof(orderSize));
        outFile.write(reinterpret_cast<const char*>(ordersList.data()), orderSize * sizeof(Shipment));

        outFile.close();
    }
}

// Method for loading (deserialization)
void load2(const string& filename) {
    std::ifstream inFile(filename, std::ios::in | std::ios::binary | std::ios::app);

    if (!inFile.is_open()) {
        std::cerr << "Error: Could not open file for reading." << endl;
    }else{
        // Deserialize the stock
        Stock loadedStock;
        inFile.read(reinterpret_cast<char*>(&loadedStock), sizeof(Stock));
        stock = loadedStock;

        // Deserialize the transportList
        size_t transportSize;
        inFile.read(reinterpret_cast<char*>(&transportSize), sizeof(transportSize));
        vehiclesList.resize(transportSize);
        inFile.read(reinterpret_cast<char*>(vehiclesList.data()), transportSize * sizeof(Transport));

        // Deserialize the packagesList
        size_t packageSize;
        inFile.read(reinterpret_cast<char*>(&packageSize), sizeof(packageSize));
        packagesList.resize(packageSize);
        inFile.read(reinterpret_cast<char*>(packagesList.data()), packageSize * sizeof(Packaging));

        // Deserialize the customersList
        size_t customerSize;
        inFile.read(reinterpret_cast<char*>(&customerSize), sizeof(customerSize));
        customersList.resize(customerSize);
        inFile.read(reinterpret_cast<char*>(customersList.data()), customerSize * sizeof(Customer));

        // Deserialize the ordersList
        size_t orderSize;
        inFile.read(reinterpret_cast<char*>(&orderSize), sizeof(orderSize));
        ordersList.resize(orderSize);
        inFile.read(reinterpret_cast<char*>(ordersList.data()), orderSize * sizeof(Shipment));

        inFile.close();
    }
    //delete stock;
    //stock = loadedStock;
}

void AccessSubMenu(string word, int choiceEntered){
    char subChoice = 'z';

    do{
        cout << "a. Create " << word << endl;
        cout << "b. Read " << word << endl;
        if (choiceEntered == 1){
            cout << "c. Update " << word << " (Product id and category will not be updated)" << endl;
        }else{
            cout << "c. Update " << word << endl;
        }
        cout << "d. Delete " << word << endl;
        cout << "e. Exit Submenu" << endl;
        cout << "Enter your choice here: ";
        cin >> subChoice;

        switch(subChoice){
            case 'a': AddObject(choiceEntered); break;
            case 'b': ReadObject(choiceEntered); break;
            case 'c': UpdateObject(choiceEntered);break;
            case 'd': DeleteObject(choiceEntered); break;
            case 'e': cout << "Exitting submenu..." << endl; break;
            default: cout << "Invalid choice!" << endl;
        }

    }while (tolower(subChoice) != 'e');
}

void ChooseShipmentToDispatch(){
    for (int i = 0; i < ordersList.size(); i++){
        ordersList.at(i).display_shipment_details();
    }

    int idToDispatch;
    cout << "Enter ID of shipment to dispatch: ";
    cin >> idToDispatch;

    bool foundShipment = false;
    int posOfShipment = 0;
    for (int i = 0; i < ordersList.size(); i++){
        if (ordersList.at(i).getID() == idToDispatch){
            foundShipment = true;
            posOfShipment = i;
            break;
        }else{
            foundShipment = false;
        }
    }

    if (!foundShipment){
        cout << "Shipment cannot be found! Please try again!" << endl;
    }else{
        cout << "Shipment found!" << endl;;

        int day, monthNum, year;
        cout << "Enter day of month of dispatch: ";
        cin >> day;

        cout << "Enter month number of dispatch: ";
        cin >> monthNum;

        cout << "Enter year of dispatch: ";
        cin >> year;

        string date = to_string(day)+"/"+to_string(monthNum)+"/"+to_string(year);

        double totalCost = ordersList.at(posOfShipment).calculate_total_cost(monthNum);
        cout << "Total Cost: " << totalCost << endl;

        string dispatchConfirmation;
        cout << "Confirm Dispatch of Shipment (Yes or No): ";
        cin >> dispatchConfirmation;

        if (toupper(dispatchConfirmation[0]) == 'Y'){
            ordersList.at(posOfShipment).dispatch(date);
            cout << "Order has been dispatched!" << endl;
            ordersList.erase(ordersList.begin() + posOfShipment);
        }else if (toupper(dispatchConfirmation[0]) == 'N'){
            cout << "Process has been cancelled!" << endl;
        }
    }
}


int main(int, char**){
    AppState as;

    string keyWord;
    int choice = 0;

    do{
        cout << "Logistics Management Application" << endl;
        cout << "----------------------------------" << endl;
        cout << "1. Stock Management" << endl;
        cout << "2. Transport Management" << endl;
        cout << "3. Packaging Management" << endl;
        cout << "4. Customer Management" << endl;
        cout << "5. Order Management" << endl;
        cout << "6. Shipment Management" << endl;
        cout << "7. Save Application State" << endl;
        cout << "8. Load Application State" << endl;
        cout << "9. Exit Application" << endl;
        cout << "Enter your choice here: ";
        cin >> choice;

        switch(choice){
            case 1: keyWord = "Product"; AccessSubMenu(keyWord, 1); break;
            case 2: keyWord = "Transport"; AccessSubMenu(keyWord, 2); break;
            case 3: keyWord = "Packaging"; AccessSubMenu(keyWord, 3); break;
            case 4: keyWord = "Customer"; AccessSubMenu(keyWord, 4); break;
            case 5: keyWord = "Order"; AccessSubMenu(keyWord, 5); break;
            case 6: keyWord = "Shipment"; ChooseShipmentToDispatch(); break;
            case 7: save2("application_state.ser"); cout << "State is saved successfully!" << endl; break;
            case 8: load2("application_state.ser"); cout << "State is loaded successfully!" << endl; break;
            case 9: cout << "Thanks for using the program!" << endl; break;
            default: cout << "Invalid choice!" << endl;
        }
    }while(choice != 9);

    //delete stock;

    /*for (int i = 0; i < vehiclesList.size(); i++){
        delete vehiclesList[i];
    }*/
    
}
