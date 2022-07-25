package restaurant.entities.drinks.interfaces;

public class Fresh extends BaseBeverage{

    private static final double fresh_Price = 3.50;

    public Fresh(String name, int counter, String brand) {
        super(name, counter, fresh_Price, brand);
    }
}
