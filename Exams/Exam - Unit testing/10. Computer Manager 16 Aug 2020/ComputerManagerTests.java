package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ComputerManagerTests {

    private ComputerManager manager;
    private Computer one;
    private Computer two;
    private Computer three;

    @Before
    public void setup(){
        manager = new ComputerManager();
        one = new Computer("X", "XX", 100);
        two = new Computer("Y", "YY", 200);
        three = new Computer("Z", "ZZ", 300);

        manager.addComputer(one);
        manager.addComputer(two);
        manager.addComputer(three);
    }

    //return Collections.unmodifiableCollection(this.data);
    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiable(){
        manager.getComputers().clear();
    }

    // get count
    @Test
    public void testGetCount(){
        Assert.assertEquals(3, manager.getCount());
    }
    //2. add
    //2.1 add successfully
    @Test
    public void testAddComputer(){
        Computer four = new Computer("F", "FF", 10);
        manager.addComputer(four);  // !!! add
        Assert.assertEquals(4, manager.getCount());
    }

    //2.2 if (flag) ....orElse(null) != null; throw
    @Test(expected = IllegalArgumentException.class)
    public void testAddComputerNotNullThrow(){
        manager.addComputer(null);
    }

    //2.3 already exists PC
    @Test(expected = IllegalArgumentException.class)
    public void testAddPCAlreadyExists(){
        manager.addComputer(one);
    }

    //3. Remove
    @Test
    public void testRemovePC(){
        manager.removeComputer("Z", "ZZ");
        Assert.assertEquals(2, manager.getCount());
    }

    //4 Get PC
    //4.1 - choose one PC as example
    @Test
    public void testGetPC(){
        Assert.assertEquals(two, manager.getComputer("Y", "YY"));
    }

    //4.2 if (computer == null); no such PC
    @Test(expected = IllegalArgumentException.class)
    public void testPCNullThrows(){  // non-existing
        manager.getComputer("S", "SS");
    }

    //5. get PC by manufacturer
    //5.1 get
    @Test
    public void testGetPCForListManufacturer(){
        //Computer four =new Computer("X", "XXXXXXX", 100);
        List<Computer> expectedPC = new ArrayList<>();
        expectedPC.add(one);
        //expectedPC.add(four);

        List<Computer> actualPC = manager.getComputersByManufacturer("X");
        Assert.assertEquals(expectedPC, actualPC);
    }

    //5.2 can't ne null manufacturer
    @Test(expected = IllegalArgumentException.class)
    public void testNonNullManufacturerThrows(){
        List<Computer> actualPC = manager.getComputersByManufacturer(null);
        
    }



}