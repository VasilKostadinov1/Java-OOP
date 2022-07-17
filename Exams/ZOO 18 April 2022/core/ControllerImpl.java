package zoo.core;

import zoo.common.ConstantMessages;
import zoo.common.ExceptionMessages;
import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;
import zoo.entities.animals.TerrestrialAnimal;
import zoo.entities.areas.Area;
import zoo.entities.areas.LandArea;
import zoo.entities.areas.WaterArea;
import zoo.entities.foods.Food;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;
import zoo.repositories.FoodRepository;
import zoo.repositories.FoodRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControllerImpl implements Controller {

    private List<Area> areas;
    private FoodRepository foodRepository;

    public ControllerImpl() {
        this.areas = new ArrayList<>();
        this.foodRepository = new FoodRepositoryImpl();
    }

    @Override
    public String addArea(String areaType, String areaName) {
        Area area;
        switch (areaType) {
            case "WaterArea":
                area = new WaterArea(areaName);
                break;
            case "LandArea":
                area = new LandArea(areaName);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_AREA_TYPE);
        }
        this.areas.add(area);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AREA_TYPE, areaType);
    }

    @Override
    public String buyFood(String foodType) {
        Food food;
        switch (foodType) {
            case "Vegetable":
                food = new Vegetable();
                break;
            case "Meat":
                food = new Meat();
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_FOOD_TYPE);
        }
        this.foodRepository.add(food);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_TYPE, foodType);
    }

    @Override
    public String foodForArea(String areaName, String foodType) {
        Food food = this.foodRepository.findByType(foodType);
        if (food == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_FOOD_FOUND, foodType));
        }
        //Area with the given name
        Area area = this.areas.stream().filter(a -> a.getName().equals(areaName)).findFirst().orElse(null);
        //Adds the desired Food to the Area
        area.addFood(food);
        //You have to remove the Food from the FoodRepository if the insert is successful. !!!
        this.foodRepository.remove(food);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_IN_AREA, foodType, areaName);
    }

    @Override
    public String addAnimal(String areaName, String animalType, String animalName, String kind, double price) {
        Animal animal;
        switch (animalType) {
            case "AquaticAnimal":
                animal = new AquaticAnimal(animalName, kind, price);
                break;
            case "TerrestrialAnimal":
                animal = new TerrestrialAnimal(animalName, kind, price);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_ANIMAL_TYPE);
        }
        Area area = this.areas.stream().filter(a -> a.getName().equals(areaName)).findFirst().orElse(null);
        String areaType = area.getClass().getSimpleName();

        boolean areSuitable = (areaType.equals("WaterArea") && animalType.equals("AquaticAnimal")) ||
                (areaType.equals("LandArea") && animalType.equals("TerrestrialAnimal"));
        if (!areSuitable) {
            return ConstantMessages.AREA_NOT_SUITABLE;
        }
        area.addAnimal(animal);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_ANIMAL_IN_AREA, animalType, areaName);
    }

    @Override
    public String feedAnimal(String areaName) {
        Area area = this.areas.stream().filter(a -> a.getName().equals(areaName)).findFirst().orElse(null);
        area.feed();
        return String.format(ConstantMessages.ANIMALS_FED, area.getAnimals().size());
    }

    @Override
    public String calculateKg(String areaName) {
        Area area = this.areas.stream().filter(a -> a.getName().equals(areaName)).findFirst().orElse(null);
        double sum = area.getAnimals().stream().mapToDouble(Animal::getKg).sum();

        return String.format(ConstantMessages.KILOGRAMS_AREA, areaName, sum);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
//        this.areas.forEach(Area::getInfo);
//        this.areas.forEach(a -> sb.append(a.toString()).append(System.lineSeparator()));

        for (Area area : areas) {
            sb.append(area.getInfo());
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
