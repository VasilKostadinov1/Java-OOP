package Encapsulation_Exercises.ClassBoxData;

public class Box {
    private double length;
    private double width;
    private double height;

    //Add data validation for each parameter given to the constructor.
    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    private void setLength(double length) {
        checkIfValid(length, "Length");
        this.length = length;
    }

    private void setWidth(double width) {
        checkIfValid(length, "Width");
        this.width = width;
    }

    private void setHeight(double height) {
        checkIfValid(length, "Height");
        this.height = height;
    }

    private void checkIfValid(double size, String parameter) {
        if (size <= 0) {
            throw new IllegalArgumentException(parameter + " cannot be zero or negative.");
        }
    }

    public double calculateSurfaceArea() {
        return 2 * ((length * width) + (length * height) + (width * height));
    }

    public double calculateLateralSurfaceArea() {
        return 2 * ((length * height) + (width * height));
    }

    public double calculateVolume() {
        return length * width * height;
    }
}
