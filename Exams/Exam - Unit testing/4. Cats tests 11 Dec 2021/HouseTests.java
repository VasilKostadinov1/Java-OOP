package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {

    private House house;
    private Cat first;
    private Cat second;
    private Cat third;

    @Before
    public void setup() {
        house = new House("House", 3);
        first = new Cat("one");
        second = new Cat("two");
        third = new Cat("three");

        house.addCat(first);
        house.addCat(second);
        house.addCat(third);
    }

    // 1. test Constructor
    @Test
    public void testConstructor() {
        Assert.assertEquals("House", house.getName());
        Assert.assertEquals(3, house.getCapacity());
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

    // 1.3 get count
    @Test
    public void testCount() {
        Assert.assertEquals(3, house.getCount());
    }

    //2. add Cat
    //2.1 adds  == 1.3 get Count
    @Test
    public void testAddCatInHouse() {
        Assert.assertEquals(3, house.getCount());
    }

    // 2.2 add in full house
    @Test(expected = IllegalArgumentException.class)
    public void addInFullHouse() {
        Cat four = new Cat("four");
        house.addCat(four);
    }

    // 3. remove cat
    //3.1 remove successfully
    @Test
    public void removeCat() {
        house.removeCat("one");
        Assert.assertEquals(2, house.getCount());
    }

    //3.2 remove if there is no such cat
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullCat() {
        house.removeCat("Ivan");
        //house.removeCat(null);
    }

    //4 cat for sale
    // 4.1 successfully sold cat - > hungry  !!!
    @Test
    public void testSoldCat() {
        Cat returnedCat = house.catForSale("one");
        Assert.assertFalse(returnedCat.isHungry());
    }

    // 4.2 no such cat
    @Test(expected = IllegalArgumentException.class)
    public void testNullCat() {
        house.catForSale("ten");
    }

    //5 Statistics
    @Test
    public void getStatsPrint(){
        Assert.assertEquals
                ("The cat one, two, three is in the house House!", house.statistics());
    }



}
