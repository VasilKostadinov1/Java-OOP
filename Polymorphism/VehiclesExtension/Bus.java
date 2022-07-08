package Interfaces.Vehicles_01;

public class Bus extends VehicleImpl {

    private static final double AC_ADDITIONAL_CONSUMPTION = 1.4;
    private boolean isEmpty = true;

    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
        isEmpty =true;
    }
    //Add new command for the bus. The bus can drive with or without people.

    public void setEmpty(boolean empty) {
        if (this.isEmpty == empty) {
            return;
        }
        if (this.isEmpty && !empty) {
            super.setFuelConsumption(super.getFuelConsumption() + AC_ADDITIONAL_CONSUMPTION);
        }
        if (!this.isEmpty && empty) {
            super.setFuelConsumption(super.getFuelConsumption() - AC_ADDITIONAL_CONSUMPTION);
        }

        this.isEmpty = empty;
    }


}
