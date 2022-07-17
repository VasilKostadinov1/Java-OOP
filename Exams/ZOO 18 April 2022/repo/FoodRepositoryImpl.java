package zoo.repositories;

import zoo.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;

public class FoodRepositoryImpl implements FoodRepository{

    private Collection<Food> foods;

    public FoodRepositoryImpl() {
        this.foods = new ArrayList<>();
    }

    @Override
    public void add(Food food) {
        this.foods.add(food);
    }

    //•	Removes food from the collection. Returns true if the deletion was successful, otherwise - false.
    @Override
    public boolean remove(Food food) {
        return this.foods.remove(food);
    }

    //•	Returns the first food of the given type, if there is. Otherwise, returns null.
    @Override
    public Food findByType(String type) {
        return this.foods.stream().filter(f->f.getClass().getSimpleName().equals(type))
                .findFirst().orElse(null);
    }
}
