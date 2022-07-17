package aquarium.entities.fish;

import aquarium.common.ExceptionMessages;

public abstract class BaseFish implements Fish {

    private String name;
    private String species;
    private int size;
    private double price;

    protected BaseFish(String name, String species, double price) {  // protected
        this.setName(name);
        this.setSpecies(species);
        this.setPrice(price);
    }

    protected void setSize(int size) {  // protected
        this.size = size;
    }

    private void setSpecies(String species) {    // change Setters to "Private"
        if (species == null || species.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.FISH_NAME_NULL_OR_EMPTY);
        }
        this.species = species;
    }

    private void setPrice(double price) {  // change Setters to "Private"
        if (price <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.FISH_PRICE_BELOW_OR_EQUAL_ZERO);
        }
        this.price = price;
    }

    @Override
    public void setName(String name) {  // change Setters to "Private"
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.FISH_NAME_NULL_OR_EMPTY);
        }
        this.name = name;

    }

    @Override
    //Keep in mind that some types of Fish can implement the method in a different way. -> ABSTRACT
    // each fish will eat differently
    public abstract void eat();

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
