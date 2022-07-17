package fairyShop.models;

import fairyShop.common.ExceptionMessages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseHelper implements Helper {
    private String name;
    private int energy;
    private Collection<Instrument> instruments;   // A collection of a helper's instruments.

    private static final int ZERO_ENERGY = 0;
    private static final int DECREASE_IN_ENERGY = 10;

    protected BaseHelper(String name, int energy) {  // protected
        this.setName(name);
        this.setEnergy(energy);
        this.instruments = new ArrayList<>();
    }

    private void setName(String name) {       // private Setters
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.HELPER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setEnergy(int energy) {
//        if (energy<ZERO_ENERGY){
//            throw new IllegalArgumentException(ExceptionMessages.HELPER_ENERGY_LESS_THAN_ZERO);
//        }
        this.energy = energy;
    }

    @Override
    public void work() {
        //•	A helper's energy should not drop below 0 (If the power becomes less than 0, set it to 0).
        this.energy = Math.max(ZERO_ENERGY, this.energy - DECREASE_IN_ENERGY);
//        this.energy -= 10;
//        if(this.energy < 0){
//            this.energy = 0;
//        }

    }

    @Override
    public void addInstrument(Instrument instrument) {
        //adds an instrument to the helper's collection of instruments
        this.instruments.add(instrument);

    }

    @Override
    public boolean canWork() {
        //•	true - if the current energy of the helper is greater than 0;
        return this.energy > ZERO_ENERGY;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return this.instruments;
    }
//    "Name: {helperName1}"
//    "Energy: {helperEnergy1}"
//     "Instruments: {countInstruments} not broken left"
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        List<Instrument> notBrokenInstruments = this.instruments
                .stream().filter(i -> !i.isBroken()).collect(Collectors.toList());

        sb.append(String.format("Name: %s", name)).append(System.lineSeparator());
        sb.append(String.format("Energy: %d",energy)).append(System.lineSeparator());
        sb.append(String.format("Instruments: %d not broken left",notBrokenInstruments.size() ))
                .append(System.lineSeparator());

        return sb.toString().trim();
    }
}
