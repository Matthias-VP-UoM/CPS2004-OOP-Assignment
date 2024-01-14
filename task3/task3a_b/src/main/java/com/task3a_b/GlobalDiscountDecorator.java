package com.task3a_b;

public class GlobalDiscountDecorator extends ShipmentDecorator {
    private double globalDiscount;

    public GlobalDiscountDecorator(Shipment decoratedShipment, double globalDiscount) {
        super(decoratedShipment);
        this.globalDiscount = globalDiscount;
    }

    @Override
    public double calculateTotalCost() {
        // Apply global discount to the total cost
        double discountedCost = super.calculateTotalCost() * (1 - globalDiscount);
        System.out.println("Applying Global Discount: " + globalDiscount * 100 + "%");
        return discountedCost;
    }
}
