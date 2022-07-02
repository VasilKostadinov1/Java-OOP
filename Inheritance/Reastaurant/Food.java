package Inheritance_Lab_Exercises.Restaurant_05;

import java.math.BigDecimal;

public class Food extends Product{

//    private String name;
//    private BigDecimal price;
    private double grams;

    public Food(String name, BigDecimal price, double grams) {
        super(name, price);
        this.grams = grams;
    }

    public double getGrams() {
        return grams;
    }
}
