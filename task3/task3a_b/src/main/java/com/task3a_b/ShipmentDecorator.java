package com.task3a_b;

abstract class ShipmentDecorator implements ShipmentInterface {
    protected Shipment decoratedShipment;

    public ShipmentDecorator(){}

    public ShipmentDecorator(Shipment decoratedShipment) {
        this.decoratedShipment = decoratedShipment;
    }

    @Override
    public double calculateTotalCost() {
        return decoratedShipment.calculateTotalCost();
    }

    @Override
    public String getDeliveryPlan() {
        return decoratedShipment.getDeliveryPlan();
    }
}
