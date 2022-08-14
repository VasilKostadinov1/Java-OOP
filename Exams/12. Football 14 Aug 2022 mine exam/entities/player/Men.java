package football.entities.player;

public class Men extends BasePlayer{

    private static final double INITIAL_KG = 85.50;
    private static final int INCREASE_STRENGTH = 145;

    public Men(String name, String nationality, int strength) {
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
