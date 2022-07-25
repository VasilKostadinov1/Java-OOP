package restaurant.entities.healthyFoods.interfaces;

public class VeganBiscuits extends Food{


    private static final int Initial_Vegan_Biscuits_Portion = 205;

    public VeganBiscuits(String name, double price) {
        super(name, Initial_Vegan_Biscuits_Portion, price);
    }
}
