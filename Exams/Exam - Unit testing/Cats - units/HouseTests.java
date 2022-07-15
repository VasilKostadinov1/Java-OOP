package cats;

import org.junit.Assert;
import org.junit.Test;

public class HouseTests {

    // 1. test Constructor
    @Test
    public void createHouse() {
        House house = new House("House1", 10);
        Assert.assertEquals("House1", house.getName());
        Assert.assertEquals(10, house.getCapacity());
    }

    //1.1 invalid name
    @Test(expected = NullPointerException.class)
    public void testSetNameThrowsNull() {
        new House(null, 15);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameThrowsWithEmpty() {
        new House("", 15);
    }

    // 1.2 invalid capacity - minus
    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityThrows() {
        new House("Softuni Profi", -5);
    }

    //2. Add cat
    // 2.1
    @Test
    public void addCat() {
        House house = new House("House1", 10);
        Assert.assertEquals(0, house.getCount());
        Cat mike = new Cat("Mike");
        house.addCat(mike);
        Assert.assertEquals(1, house.getCount());
    }

    //2.2 adds in full house - throws
    @Test(expected = IllegalArgumentException.class)
    public void testAddCatThrows() {
        House house = new House("House1", 1);
        Cat mike = new Cat("Mike");
        house.addCat(mike);
        Cat mike1 = new Cat("Mike1");
        house.addCat(mike1);

    }
    // 3. remove
    //3.1 remove successfully cat
    @Test
    public void testRemoveCat(){
        House house = new House("House1", 10);
        Cat mike = new Cat("Mike");
        Cat mike1 = new Cat("Mike1");
        house.addCat(mike);
        house.addCat(mike1);
        Assert.assertEquals(2, house.getCount());

        house.removeCat("Mike");
        Assert.assertEquals(1, house.getCount());

    }

    //3.2 there is no such cat in house - throws
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistentCat(){
        House house = new House("House1", 10);
        house.removeCat("Ivan");
    }

    //4. cat for sale
    // 4.1 successfully sold cat - > hungry
    @Test
    public void testCatForSale() {
        House house = new House("House1", 1);
        Cat mike = new Cat("Mike"); // isHungry = true
        house.addCat(mike);

        Cat returnedCat = house.catForSale("Mike");
        Assert.assertFalse(returnedCat.isHungry());
    }
    //4.2 there is no such cat
    @Test(expected = IllegalArgumentException.class)
    public void testForNonCatForSale(){
        House house = new House("House1", 10);
        house.catForSale("Ivan");
    }

    // 5. Stats
    @Test
    public void testStats(){
        House house = new House("House1", 10);
        Cat mike = new Cat("Mike");
        Cat mike1 = new Cat("Mike1");
        Cat john = new Cat("John");
        house.addCat(mike);
        house.addCat(mike1);
        house.addCat(john);

        Assert.assertEquals("The cat Mike, Mike1, John is in the house House1!",
                house.statistics());

    }


}
