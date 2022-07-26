package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GarageTests {
    //TODO: Test Garage class

    private Garage garage;  // problem is the same as Pet Store from Exam 18 April 2022 !!!
    private Car first;
    private Car second;
    private Car third;

    @Before
    public void setUp() {
        garage = new Garage();
        first = new Car("Audi", 150, 1000.5);
        second = new Car("BMW", 120, 900.5);
        third = new Car("Lada", 100, 170.5);

        garage.addCar(first);
        garage.addCar(second);
        garage.addCar(third);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testShouldThrowExceptionForTryingToModifyUnmodifiableCollection() {
        garage.getCars().clear();
    }

    //2. add car
    // 2.1  if (car == null)  throw
    @Test(expected = IllegalArgumentException.class)
    public void testAddCarNullThrows() {
        garage.addCar(null);
    }

    //2.2 add if there is space
    @Test
    public void addCar() {
        Assert.assertEquals(3, garage.getCount());
    }

    //car count check
    @Test
    public void carsCountInGarage() {
        Assert.assertEquals(3, garage.getCount());
    }

    // 3. Most Expensive
    @Test
    public void testTheMostExpensiveCar() {
        Assert.assertEquals(first, garage.getTheMostExpensiveCar());
    }

    // 4. cars by given type
    @Test
    public void findAllCarsByTypeAudi() {
        //Animal four = new Animal("Dog", 80, 120.5);
        List<Car> expected = new ArrayList<>();
        expected.add(first);
        //expected.add(four);

        List<Car> actual = garage.findAllCarsByBrand("Audi");
        Assert.assertEquals(expected, actual);
    }

    // 5. cars with speed above 110 for ex.
    @Test
    public void findAllCarsWithMaxSpeedAbove() {
        List<Car> expected = new ArrayList<>();
        expected.add(first);
        expected.add(second);

        List<Car> actual = garage.findAllCarsWithMaxSpeedAbove(110);
        Assert.assertEquals(expected, actual);
    }

}