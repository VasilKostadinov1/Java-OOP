package Inheritance_Lab_Exercises.Animals_06;

public class Animal {

    private String name;
    private int age;
    private String gender;

    public Animal(String name, int age, String gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    // Method !!
    public String produceSound(){
        return null;
        //return "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid input!");
        }
        this.gender = gender;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getSimpleName()).append(System.lineSeparator());
        //Tom 12 Male
        sb.append(this.name).append(" ").append(this.age).append(" ").append(this.gender).append(System.lineSeparator());
        sb.append(this.produceSound());

        return sb.toString().trim();
    }
}
