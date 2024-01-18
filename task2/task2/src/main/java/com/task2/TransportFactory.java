package com.task2;

public class TransportFactory implements TFInterface {
    @Override
    public Transport createTransport(String type) {
        Transport vehicle;

        // Instantiate the vehicle based on the type
        switch (type) {
            case "Aircraft":
                vehicle = new Aircraft();
                break;
            case "Sea Vessel":
                vehicle =  new SeaVessel();
                break;
            case "Truck":
                vehicle = new Truck();
                break;
            default:
                throw new IllegalArgumentException("Invalid transport type: " + type);
        }

        return vehicle;
    }
}
