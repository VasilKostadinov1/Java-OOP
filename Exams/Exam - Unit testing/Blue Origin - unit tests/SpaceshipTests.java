package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Spaceship

    private Spaceship spaceship;
    private Astronaut first;
    private Astronaut second;
    private Astronaut third;

    @Before
    public void setUp() {
        this.spaceship = new Spaceship("Softuni Profi", 25);
        // Initilize astronauts
        this.first = new Astronaut("Pesho", 100);
        this.second = new Astronaut("ivan", 90);
        this.third = new Astronaut("tosho", 80);
    }

    //1. test Constructor ...= new Array
    @Test
    public void testCreateSpaceshipShouldWork() {
        new Spaceship("Softuni Profi", 25);
        Assert.assertEquals("Softuni Profi", spaceship.getName());
        Assert.assertEquals(25, spaceship.getCapacity());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameThrowsNull() {
        new Spaceship(null, 15);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameThrowsWithEmpty() {
        new Spaceship("", 15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityThrows() {
        new Spaceship("Softuni Profi", -5);
    }

    //2. addAstronaut
    //2.1 adds successfully
    @Test
    public void testAddShouldWork() {
        Spaceship spaceship = new Spaceship("Softuni Profi", 25);
        spaceship.add(first);
        Assert.assertEquals(1, spaceship.getCount());
        spaceship.add(second);
        Assert.assertEquals(2, spaceship.getCount());
        spaceship.add(third);
        Assert.assertEquals(3, spaceship.getCount());
    }

//    @Test(expected = IllegalArgumentException.class)
//    public void testAddShouldFailWithoutCapacity() {
//        Spaceship spaceship = new Spaceship("Softuni Profi", 1);
//        spaceship.add(first);
//        spaceship.add(second);
//    }

    //2.2 adds in full -> throws
    //if (astronauts.size() == this.getCapacity())
    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldFailWithDuplicate() {
        Spaceship spaceship = new Spaceship("Softuni Profi", 1);
        spaceship.add(first);
        spaceship.add(second);
    }

    //if (astronautExists)
    @Test(expected = IllegalArgumentException.class)
    public void testForAlreadyExists(){
        Spaceship spaceship = new Spaceship("Softuni Profi", 10);
        Astronaut astro = new Astronaut("Astro",30);
        spaceship.add(astro);
        spaceship.add(astro);
    }

    // 3. remove
    // 3.1 removes successfully
    @Test
    public void testRemoveShouldRemove() {
        this.spaceship.add(first);
        Assert.assertEquals(1, spaceship.getCount());
        this.spaceship.remove(first.getName());
        Assert.assertEquals(0, spaceship.getCount());
    }


}
