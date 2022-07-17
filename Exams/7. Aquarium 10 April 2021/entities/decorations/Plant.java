package aquarium.entities.decorations;

public class Plant extends BaseDecoration{


    private static final int COMFORT = 5;
    private static final double PRICE = 10;

    public Plant() {  //Constructor should take no values upon initialization.
        super(COMFORT, PRICE);
    }
}
