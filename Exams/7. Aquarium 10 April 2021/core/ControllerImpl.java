package aquarium.core;

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;
import aquarium.repositories.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {
    private Repository decorations;            // •	decorations - DecorationRepository
    private Collection<Aquarium> aquariums;   // •	aquariums - collection of Aquarium

    //The constructor of ControllerImpl does not take any arguments
    public ControllerImpl() {           // empty Constructor
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();

    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        Aquarium aquarium;                // just declare it, do not initialize it
        if ("FreshwaterAquarium".equals(aquariumType)) {
            aquarium = new FreshwaterAquarium(aquariumName);   // now initialize it
        } else if ("SaltwaterAquarium".equals(aquariumType)) {
            aquarium = new SaltwaterAquarium(aquariumName);
        } else {
            throw new NullPointerException(ExceptionMessages.INVALID_AQUARIUM_TYPE);
        }
        this.aquariums.add(aquarium);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration;
        if ("Ornament".equals(type)) {
            decoration = new Ornament();
        } else if ("Plant".equals(type)) {
            decoration = new Plant();
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_DECORATION_TYPE);
        }
        this.decorations.add(decoration);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        //If there is no such decoration, you have to throw...
        Decoration decoration = this.decorations.findByType(decorationType);
        if (decoration == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_DECORATION_FOUND, decorationType));
        }
        //Adds the desired Decoration to the Aquarium with the given name
        // add to Aquarium, but 1st find the aq.
        Aquarium aquarium = this.aquariums
                .stream().filter(a -> a.getName().equals(aquariumName)).findFirst().orElse(null);

        aquarium.addDecoration(decoration);  // !!! have missed this 122/150 and was giving wrong sum
        //You have to remove the Decoration from the DecorationRepository if the insert is successful.
        this.decorations.remove(decoration);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        Fish fish;   // declare fish
        if ("FreshwaterFish".equals(fishType)) {
            fish = new FreshwaterFish(fishName, fishSpecies, price);  // initialize
        } else if ("SaltwaterFish".equals(fishType)) {
            fish = new SaltwaterFish(fishName, fishSpecies, price);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_FISH_TYPE);
        }
        //if there is not enough capacity to add the Fish in the Aquarium
        Aquarium aquarium = this.aquariums.stream()
                .filter(a -> a.getName().equals(aquariumName)).findFirst().orElse(null);

        //Water not suitable." - if the Fish cannot live in the Aquarium
//        if (!aquarium.getClass().getSimpleName().substring(0, 5).equals(fishType.substring(0, 5))) {
//            return ConstantMessages.WATER_NOT_SUITABLE;
//        }
        String aquaType = aquarium.getClass().getSimpleName();

        boolean areSuitable = (aquaType.equals("FreshwaterAquarium") && fishType.equals("FreshwaterFish")) ||
                (aquaType.equals("SaltwaterAquarium") && fishType.equals("SaltwaterFish"));
        if (!areSuitable) {
            return ConstantMessages.WATER_NOT_SUITABLE;
        }
        aquarium.addFish(fish);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        Aquarium aquarium = this.aquariums.stream()
                .filter(a -> a.getName().equals(aquariumName)).findFirst().orElse(null);
        aquarium.feed();
        int count = aquarium.getFish().size();
        return String.format(ConstantMessages.FISH_FED, count);
    }

    @Override
    public String calculateValue(String aquariumName) {

        Aquarium aquarium = this.aquariums.stream().filter(a -> a.getName().equals(aquariumName)).findFirst().orElse(null);
        //It is calculated by the sum of all Fish’s and Decorations’ prices in the Aquarium.

        double fishPrice = aquarium.getFish().stream().mapToDouble(Fish::getPrice).sum();
        double decorationPrice = aquarium.getDecorations().stream().mapToDouble(Decoration::getPrice).sum();
        double totalValue = fishPrice + decorationPrice;
//        double value = 0;
//        for (Fish fish : aquarium.getFish()) {
//            value += fish.getPrice();
//        }
//        for (Decoration decoration : aquarium.getDecorations()) {
//            value += decoration.getPrice();
//        }
        return String.format(ConstantMessages.VALUE_AQUARIUM, aquarium.getName(), totalValue);
    }

    @Override
    public String report() {
//        "{aquariumName} ({aquariumType}):
//        Fish: {fishName1} {fishName2} {fishName3} (…) / Fish: none
//        Decorations: {decorationsCount}
//        Comfort: {aquariumComfort}

        StringBuilder sb = new StringBuilder();
        for (Aquarium aquarium : aquariums) {
            sb
                    .append(aquarium.getInfo())        // !!! getInfo
                    .append(System.lineSeparator());

        }
        return sb.toString().trim();
    }
}
