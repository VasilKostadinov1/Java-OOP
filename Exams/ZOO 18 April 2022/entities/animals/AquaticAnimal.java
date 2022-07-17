package zoo.entities.animals;

public class AquaticAnimal extends BaseAnimal{

    private static final double INITIAL_KG  = 2.50;
    private static final double INCREASE_IN_KG  = 7.50;


    public AquaticAnimal(String name, String kind,  double price) {
        super(name, kind, INITIAL_KG, price);
    }

    @Override
    public void eat(){
        this.setKg(INITIAL_KG + INCREASE_IN_KG);
    }
}
