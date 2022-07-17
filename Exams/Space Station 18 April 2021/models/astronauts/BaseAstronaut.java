package spaceStation.models.astronauts;

import spaceStation.common.ExceptionMessages;
import spaceStation.models.bags.Backpack;
import spaceStation.models.bags.Bag;

import java.util.stream.Collectors;

public abstract class BaseAstronaut implements Astronaut {

    private static final int DECREASE_OXYGEN = 10;

    private String name;
    private double oxygen;
    private Bag bag;

    protected BaseAstronaut(String name, double oxygen) {
        this.setName(name);
        this.setOxygen(oxygen);
        this.bag = new Backpack();

    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.ASTRONAUT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setOxygen(double oxygen) {
        if (oxygen < 0) {
            throw new IllegalArgumentException(ExceptionMessages.ASTRONAUT_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getOxygen() {
        return this.oxygen;
    }

    @Override
    public boolean canBreath() {
        return this.oxygen > 0;
    }

    @Override
    public Bag getBag() {
        return this.bag;
    }

    @Override
    public void breath() {
        this.oxygen = Math.max(0, this.oxygen - DECREASE_OXYGEN);
        //this.setOxygen(this.getOxygen() - 10);
    }

//    "Astronauts info:"
//    "Name: {astronautName One}"
//    "Oxygen: {astronautOxygen One}"
//     "Bag items: {bagItem1, bagItem2, bagItem3, …, bagItemn \ "none"}"

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Name: %s", name)).append(System.lineSeparator());
        sb.append(String.format("Oxygen: %.0f", oxygen)).append(System.lineSeparator());
        sb.append("Bag items: ");

        if (bag.getItems().size() == 0) {
            sb.append("none");
        } else {
            sb.append(String.join(", ", bag.getItems()));
        }
        sb.append(System.lineSeparator());
        return sb.toString().trim();

//        String bagItems = this.getBag().getItems().size() == 0
//                ? "none"
//                : String.join(", ", this.bag.getItems());
//
//        StringBuilder info = new StringBuilder();
//
//        info.append(String.format("Name: %s", this.name)).append(System.lineSeparator());
//        info.append(String.format("Oxygen: %.0f", this.oxygen)).append(System.lineSeparator());
//        info.append(String.format("Bag items: %s", bagItems));
//
//        return info.toString().trim();
    }
}
