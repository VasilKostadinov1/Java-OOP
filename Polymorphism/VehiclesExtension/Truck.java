package Interfaces.Vehicles_01;

public class Truck extends VehicleImpl {

    private static final double AC_ADDITIONAL_CONSUMPTION = 1.6;
    private static final double REFILL_PERCENTAGE = 0.95;

    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
        super.setFuelConsumption(super.getFuelConsumption()+AC_ADDITIONAL_CONSUMPTION);
    }

//    public Truck(double fuelQuantity, double fuelConsumption) {
//        super(fuelQuantity, fuelConsumption);
//        super.setFuelConsumption(super.getFuelConsumption() + AC_ADDITIONAL_CONSUMPTION);
//    }

    @Override
    public void refuel(double liters) {
        liters = liters * REFILL_PERCENTAGE;
        // this.fuelQuantity += liters;  // make fuelQuantity "protected" to be used
        super.refuel(liters);
    }
}
