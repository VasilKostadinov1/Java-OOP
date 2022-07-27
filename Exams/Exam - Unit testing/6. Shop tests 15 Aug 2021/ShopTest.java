package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ShopTest {
    // TODO

    private Shop shop;
    private Goods goods;

    @Before
    public void setup(){
        shop = new Shop();
        goods = new Goods("name", "code");
    }

    //return Collections.unmodifiableMap(this.shelves);
    @Test(expected = UnsupportedOperationException.class)
    public void testGetShelvesTrows(){
        shop.getShelves().clear();
    }
    // test Constructor
    @Test
    public void testConstructor(){
        Assert.assertEquals(null, shop.getShelves().get("Shelves1"));
    }

    // 2. add goods
    //2.1
    //if (!this.shelves.containsKey(shelf)) = No such shelf exists
    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsThrowsTheShelfDoesNotExist() throws OperationNotSupportedException {
        shop.addGoods("Doesn't exist", goods);
    }
    //2.2 if (this.shelves.get(shelf) != null) == shelf already taken
    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsThrowsTheShelfIsTaken() throws OperationNotSupportedException {
        Goods goods2 = new Goods("name2", "code2");

        shop.addGoods("Shelves1", goods);
        shop.addGoods("Shelves1", goods2);
    }
    //2.3  if (itemExist) == item exists
    @Test(expected = OperationNotSupportedException.class)
    public void testItemAlreadyExistsThrows() throws OperationNotSupportedException {
        shop.addGoods("Shelves1", goods);
        shop.addGoods("Shelves2", goods);
    }

    //2.4 adds goods correct
    @Test
    public void testAddsGoodsCorrectly() throws OperationNotSupportedException {
        String expected = "Goods: code is placed successfully!";
        String actual = shop.addGoods("Shelves1", goods);
        Assert.assertEquals(expected, actual);
    }

    // 3. Remove
    // 3.1 if (!this.shelves.containsKey(shelf)) = No such shelf exists
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsThrowsTheShelfDoesNotExist() throws OperationNotSupportedException {
        shop.removeGoods("Doesn't exist", goods);
    }
    //3.2 if (this.shelves.get(shelf) != goods)
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsThrowsGoodsDoesNotExist() throws OperationNotSupportedException {
        Goods empty = new Goods("Empty", "EmptyCode");
        shop.removeGoods("Shelves1", empty);
    }

    //3.3 adds goods correct
    @Test
    public void testRemoveGoodsCorrectly() throws OperationNotSupportedException {
        String expected = "Goods: code is removed successfully!";
        shop.addGoods("Shelves1", goods);

        String actual = shop.removeGoods("Shelves1", goods);
        Assert.assertEquals(expected, actual);
        Assert.assertNull(shop.getShelves().get("Shelves1"));
    }

    // 100/100 without below
    @Test
    public void testGoodsGetNameAndGoodsCode(){
        Assert.assertEquals("name", goods.getName());
        Assert.assertEquals("code", goods.getGoodsCode());
    }
}