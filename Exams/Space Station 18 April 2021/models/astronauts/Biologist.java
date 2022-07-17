package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut{


    private static final int DECREASE_OXYGEN  = 5;
    private static final int INITIAL_OXYGEN  = 70;

    public Biologist(String name) {
        super(name, INITIAL_OXYGEN);
    }
    @Override
    public void breath() {
        this.setOxygen(INITIAL_OXYGEN - DECREASE_OXYGEN);
        //this.setOxygen(this.getOxygen() - DECREASE_OXYGEN);
    }
}
