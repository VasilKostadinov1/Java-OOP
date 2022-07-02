package Inheritance_Lab_Exercises.Animals_06;

public class Kitten extends Cat{

    private static final String GENDER  = "Female";

    //•	If you receive an input for the gender of a Tomcat or a Kitten, ignore it but create the animal.
    public Kitten(String name, int age) {
        super(name, age,GENDER);
    }


    @Override
    public String produceSound() {
        return "Meow";
    }

}
