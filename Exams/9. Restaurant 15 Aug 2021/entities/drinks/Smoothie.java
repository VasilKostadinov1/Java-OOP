package restaurant.entities.drinks.interfaces;

public class Smoothie extends BaseBeverage{

    private static final double smoothie_Price = 4.50;


    public Smoothie(String name, int counter, String brand) {
        super(name, counter, smoothie_Price, brand);
    }
}
