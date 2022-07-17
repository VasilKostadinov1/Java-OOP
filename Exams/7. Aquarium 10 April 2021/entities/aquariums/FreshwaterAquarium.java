package aquarium.entities.aquariums;

public class FreshwaterAquarium extends BaseAquarium{

    private static final int CAPACITY = 50;

    //Constructor should take the following values upon initialization: String name
    public FreshwaterAquarium(String name) {
        super(name, CAPACITY);
    }
}
