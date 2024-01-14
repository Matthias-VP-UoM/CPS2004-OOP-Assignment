package com.task2;

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
