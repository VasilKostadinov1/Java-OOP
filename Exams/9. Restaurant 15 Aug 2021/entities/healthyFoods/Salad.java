package restaurant.entities.healthyFoods.interfaces;

public class Salad extends Food{

    private static final int Initial_Salad_Portion = 150;

    public Salad(String name, double price) {
        super(name, Initial_Salad_Portion, price);
    }
}
