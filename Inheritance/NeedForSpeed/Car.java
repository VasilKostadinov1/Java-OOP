package Inheritance_Lab_Exercises.NeedForSpeed_04;

public class Car extends Vehicle{

    private static final double DEFAULT_FUEL_CONSUMPTION = 3;

    public Car(double fuel, int horsePower) {
        super(fuel, horsePower);
        //In the child classes' constructors use super.setFuelConsumption() to set fuelConsumption.
        super.setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
        // this.setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);  // Zahary
    }
}
