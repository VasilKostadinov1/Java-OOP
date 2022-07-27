package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmvilleTests {

    private Farm farm;
    private Animal one;
    private Animal two;
    private Animal three;

    @Before
    public void setup() {
        farm = new Farm("Farm", 3);
        one = new Animal("dog", 10);
        two = new Animal("cat", 8);
        three = new Animal("parrot", 6);

        farm.add(one);
        farm.add(two);
        farm.add(three);
    }

    // test Construcor
    @Test
    public void testConstrucor() {
        Assert.assertEquals(3, farm.getCount());
        Assert.assertEquals("Farm", farm.getName());
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

    //1.2 invalid capacity
    @Test(expected = IllegalArgumentException.class)
    public void testZeroCapacity() {
        new Farm("House", -5);
    }

    // 1.3 get count
    @Test
    public void testCount() {
        Assert.assertEquals(3, farm.getCount());
    }

    // 2. add animal
    //2.1  add if space - this test is already covered, but write it
    @Test
    public void testAddsWhenThereIsSpace() {
//        farm = new Farm("Farm", 4);
//        Animal four = new Animal("monkey", 20);
//        farm.add(four);
        Assert.assertEquals(3, farm.getCount());
    }


    //2.2  adds in full: if (animals.size() == this.getCapacity())
    // create new animal, as using already existing was 88/100 !!!
    @Test(expected = IllegalArgumentException.class)
    public void testAddInFull() {
        Animal four = new Animal("Tiger", 50);
        farm.add(four);
    }

    //2.3 if (animalExists)
    @Test(expected = IllegalArgumentException.class)
    public void testAnimalAlreadyExists() {
        // increase capacity first !!! then add existing animal
        farm = new Farm("Farm", 5);
        farm.add(one);
        farm.add(one);
    }

    //3. remove
    @Test
    public void testRemove() {
        farm.remove("dog");
        Assert.assertEquals(2, farm.getCount());
    }




}
