package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{


    private static final int INITIAL_ENERGY = 60;
    private static final int DECREASE_IN_ENERGY = 7;

    public NaturalExplorer(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void search() {
        //this.setEnergy(INITIAL_ENERGY - DECREASE_IN_ENERGY);
        this.setEnergy(this.getEnergy() - DECREASE_IN_ENERGY);
    }
}
