package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GarageTests {
    //TODO: Test Garage class

    private Garage garage;
    private Car car;

    @Before
    public void setUp() {
        garage = new Garage();
        car = new Car("Lambo",350, 10000);
        garage.addCar(car);   // tests addCar method
    }

    // tests Collection Unmodifiable !!!
    @Test(expected = UnsupportedOperationException.class)
    public void testThrowsExceptionForTryingToModifyUnmodifiableCollection(){
        garage.getCars().clear();
    }

    // tests Constructor, addCar method when throws
    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowExceptionForTryingToAddNullCarInTheGarage() {
        garage.addCar(null);
    }
    //below 3 tests: are built in "garage."
    @Test
    public void testShouldFindTheMostExpensiveCarByTheGivenPrice() {
        Car mostExpensive = garage.getTheMostExpensiveCar();
        Assert.assertEquals(car, mostExpensive);
        Assert.assertEquals(1, garage.getCount());
    }
    @Test
    public void testFindAllCarsByBrand(){
        List<Car> allCarsByBrand = garage.findAllCarsByBrand("Lambo");
        Assert.assertEquals(car, allCarsByBrand.get(0));
    }
    @Test
    public void testAllCarsWithMaxSpeedAbove(){
        List<Car> allCarsWithMaxSpeedAbove =
                garage.findAllCarsWithMaxSpeedAbove(200);
        Assert.assertEquals(car,allCarsWithMaxSpeedAbove.get(0));
    }

}