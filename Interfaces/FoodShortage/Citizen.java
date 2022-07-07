package FoodShortage_04;

public class Citizen implements Birthable, Identifiable, Person,Buyer {

    private static final int INITIAL_FOOD = 0;
    private static final int INCREASE_FOOD = 10;

    private String name;
    private int age;
    private String id;
    private String birthDate;
    private int food;

    public Citizen(String name, int age, String id, String birthDate) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.birthDate = birthDate;
        this.food = INITIAL_FOOD;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getBirthDate() {
        return this.birthDate;
    }

    public int getFood() {
        return food;
    }

    public void buyFood() {
        this.food += INCREASE_FOOD;

    }
}
