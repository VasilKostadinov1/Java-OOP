package aquarium;

import org.junit.Assert;
import org.junit.Test;

public class AquariumTests { // same as Cats - unit testing

    // 1. test Constructor
    @Test
    public void createHouse() {
        Aquarium aqua = new Aquarium("Aqua1", 10);
        Assert.assertEquals("Aqua1", aqua.getName());
        Assert.assertEquals(10, aqua.getCapacity());
    }

    //1.1 invalid name
    @Test(expected = NullPointerException.class)
    public void testSetNameThrowsNull() {
        new Aquarium(null, 15);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameThrowsWithEmpty() {
        new Aquarium("", 15);
    }

    // 1.2 invalid capacity - minus
    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityThrows() {
        new Aquarium("Aqu", -5);
    }
    //1.3 check Getter Count
    @Test
    public void testGetterCount() {
        Aquarium aqua = new Aquarium("Aqua1", 10);
        Fish fish = new Fish("Fish1");
        aqua.add(fish);
        Assert.assertEquals(1, aqua.getCount());
    }

    //2. Add Animal
    //2.1 adds and there is space for it
    @Test
    public void addAnimal() {
        Aquarium aqua = new Aquarium("Aqua1", 10);
        Assert.assertEquals(0, aqua.getCount());
        Fish fish = new Fish("Fish1");
        aqua.add(fish);
        Assert.assertEquals(1, aqua.getCount());
    }
    //2.2 adds in full aqua
    @Test(expected = IllegalArgumentException.class)
    public void testAddFishInFullAqua(){
        Aquarium aqua = new Aquarium("Aqua1", 1);
        Fish fish = new Fish("Fish1");
        Fish fish2 = new Fish("Fish2");
        aqua.add(fish);
        aqua.add(fish2);
    }
    // 3. remove
    //3.1 remove successfully fish
    @Test
    public void testRemoveCat(){
        Aquarium aqua = new Aquarium("Aqua1", 10);
        Fish fish = new Fish("Fish1");
        Fish fish2 = new Fish("Fish2");
        aqua.add(fish);
        aqua.add(fish2);
        Assert.assertEquals(2, aqua.getCount());

        aqua.remove("Fish1");
        Assert.assertEquals(1, aqua.getCount());
    }
    //3.2 there is no such fish in aqua - throws
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistentFish(){
        Aquarium aqua = new Aquarium("Aqua1", 10);
        aqua.remove("Ivan");
    }
    //4. fish for sale
    // 4.1 successfully sold fish
    @Test
    public void testFishForSale() {
        Aquarium aqua = new Aquarium("Aqua1", 10);
        Fish fish = new Fish("Fish1");
        aqua.add(fish);

        Fish returnedFish = aqua.sellFish("Fish1");
        Assert.assertFalse(returnedFish.isAvailable());
    }
    //4.2 if (requestedFish == null){
    @Test(expected = IllegalArgumentException.class)
    public void testSellFishThrowsForFishNull(){
        Aquarium aqua = new Aquarium("Aqua1", 10);
        aqua.sellFish("Gosho");
    }
    // 5. Stats
    @Test
    public void testStats(){
        Aquarium aqua = new Aquarium("Aqua1", 10);
        Fish fish = new Fish("Fish1");
        Fish fish2 = new Fish("Fish2");
        aqua.add(fish);
        aqua.add(fish2);

        Assert.assertEquals("Fish available at Aqua1: Fish1, Fish2",
                aqua.report());
    }

}

