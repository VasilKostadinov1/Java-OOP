package restaurant.core;

import restaurant.common.ExceptionMessages;
import restaurant.common.OutputMessages;
import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.interfaces.Fresh;
import restaurant.entities.drinks.interfaces.Smoothie;
import restaurant.entities.healthyFoods.interfaces.Food;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.Salad;
import restaurant.entities.healthyFoods.interfaces.VeganBiscuits;
import restaurant.entities.tables.interfaces.InGarden;
import restaurant.entities.tables.interfaces.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.*;

public class ControllerImpl implements Controller {

    private BeverageRepository<Beverages> beverageRepository;
    private TableRepository<Table> tableRepository;
    private HealthFoodRepository<HealthyFood> healthFoodRepository;
    private double totalMoney;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository,
                          BeverageRepository<Beverages> beverageRepository,
                          TableRepository<Table> tableRepository) {
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
        this.healthFoodRepository = healthFoodRepository;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        HealthyFood healthyFood = null;
        switch (type) {
            case "Salad":
                healthyFood = new Salad(name, price);
                break;
            case "VeganBiscuits":
                healthyFood = new VeganBiscuits(name, price);
                break;
        }
        //If a healthy food with the given name already exists in the food repository, throw
        HealthyFood previouslyAdded = healthFoodRepository.foodByName(name);
        if (previouslyAdded != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_EXIST, name));
        }
        healthFoodRepository.add(healthyFood);
        return String.format(OutputMessages.FOOD_ADDED, name);

    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name) {
        Beverages beverages = null;
        switch (type) {
            case "Fresh":
                beverages = new Fresh(name, counter, brand);
                break;
            case "Smoothie":
                beverages = new Smoothie(name, counter, brand);
                break;
        }
        //If a beverage with the given name already exists in the beverage repository...
        Beverages previouslyAdd = this.beverageRepository.beverageByName(name, brand);
        if (previouslyAdd != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.BEVERAGE_EXIST, name));
        }
        this.beverageRepository.add(beverages);
        return String.format(OutputMessages.BEVERAGE_ADDED, type, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = null;
        switch (type) {
            case "Indoors":
                table = new Indoors(tableNumber, capacity);
                break;
            case "InGarden":
                table = new InGarden(tableNumber, capacity);
                break;
        }
        //If a table with the given number already exists in the table repository, throw
        Table previousTable = this.tableRepository.byNumber(tableNumber);
        if (previousTable != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.TABLE_IS_ALREADY_ADDED, tableNumber));
        }
        this.tableRepository.add(table);
        return String.format(OutputMessages.TABLE_ADDED, tableNumber);
    }

    @Override
    //Finds a table which is not reserved, and its size is enough for the number of people provided
    public String reserve(int numberOfPeople) {
        Table suitableTable = this.tableRepository
                .getAllEntities()
                .stream()
                .filter(t -> !t.isReservedTable() && t.getSize() >= numberOfPeople)
                .findFirst().orElse(null);
        if (suitableTable == null) {
            return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }
        //In the other case reserves the table and returns:
        suitableTable.reserve(numberOfPeople);
        return String.format(OutputMessages.TABLE_RESERVED, suitableTable.getTableNumber(), numberOfPeople);
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        //Finds the table with that number and the food with that name in the menu.
        // You first check if the table exists.
        Table table = this.tableRepository.byNumber(tableNumber);
        if (table == null) {
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }
        HealthyFood healthyFood = this.healthFoodRepository.foodByName(healthyFoodName);
        if (healthyFood == null) {
            return String.format(OutputMessages.NONE_EXISTENT_FOOD, healthyFoodName);
        }
        //orders the food for that table
        table.orderHealthy(healthyFood);
        return String.format(OutputMessages.FOOD_ORDER_SUCCESSFUL, healthyFoodName, tableNumber);
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        //Finds the table with that number and finds the beverage with that name and brand.
        // You first check if the table exists
        Table table = this.tableRepository.byNumber(tableNumber);
        if (table == null) {
            return String.format(OutputMessages.WRONG_TABLE_NUMBER, tableNumber);
        }
        Beverages beverages = this.beverageRepository.beverageByName(name, brand);
        if (beverages == null) {
            return String.format(OutputMessages.NON_EXISTENT_DRINK, name, brand);
        }
        //orders the beverage for that table
        table.orderBeverages(beverages);
        return String.format(OutputMessages.BEVERAGE_ORDER_SUCCESSFUL, name, tableNumber);
    }

    @Override
    public String closedBill(int tableNumber) {
        //Finds the table with the same table number
        Table table = this.tableRepository.byNumber(tableNumber);
        //. Gets the bill for that table and clears it
        double tableBill = table.bill();
        totalMoney += tableBill;
        table.clear();   // and clears it

        return String.format(OutputMessages.BILL, tableNumber, tableBill);
    }


    @Override
    public String totalMoney() {
        return String.format(OutputMessages.TOTAL_MONEY, totalMoney);
    }
}
