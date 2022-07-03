package InterfacesAndAbstraction.CarShop;

public interface Car {

    static final int TIRES = 4;

    String getModel();
    String getColor();
    Integer getHorsePower();
    String countryProduced();
}
