package Encapsulation_Exercises.ShoppingSpree;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void buyProduct(Product product) {
        //If a person doesn’t have enough money, print an appropriate exception message:
        if (money < product.getCost()) {
            throw new IllegalArgumentException(String.format("%s can't afford %s",
                    this.getName(), product.getName()));
        }
        //If the person can afford a product add it to his bag
        this.products.add(product);
        this.money -= product.getCost();

        System.out.printf("%s bought %s%n",this.getName(), product.getName());

    }

    public List<Product> getProducts() {
        return products;

    }
}
