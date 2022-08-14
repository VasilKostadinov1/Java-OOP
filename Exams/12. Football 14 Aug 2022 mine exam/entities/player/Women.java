package football.entities.player;

public class Women extends BasePlayer {

    private static final double INITIAL_KG = 60;
    private static final int INCREASE_STRENGTH = 115;

    public Women(String name, String nationality, int strength) {
        super(name, nationality, strength);
        setKg(INITIAL_KG);
    }

    @Override
    public void stimulation() {
        this.setStrength(this.getStrength() + INCREASE_STRENGTH);
    }

//    @Override
//    public void setKg(double kg) {
//        kg=INITIAL_KG;
//    }
}
