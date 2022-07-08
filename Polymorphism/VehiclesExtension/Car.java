package Interfaces.Vehicles_01;

public class Car extends VehicleImpl{

    private static final double AC_ADDITIONAL_CONSUMPTION = 0.9;

    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
        super.setFuelConsumption(super.getFuelConsumption()+AC_ADDITIONAL_CONSUMPTION);
    }


//    public Car(double fuelQuantity, double fuelConsumption) {
//        super(fuelQuantity, fuelConsumption);
//        super.setFuelConsumption(super.getFuelConsumption()+AC_ADDITIONAL_CONSUMPTION);  // 1st var
//    }

    //2nd var
//    @Override
//    public  void setFuelConsumption(double fuelConsumption){
//        super.setFuelConsumption(super.getFuelConsumption()+AC_ADDITIONAL_CONSUMPTION);
//    }


}
