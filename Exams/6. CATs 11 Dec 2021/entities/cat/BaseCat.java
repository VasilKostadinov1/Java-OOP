package catHouse.entities.cat;

import catHouse.common.ExceptionMessages;

public class BaseCat implements Cat {

    private String name;
    private String breed;
    private int kilograms;
    private double price;

    public BaseCat(String name, String breed, double price) {
        this.setName(name);
        this.setBreed(breed);
        this.setPrice(price);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.CAT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;

    }

    @Override
    public int getKilograms() {
        return this.getKilograms();
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void eating() {

    }

    public void setBreed(String breed) {
        if (breed.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.CAT_BREED_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.breed = breed;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new NullPointerException(ExceptionMessages.CAT_PRICE_CANNOT_BE_BELOW_OR_EQUAL_TO_ZERO);
        }
        this.price = price;
    }

    public void setKilograms(int kilograms) {
        this.kilograms = kilograms;
    }
}
