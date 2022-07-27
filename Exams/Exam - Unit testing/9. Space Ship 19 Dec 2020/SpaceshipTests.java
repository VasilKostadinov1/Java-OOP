package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {

    private Spaceship spaceship;
    private Astronaut one;
    private Astronaut two;
    private Astronaut three;

    @Before
    public void setup() {
        spaceship = new Spaceship("X", 3);
        one = new Astronaut("one", 20);
        two = new Astronaut("two", 30);
        three = new Astronaut("three", 40);

        spaceship.add(one);
        spaceship.add(two);
        spaceship.add(three);
    }

    // test Constructor
    @Test
    public void testConstructor() {
        Assert.assertEquals(3, spaceship.getCount());
        Assert.assertEquals("X", spaceship.getName());
    }

    //1.1 validate name
    @Test(expected = NullPointerException.class)
    public void testNameNull() {
        new Spaceship(null, 3);
    }

    @Test(expected = NullPointerException.class)
    public void testNameIsEmpty() {
        new Spaceship("", 3);
    }

    //1.2 validate capacity
    @Test(expected = IllegalArgumentException.class)
    public void testZeroCapacity() {
        new Spaceship("X", -5);
    }

    // 2. add astronaut
    //2.1 add successfully
    @Test
    public void addAstroToSpaceship() {
        Assert.assertEquals(3, spaceship.getCount());
    }
    //2.2 astro exists already: if (astronautExists)
    @Test(expected = IllegalArgumentException.class)
    public void testAstronautExistsAlreadyThrows(){
        spaceship = new Spaceship("X", 4);
        spaceship.add(one);
        spaceship.add(one); // !!! without this line is giving error ?!
    }

    //2.3 if (astronauts.size() == this.getCapacity())
    @Test(expected = IllegalArgumentException.class)
    public void testSpaceshipIsFullThrow(){
        Astronaut four = new Astronaut("four", 5);
        spaceship.add(four);

    }

    // 3. Remove
    // 3.1 remove successfully
    @Test
    public void testRemoveAstro(){
        spaceship.remove("one");
        Assert.assertEquals(2, spaceship.getCount());
    }



}
