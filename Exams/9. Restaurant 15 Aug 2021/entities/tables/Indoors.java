package restaurant.entities.tables.interfaces;

public class Indoors extends BaseTable{


    private static final double price_Per_Person = 3.50;

    public Indoors(int number, int size) {
        super(number, size, price_Per_Person);
    }
}
