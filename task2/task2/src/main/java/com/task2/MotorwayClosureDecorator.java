package com.task2;

public class MotorwayClosureDecorator extends ShipmentDecorator {
    private String closedMotorway;

    public MotorwayClosureDecorator(Shipment decoratedShipment, String closedMotorway) {
        super(decoratedShipment);
        this.closedMotorway = closedMotorway;
    }

    @Override
    public String getDeliveryPlan() {
        // Modify the delivery plan based on motorway closure
        String originalPlan = super.getDeliveryPlan();
        String modifiedPlan = originalPlan + "\nMotorway Closure: " + closedMotorway;
        System.out.println("Adjusting Delivery Plan due to Motorway Closure: " + closedMotorway);
        return modifiedPlan;
    }
}

