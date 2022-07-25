package restaurant.entities.tables.interfaces;

import restaurant.common.ExceptionMessages;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class BaseTable implements Table {

    private Collection<HealthyFood> healthyFood;
    private Collection<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable = false;
    private double allPeople;

    public BaseTable(int number, int size, double pricePerPerson) {
        this.number = number;
        this.setSize(size);
        this.pricePerPerson = pricePerPerson;
        this.healthyFood = new ArrayList<>();
        this.beverages = new ArrayList<>();
    }

    public void setSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    public void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    @Override
    public int getTableNumber() {
        return this.number;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int numberOfPeople() {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }
        return this.numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return this.isReservedTable;
    }

    @Override
    public double allPeople() {
        return allPeople;
    }

    @Override
    //Reserves the table with the counter of people given.
    public void reserve(int numberOfPeople) {
        if (numberOfPeople<0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }
        this.isReservedTable = true;
        this.numberOfPeople = numberOfPeople;

    }

    @Override
    //Orders the provided healthy food (think of a way to collect all the healthy food which is ordered).
    public void orderHealthy(HealthyFood food) {
        //this.healthyFood.stream().iterator().next();
        this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverages) {
        this.beverages.add(beverages);
    }

    @Override
    //Returns the bill for all orders.
    public double bill() {
        double sumBeverages = this.beverages.stream().mapToDouble(Beverages::getPrice).sum();
        double sumHealthy = this.healthyFood.stream().mapToDouble(HealthyFood::getPrice).sum();

        return sumBeverages + sumHealthy + (numberOfPeople * pricePerPerson);
//        double bill = this.allPeople;
//        bill += sumBeverages + sumHealthy;
//        return bill;
    }

    @Override
    //Removes all the ordered drinks and food and finally frees the table,
    // the table is not reserved,sets the count of people and price to 0.
    public void clear() {
        this.beverages.clear();
        this.healthyFood.clear();
        this.isReservedTable = false;
        this.numberOfPeople = 0;
    }

    @Override
    public String tableInformation() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Table - %s", number)).append(System.lineSeparator());
        sb.append(String.format("Size - %s", size)).append(System.lineSeparator());
        sb.append(String.format("Type - %s", this.getClass().getSimpleName())).append(System.lineSeparator());
        sb.append(String.format("All price - %s", pricePerPerson)).append(System.lineSeparator());

        return sb.toString().trim();
    }


}
