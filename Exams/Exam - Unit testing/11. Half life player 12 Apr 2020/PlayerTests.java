package halfLife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTests {

    private Player player;
    private Gun one;
    private Gun two;
    private Gun three;

    @Before
    public void setup() {
        player = new Player("Vasil", 10);
        one = new Gun("one", 5);
        two = new Gun("two", 10);
        three = new Gun("three", 20);

        player.addGun(one);
        player.addGun(two);
        player.addGun(three);
    }

    //test Constructor
    @Test
    public void testConstructor() {
        Assert.assertEquals(10, player.getHealth());
        Assert.assertEquals("Vasil", player.getUsername());
    }

    // Collections.unmodifiableList(this.guns);
    @Test(expected = UnsupportedOperationException.class)
    public void testShouldThrowExceptionForTryingToModifyUnmodifiableCollection() {
        player.getGuns().clear();
    }

    // if (username == null || username.trim().length() < 1)
    @Test(expected = NullPointerException.class)
    public void testNameNull() {
        new Player(null, 10);
    }

    //if (health < 0)
    @Test(expected = IllegalArgumentException.class)
    public void testHealthBelowZeroThrow() {
        new Player("ZZZ", -5);
    }

    //2 Take Damage
    //2.1 if (this.health <= 0)
    @Test(expected = IllegalStateException.class)
    public void testDamageOnDeadPLayer() {
        player = new Player("Test", 0);
        player.takeDamage(30);
    }

    //2.2 if (this.health - damage < 0)
    //    this.health = 0;
    //    } else {
    //    this.health -= damage;
    @Test
    public void takeDamageOnLivePlayer() {
        //health 10 - damage 3 = 7
        player.takeDamage(3);
        Assert.assertEquals(7, player.getHealth());
    }

    // 10 -10 = 0
    @Test
    public void takeDamageOnLivePlayerToZero() {
        player.takeDamage(10);
        Assert.assertEquals(0, player.getHealth());
    }

    // 3 add gun
    // 3.1 if (gun == null)
    @Test(expected = NullPointerException.class)
    public void testGunCanNotBeZeroThrow() {
        player.addGun(null);
    }

    // 4 remove gun
    //public boolean removeGun(Gun gun)
    @Test
    public void testRemoveGun() {
        Assert.assertTrue(player.removeGun(one));
    }

    // 5. get gun - choose a gun as an example
    @Test
    public void testGetGun() {
        Assert.assertEquals(two, player.getGun("two"));
    }

}
