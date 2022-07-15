package Garage.src.test.java.garage;

import Garage.src.main.java.garage.Car;
import Garage.src.main.java.garage.Garage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class GarageTests {

    private static final int EXAMPLE_MAX_SPEED = 250;

    private Garage garage;
    private Car porsche;
    private Car mercedes;
    private Car audi;

    @Before
    public void setup() {
        this.garage = new Garage();
        porsche = new Car("Porsche", 300, 1200);
        mercedes = new Car("Mercedes", 200, 1500);
        audi = new Car("Aude", 220, 1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCarShouldThrowException() {
        garage.addCar(null);

    }

    @Test
    public void testAddCarSuccessfully() {
        garage.addCar(porsche);
        Assert.assertEquals(1, garage.getCount());
        garage.addCar(mercedes);
        Assert.assertEquals(2, garage.getCount());
    }

    @Test
//    public List<Car> getCars() {
//    return Collections.unmodifiableList(this.cars);
    public void testGetCarsSuccessfully() {
        garage.addCar(porsche);
        List<Car> carsInGarage = garage.getCars();
        Assert.assertEquals(1, garage.getCount());
        Assert.assertEquals(porsche.getBrand(), carsInGarage.get(0).getBrand());
    }

    @Test
    public void testFastestCar() {
        garage.addCar(porsche);
        garage.addCar(mercedes);
        garage.addCar(audi);

        List<Car> carsWithSpeedAboveValue = garage.findAllCarsWithMaxSpeedAbove(EXAMPLE_MAX_SPEED);
        Assert.assertEquals(porsche.getBrand(), carsWithSpeedAboveValue.get(0).getBrand());
    }

    @Test
    public void getTheMostExpensiveCar() {
        garage.addCar(porsche);
        garage.addCar(mercedes);
        garage.addCar(audi);

        Car mostExpensiveCar = garage.getTheMostExpensiveCar();
        Assert.assertEquals(mercedes.getBrand(), mostExpensiveCar.getBrand());

    }

    @Test
    public void findAllCarsByBrand() {
        garage.addCar(porsche);
        garage.addCar(porsche);
        garage.addCar(audi);

        List<Car> cars = garage.findAllCarsByBrand(porsche.getBrand());
        Assert.assertEquals(2, cars.size());

    }

}