package aquarium.entities.fish;

public class FreshwaterFish extends BaseFish {

    private static final int INITIAL_SIZE = 3;
    // Setter should be created in the BaseFish for the size
    private static final int INCREASES = 3;

    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
        super.setSize(INITIAL_SIZE);
    }

    @Override
    public void eat() {
        int oldSize = super.getSize();
        super.setSize(oldSize + INCREASES);

    }
}
