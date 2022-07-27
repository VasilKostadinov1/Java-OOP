package aquarium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AquariumTests { // same as Cats - unit testing

    private Aquarium aquarium;
    private Fish one;
    private Fish two;
    private Fish three;

    @Before
    public void setup() {
        aquarium = new Aquarium("Aqua", 3);
        one = new Fish("az");
        two = new Fish("ti");
        three = new Fish("toi");

        aquarium.add(one);
        aquarium.add(two);
        aquarium.add(three);
    }

    // test Constructor
    @Test
    public void testConstructor() {
        Assert.assertEquals(3, aquarium.getCount());
        Assert.assertEquals("Aqua", aquarium.getName());
    }

    // 1.1 invalid name
    @Test(expected = NullPointerException.class)
    public void testNameNullThrow() {
        new Aquarium(null, 5);
    }

    @Test(expected = NullPointerException.class)
    public void testNameIsEmptyThrow() {
        new Aquarium("", 5);
    }

    //1.2 invalid capacity - minus
    @Test(expected = IllegalArgumentException.class)
    public void testMinusCapacityThrows() {
        new Aquarium("Aqua", -5);
    }

    //1.3 get count
    @Test
    public void testGetCount() {
        Assert.assertEquals(3, aquarium.getCount());
    }

    //2. add Fish
    //2.1 add successfully
    @Test
    public void testAddSuccessfully() {
        Assert.assertEquals(3, aquarium.getCount());
    }

    //2.2 if (this.fish.size() == this.capacity) adds in full
    @Test(expected = IllegalArgumentException.class)
    public void testAddFishInFull() {
        Fish four = new Fish("four");
        aquarium.add(four);
    }

    // 3. Remove
    //3.1 remove successfully
    @Test
    public void testRemoveFish() {
        aquarium.remove("az");
        Assert.assertEquals(2, aquarium.getCount());
    }

    //3.2 if (fishToRemove == null) remove non-existent fish
    @Test(expected = IllegalArgumentException.class)
    public void testFishToRemoveNull() {
        aquarium.remove("Ivan");
    }

    //4. sell fish
    //4.1 successfully sold fish !!!!
    //requestedFish.setAvailable(false);
    @Test
    public void testSoldFish() {
        Fish soldFish = aquarium.sellFish("az");
        Assert.assertFalse(soldFish.isAvailable());
    }


    //4.2 if (requestedFish == null)
    @Test(expected = IllegalArgumentException.class)
    public void testFishNull() {
        aquarium.sellFish("Ivan");
    }

    //5. report print
    //return String.format("Fish available at %s: %s"
    @Test
    public void testReportPrint(){
        String expectedMsg = "Fish available at Aqua: az, ti, toi";
        Assert.assertEquals(expectedMsg,aquarium.report());
    }

    //6. get Capacity
    @Test
    public void testGetCapacity(){
        Assert.assertEquals(3, aquarium.getCapacity());
    }


}

