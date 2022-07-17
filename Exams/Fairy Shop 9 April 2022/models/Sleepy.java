package fairyShop.models;

public class Sleepy extends BaseHelper{

    private static final int INITIAL_ENERGY = 50;
    //The method work() decreases the helpers' energy by additional 5 units.
    private static final int DECREASE_IN_ENERGY = 15;

    public Sleepy(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void work() {
        super.setEnergy(Math.max(0, INITIAL_ENERGY-DECREASE_IN_ENERGY));
        //this.setEnergy(Math.max(0, INITIAL_ENERGY-DECREASE_IN_ENERGY));

//        setEnergy(getEnergy() - 5);
//        super.work();
    }
}
