package Inheritance_Lab_Exercises.Animals_06;

public class Tomcat extends Cat{

    private static final String GENDER  = "Male";

    public Tomcat(String name, int age) {
        super(name, age, GENDER);
    }

    @Override
    public String produceSound() {
        return "MEOW";
    }


}
