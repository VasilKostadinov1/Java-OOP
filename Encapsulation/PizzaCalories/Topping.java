package Encapsulation_Exercises.PizzaCalories;

public class Topping {

    //fields
    private String toppingType;
    private double weight;

    //constructor
    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        //валидни -> Meat;	Veggies;	Cheese; 	Sauce
        switch(toppingType) {
            case "Meat":
            case "Veggies":
            case "Cheese":
            case "Sauce":
                this.toppingType = toppingType;
                break;
            default:
                String msg = String.format("Cannot place %s on top of your pizza.", toppingType);
                throw new IllegalArgumentException(msg);
        }

    }

    public void setWeight(double weight) {
        //валидно -> [1..50]
        if(weight >= 1 && weight <= 50) {
            this.weight = weight;
        } else {
            String msg = String.format("%s weight should be in the range [1..50].", this.toppingType);
            throw new IllegalArgumentException(msg);
        }
    }

    public double calculateCalories() {
        //(2 * weigth) * toppingTypeModificator
        double toppingTypeModificator = this.getToppingTypeModificator(this.toppingType);
        return 2 * this.weight * toppingTypeModificator;
    }

    private double getToppingTypeModificator(String toppingType) {
        //Meat;	Veggies;	Cheese; 	Sauce
        switch(toppingType) {
            case "Meat":
                return 1.2;
            case "Veggies":
                return 0.8;
            case "Cheese":
                return 1.1;
            case "Sauce":
                return 0.9;
            default:
                return 0;
        }
    }
}
