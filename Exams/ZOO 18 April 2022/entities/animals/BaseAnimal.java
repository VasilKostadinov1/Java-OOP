package zoo.entities.animals;

import zoo.common.ExceptionMessages;

public class BaseAnimal implements Animal {

    private static final double INCREASE_IN_KG_PER_MEAL = 5;

    private String name;
    private String kind;
    private double kg;
    private double price;

    public BaseAnimal(String name, String kind, double kg, double price) {
        this.setName(name);
        this.setKind(kind);
        this.kg = kg;
        this.setPrice(price);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.ANIMAL_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setKind(String kind) {
        if (kind == null || kind.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.ANIMAL_KIND_NULL_OR_EMPTY);
        }
        this.kind = kind;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.ANIMAL_PRICE_BELOW_OR_EQUAL_ZERO);
        }
        this.price = price;
    }

    public void setKg(double kg) {
        this.kg = kg;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getKg() {
        return this.kg;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void eat() {
        this.kg += INCREASE_IN_KG_PER_MEAL;

    }
}
