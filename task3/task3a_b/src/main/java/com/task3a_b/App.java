package com.task3a_b;

import java.util.*;

import com.task3a_b.LogisticsManagement.PB_Aircraft;
import com.task3a_b.LogisticsManagement.PB_Book;
import com.task3a_b.LogisticsManagement.PB_Clothing;
import com.task3a_b.LogisticsManagement.PB_ComputerGame;
import com.task3a_b.LogisticsManagement.PB_Customer;
import com.task3a_b.LogisticsManagement.PB_CustomersList;
import com.task3a_b.LogisticsManagement.PB_Electronics;
import com.task3a_b.LogisticsManagement.PB_OrdersList;
import com.task3a_b.LogisticsManagement.PB_Packaging;
import com.task3a_b.LogisticsManagement.PB_PackagingList;
import com.task3a_b.LogisticsManagement.PB_Product;
import com.task3a_b.LogisticsManagement.PB_SeaVessel;
import com.task3a_b.LogisticsManagement.PB_Shipment;
import com.task3a_b.LogisticsManagement.PB_Stock;
import com.task3a_b.LogisticsManagement.PB_StockItem;
import com.task3a_b.LogisticsManagement.PB_Transport;
import com.task3a_b.LogisticsManagement.PB_Truck;
import com.task3a_b.LogisticsManagement.PB_VehiclesList;

import java.io.*;

public class App implements Serializable
{
    static Scanner sc = new Scanner(System.in);

    static Stock stock = new Stock();
    static ArrayList<Transport> vehiclesList = new ArrayList<Transport>();
    static ArrayList<Packaging> packagesList = new ArrayList<Packaging>();
    static ArrayList<Customer> customersList = new ArrayList<Customer>();
    static ArrayList<Shipment> ordersList = new ArrayList<Shipment>();

    static final String STOCK_FILE = "state_stock.bin";
    static final String PACKAGING_FILE = "state_packaging.bin";
    static final String VEHICLES_FILE = "state_vehicles.bin";
    static final String CUSTOMERS_FILE = "state_customers.bin";
    static final String ORDERS_FILE = "state_orders.bin";

    public static void main( String[] args )
    {
        LogisticsFacade logFacade = new LogisticsFacade();

        int choice = 0;
        boolean success = false;
        do{
            try{
                System.out.println("1. Stock Management");
                System.out.println("2. Transport Management");
                System.out.println("3. Packaging Management");
                System.out.println("4. Customer Management");
                System.out.println("5. Order Management");
                System.out.println("6. Dispatch Shipment");
                System.out.println("7. Save Application State");
                System.out.println("8. Restore Application State");
                System.out.println("9. Exit Application");
                System.out.print("Enter your choice here: ");
                choice = sc.nextInt();

                switch(choice){
                    case 1: AccessSubmenu("Product", 1); break;
                    case 2: AccessSubmenu("Transport", 2); break;
                    case 3: AccessSubmenu("Packaging", 3); break;
                    case 4: AccessSubmenu("Customer", 4); break;
                    case 5: AccessSubmenu("Order", 5); break;
                    case 6: ChooseShipmentToDispatch(); break;
                    case 7: logFacade.serialize(); System.out.println("State is saved successfully!"); break;
                    case 8: logFacade.deserialize(); System.out.println("State is restored successfully!"); break;
                    case 9: System.out.println("Thanks for using the program!"); break;
                    default: System.out.println("Invalid choice!");
                }
                success = true;
            }catch(InputMismatchException e){
                System.out.println("Enter a number!");
                success = false;
                sc.next();
            }
        }while (choice != 9 || !success);
    }

    public static void AccessSubmenu(String word, int choiceEntered){
        char subChoice = 'z';

        do{
            System.out.println("a. Add "+ word);
            System.out.println("b. Read "+ word);
            if (choiceEntered == 1 || choiceEntered == 2){
                System.out.println("c. Update " + word + " (Id and category/type will not be updated)");
            }else{
                System.out.println("c. Update "+word);
            }
            System.out.println("d. Delete "+word);
            System.out.println("e. Exit");
            System.out.print("Enter your choice here: ");
            subChoice = sc.next().charAt(0);

            switch(subChoice){
                case 'a': AddObject(choiceEntered); break;
                case 'b': ReadObject(choiceEntered); break;
                case 'c': UpdateObject(choiceEntered); break;
                case 'd': DeleteObject(choiceEntered); break;
                case 'e': System.out.println("Exitting submenu..."); break;
                default: System.out.println("Invalid choice!");
            }
        }while (subChoice != 'e');
    }

    /*public static void AccessShipmentMenu(){
        char subChoice = 'z';

        do{
            System.out.println("a. Dispatch Shipment");
            System.out.println("b. Apply Global Discount to Shipment");
            System.out.println("c. Adjust Delivery Plan for Road Closure");
            System.out.println("d. Do both above operations at once");
            System.out.println("e. Exit");
            System.out.print("Enter your choice here: ");
            subChoice = sc.next().charAt(0);

            switch(subChoice){
                case 'a': ChooseShipmentToDispatch(); break;
                case 'b': ChooseShipmentForDiscount(); break;
                case 'c': AdjustShipmentDelivery(); break;
                case 'd': ApplyDiscountAndAdjustDelivery(); break;
                case 'e': System.out.println("Exitting submenu..."); break;
                default: System.out.println("Invalid choice!");
            }
        }while (subChoice != 'e');
    }*/

