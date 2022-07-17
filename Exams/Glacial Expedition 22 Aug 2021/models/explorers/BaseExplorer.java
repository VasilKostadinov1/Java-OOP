package glacialExpedition.models.explorers;

import glacialExpedition.common.ConstantMessages;
import glacialExpedition.common.ExceptionMessages;
import glacialExpedition.models.suitcases.Carton;
import glacialExpedition.models.suitcases.Suitcase;

public abstract class BaseExplorer implements Explorer {

    private String name;
    private double energy;
    private Suitcase suitcase;

    public BaseExplorer(String name, double energy) {
        this.setName(name);
        this.setEnergy(energy);
         this.suitcase = new Carton();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.EXPLORER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setEnergy(double energy) {
        if (energy < 0) {
            throw new IllegalArgumentException(ExceptionMessages.EXPLORER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getEnergy() {
        return this.energy;
    }

    @Override
    public boolean canSearch() {
        return this.energy > 0;
    }

    @Override
    public Suitcase getSuitcase() {
        return this.suitcase;
    }

    @Override
    public void search() {
        this.energy = Math.max(0, energy - 15);

    }

    @Override
    public String toString() {
        //•	If the explorers don't have any suitcase exhibits, print "None" on their place.....
//        "Name: {explorerName}"
//        "Energy: {explorerName}"
//        "Suitcase exhibits: {suitcaseExhibits1, suitcaseExhibits2, suitcaseExhibits3, …, suitcaseExhibits n}"
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ConstantMessages.FINAL_EXPLORER_NAME, name)).append(System.lineSeparator());
        sb.append(String.format(ConstantMessages.FINAL_EXPLORER_ENERGY, this.getEnergy())).append(System.lineSeparator());
        sb.append("Suitcase exhibits: ");
        boolean isNull = this.suitcase.getExhibits().isEmpty();
        if (isNull){
            sb.append("None").append(System.lineSeparator());
        }else {
            sb.append(String.join(", ", this.getSuitcase().getExhibits())).append(System.lineSeparator());
        }
        return sb.toString().trim();

    }
}
