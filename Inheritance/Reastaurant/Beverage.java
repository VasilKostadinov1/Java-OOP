package Inheritance_Lab_Exercises.Restaurant_05;

import java.math.BigDecimal;

public class Beverage extends Product{

//    private String name;
//    private BigDecimal price;
    private double milliliters;

    public Beverage(String name, BigDecimal price, double milliliters) {
        super(name, price);
        this.milliliters = milliliters;
    }

    public double getMilliliters() {
        return milliliters;
    }
}
