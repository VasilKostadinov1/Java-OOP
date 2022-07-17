package zoo.entities.areas;

import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class BaseArea implements Area {

    private String name;
    private int capacity;
    private Collection<Food> foods;
    private Collection<Animal> animals;

    public BaseArea(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.foods = new ArrayDeque<>();
        this.animals = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.AREA_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Animal> getAnimals() {
        return this.animals;
    }

    @Override
    public Collection<Food> getFoods() {
        return this.foods;
    }

    @Override
    public int sumCalories() {
        return this.foods.stream().mapToInt(Food::getCalories).sum();
    }

    @Override
    public void addAnimal(Animal animal) {
        if (animals.size() >= this.capacity) {
            throw new IllegalStateException(ExceptionMessages.NOT_ENOUGH_CAPACITY);
        }
        this.animals.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    @Override
    public void addFood(Food food) {
        this.foods.add(food);
    }

    @Override
    public void feed() {
        this.animals.forEach(Animal::eat);
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();

//        String names = this.animals.isEmpty()
//                ? "none"
//                : this.animals.stream().map(Animal::getName).collect(Collectors.joining(" "));

        sb.append(String.format("%s (%s):", this.name, this.getClass().getSimpleName())).append(System.lineSeparator());

        String names = this.animals.stream().map(Animal::getName).collect(Collectors.joining(" "));
        if (this.animals.size() == 0) {
            sb.append("Animals: none");
        } else {
            sb.append(String.format("Animals: %s", names));
        }
        sb.append(System.lineSeparator());
        sb.append(String.format("Foods: %d", foods.size())).append(System.lineSeparator());
        sb.append(String.format("Calories: %d", sumCalories())).append(System.lineSeparator());

        return sb.toString().trim();
    }
}
