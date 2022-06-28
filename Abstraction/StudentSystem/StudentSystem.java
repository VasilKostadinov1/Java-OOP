package Abstraction_Lab.StudentSystem;

public class StudentSystem {

    private StudentData data;

    public StudentSystem() {
        this.data = new StudentData();
    }
    public void ParseCommand(String[] tokens) {
        switch (tokens[0]) {
            case "Create":
                createStudent(tokens);
                break;
            case "Show":
                showStudent(tokens[1]);
                break;
        }
    }

    private void showStudent(String name) {
        if (this.data.containsStudent(name)) {
            Student student = data.getStudent(name);
            System.out.println(student);
        }
    }

    private void createStudent(String[] tokens) {
        String name = tokens[1];
        int age = Integer.parseInt(tokens[2]);
        double grade = Double.parseDouble(tokens[3]);

        if (!this.data.containsStudent(name)) {
            data.addStudent(name, new Student(name, age, grade));
        }
    }
}