    public static void AddObject(int choiceEntered){
        if (choiceEntered == 2){
            int id;
            String category = null;
            char categoryChoice;

            boolean validID = false;

            do{
                System.out.print("Enter vehicle ID: ");
                id = sc.nextInt();

                if (!vehiclesList.isEmpty()){
                    validID = true;
                    for (int i = 0; i < vehiclesList.size(); i++){
                        if (id == vehiclesList.get(i).getID()){
                            validID = false;
                            System.out.println("Vehicle with same ID already exists! Please enter another one!");
                            break;
                        }
                    }
                }else{
                    validID = true;
                }
            }while (!validID);
            
            System.out.print("Enter vehicle speed: ");
            double speed = sc.nextDouble();
            System.out.print("Enter vehicle transportation cost per km: ");
            double cost = sc.nextDouble();
    
            boolean validChoice = false;

            TransportFactory tf = new TransportFactory();
    
            do{
                System.out.println("A. Aircraft");
                System.out.println("B. SeaVessel");
                System.out.println("C. Truck");
                System.out.print("Enter choice of category: ");
                categoryChoice = sc.next().toUpperCase().charAt(0);
    
                switch(categoryChoice){
                    case 'A': category = "Aircraft"; validChoice = true; break;
                    case 'B': category = "Sea Vessel"; validChoice = true; break;
                    case 'C': category = "Truck"; validChoice = true; break;
                    default: System.out.println("Invalid choice of category!");
                }
            }while(!validChoice);
    
            if (category.equals("Aircraft")){
                // Creating an aircraft through the use of the TransportFactory class
                Aircraft ac = (Aircraft) tf.createTransport("Aircraft");

                // Setting the attribute values through the use of the overloaded constructor
                ac = new Aircraft(id, "Aircraft", speed, cost);

                boolean isFinished = false;
                do{
                    String ans;
                    System.out.print("Do you want to add in an airspace (Yes or No): ");
                    ans = sc.next();
    
                    if (ans.toUpperCase().charAt(0) == 'Y'){
                        isFinished = false;
                        String air_space;
                        System.out.print("Enter an air space: ");
                        air_space = sc.next();
                        ac.setAirSpace(air_space);
                        ac.addToList(air_space);
                    }else if (ans.toUpperCase().charAt(0) == 'N'){
                        isFinished = true;
                    }
                }while(!isFinished);
                vehiclesList.add(ac);
            }else if (category.equals("Sea Vessel")){
                // Creating a sea vessel through the use of the TransportFactory class
                SeaVessel sv = (SeaVessel) tf.createTransport("Sea Vessel");

                // Setting the attribute values through the use of the overloaded constructor
                sv = new SeaVessel(id, "Sea Vessel", speed, cost);

                boolean isFinished = false;
                do{
                    String ans;
                    System.out.print("Do you want to add in a route (Yes or No): ");
                    ans = sc.next();
    
                    if (ans.toUpperCase().charAt(0) == 'Y'){
                        isFinished = false;
                        String sea_route;
                        System.out.print("Enter a route: ");
                        sea_route = sc.next();
                        sv.setRoute(sea_route);
                        sv.addToList(sea_route);
                    }else if (ans.toUpperCase().charAt(0) == 'N'){
                        isFinished = true;
                    }
                }while(!isFinished);
                vehiclesList.add(sv);
            }else if (category.equals("Truck")){
                // Creating a truck through the use of the TransportFactory class
                Truck t = (Truck) tf.createTransport("Truck");

                // Setting the attribute values through the use of the overloaded constructor
                t = new Truck(id, "Truck", speed, cost);

                boolean isFinished = false;
                do{
                    String ans;
                    System.out.print("Do you want to add in a motorway (Yes or No): ");
                    ans = sc.next();
    
                    if (ans.toUpperCase().charAt(0) == 'Y'){
                        isFinished = false;
                        String motorway;
                        System.out.print("Enter a motorway: ");
                        motorway = sc.next();
                        t.setMotorway(motorway);
                        t.addToList(motorway);
                    }else if (ans.toUpperCase().charAt(0) == 'N'){
                        isFinished = true;
                    }
                }while(!isFinished);
                vehiclesList.add(t);
            }
        }else if (choiceEntered == 1){
            String category = null;
            char categoryChoice;

            //Product prod;
    
            System.out.print("Enter product ID: ");
            int id = sc.nextInt();
            System.out.print("Enter name of product: ");
            String name = sc.next();
            System.out.print("Enter product price: ");
            double price = sc.nextDouble();
            System.out.print("Enter product volume: ");
            int volume = sc.nextInt();

            System.out.print("Enter quantity of product: ");
            int quantity = sc.nextInt();
    
            boolean validChoice = false;

            ProductFactory pf = new ProductFactory();
    
            do{
                System.out.println("A. Book");
                System.out.println("B. Clothing");
                System.out.println("C. Computer Game");
                System.out.println("D. Electronics");
                System.out.print("Enter choice of category: ");
                categoryChoice = sc.next().toUpperCase().charAt(0);
    
                switch(categoryChoice){
                    case 'A': category = "Book"; validChoice = true; break;
                    case 'B': category = "Clothing"; validChoice = true; break;
                    case 'C': category = "Computer Game"; validChoice = true; break;
                    case 'D': category = "Electronics"; validChoice = true; break;
                    default: System.out.println("Invalid choice of category: ");
                }
            }while(!validChoice);
    
            if (category.equals("Book")){
                System.out.print("Enter book IBAN: ");
                String iban = sc.next();
                System.out.print("Enter book genre: ");
                String genre = sc.next();
                System.out.print("Enter book author: ");
                String author = sc.next();
                // Creating a book through the use of the ProductFactory class
                Book book = (Book) pf.createProduct("Book");

                // Setting the attribute values through the use of the overloaded constructor
                book = new Book(id, name, price, "Book", volume, iban, genre, author);

                StockItem item = new StockItem();
                item.setQuantity(quantity);
                item.setProduct(book);

                stock.addItem(item);

            }else if (category.equals("Clothing")){
                System.out.print("Enter width: ");
                double width = sc.nextDouble();
                System.out.print("Enter length: ");
                double length = sc.nextDouble();
                System.out.print("Enter size: ");
                String size = sc.next();
                // Creating a clothing item through the use of the ProductFactory class
                Clothing cloth = (Clothing) pf.createProduct("Clothing");

                // Setting the attribute values through the use of the overloaded constructor
                cloth = new Clothing(id, name, price, "Clothing", volume, width, length, size);
                
                StockItem item = new StockItem();
                item.setQuantity(quantity);
                item.setProduct(cloth);

                stock.addItem(item);
            }else if (category.equals("Computer Game")){
                System.out.print("Enter game publisher: ");
                String publisher = sc.next();
                // Creating a computer game through the use of the ProductFactory class
                ComputerGame cg = (ComputerGame) pf.createProduct("Computer Game");

                // Setting the attribute values through the use of the overloaded constructor
                cg = new ComputerGame(id, name, price, "Computer Game", volume, publisher);

                StockItem item = new StockItem();
                item.setQuantity(quantity);
                item.setProduct(cg);

                stock.addItem(item);
            }else if (category.equals("Electronics")){
                // Creating an electronic item through the use of the ProductFactory class
                Electronics electronic = (Electronics) pf.createProduct("Electronics");

                // Setting the attribute values through the use of the overloaded constructor
                electronic = new Electronics(id, name, price, "Electronics", volume);

                StockItem item = new StockItem();
                item.setQuantity(quantity);
                item.setProduct(electronic);

                stock.addItem(item);
            }

        }else if (choiceEntered == 3){
            int id;
            String material = null;
            char materialChoice;

            boolean validID = false;

            do{
                System.out.print("Enter packaging ID: ");
                id = sc.nextInt();

                if (!packagesList.isEmpty()){
                    validID = true;
                    for (int i = 0; i < packagesList.size(); i++){
                        if (id == packagesList.get(i).getID()){
                            validID = false;
                            System.out.println("Packaging with same ID already exists! Please enter another one!");
                            break;
                        }
                    }
                }else{
                    validID = true;
                }
            }while (!validID);

            System.out.print("Enter packaging cost: ");
            double cost = sc.nextDouble();
            System.out.print("Enter capacity: ");
            int capacity = sc.nextInt();

            boolean validChoice = false;

            do{
                System.out.println("A. Padded Envelope");
                System.out.println("B. Bubble Wrap");
                System.out.println("C. Cardboard Box");
                System.out.println("D. Wooded Box");
                System.out.println("E. Vacuum Packaging");
                System.out.print("Enter choice of category: ");
                materialChoice = sc.next().toUpperCase().charAt(0);

                switch(materialChoice){
                    case 'A': material = "Padded Envelope"; validChoice = true; break;
                    case 'B': material = "Bubble Wrap"; validChoice = true; break;
                    case 'C': material = "Cardboard"; validChoice = true; break;
                    case 'D': material = "Wood"; validChoice = true; break;
                    case 'E': material = "Vacuum"; validChoice = true; break;
                    default: System.out.println("Invalid choice of material!");
                }
            }while(!validChoice);

            Packaging pack = new Packaging(id, cost, material, capacity);
            packagesList.add(pack);
        }else if (choiceEntered == 4){
            int id;

            boolean validID = false;

            do{
                System.out.print("Enter customer ID: ");
                id = sc.nextInt();

                if (!customersList.isEmpty()){
                    for (int i = 0; i < customersList.size(); i++){
                        validID = true;
                        if (id == customersList.get(i).getID()){
                            validID = false;
                            System.out.println("Customer with same ID already exists! Please enter another one!");
                            break;
                        }
                    }
                }else{
                    validID = true;
                }
            }while (!validID);
            System.out.print("Enter name: ");
            String name = sc.next();
            System.out.print("Enter road: ");
            String road = sc.next();
            System.out.print("Enter town: ");
            String town = sc.next();
            System.out.print("Enter post code: ");
            String postCode = sc.next();

            Customer cust = new Customer(id, name, road, town, postCode);
            customersList.add(cust);
        }else if (choiceEntered == 5){
            int orderID;

            boolean validID = false;

            do{
                System.out.print("Enter order ID: ");
                orderID = sc.nextInt();

                if (!ordersList.isEmpty()){
                    for (int i = 0; i < ordersList.size(); i++){
                        validID = true;
                        if (orderID == ordersList.get(i).getID()){
                            validID = false;
                            System.out.println("Order with same ID already exists! Please enter another one!");
                            break;
                        }
                    }
                }else{
                    validID = true;
                }
            }while (!validID);

            for (int i = 0; i < customersList.size(); i++){
                System.out.println(customersList.get(i).toString());
            }

            for (int i = 0; i < packagesList.size(); i++){
                packagesList.get(i).display_package_details();
            }

            System.out.print("Enter ID of packaging to use: ");
            int packID = sc.nextInt();

            int counter = 0, posOfPack = 0;
            boolean isFound = false;
            do{
                if (customersList.get(counter).getID() == packID){
                    isFound = true;
                    posOfPack = counter;
                }else{
                    isFound = false;
                    counter++;
                }
            }while (!isFound);

            if (!isFound){
                System.out.println("ID of packaging could not be found! Please try again!");
                return;
            }
            Packaging packOrder = packagesList.get(posOfPack);

            System.out.print("Enter ID of customer that ordered: ");
            int custID = sc.nextInt();

            int posOfCust = 0;
            counter = 0;
            isFound = false;
            do{
                if (customersList.get(counter).getID() == custID){
                    isFound = true;
                    posOfCust = counter;
                }else{
                    isFound = false;
                    counter++;
                }
            }while (!isFound);

            if (!isFound){
                System.out.println("ID of customer could not be found! Please try again!");
            }else{
                Customer custOrder = customersList.get(posOfCust);
                stock.read_stock_list();

                boolean isValid = false;

                do{
                    System.out.print("Enter index of product to order (from 0): ");
                    int prodIndex = sc.nextInt();

                    System.out.print("Enter quantity to order for the product you chose: ");
                    int orderQuantity = sc.nextInt();

                    System.out.print("Enter day of month of order: ");
                    int day = sc.nextInt();

                    System.out.print("Enter month number of order: ");
                    int monthNum = sc.nextInt();

                    System.out.print("Enter year of order: ");
                    int year = sc.nextInt();

                    String date = day+"/"+monthNum+"/"+year;

                    if (stock.getItem(prodIndex) != null && orderQuantity <= stock.calculate_product_quantity(stock.getItem(prodIndex))){
                        StockItem itemOrdered = new StockItem(orderQuantity, stock.getItem(prodIndex).getProduct());
                        Shipment ship = new Shipment(orderID, custOrder, date, packOrder);
                        ship.addItemToList(itemOrdered);
                        stock.getItem(prodIndex).updateQuantity(-orderQuantity);
                        

                        if (stock.getItem(prodIndex).getQuantity() == 0){
                            stock.removeItem(stock.getItem(prodIndex));
                        }

                        isValid = true;

                        System.out.println("Enter distance between customer and warehouse: ");
                        double distance = sc.nextDouble();
						ship.setDistance(distance);

                        String transportType = ship.stateTransportType(distance);

                        for (int i = 0; i < vehiclesList.size(); i++){
                            if (transportType.equals("Truck")){
                                if (vehiclesList.get(i) instanceof Truck){
                                    Truck currentVehicle = (Truck) vehiclesList.get(i);
                                    int countVeh = 0;
                                    boolean isSearched = false;

                                    while(!isSearched){
                                        if (currentVehicle.getRoute(countVeh).contains(custOrder.getTown())){
                                            ship.setTransport(currentVehicle);
                                            isSearched = true;
                                        }else{
                                            countVeh++;
                                        }
                                    }
                                    break;
                                }
                            }else if (transportType.equals("Sea Vessel")){
                                if (vehiclesList.get(i) instanceof SeaVessel){
                                    SeaVessel currentVehicle = (SeaVessel) vehiclesList.get(i);
                                    int countVeh = 0;
                                    boolean isSearched = false;

                                    while(!isSearched){
                                        if (currentVehicle.getRoute(countVeh).contains(custOrder.getTown())){
                                            ship.setTransport(currentVehicle);
                                            isSearched = true;
                                        }else{
                                            countVeh++;
                                        }
                                    }
                                    break;
                                }
                            }else if (transportType.equals("Aircraft")){
                                if (vehiclesList.get(i) instanceof Aircraft){
                                    Aircraft currentVehicle = (Aircraft) vehiclesList.get(i);
                                    int countVeh = 0;
                                    boolean isSearched = false;

                                    while(!isSearched){
                                        if (currentVehicle.getRoute(countVeh).contains(custOrder.getTown())){
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
                        ordersList.add(ship);

                        System.out.println("Item has been put to cart!");
                    }else{
                        isValid = false;
                        System.out.println("Please try again!");
                    }
                }while (!isValid);
            }
        }
    }

    public static void ReadObject(int choiceEntered){
        if (choiceEntered == 1){
            if (!stock.checkIfEmptyList()){
                int readChoice = 0;
                boolean validOption = false;

                do{
                    System.out.println("1. Read entire list of items in stock");
                    System.out.println("2. Read one item in stock");
                    System.out.print("Enter your choice here: ");
                    readChoice = sc.nextInt();

                    switch(readChoice){
                        case 1: stock.read_stock_list(); validOption = true; break;
                        case 2:{
                            int posToRead;
                            System.out.println("Enter position of item to search in array (from 0): ");
                            posToRead = sc.nextInt();
                            stock.print_item_details(posToRead);
                            validOption = true;
                        } break;
                        default: System.out.println("Invalid option! Please try again!");
                    }
                }while (!validOption);
            }else{
                System.out.println("List of Products is empty!");
            }
        }else if (choiceEntered == 2){
            if (!vehiclesList.isEmpty()){
                int readChoice = 0;
                boolean validOption = false;

                do{
                    System.out.println("1. Read entire list of vehicles");
                    System.out.println("2. Read one vehicle");
                    System.out.print("Enter your choice here: ");
                    readChoice = sc.nextInt();

                    switch(readChoice){
                        case 1: {
                            for (int i = 0; i < vehiclesList.size(); i++){
                                System.out.println("Vehicle " + (i+1) + ":");
                                vehiclesList.get(i).display_vehicle_details();
                                System.out.println();
                                validOption = true;
                            }
                        } break;
                        case 2:{
                            int posToRead;
                            System.out.print("Enter position of vehicle to search in array (from 0): ");
                            posToRead = sc.nextInt();
                            try{
                                int count = 0;
                                do{
                                    if (count == posToRead){
                                        vehiclesList.get(count).display_vehicle_details();
                                        break;
                                    }else{
                                        count++;
                                    }
                                }while (count < vehiclesList.size());
                            }catch(Exception e){
                                System.out.println("Vehicle does not exist in list!");
                            }
                            validOption = true;
                        } break;
                        default: System.out.println("Invalid option! Please try again!");
                    }
                }while (!validOption);
            }else{
                System.out.println("List of Vehicles is empty!");
            }
        }else if (choiceEntered == 3){
            if (!packagesList.isEmpty()){
                int readChoice = 0;
                boolean validOption = false;

                do{
                    System.out.println("1. Read entire list of packages");
                    System.out.println("2. Read one package");
                    System.out.print("Enter your choice here: ");
                    readChoice = sc.nextInt();

                    switch(readChoice){
                        case 1: {
                            for (int i = 0; i < packagesList.size(); i++){
                                System.out.println("Package " + (i+1) + ":");
                                packagesList.get(i).display_package_details();
                                System.out.println();
                                validOption = true;
                            }
                        } break;
                        case 2:{
                            int posToRead;
                            System.out.print("Enter position of package to search in array (from 0): ");
                            posToRead = sc.nextInt();
                            try{
                                int count = 0;
                                do{
                                    if (count == posToRead){
                                        packagesList.get(count).display_package_details();
                                        break;
                                    }else{
                                        count++;
                                    }
                                }while (count < packagesList.size());
                            }catch(Exception e){
                                System.out.println("Package does not exist in list!");
                            }
                            validOption = true;
                        } break;
                        default: System.out.println("Invalid option! Please try again!");
                    }
                }while (!validOption);
            }else{
                System.out.println("List of Packages is empty!");
            }
        }else if (choiceEntered == 4){
            if (!customersList.isEmpty()){
                int readChoice = 0;
                boolean validOption = false;

                do{
                    System.out.println("1. Read entire list of customers");
                    System.out.println("2. Read one customer");
                    System.out.print("Enter your choice here: ");
                    readChoice = sc.nextInt();

                    switch(readChoice){
                        case 1: {
                            for (int i = 0; i < customersList.size(); i++){
                                System.out.println("Customer " + (i+1) + ":");
                                System.out.println(customersList.get(i).toString());
                                System.out.println();
                                validOption = true;
                            }
                        } break;
                        case 2:{
                            int posToRead;
                            System.out.print("Enter position of customer to search in array (from 0): ");
                            posToRead = sc.nextInt();
                            try{
                                int count = 0;
                                do{
                                    if (count == posToRead){
                                        System.out.println(customersList.get(count).toString());
                                        break;
                                    }else{
                                        count++;
                                    }
                                }while (count < customersList.size());
                            }catch(Exception e){
                                System.out.println("Customer does not exist in list!");
                            }
                            validOption = true;
                        } break;
                        default: System.out.println("Invalid option! Please try again!");
                    }
                }while (!validOption);
            }else{
                System.out.println("List of Customers is empty!");
            }
        }else if (choiceEntered == 5){
            if (!ordersList.isEmpty()){
                int readChoice = 0;
                boolean validOption = false;

                do{
                    System.out.println("1. Read entire list of orders");
                    System.out.println("2. Read one order");
                    System.out.print("Enter your choice here: ");
                    readChoice = sc.nextInt();

                    switch(readChoice){
                        case 1: {
                            for (int i = 0; i < ordersList.size(); i++){
                                System.out.println("Order " + (i+1) + ":");
                                ordersList.get(i).display_shipment_details();
                                System.out.println();
                                validOption = true;
                            }
                        } break;
                        case 2:{
                            int posToRead;
                            System.out.print("Enter position of order to search in array (from 0): ");
                            posToRead = sc.nextInt();
                            try{
                                int count = 0;
                                do{
                                    if (count == posToRead){
                                        ordersList.get(count).display_shipment_details();
                                        break;
                                    }else{
                                        count++;
                                    }
                                }while (count < packagesList.size());
                            }catch(Exception e){
                                System.out.println("Order does not exist in list!");
                            }
                            validOption = true;
                        } break;
                        default: System.out.println("Invalid option! Please try again!");
                    }
                }while (!validOption);
            }else{
                System.out.println("List of Orders is empty!");
            }
        }
    }

    public static void UpdateObject(int choiceEntered){
        if (choiceEntered == 1){
            int posToUpdate;
            try{
                System.out.print("Enter position of item to update in array (from 0): ");
                posToUpdate = sc.nextInt();

                StockItem itemAtPos = stock.getItem(posToUpdate);
                Product prodAtPos = itemAtPos.getProduct();

                System.out.println("Original Product Name (enter same value to keep same name): " + prodAtPos.getName());
                System.out.print("Enter new name of product: ");
                String name = sc.next();
                if (name.length() != 0){
                    prodAtPos.setName(name);
                }
                

                System.out.println("Original Product Price: " + prodAtPos.getPrice());
                System.out.print("Enter new product price (enter same value to keep same price): ");
                double price = sc.nextDouble();
                String priceString = Double.toString(price);
                if (!priceString.isEmpty()){
                    prodAtPos.setPrice(price);
                }
                

                System.out.println("Original Product Volume: " + prodAtPos.getVolume());
                System.out.print("Enter new product volume (enter same value to keep same volume): ");
                int volume = sc.nextInt();
                String volumeString = Integer.toString(volume);
                if (!volumeString.isEmpty()){
                    prodAtPos.setVolume(volume);
                }

                System.out.println("Original Item Quantity: " + stock.calculate_product_quantity(itemAtPos));
                System.out.print("Enter new item quantity (enter same value to keep same quantity): ");
                int quantity = sc.nextInt();
                String quantityString = Integer.toString(quantity);
                if (!quantityString.isEmpty()){
                    itemAtPos.updateQuantity(quantity);
                }
                itemAtPos.setProduct(prodAtPos);
                stock.updateItem(itemAtPos, posToUpdate);

                if (prodAtPos instanceof Book){
                    Book b = (Book) prodAtPos;

                    System.out.println("Original IBAN: " + b.getIBAN());
                    System.out.print("Enter new IBAN (enter same value to keep same IBAN): ");
                    String IBAN = sc.next();
                    if (IBAN.length() != 0){
                        b.setIBAN(IBAN);
                    }

                    System.out.println("Original Genre: " + b.getGenre());
                    System.out.print("Enter new genre (enter same value to keep same genre): ");
                    String genre = sc.next();
                    if (genre.length() != 0){
                        b.setGenre(genre);
                    }

                    System.out.println("Original Author: " + b.getAuthor());
                    System.out.print("Enter new author (enter same value to keep same author): ");
                    String author = sc.next();
                    if (author.length() != 0){
                        b.setAuthor(author);
                    }

                    itemAtPos.setProduct(b);
                    stock.updateItem(itemAtPos, posToUpdate);
                }else if (prodAtPos instanceof Clothing){
                    Clothing c = (Clothing) prodAtPos;

                    System.out.println("Original Width: " + c.getWidth());
                    System.out.print("Enter new width (enter same value to keep same width): ");
                    double width = sc.nextDouble();
                    String widthString = Double.toString(width);
                    if (!widthString.isEmpty()){
                        c.setWidth(quantity);
                    }

                    System.out.println("Original Length: " + c.getLength());
                    System.out.print("Enter new length (enter same value to keep same length): ");
                    double length = sc.nextDouble();
                    String lengthString = Double.toString(length);
                    if (!lengthString.isEmpty()){
                        c.setLength(length);
                    }

                    System.out.println("Original Size: " + c.getSize());
                    System.out.print("Enter new size (enter same value to keep same size): ");
                    String size = sc.next();
                    if (size.length() != 0){
                        c.setSize(size);
                    }

                    itemAtPos.setProduct(c);
                    stock.updateItem(itemAtPos, posToUpdate);
                }else if (prodAtPos instanceof ComputerGame){
                    ComputerGame cg = (ComputerGame) prodAtPos;

                    System.out.println("Original Publisher: " + cg.getPublisher());
                    System.out.print("Enter new publisher (enter same value to keep same publisher): ");
                    String publisher = sc.next();
                    if (publisher.length() != 0){
                        cg.setPublisher(publisher);
                    }

                    itemAtPos.setProduct(cg);
                    stock.updateItem(itemAtPos, posToUpdate);
                }
            }catch (IndexOutOfBoundsException e){
                System.out.println("Item not found!");
            }
        }else if (choiceEntered == 2){
            int posToUpdate;
            try{
                System.out.print("Enter position of vehicle to update in array (from 0): ");
                posToUpdate = sc.nextInt();

                String route;
                double speed, cost;

                Aircraft acAtPos;
                SeaVessel svAtPos;
                Truck tAtPos;

                if(vehiclesList.get(posToUpdate) instanceof Aircraft){
                    System.out.println("Original Vehicle Transportation Speed: " + vehiclesList.get(posToUpdate).getSpeed());
                    System.out.print("Enter new vehicle speed (enter same value to keep same speed): ");
                    speed = sc.nextDouble();
                    String speedString = Double.toString(speed);
                    if (!speedString.isEmpty()){
                        vehiclesList.get(posToUpdate).setSpeed(speed);
                    }
                    

                    System.out.println("Original Vehicle Transportation Cost/km: " + vehiclesList.get(posToUpdate).getCost());
                    System.out.print("Enter new vehicle cost (enter same value to keep same cost): ");
                    cost = sc.nextDouble();
                    String costString = Double.toString(cost);
                    if (!costString.isEmpty()){
                        vehiclesList.get(posToUpdate).setCost(cost);
                    }
                }else if(vehiclesList.get(posToUpdate) instanceof SeaVessel){
                    System.out.println("Original Vehicle Transportation Speed: " + vehiclesList.get(posToUpdate).getSpeed());
                    System.out.print("Enter new vehicle speed (enter same value to keep same speed): ");
                    speed = sc.nextDouble();
                    String speedString = Double.toString(speed);
                    if (!speedString.isEmpty()){
                        vehiclesList.get(posToUpdate).setSpeed(speed);
                    }
                    

                    System.out.println("Original Vehicle Transportation Cost/km: " + vehiclesList.get(posToUpdate).getCost());
                    System.out.print("Enter new vehicle cost (enter same value to keep same cost): ");
                    cost = sc.nextDouble();
                    String costString = Double.toString(cost);
                    if (!costString.isEmpty()){
                        vehiclesList.get(posToUpdate).setCost(cost);
                    }
                }else if(vehiclesList.get(posToUpdate) instanceof Truck){
                    System.out.println("Original Vehicle Transportation Speed: " + vehiclesList.get(posToUpdate).getSpeed());
                    System.out.print("Enter new vehicle speed (enter same value to keep same speed): ");
                    speed = sc.nextDouble();
                    String speedString = Double.toString(speed);
                    if (!speedString.isEmpty()){
                        vehiclesList.get(posToUpdate).setSpeed(speed);
                    }
                    

                    System.out.println("Original Vehicle Transportation Cost/km: " + vehiclesList.get(posToUpdate).getCost());
                    System.out.print("Enter new vehicle cost (enter same value to keep same cost): ");
                    cost = sc.nextDouble();
                    String costString = Double.toString(cost);
                    if (!costString.isEmpty()){
                        vehiclesList.get(posToUpdate).setCost(cost);
                    }
                }

                int readChoice = 0;
                boolean validOption = false;

                do{
                    System.out.println("1. Update existing air space/route/motorway");
                    System.out.println("2. Add air space/route/motorway");
                    System.out.println("Enter your choice here: ");
                    readChoice = sc.nextInt();

                    switch(readChoice){
                        case 1: {
                            if(vehiclesList.get(posToUpdate) instanceof Aircraft){
                                acAtPos = (Aircraft) vehiclesList.get(posToUpdate);
                                System.out.print("Enter new air space (enter same value to keep same air space): ");
                                route = sc.next();
                                acAtPos.updateAirSpaceinList(route, posToUpdate);
                                vehiclesList.set(posToUpdate, acAtPos);
                            }else if(vehiclesList.get(posToUpdate) instanceof SeaVessel){
                                svAtPos = (SeaVessel) vehiclesList.get(posToUpdate);
                                System.out.print("Enter new route (enter same value to keep same route): ");
                                route = sc.next();
                                svAtPos.updateRouteinList(route, posToUpdate);
                                vehiclesList.set(posToUpdate, svAtPos);
                            }else if(vehiclesList.get(posToUpdate) instanceof Truck){
                                tAtPos = (Truck) vehiclesList.get(posToUpdate);
                                System.out.print("Enter new motorway (enter same value to keep same motorway): ");
                                route = sc.next();
                                tAtPos.updateMotorwayinList(route, posToUpdate);
                                vehiclesList.set(posToUpdate, tAtPos);
                            }
                            validOption = true;
                        } break;
                        case 2:{
                            if(vehiclesList.get(posToUpdate) instanceof Aircraft){
                                acAtPos = (Aircraft) vehiclesList.get(posToUpdate);
                                System.out.print("Enter new air space: ");
                                route = sc.next();
                                acAtPos.setAirSpace(route);
                                acAtPos.addToList(route);
                                vehiclesList.set(posToUpdate, acAtPos);
                            }else if(vehiclesList.get(posToUpdate) instanceof SeaVessel){
                                svAtPos = (SeaVessel) vehiclesList.get(posToUpdate);
                                System.out.print("Enter new route (enter same value to keep same route): ");
                                route = sc.next();
                                svAtPos.setRoute(route);
                                svAtPos.addToList(route);
                                vehiclesList.set(posToUpdate, svAtPos);
                            }else if(vehiclesList.get(posToUpdate) instanceof Truck){
                                tAtPos = (Truck) vehiclesList.get(posToUpdate);
                                System.out.print("Enter new motorway (enter same value to keep same motorway): ");
                                route = sc.next();
                                tAtPos.setMotorway(route);
                                tAtPos.addToList(route);
                                vehiclesList.set(posToUpdate, tAtPos);
                            }
                            validOption = true;
                        } break;
                        default: System.out.println("Invalid option! Please try again!");
                    }
                }while (!validOption);
            }catch (IndexOutOfBoundsException e){
                System.out.println("Vehicle not found!");
            }
            
        }else if (choiceEntered == 3){
            int posToUpdate;
            try{
                System.out.print("Enter position of package to update in array (from 0): ");
                posToUpdate = sc.nextInt();

                Packaging packageAtPos = packagesList.get(posToUpdate);

                System.out.println("Original Packaging Material: " + packageAtPos.getMaterial());
                System.out.print("Enter new packaging material (enter same value to keep same material): ");
                String material = sc.next();
                if (material.length() != 0){
                    packageAtPos.setMaterial(material);
                }

                System.out.println("Original Packaging Cost: " + packageAtPos.getCost());
                System.out.print("Enter new packaging cost (enter same value to keep same cost): ");
                double cost = sc.nextDouble();
                String costString = Double.toString(cost);
                if (!costString.isEmpty()){
                    packageAtPos.setCost(cost);
                }

                System.out.println("Original Packaging Capacity: " + packageAtPos.getCapacity());
                System.out.print("Enter new product capacity (enter same value to keep same capacity): ");
                int capacity = sc.nextInt();
                String capacityString = Integer.toString(capacity);
                if (!capacityString.isEmpty()){
                    packageAtPos.setCapacity(capacity);
                }
                packagesList.set(posToUpdate, packageAtPos);
            }catch (IndexOutOfBoundsException e){
                System.out.println("Package not found!");
            }
        }else if (choiceEntered == 4){
            int posToUpdate;
            try{
                System.out.print("Enter position of customer to update in array (from 0): ");
                posToUpdate = sc.nextInt();

                Customer custAtPos = customersList.get(posToUpdate);

                System.out.println("Original Customer Name: " + custAtPos.getName());
                System.out.print("Enter new name (enter same value to keep same name): ");
                String name = sc.next();
                if (name.length() != 0){
                    custAtPos.setName(name);
                }

                System.out.println("Original Customer Road: " + custAtPos.getRoad());
                System.out.print("Enter new road (enter same value to keep same cost): ");
                String road = sc.next();
                if (road.length() != 0){
                    custAtPos.setRoad(road);
                }

                System.out.println("Original Customer Town: " + custAtPos.getTown());
                System.out.print("Enter new town (enter same value to keep same cost): ");
                String town = sc.next();
                if (town.length() != 0){
                    custAtPos.setTown(town);
                }

                System.out.println("Original Customer Post Code: " + custAtPos.getPostCode());
                System.out.print("Enter new post code (enter same value to keep same cost): ");
                String postCode = sc.next();
                if (postCode.length() != 0){
                    custAtPos.setPostCode(postCode);
                }
                customersList.set(posToUpdate, custAtPos);
            }catch(IndexOutOfBoundsException e){
                System.out.println("Customer not found!");
            }
        }else if (choiceEntered == 5){
            System.out.print("Enter position of order to update in array (from 0): ");
            int posToUpdate = sc.nextInt();

            System.out.println("Original Order Date: "+ordersList.get(posToUpdate).getOrderDate());
            System.out.print("Enter day of month of order: ");
            int day = sc.nextInt();

            System.out.print("Enter month number of order: ");
            int monthNum = sc.nextInt();

            System.out.print("Enter year of order: ");
            int year = sc.nextInt();

            String date = day+"/"+monthNum+"/"+year;

            int orderID = ordersList.get(posToUpdate).getID();

            System.out.println("Original Packaging: "+ordersList.get(posToUpdate).getPackaging());

            for (int i = 0; i < packagesList.size(); i++){
                packagesList.get(i).display_package_details();
            }

            System.out.print("Enter new ID of packaging (enter same value to keep current packaging): ");
            int packID = sc.nextInt();

            int counter = 0, posOfPack = 0;
            boolean isFound = false;
            do{
                if (customersList.get(counter).getID() == packID){
                    isFound = true;
                    posOfPack = counter;
                }else{
                    isFound = false;
                    counter++;
                }
            }while (!isFound);

            if (!isFound){
                System.out.println("ID of customer could not be found! Please try again!");
                return;
            }
            Packaging packOrder = packagesList.get(posOfPack);

            System.out.println("Original Customer: "+ordersList.get(posToUpdate).getCustomer());

            for (int i = 0; i < customersList.size(); i++){
                System.out.println(customersList.get(i).toString());
            }

            System.out.print("Enter new ID of customer that ordered (enter same value to keep current customer): ");
            int custID = sc.nextInt();

            int posOfCust = 0;
            counter = 0;
            isFound = false;
            do{
                if (customersList.get(counter).getID() == custID){
                    isFound = true;
                    posOfCust = counter;
                }else{
                    isFound = false;
                    counter++;
                }
            }while (!isFound);

            if (!isFound){
                System.out.println("ID of customer could not be found! Please try again!");
            }else{
                Customer custOrder = customersList.get(posOfCust);
                stock.read_stock_list();

                boolean isValid = false;

                do{
                    System.out.println("Current List of Items Ordered: ");
                    ordersList.get(posToUpdate).print_items_list();

                    boolean validSelection = false;

                    do{
                        System.out.println("1. Update existing item in list");
                        System.out.println("2. Add item to list");
                        System.out.println("3. Remove item from list");
                        System.out.print("Enter choice here: ");
                        int selection = sc.nextInt();

                        switch (selection) {
                            case 1:{
                                validSelection = true;

                                System.out.print("Enter index of product to update (from 0): ");
                                int prodIndex = sc.nextInt();

                                StockItem itemChosen = ordersList.get(posToUpdate).getItem(prodIndex);

                                int originalQuantity = itemChosen.getQuantity();

                                System.out.println("Original Product Quantity: "+originalQuantity);
                                System.out.print("Enter new quantity (enter same value to keep quantity): ");
                                int orderQuantity = sc.nextInt();

                                if (itemChosen != null && orderQuantity <= stock.calculate_product_quantity(stock.getItem(prodIndex))){
                                    StockItem itemOrdered = new StockItem(orderQuantity, itemChosen.getProduct());
                                    Shipment ship = new Shipment(orderID, custOrder, date, packOrder);
                                    ship.updateItemInList(itemOrdered, posToUpdate);
                                    if (orderQuantity < originalQuantity || orderQuantity > originalQuantity)
                                        stock.getItem(prodIndex).updateQuantity(orderQuantity - originalQuantity);
                                    

                                    if (stock.getItem(prodIndex).getQuantity() <= 0){
                                        stock.removeItem(stock.getItem(prodIndex));
                                    }

                                    isValid = true;

                                    ordersList.set(posToUpdate, ship);

                                    System.out.println("Item has been updated!");
                                }else{
                                    isValid = false;
                                    System.out.println("Please try again!");
                                }
                            } break;
                            case 2: {
                                validSelection = true;

                                System.out.print("Enter index of product to order (from 0): ");
                                int prodIndex = sc.nextInt();

                                System.out.print("Enter quantity to order for the product you chose: ");
                                int orderQuantity = sc.nextInt();

                                

                                if (stock.getItem(prodIndex) != null && orderQuantity <= stock.calculate_product_quantity(stock.getItem(prodIndex))){
                                    StockItem itemOrdered = new StockItem(orderQuantity, stock.getItem(prodIndex).getProduct());
                                    Shipment ship = new Shipment(orderID, custOrder, date, packOrder);
                                    ship.addItemToList(itemOrdered);
                                    stock.getItem(prodIndex).updateQuantity(-orderQuantity);
                                    

                                    if (stock.getItem(prodIndex).getQuantity() <= 0){
                                        stock.removeItem(stock.getItem(prodIndex));
                                    }

                                    isValid = true;

                                    System.out.println("Enter distance between customer and warehouse: ");
                                    double distance = sc.nextDouble();

                                    String transportType = ship.stateTransportType(distance);

                                    for (int i = 0; i < vehiclesList.size(); i++){
                                        if (transportType.equals("Truck")){
                                            if (vehiclesList.get(i) instanceof Truck){
                                                Truck currentVehicle = (Truck) vehiclesList.get(i);
                                                int countVeh = 0;
                                                boolean isSearched = false;

                                                while(!isSearched){
                                                    if (currentVehicle.getRoute(countVeh).contains(custOrder.getTown())){
                                                        ship.setTransport(currentVehicle);
                                                        isSearched = true;
                                                    }else{
                                                        countVeh++;
                                                    }
                                                }
                                                break;
                                            }
                                        }else if (transportType.equals("Sea Vessel")){
                                            if (vehiclesList.get(i) instanceof SeaVessel){
                                                SeaVessel currentVehicle = (SeaVessel) vehiclesList.get(i);
                                                int countVeh = 0;
                                                boolean isSearched = false;

                                                while(!isSearched){
                                                    if (currentVehicle.getRoute(countVeh).contains(custOrder.getTown())){
                                                        ship.setTransport(currentVehicle);
                                                        isSearched = true;
                                                    }else{
                                                        countVeh++;
                                                    }
                                                }
                                                break;
                                            }
                                        }else if (transportType.equals("Aircraft")){
                                            if (vehiclesList.get(i) instanceof Aircraft){
                                                Aircraft currentVehicle = (Aircraft) vehiclesList.get(i);
                                                int countVeh = 0;
                                                boolean isSearched = false;

                                                while(!isSearched){
                                                    if (currentVehicle.getRoute(countVeh).contains(custOrder.getTown())){
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
                                    ordersList.add(ship);

                                    System.out.println("Item has been put to cart!");
                                }else{
                                    isValid = false;
                                    System.out.println("Please try again!");
                                }
                            } break;
                            case 3: {
                                System.out.print("Enter position of item to remove (from 0): ");
                                int posToRemove = sc.nextInt();
                                ordersList.get(posToUpdate).removeItemFromlist(posToRemove);
                                validSelection = true;
                            }
                            default: System.out.println("Invalid option!"); validSelection = false; break;
                        }
                    }while (!validSelection);

                    System.out.print("Enter index of product to order (from 0): ");
                    int prodIndex = sc.nextInt();

                    System.out.print("Enter quantity to order for the product you chose: ");
                    int orderQuantity = sc.nextInt();

                    if (stock.getItem(prodIndex) != null && orderQuantity <= stock.calculate_product_quantity(stock.getItem(prodIndex))){
                        StockItem itemOrdered = new StockItem(orderQuantity, stock.getItem(prodIndex).getProduct());
                        Shipment ship = new Shipment(orderID, custOrder, date, packOrder);
                        ship.addItemToList(itemOrdered);
                        stock.getItem(prodIndex).updateQuantity(-orderQuantity);
                        

                        if (stock.getItem(prodIndex).getQuantity() <= 0){
                            stock.removeItem(stock.getItem(prodIndex));
                        }

                        isValid = true;

                        System.out.println("Enter distance between customer and warehouse: ");
                        double distance = sc.nextDouble();

                        String transportType = ship.stateTransportType(distance);

                        for (int i = 0; i < vehiclesList.size(); i++){
                            if (transportType.equals("Truck")){
                                if (vehiclesList.get(i) instanceof Truck){
                                    Truck currentVehicle = (Truck) vehiclesList.get(i);
                                    int countVeh = 0;
                                    boolean isSearched = false;

                                    while(!isSearched){
                                        if (currentVehicle.getRoute(countVeh).contains(custOrder.getTown())){
                                            ship.setTransport(currentVehicle);
                                            isSearched = true;
                                        }else{
                                            countVeh++;
                                        }
                                    }
                                    break;
                                }
                            }else if (transportType.equals("Sea Vessel")){
                                if (vehiclesList.get(i) instanceof SeaVessel){
                                    SeaVessel currentVehicle = (SeaVessel) vehiclesList.get(i);
                                    int countVeh = 0;
                                    boolean isSearched = false;

                                    while(!isSearched){
                                        if (currentVehicle.getRoute(countVeh).contains(custOrder.getTown())){
                                            ship.setTransport(currentVehicle);
                                            isSearched = true;
                                        }else{
                                            countVeh++;
                                        }
                                    }
                                    break;
                                }
                            }else if (transportType.equals("Aircraft")){
                                if (vehiclesList.get(i) instanceof Aircraft){
                                    Aircraft currentVehicle = (Aircraft) vehiclesList.get(i);
                                    int countVeh = 0;
                                    boolean isSearched = false;

                                    while(!isSearched){
                                        if (currentVehicle.getRoute(countVeh).contains(custOrder.getTown())){
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
                        ordersList.add(ship);

                        System.out.println("Item has been put to cart!");
                    }else{
                        isValid = false;
                        System.out.println("Please try again!");
                    }
                }while (!isValid);
            }
        }
    }

    public static void DeleteObject(int choiceEntered){
        int posToRemove;
        if (choiceEntered == 1){
            System.out.print("Enter position of item to remove (from 0): ");
            posToRemove = sc.nextInt();
            stock.removeItem(stock.getItem(posToRemove));
        }else if (choiceEntered == 2){
            System.out.print("Enter position of vehicle to remove (from 0): ");
            posToRemove = sc.nextInt();
            vehiclesList.remove(posToRemove);
        }else if (choiceEntered == 3){
            System.out.print("Enter position of package to remove (from 0): ");
            posToRemove = sc.nextInt();
            packagesList.remove(posToRemove);
        }else if (choiceEntered == 4){
            System.out.print("Enter position of customer to remove (from 0): ");
            posToRemove = sc.nextInt();
            customersList.remove(posToRemove);
        }else if (choiceEntered == 5){
            System.out.print("Enter position of order to remove (from 0): ");
            posToRemove = sc.nextInt();
            Shipment orderToRemove = ordersList.get(posToRemove);
            for (int i = 0; i < orderToRemove.getListSize(); i++){
                orderToRemove.getItem(i).updateQuantity(orderToRemove.getOrderedQuantity(i));
            }
            ordersList.get(posToRemove).getItem(posToRemove);
            ordersList.remove(posToRemove);
        }
    }

    public static void ChooseShipmentToDispatch(){
        for (int i = 0; i < ordersList.size(); i++){
            ordersList.get(i).display_shipment_details();
        }

        System.out.println("Enter ID of shipment to dispatch: ");
        int idToDispatch = sc.nextInt();

        boolean foundShipment = false;
        int posOfShipment = 0;
        for (int i = 0; i < ordersList.size(); i++){
            if (ordersList.get(i).getID() == idToDispatch){
                foundShipment = true;
                posOfShipment = i;
                break;
            }else{
                foundShipment = false;
            }
        }

        if (!foundShipment){
            System.out.println("Shipment cannot be found! Please try again!");
        }else{
            System.out.println("Shipment found!");

            boolean giveDiscount = false, closeMotorway = false;
            System.out.print("Would you like to apply a discount (Yes or No): ");
            String ans = sc.next();

            if (ans.toUpperCase().charAt(0) == 'Y'){
                giveDiscount = true;
            }else{
                giveDiscount = false;
            }

            System.out.print("Is there a closed motorway (Yes or No): ");
            String ans2 = sc.next();

            if (ans2.toUpperCase().charAt(0) == 'Y'){
                closeMotorway = true;
				AdjustShipmentDelivery(ordersList.get(posOfShipment));
            }else{
                closeMotorway = false;
            }

            System.out.print("Enter day of month of dispatch: ");
            int day = sc.nextInt();

            System.out.print("Enter month number of dispatch: ");
            int monthNum = sc.nextInt();

            System.out.print("Enter year of dispatch: ");
            int year = sc.nextInt();

            String date = day+"/"+monthNum+"/"+year;

            double totalCost;
            if (!giveDiscount){
                totalCost = ordersList.get(posOfShipment).calculateTotalCost(monthNum);
            }else{
                totalCost = ApplyDiscountToShipment(ordersList.get(posOfShipment));
            }
            System.out.println("Total Cost: €"+totalCost);

            double dist = ordersList.get(posOfShipment).getDistance();
            System.out.println("Estimated Delivery Time: "+ordersList.get(posOfShipment).getTransport().calculate_delivery_time(dist) + " days");

            ordersList.get(posOfShipment).dispatch(date);
            System.out.println("Order has been dispatched!");
            ordersList.remove(posOfShipment);
        }
    }

    public static double ApplyDiscountToShipment(Shipment shipmentChosen){
        String monthNumString = shipmentChosen.getOrderDate().substring(3, 5);
        int monthNum = Integer.parseInt(monthNumString);

        System.out.print("Enter discount rate (between 0 and 1): ");
        double discountRate = sc.nextDouble();

        if (shipmentChosen != null && (discountRate >= 0 && discountRate <= 1)){
            ShipmentInterface discountedShipment = new GlobalDiscountDecorator(shipmentChosen, discountRate);

            // Calculate and print the total cost
            double cost = discountedShipment.calculateTotalCost(monthNum);
            return cost;
        }else if (discountRate < 0 || discountRate > 1){
            System.out.println("Invalid discount rate! Please try again later!");
            return 0.0;
        }else{
            System.out.println("Shipment cannot be found!");
            return 0.0;
        }
    }

    public static void AdjustShipmentDelivery(Shipment shipmentChosen){
        System.out.print("Enter closed motorway: ");
        String closedMotorway = sc.next();

        if (shipmentChosen != null && closedMotorway.length() != 0){
            ShipmentInterface adjustedShipment = new MotorwayClosureDecorator(shipmentChosen, closedMotorway);

            // Get and print the delivery plan
            System.out.println("Delivery Plan: " + adjustedShipment.getDeliveryPlan());
        }else if (shipmentChosen == null){
            System.out.println("Shipment cannot be found!");
        }else if (closedMotorway.length() == 0){
            System.out.println("Invalid motorway given! Please try again later!");
        }
    }

    public static void ApplyDiscountAndAdjustDelivery(){
        System.out.print("Enter position of order (from 0): ");
        int pos = sc.nextInt();
        Shipment shipmentChosen = ordersList.get(pos);

        String monthNumString = shipmentChosen.getOrderDate().substring(3, 5);
        int monthNum = Integer.parseInt(monthNumString);

        System.out.print("Enter discount rate (between 0 and 1): ");
        double discountRate = sc.nextDouble();

        System.out.print("Enter closed motorway: ");
        String closedMotorway = sc.next();

        if (shipmentChosen != null && (discountRate >= 0 && discountRate <= 1) && closedMotorway.length() != 0){
            ShipmentInterface discountedShipment = new GlobalDiscountDecorator(shipmentChosen, discountRate);
            Shipment discountedShipment2 = (Shipment) discountedShipment;
            ShipmentInterface finalShipment = new MotorwayClosureDecorator(discountedShipment2, closedMotorway);

            // Calculate and print the total cost
            System.out.println("Total Cost: $" + finalShipment.calculateTotalCost(monthNum));

            // Get and print the delivery plan
            System.out.println("Delivery Plan: " + finalShipment.getDeliveryPlan());
        }else if (discountRate < 0 || discountRate > 1){
            System.out.println("Invalid discount rate! Please try again later!");
        }else{
            System.out.println("Shipment cannot be found!");
        }
    }

    // Method for saving (serialization)
    public static void save2() {
        // Packing into protobuf objects
        PB_Stock.Builder pbStockBuilder = PB_Stock.newBuilder();

        for (int i = 0; i < stock.getListSize(); i++){
            PB_StockItem.Builder pbItemBuilder = PB_StockItem.newBuilder();
            pbItemBuilder.setQuantity(stock.getItem(i).getQuantity());

            Product prod = stock.getItem(i).getProduct();
            PB_Product.Builder pbProductBuilder = PB_Product.newBuilder();
            pbProductBuilder.setProductID(prod.getID())
            .setProductName(prod.getName())
            .setProductPrice(prod.getPrice())
            .setProductVolume(prod.getVolume());

            if (prod.getCategory().equals("Book")){
                pbProductBuilder.setProductCategory(PB_Product.ProdCategory.BOOK);
                Book b = (Book) prod;
                PB_Book.Builder pbBookBuilder = PB_Book.newBuilder();
                pbBookBuilder.setBookIBAN(b.getIBAN())
                .setBookGenre(b.getGenre())
                .setBookAuthor(b.getAuthor())
                .setProduct(pbProductBuilder);

                pbItemBuilder.setProductBook(pbBookBuilder);
            }else if (prod.getCategory().equals("Clothing")){
                pbProductBuilder.setProductCategory(PB_Product.ProdCategory.CLOTHING);
                Clothing c = (Clothing) prod;
                PB_Clothing.Builder pbClothingBuilder = PB_Clothing.newBuilder();
                pbClothingBuilder.setWidth(c.getWidth())
                .setLength(c.getLength())
                .setSizeClassification(c.getSize())
                .setProduct(pbProductBuilder);

                pbItemBuilder.setProductCloth(pbClothingBuilder);
            }else if (prod.getCategory().equals("Computer Game")){
                pbProductBuilder.setProductCategory(PB_Product.ProdCategory.COMPUTERGAME);
                ComputerGame cg = (ComputerGame) prod;
                PB_ComputerGame.Builder pbGameBuilder = PB_ComputerGame.newBuilder();
                pbGameBuilder.setGamePublisher(cg.getPublisher())
                .setProduct(pbProductBuilder);
                pbItemBuilder.setProductGame(pbGameBuilder);
            }else if (prod.getCategory().equals("Electronics")){
                pbProductBuilder.setProductCategory(PB_Product.ProdCategory.ELECTRONIC);
                Electronics e = (Electronics) prod;
                PB_Electronics.Builder pbElecBuilder = PB_Electronics.newBuilder();
                pbElecBuilder.setProduct(pbProductBuilder);
                pbItemBuilder.setProductElec(pbElecBuilder);
            }
            pbStockBuilder.addItems(pbItemBuilder);
        }

        PB_Stock pbStock = pbStockBuilder.build();

        // Packing all packages in list
        PB_PackagingList.Builder pbPackagingListBuilder = PB_PackagingList.newBuilder();

        for (Packaging pack: packagesList){
            PB_Packaging.Builder pbPackageBuilder = PB_Packaging.newBuilder();
            pbPackageBuilder.setPackagingID(pack.getID())
            .setCost(pack.getCost())
            .setMaterial(pack.getMaterial())
            .setCapacityInPackagingUnits(pack.getCapacity());

            pbPackagingListBuilder.addPackages(pbPackageBuilder);
        }

        PB_PackagingList pbPackagingList = pbPackagingListBuilder.build();


        // Packing all vehicles in list
        PB_VehiclesList.Builder pbVehiclesListBuilder  =PB_VehiclesList.newBuilder();

        for (Transport vehicle: vehiclesList){
            PB_Transport.Builder pbTransportBuilder = PB_Transport.newBuilder();
            pbTransportBuilder.setVehicleID(vehicle.getID())
            .setTransportatoinSpeed(vehicle.getSpeed())
            .setTransportationCostPerKm(vehicle.getCost());

            if (vehicle.getType().equals("Aircraft")){
                pbTransportBuilder.setVehicleType(PB_Transport.TransportType.AIRCRAFT);
                Aircraft ac = (Aircraft) vehicle;
                PB_Aircraft.Builder pbAircraftBuilder = PB_Aircraft.newBuilder();
                for (String as: ac.getList()){
                    pbAircraftBuilder.addAirSpaceList(as);
                }
                pbAircraftBuilder.setTransport(pbTransportBuilder);

                pbVehiclesListBuilder.addVehiclesAir(pbAircraftBuilder);
            }else if (vehicle.getType().equals("Sea Vessel")){
                pbTransportBuilder.setVehicleType(PB_Transport.TransportType.SEAVESSEL);
                SeaVessel sv = (SeaVessel) vehicle;
                PB_SeaVessel.Builder pbSeaVesselBuilder = PB_SeaVessel.newBuilder();

                for (String r: sv.getList()){
                    pbSeaVesselBuilder.addRouteList(r);
                }
                pbSeaVesselBuilder.setTransport(pbTransportBuilder);

                pbVehiclesListBuilder.addVehiclesSea(pbSeaVesselBuilder);
            }else if (vehicle.getType().equals("Truck")){
                pbTransportBuilder.setVehicleType(PB_Transport.TransportType.TRUCK);
                Truck t = (Truck) vehicle;
                PB_Truck.Builder pbTruckBuilder = PB_Truck.newBuilder();

                for (String mw: t.getList()){
                    pbTruckBuilder.addMotorwayList(mw);
                }
                pbTruckBuilder.setTransport(pbTransportBuilder);

                pbVehiclesListBuilder.addVehiclesTruck(pbTruckBuilder);
            }
        }

        PB_VehiclesList pbVehiclesList = pbVehiclesListBuilder.build();


        // Packing all customers in list
        PB_CustomersList.Builder pbCustomersListBuilder = PB_CustomersList.newBuilder();

        for (Customer cust: customersList){
            PB_Customer.Builder pbCustomerBuilder = PB_Customer.newBuilder();
            pbCustomerBuilder.setCustomerID(cust.getID())
            .setCustomerName(cust.getName())
            .setCustomerRoad(cust.getRoad())
            .setCustomerTown(cust.getTown())
            .setCustomerPostCode(cust.getPostCode());

            pbCustomersListBuilder.addCustomers(pbCustomerBuilder);
        }

        PB_CustomersList pbCustomersList = pbCustomersListBuilder.build();


        // Packing all orders in list
        PB_OrdersList.Builder pbOrdersListBuilder = PB_OrdersList.newBuilder();

        for (Shipment order: ordersList){
            PB_Shipment.Builder pbShipmentBuilder = PB_Shipment.newBuilder();
            pbShipmentBuilder.setShipmentID(order.getID())
            .setShipmentStatus(order.getStatus())
            .setDispatchDate(order.getDispatchDate())
            .setOrderDate(order.getOrderDate())
            .setShipmentDistance(order.getDistance());

            // Setting ordered items
            for (int i = 0; i < order.getListSize(); i++){
                PB_StockItem.Builder pbItemOrderedBuilder = PB_StockItem.newBuilder();
                pbItemOrderedBuilder.setQuantity(order.getOrderedQuantity(i));

                Product prod = order.getOrderedProduct(i);
                PB_Product.Builder pbProductBuilder = PB_Product.newBuilder();
                pbProductBuilder.setProductID(prod.getID())
                .setProductName(prod.getName())
                .setProductPrice(prod.getPrice())
                .setProductVolume(prod.getVolume());

                if (prod.getCategory().equals("Book")){
                    pbProductBuilder.setProductCategory(PB_Product.ProdCategory.BOOK);
                    Book b = (Book) prod;
                    PB_Book.Builder pbBookBuilder = PB_Book.newBuilder();
                    pbBookBuilder.setBookIBAN(b.getIBAN())
                    .setBookGenre(b.getGenre())
                    .setBookAuthor(b.getAuthor())
                    .setProduct(pbProductBuilder);

                    pbItemOrderedBuilder.setProductBook(pbBookBuilder);
                }else if (prod.getCategory().equals("Clothing")){
                    pbProductBuilder.setProductCategory(PB_Product.ProdCategory.CLOTHING);
                    Clothing c = (Clothing) prod;
                    PB_Clothing.Builder pbClothingBuilder = PB_Clothing.newBuilder();
                    pbClothingBuilder.setWidth(c.getWidth())
                    .setLength(c.getLength())
                    .setSizeClassification(c.getSize())
                    .setProduct(pbProductBuilder);

                    pbItemOrderedBuilder.setProductCloth(pbClothingBuilder);
                }else if (prod.getCategory().equals("Computer Game")){
                    pbProductBuilder.setProductCategory(PB_Product.ProdCategory.COMPUTERGAME);
                    ComputerGame cg = (ComputerGame) prod;
                    PB_ComputerGame.Builder pbGameBuilder = PB_ComputerGame.newBuilder();
                    pbGameBuilder.setGamePublisher(cg.getPublisher())
                    .setProduct(pbProductBuilder);

                    pbItemOrderedBuilder.setProductGame(pbGameBuilder);
                }else if (prod.getCategory().equals("Electronics")){
                    pbProductBuilder.setProductCategory(PB_Product.ProdCategory.ELECTRONIC);
                    Electronics e = (Electronics) prod;
                    PB_Electronics.Builder pbElecBuilder = PB_Electronics.newBuilder();
                    pbElecBuilder.setProduct(pbProductBuilder);
                    pbItemOrderedBuilder.setProductElec(pbElecBuilder);
                }
            }

            // Setting packaging
            Packaging pack = order.getPackaging();
            PB_Packaging.Builder pbPackageBuilder = PB_Packaging.newBuilder();
            pbPackageBuilder.setPackagingID(pack.getID())
            .setCost(pack.getCost())
            .setMaterial(pack.getMaterial())
            .setCapacityInPackagingUnits(pack.getCapacity());

            pbShipmentBuilder.setShipmentPackaging(pbPackageBuilder);


            // Setting customer
            Customer cust = order.getCustomer();
            PB_Customer.Builder pbCustomerBuilder = PB_Customer.newBuilder();
            pbCustomerBuilder.setCustomerID(cust.getID())
            .setCustomerName(cust.getName())
            .setCustomerRoad(cust.getRoad())
            .setCustomerTown(cust.getTown())
            .setCustomerPostCode(cust.getPostCode());

            pbShipmentBuilder.setShipmentCustomer(pbCustomerBuilder);

            // Setting transport
            Transport vehicle = order.getTransport();
            PB_Transport.Builder pbTransportBuilder = PB_Transport.newBuilder();
            pbTransportBuilder.setVehicleID(vehicle.getID())
            .setTransportatoinSpeed(vehicle.getSpeed())
            .setTransportationCostPerKm(vehicle.getCost());

            if (vehicle.getType().equals("Aircraft")){
                pbTransportBuilder.setVehicleType(PB_Transport.TransportType.AIRCRAFT);
                Aircraft ac = (Aircraft) vehicle;
                PB_Aircraft.Builder pbAircraftBuilder = PB_Aircraft.newBuilder();
                for (String as: ac.getList()){
                    pbAircraftBuilder.addAirSpaceList(as);
                }

                pbAircraftBuilder.setTransport(pbTransportBuilder);
                pbShipmentBuilder.setShipmentAircraft(pbAircraftBuilder);
            }else if (vehicle.getType().equals("Sea Vessel")){
                pbTransportBuilder.setVehicleType(PB_Transport.TransportType.SEAVESSEL);
                SeaVessel sv = (SeaVessel) vehicle;
                PB_SeaVessel.Builder pbSeaVesselBuilder = PB_SeaVessel.newBuilder();

                for (String r: sv.getList()){
                    pbSeaVesselBuilder.addRouteList(r);
                }

                pbSeaVesselBuilder.setTransport(pbTransportBuilder);

                pbShipmentBuilder.setShipmentVessel(pbSeaVesselBuilder);
            }else if (vehicle.getType().equals("Truck")){
                pbTransportBuilder.setVehicleType(PB_Transport.TransportType.TRUCK);
                Truck t = (Truck) vehicle;
                PB_Truck.Builder pbTruckBuilder = PB_Truck.newBuilder();

                for (String mw: t.getList()){
                    pbTruckBuilder.addMotorwayList(mw);
                }

                pbTruckBuilder.setTransport(pbTransportBuilder);
                pbShipmentBuilder.setShipmentTruck(pbTruckBuilder);
            }

            pbOrdersListBuilder.addOrders(pbShipmentBuilder);
        }

        PB_OrdersList pbOrdersList = pbOrdersListBuilder.build();

        // Serializing stock
        try (FileOutputStream out = new FileOutputStream(STOCK_FILE)) {
            pbStock.writeTo(out);
        }catch (IOException e) {
            System.err.println("Failed to write stock to output file!");
        }

        // Serializing the lists
        try (FileOutputStream out = new FileOutputStream(VEHICLES_FILE)) {
            pbVehiclesList.writeTo(out);
        }catch (IOException e) {
            System.err.println("Failed to write vehicles to output file!");
        }

        try (FileOutputStream out = new FileOutputStream(PACKAGING_FILE)) {
            pbPackagingList.writeTo(out);
        }catch (IOException e) {
            System.err.println("Failed to write packages to output file!");
        }

        try (FileOutputStream out = new FileOutputStream(CUSTOMERS_FILE)) {
            pbCustomersList.writeTo(out);
        }catch (IOException e) {
            System.err.println("Failed to write customers to output file!");
        }

        try (FileOutputStream out = new FileOutputStream(ORDERS_FILE)) {
            pbOrdersList.writeTo(out);
        }catch (IOException e) {
            System.err.println("Failed to write orders to output file!");
        }
    }

    // Method for loading (deserialization)
    public static void load2() {
        PB_Stock pbStock2;
        PB_PackagingList pbPackagingList2;
        PB_VehiclesList pbVehiclesList2;
        PB_CustomersList pbCustomersList2;
        PB_OrdersList pbOrdersList2;

        // Deserializing stock
        try (FileInputStream in = new FileInputStream(STOCK_FILE)) {
            pbStock2 = PB_Stock.parseFrom(in);
        } catch (IOException e) {
            System.out.println("Failed to read stock from input file!");
            return;
        }

        // Deserializing the lists
        try (FileInputStream in = new FileInputStream(VEHICLES_FILE)) {
            pbVehiclesList2 = PB_VehiclesList.parseFrom(in);
        } catch (IOException e) {
            System.out.println("Failed to read list of vehicles from input file!");
            return;
        }

        try (FileInputStream in = new FileInputStream(PACKAGING_FILE)) {
            pbPackagingList2 = PB_PackagingList.parseFrom(in);
        } catch (IOException e) {
            System.out.println("Failed to read list of packages from input file!");
            return;
        }

        try (FileInputStream in = new FileInputStream(CUSTOMERS_FILE)) {
            pbCustomersList2 = PB_CustomersList.parseFrom(in);
        } catch (IOException e) {
            System.out.println("Failed to read list of customers from input file!");
            return;
        }

        try (FileInputStream in = new FileInputStream(ORDERS_FILE)) {
            pbOrdersList2 = PB_OrdersList.parseFrom(in);
        } catch (IOException e) {
            System.out.println("Failed to read list of orders from input file!");
            return;
        }

        // Unpacking stock object
        stock.getItemsList().clear();

        for (PB_StockItem item: pbStock2.getItemsList()){
            PB_Book pbBook = item.getProductBook();
            PB_Clothing pbCloth = item.getProductCloth();
            PB_ComputerGame pbGame = item.getProductGame();
            PB_Electronics pbElec = item.getProductElec();
            if (pbBook.getProduct().getProductName().length() > 0){
                Book inBook = new Book(
                    pbBook.getProduct().getProductID(),
                    pbBook.getProduct().getProductName(),
                    pbBook.getProduct().getProductPrice(),
                    "Book",
                    pbBook.getProduct().getProductVolume(),
                    pbBook.getBookIBAN(),
                    pbBook.getBookGenre(),
                    pbBook.getBookAuthor()
                );

                stock.addItem(new StockItem(item.getQuantity(), inBook));
            }else if (pbCloth.getProduct().getProductName().length() > 0){
                Clothing inCloth = new Clothing(
                    pbCloth.getProduct().getProductID(),
                    pbCloth.getProduct().getProductName(),
                    pbCloth.getProduct().getProductPrice(),
                    "Clothing",
                    pbCloth.getProduct().getProductVolume(),
                    pbCloth.getWidth(),
                    pbCloth.getLength(),
                    pbCloth.getSizeClassification()
                );

                stock.addItem(new StockItem(item.getQuantity(), inCloth));
            }else if (pbGame.getProduct().getProductName().length() > 0){
                ComputerGame inGame = new ComputerGame(
                    pbGame.getProduct().getProductID(),
                    pbGame.getProduct().getProductName(),
                    pbGame.getProduct().getProductPrice(),
                    "Computer Game",
                    pbGame.getProduct().getProductVolume(),
                    pbGame.getGamePublisher()
                );

                stock.addItem(new StockItem(item.getQuantity(), inGame));
            }else if (pbElec.getProduct().getProductName().length() > 0){
                Electronics inElec = new Electronics(
                    pbElec.getProduct().getProductID(),
                    pbElec.getProduct().getProductName(),
                    pbElec.getProduct().getProductPrice(),
                    "Electronics",
                    pbElec.getProduct().getProductVolume()
                );

                stock.addItem(new StockItem(item.getQuantity(), inElec));
            }
        }

        // Unpacking packaging objects
        packagesList.clear();
        for (PB_Packaging pbPackage : pbPackagingList2.getPackagesList()) {
            Packaging inPack = new Packaging(
                pbPackage.getPackagingID(),
                pbPackage.getCost(),
                pbPackage.getMaterial(),
                pbPackage.getCapacityInPackagingUnits()
            );
            packagesList.add(inPack);
        }

        // Unpacking vehicle objects
        vehiclesList.clear();

        // Unpacking all trucks
        for (PB_Truck pbTruck: pbVehiclesList2.getVehiclesTruckList()) {
            Truck inTruck = new Truck(
                pbTruck.getTransport().getVehicleID(),
                "Truck",
                pbTruck.getTransport().getTransportatoinSpeed(),
                pbTruck.getTransport().getTransportationCostPerKm()
            );

            for (String pbMotorway: pbTruck.getMotorwayListList()){
                inTruck.addToList(pbMotorway);
            }
            vehiclesList.add(inTruck);
        }

        // Unpacking all sea vessels
        for (PB_SeaVessel pbSeaVessel: pbVehiclesList2.getVehiclesSeaList()) {
            SeaVessel inVessel = new SeaVessel(
                pbSeaVessel.getTransport().getVehicleID(),
                "Sea Vessel",
                pbSeaVessel.getTransport().getTransportatoinSpeed(),
                pbSeaVessel.getTransport().getTransportationCostPerKm()
            );

            for (String pbRoute: pbSeaVessel.getRouteListList()){
                inVessel.addToList(pbRoute);
            }
            vehiclesList.add(inVessel);
        }

        // Unpacking all aircrafts
        for (PB_Aircraft pbAircraft: pbVehiclesList2.getVehiclesAirList()) {
            Aircraft inAir = new Aircraft(
                pbAircraft.getTransport().getVehicleID(),
                "Aircraft",
                pbAircraft.getTransport().getTransportatoinSpeed(),
                pbAircraft.getTransport().getTransportationCostPerKm()
            );

            for (String pbAirSpace: pbAircraft.getAirSpaceListList()){
                inAir.addToList(pbAirSpace);
            }
            vehiclesList.add(inAir);
        }

        // Unpacking customer objects
        customersList.clear();
        for (PB_Customer pbCustomer : pbCustomersList2.getCustomersList()) {
            Customer inCustomer = new Customer(
                pbCustomer.getCustomerID(),
                pbCustomer.getCustomerName(),
                pbCustomer.getCustomerRoad(),
                pbCustomer.getCustomerTown(),
                pbCustomer.getCustomerPostCode()
            );
            customersList.add(inCustomer);
        }

        // Unpacking order objects
        ordersList.clear();
        for (PB_Shipment pbOrder : pbOrdersList2.getOrdersList()) {
            PB_Packaging pbPackage = pbOrder.getShipmentPackaging();
            Packaging inPack = new Packaging(
                pbPackage.getPackagingID(),
                pbPackage.getCost(),
                pbPackage.getMaterial(),
                pbPackage.getCapacityInPackagingUnits()
            );

            PB_Customer pbCustomer = pbOrder.getShipmentCustomer();
            Customer inCustomer = new Customer(
                pbCustomer.getCustomerID(),
                pbCustomer.getCustomerName(),
                pbCustomer.getCustomerRoad(),
                pbCustomer.getCustomerTown(),
                pbCustomer.getCustomerPostCode()
            );

            Shipment inOrder = new Shipment(
                pbOrder.getShipmentID(),
                inCustomer,
                pbOrder.getOrderDate(),
                inPack
            );

            PB_Truck pbTruck = pbOrder.getShipmentTruck();
            PB_SeaVessel pbSeaVessel = pbOrder.getShipmentVessel();
            PB_Aircraft pbAircraft = pbOrder.getShipmentAircraft();

            if (pbTruck.getTransport().getTransportatoinSpeed() > 0){
                Truck inTruck = new Truck(
                    pbTruck.getTransport().getVehicleID(),
                    "Truck",
                    pbTruck.getTransport().getTransportatoinSpeed(),
                    pbTruck.getTransport().getTransportationCostPerKm()
                );

                for (String pbMotorway: pbTruck.getMotorwayListList()){
                    inTruck.addToList(pbMotorway);
                }

                inOrder.setTransport(inTruck);
            }else if (pbSeaVessel.getTransport().getTransportatoinSpeed() > 0){
                SeaVessel inVessel = new SeaVessel(
                    pbSeaVessel.getTransport().getVehicleID(),
                    "Sea Vessel",
                    pbSeaVessel.getTransport().getTransportatoinSpeed(),
                    pbSeaVessel.getTransport().getTransportationCostPerKm()
                );

                for (String pbRoute: pbSeaVessel.getRouteListList()){
                    inVessel.addToList(pbRoute);
                }

                inOrder.setTransport(inVessel);
            }else if (pbAircraft.getTransport().getTransportatoinSpeed() > 0){
                Aircraft inAir = new Aircraft(
                    pbAircraft.getTransport().getVehicleID(),
                    "Aircraft",
                    pbAircraft.getTransport().getTransportatoinSpeed(),
                    pbAircraft.getTransport().getTransportationCostPerKm()
                );

                for (String pbAirSpace: pbAircraft.getAirSpaceListList()){
                    inAir.addToList(pbAirSpace);
                }

                inOrder.setTransport(inAir);
            }

            for (PB_StockItem item: pbOrder.getItemsToShipList()){
                PB_Book pbBook = item.getProductBook();
                PB_Clothing pbCloth = item.getProductCloth();
                PB_ComputerGame pbGame = item.getProductGame();
                PB_Electronics pbElec = item.getProductElec();
                if (pbBook.getProduct().getProductName().length() > 0){
                    Book inBook = new Book(
                        pbBook.getProduct().getProductID(),
                        pbBook.getProduct().getProductName(),
                        pbBook.getProduct().getProductPrice(),
                        "Book",
                        pbBook.getProduct().getProductVolume(),
                        pbBook.getBookIBAN(),
                        pbBook.getBookGenre(),
                        pbBook.getBookAuthor()
                    );

                    inOrder.addItemToList(new StockItem(item.getQuantity(), inBook));
                }else if (pbCloth.getProduct().getProductName().length() > 0){
                    Clothing inCloth = new Clothing(
                        pbCloth.getProduct().getProductID(),
                        pbCloth.getProduct().getProductName(),
                        pbCloth.getProduct().getProductPrice(),
                        "Clothing",
                        pbCloth.getProduct().getProductVolume(),
                        pbCloth.getWidth(),
                        pbCloth.getLength(),
                        pbCloth.getSizeClassification()
                    );

                    inOrder.addItemToList(new StockItem(item.getQuantity(), inCloth));
                }else if (pbGame.getProduct().getProductName().length() > 0){
                    ComputerGame inGame = new ComputerGame(
                        pbGame.getProduct().getProductID(),
                        pbGame.getProduct().getProductName(),
                        pbGame.getProduct().getProductPrice(),
                        "Computer Game",
                        pbGame.getProduct().getProductVolume(),
                        pbGame.getGamePublisher()
                    );

                    inOrder.addItemToList(new StockItem(item.getQuantity(), inGame));
                }else if (pbElec.getProduct().getProductName().length() > 0){
                    Electronics inElec = new Electronics(
                        pbElec.getProduct().getProductID(),
                        pbElec.getProduct().getProductName(),
                        pbElec.getProduct().getProductPrice(),
                        "Electronics",
                        pbElec.getProduct().getProductVolume()
                    );

                    inOrder.addItemToList(new StockItem(item.getQuantity(), inElec));
                }
            }

            ordersList.add(inOrder);
        }
    }
}