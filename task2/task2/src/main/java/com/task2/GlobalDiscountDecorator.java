package com.task2;

public class GlobalDiscountDecorator extends ShipmentDecorator {
    private double globalDiscount;

    public GlobalDiscountDecorator(Shipment decoratedShipment, double globalDiscount) {
        super(decoratedShipment);
        this.globalDiscount = globalDiscount;
    }

    @Override
    public double calculateTotalCost(int monthNum) {
        // Apply global discount to the total cost
        double discountedCost = super.calculateTotalCost(monthNum) * (1 - globalDiscount);
        System.out.println("Applying Global Discount: " + globalDiscount * 100 + "%");
        return discountedCost;
    }
}
