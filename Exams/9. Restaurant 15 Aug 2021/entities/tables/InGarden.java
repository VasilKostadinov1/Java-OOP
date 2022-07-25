package restaurant.entities.tables.interfaces;

public class InGarden extends BaseTable{


    private static final double price_Per_Person = 4.50;

    public InGarden(int number, int size) {
        super(number, size, price_Per_Person);
    }
}
