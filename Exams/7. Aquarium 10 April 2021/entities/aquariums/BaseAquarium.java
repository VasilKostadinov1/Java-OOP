package aquarium.entities.aquariums;

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    public BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>(); // in order to be able to add !!!
        this.fish = new ArrayList<>();
    }

    private void setName(String name) {  // change to Private
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int calculateComfort() { //Returns the sum of each decoration’s comfort in the Aquarium
        int comfort = this.decorations.stream().mapToInt(d -> d.getComfort()).sum();
//        int sum=0;
//        for (Decoration decoration : decorations) {
//            sum+=decoration.getComfort();
//        }
        return comfort;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {
        if (this.fish.size() == this.capacity) {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }
        this.fish.add(fish);

    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);

    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);

    }

    @Override
    public void feed() {
        this.fish.forEach(f -> f.eat());
//        for (Fish fish1 : fish) {
//            fish1.eat();
//        }

    }

    @Override
    public String getInfo() {
//        "{aquariumName} ({aquariumType}):
//        Fish: {fishName1} {fishName2} {fishName3} (…) / Fish: none
//        Decorations: {decorationsCount}
//        Comfort: {aquariumComfort}"

        StringBuilder sb = new StringBuilder();
        sb
                .append(String.format("%s (%s):", this.getName(), this.getClass().getSimpleName()))
                .append(System.lineSeparator())
                .append(String.format("Fish: %s", this.fish.isEmpty() ? "none" : printFish()))
                .append(System.lineSeparator())
                .append(String.format("Decorations: %d", this.decorations.size()))
                .append(System.lineSeparator())
                .append(String.format("Comfort: %d", this.calculateComfort()));

        return sb.toString().trim();
    }

    private String printFish() {
        StringBuilder sb = new StringBuilder();
        //this.fish.forEach(f -> sb.append(f.getName()).append(" "));
        for (Fish fish1 : fish) {
            sb.append(fish1.getName())
                    .append(" ");
        }
        return sb.toString().trim();
    }

    @Override
    public Collection<Fish> getFish() {
        return Collections.unmodifiableCollection(this.fish);
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return Collections.unmodifiableCollection(this.decorations);
    }
}
