package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmvilleTests {  // 88/100 not working removing when animalExists

    // 1. test Constructor
    @Test
    public void createHouse() {
        Farm farm = new Farm("Farm1", 10);
        Assert.assertEquals("Farm1", farm.getName());
        Assert.assertEquals(10, farm.getCapacity());
    }

    //1.1 invalid name
    @Test(expected = NullPointerException.class)
    public void testSetNameThrowsNull() {
        new Farm(null, 15);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameThrowsWithEmpty() {
        new Farm("", 15);
    }

    // 1.2 invalid capacity - minus
    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityThrows() {
        new Farm("Softuni Profi", -5);
    }

    //1.3 check Getter Count
    @Test
    public void testGetterCount() {
        Farm farm = new Farm("Farm1", 1);
        Animal animal = new Animal("Mike", 20);
        farm.add(animal);
        Assert.assertEquals(1, farm.getCount());
    }

    //2. Add Animal
//2.1 adds and there is space for it
    @Test
    public void addAnimal() {
        Farm farm = new Farm("Farm1", 10);
        Assert.assertEquals(0, farm.getCount());
        Animal animal = new Animal("Mike", 20);
        farm.add(animal);
        Assert.assertEquals(1, farm.getCount());
    }

    //2.2 adds in full farm - throws
    @Test(expected = IllegalArgumentException.class)
    public void testAddInFullFarmShouldThrow() {
        Farm farm = new Farm("Farm1", 1);
        Animal animal = new Animal("Mike", 20);
        Animal animal1 = new Animal("Mike1", 20);
        farm.add(animal);
        farm.add(animal1);
    }

    // not working ??? 88/100 because capacity was 1 !!!
    //2.3 animal already exists ==  if (animalExists)
    @Test(expected = IllegalArgumentException.class)
    public void testForAnimalAlreadyExists() {
        Farm farm = new Farm("Farm1", 10);
        Animal animal = new Animal("Mike", 20);
        farm.add(animal);
        farm.add(animal);
        farm.add(animal);
    }

    // 3. Remove
    // 3.1 remove successfully animal
    @Test
    public void testRemoveAnimal(){
        Farm farm = new Farm("Farm1", 1);
        Animal animal = new Animal("Mike", 20);
        farm.add(animal);
        farm.remove("Mike");
        Assert.assertEquals(0, farm.getCount());
    }

    // 3.2 == same as 3.1
    @Test
    public void testShouldRemoveAnimalByGivenNameFromTheFarm() {
        Farm farm = new Farm("Farm1", 1);
        Animal animal = new Animal("Mike", 20);
        farm.add(animal);
        boolean isRemoved = farm.remove("Mike");
        Assert.assertTrue(isRemoved);
    }


}
