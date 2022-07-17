package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControllerImpl implements Controller {

    private ToyRepository toys;
    private Collection<House> houses;

    public ControllerImpl() {
        this.toys = new ToyRepository();
        this.houses = new ArrayList<>();
    }

    @Override
    public String addHouse(String type, String name) {
        House house;
        if (type.equals("ShortHouse")) {
            house = new ShortHouse(name);
        } else if (type.equals("LongHouse")) {
            house = new LongHouse(name);
        } else {
            throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }
        this.houses.add(house);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE, type);

    }

    @Override
    public String buyToy(String type) {
        Toy toy;
        if (type.equals("Ball")) {
            toy = new Ball();
        } else if (type.equals("Mouse")) {
            toy = new Mouse();
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TOY_TYPE);
        }
        this.toys.buyToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toy = this.toys.findFirst(toyType);
        if (toy == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND, toyType));
        }
        //Adds (buys) the desired Toy to the House with the given name.
        // You have to remove the Toy from the ToyRepository if the insert is successful.
        House house = this.houses.stream().filter(h -> h.getName().equals(houseName)).findFirst().orElse(null);

        house.buyToy(toy);
        this.toys.removeToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);

    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        //Creates and adds the desired Cat to the House with the given name. Valid Cat types are: "ShorthairCat", "LonghairCat".
        Cat cat =null;
        if (catType.equals("ShorthairCat")) {
            cat = new ShorthairCat(catName, catBreed, price);
        } else if (catType.equals("LonghairCat")) {
            cat = new LonghairCat(catName, catBreed, price);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_CAT_TYPE);
        }
        //If no errors are thrown, return one of the following strings:
        //Unsuitable house." - if the given Cat cannot live in the House.
        //•	"Successfully added {catType} to {houseName}."
        House house = this.houses.stream().filter(h -> h.getName().equals(houseName)).findFirst().orElse(null);
        String houseType = house.getClass().getSimpleName();  // !!! 136 /150
        //String houseType = house.getClass().getTypeName();  // !!! 109 /150

        boolean suitable = ((houseType.equals("LongHouse")) && (catType.equals("LonghairCat")) ||
                (houseType.equals("ShortHouse")) && (catType.equals("ShorthairCat")));
        if (suitable) {
            house.addCat(cat);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
        }
        return ConstantMessages.UNSUITABLE_HOUSE;

    }

    @Override
    public String feedingCat(String houseName) {
        House house = this.houses.stream().filter(h -> h.getName().equals(houseName)).findFirst().orElse(null);
        //Feeds all Cat in the House with the given name.
        house.feeding();
        return String.format(ConstantMessages.FEEDING_CAT, house.getCats().size());
//        Collection<Cat> cats = house.getCats();
//        int catFed = 0;
//        for (Cat cat : cats) {
//            cat.eating();
//            catFed++;
//        }
//        return String.format(ConstantMessages.FEEDING_CAT, catFed);
    }

    @Override
    public String sumOfAll(String houseName) {
        //Calculates the value of the House with the given name.
        // It is calculated by the sum of all Cat’s and Toy’s prices in the House.
        House house = this.houses.stream().filter(h -> h.getName().equals(houseName)).findFirst().orElse(null); //House with the given name

        double catsPrice = house.getCats().stream().mapToDouble(Cat::getPrice).sum();
        double toysPrice = house.getToys().stream().mapToDouble(Toy::getPrice).sum();
        double sumCatsAndToys = catsPrice + toysPrice;

        return String.format(ConstantMessages.VALUE_HOUSE, houseName, sumCatsAndToys);
        /*double totalSum = 0;
        Collection<Toy> toys = house.getToys();
        for (Toy toy : toys) {
            totalSum += toy.getPrice();
        }
        Collection<Cat> cats = house.getCats();
        for (Cat cat : cats) {
            totalSum += cat.getPrice();
        }
        return String.format(ConstantMessages.VALUE_HOUSE, houseName, totalSum);*/
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        //this.houses.forEach(h -> sb.append(h.getStatistics()).append(System.lineSeparator()));  // 150/150
        for (House house : houses) {
            sb.append(house.getStatistics()).append(System.lineSeparator());  // 150/150
            // sb.append(house.getStatistics());                              // 136/150
        }
        return sb.toString().trim();
    }
}
