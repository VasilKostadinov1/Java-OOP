package football;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FootballTeamTests {

    private FootballTeam team;
    private Footballer one;
    private Footballer two;
    private Footballer three;

    @Before
    public void setUp() {
        team = new FootballTeam("Vasil", 3);
        one = new Footballer("one");
        two = new Footballer("two");
        three = new Footballer("three");

        team.addFootballer(one);
        team.addFootballer(two);
        team.addFootballer(three);
    }

    // 1. test Constructor
    @Test
    public void testConstructor() {
        Assert.assertEquals(3, team.getCount());
        Assert.assertEquals("Vasil", team.getName());
    }

    //1.1 invalid name
    @Test(expected = NullPointerException.class)
    public void testNameNullThrow() {
        new FootballTeam(null, 3);
    }

    @Test(expected = NullPointerException.class)
    public void testNameEmptyThrow() {
        new FootballTeam(" ", 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPositionsNegativeThrow() {
        new FootballTeam("Vasil", -3);
    }
    // 1.3 get count
    @Test
    public void testCount() {
        Assert.assertEquals(3, team.getCount());
    }

    // 2. Add footballer
    //2.1 happy
    @Test
    public void addFootballer(){
        Assert.assertEquals(3, team.getCount());
    }

    //2.2 add in full
    @Test(expected = IllegalArgumentException.class)
    public void addInFull(){
        Footballer four = new Footballer("four");
        team.addFootballer(four);
    }

    // 3 remove
    // 3.1 remove happy
    @Test
    public void removeFromTeam(){
        team.removeFootballer("one");
        Assert.assertEquals(2, team.getCount());
    }

    //3.2 throws as name is missing
    @Test(expected = IllegalArgumentException.class)
    public void removeNameIsNullThrows(){
        team.removeFootballer("Ivannnn");
    }

    // 4. for sale
    //4.1 successfully sold Active
    @Test
    public void forSaleIsActive(){
        Footballer forSale = team.footballerForSale("one");
        Assert.assertFalse(forSale.isActive());
    }
    //4.2 no such footballer
    @Test(expected = IllegalArgumentException.class)
    public void testNoSuchFootballerThrows(){
        team.footballerForSale("ten");
    }

    //5 print
    @Test
    public void testPrint(){
        String msg = "The footballer one, two, three is in the team Vasil.";
        Assert.assertEquals(msg, team.getStatistics());
    }




}
