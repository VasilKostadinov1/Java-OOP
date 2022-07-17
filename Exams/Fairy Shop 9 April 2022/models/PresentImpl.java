package fairyShop.models;

import fairyShop.common.ExceptionMessages;

public class PresentImpl implements Present {

    private String name;
    private int energyRequired;
    private static final int ENERGY_DECREASE = 10;

    public PresentImpl(String name, int energyRequired) {
        this.setName(name);
        this.setEnergyRequired(energyRequired);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.PRESENT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    private void setEnergyRequired(int energyRequired) {
        if (energyRequired < 0) {
            throw new IllegalStateException(ExceptionMessages.PRESENT_ENERGY_LESS_THAN_ZERO);
        }
        this.energyRequired = energyRequired;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergyRequired() {
        return this.energyRequired;
    }

    @Override
    public boolean isDone() {  //returns true if the energyRequired reaches 0.
        return this.energyRequired == 0;
    }

    @Override
    public void getCrafted() {
        //decreases the required energy of the present by 10 units.
        //•	A present's required energy should not drop below 0 (If the energy becomes less than 0, set it to 0).
        this.energyRequired = Math.max(0, this.energyRequired - ENERGY_DECREASE);

    }
}
