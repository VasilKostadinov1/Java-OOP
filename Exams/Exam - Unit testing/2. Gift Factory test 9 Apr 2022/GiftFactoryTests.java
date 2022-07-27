package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GiftFactoryTests {

    private GiftFactory factory;
    private Gift one;
    private Gift two;
    private Gift three;

    @Before
    public void setup() {
        factory = new GiftFactory();
        one = new Gift("water", 10);
        two = new Gift("fire", 8);    // least magic
        three = new Gift("land", 12);

        factory.createGift(one);
        factory.createGift(two);
        factory.createGift(three);
    }

    //return Collections.unmodifiableCollection(this.data);
    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiable() {
        factory.getPresents().clear();
    }

    // test getCount
    @Test
    public void testCount() {
        Assert.assertEquals(3, factory.getCount());
    }

    //2. create gift
    //2.1 create successfully
    @Test
    public void addGiftSuccessfully() {
        Gift four = new Gift("forth", 2);
        factory.createGift(four);
        Assert.assertEquals(4, factory.getCount());
    }

    //2.2 throws for already exists gift
    @Test(expected = IllegalArgumentException.class)
    public void testGiftAlreadyExistsInFactory() {
        factory.createGift(one);
    }

    // 3 . Remove
    //3.1 remove successfully
    @Test
    public void testRemoveSuccessfully() {
        factory.removeGift("water");
        Assert.assertEquals(2, factory.getCount());
    }

    //3.2 remove if (name == null || name.trim().isEmpty())
    @Test(expected = NullPointerException.class)
    public void testRemoveNameNull() {
        factory.removeGift(null);
    }

    //4. getPresentWithLeastMagic()
    @Test
    public void testPresentWithLeastMagic() {
        Assert.assertEquals
                (two, factory.getPresentWithLeastMagic());
    }

    // 5. get present (I choose which one !!!)
    @Test
    public void getPresentWithNameFire() {
        Assert.assertEquals(two, factory.getPresent("fire"));
    }


}
