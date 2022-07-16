package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ShopTest {

    private Shop shop;
    private Goods goods;

    @Before
    public void setup() {
        shop = new Shop();
    }

    //test Constructor
    @Test
    public void createShop() {
        goods = new Goods("Az", "Az-codes");
        Assert.assertEquals("Az", goods.getName());
        Assert.assertEquals("Az-codes", goods.getGoodsCode());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testShouldReturnUnmodifiableCollection() {
        shop.getShelves().clear();
    }

    // 2. Add goods
    // 2.1 do not contain shelf: if (!this.shelves.containsKey(shelf)) {
    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowExceptionForNoneExistingGoodOnTheShelf() throws OperationNotSupportedException {
        shop.addGoods("invalid_test_shelf", null);
    }

    //2.2 if (this.shelves.get(shelf) != null) {
    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsShouldFailForExistingGoods() throws OperationNotSupportedException {
        Goods goods = new Goods("test_good", "test_code");
        shop.addGoods("Shelves1", goods);
        shop.addGoods("Shelves1", goods);
    }

    //2.3 return String.format("Goods: %s is placed successful
    @Test
    public void testShouldReturnCorrectMessage() throws OperationNotSupportedException {
        Goods goods = new Goods("test_good", "test_code");
        String expected = "Goods: test_code is placed successfully!";
        String actual = shop.addGoods("Shelves1", goods);
        Assert.assertEquals(expected, actual);
    }
    //2.4  if (itemExist) {
    @Test(expected = OperationNotSupportedException.class)
    public void testShouldThrowExceptionForAlreadyExistingGoods() throws OperationNotSupportedException {
        shop.addGoods("Shelves1", goods);
        shop.addGoods("Shelves2", goods);
    }

    //3. Remove
    //3.1 if (!this.shelves.containsKey(shelf)) {
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveShouldThrowExceptionForNoneExistingGoodOnTheShelf() throws OperationNotSupportedException {
        shop.removeGoods("invalid_test_shelf", null);
    }

    //2.2 if this.shelves.get(shelf) != goods)  {
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsShouldFailForDiffGoodsOnSameShelf() throws OperationNotSupportedException {
        Goods goods = new Goods("test_good", "test_code");
        Goods goodsOther = new Goods("test_good_other", "test_code_other");
        shop.addGoods("Shelves1", goods);
        shop.removeGoods("Shelves1", goodsOther);
    }

    //2.3 return String.format("Goods: %s is removed successful
    @Test
    public void testRemoveShouldReturnCorrectMessage() throws OperationNotSupportedException {
        Goods goods = new Goods("test_good", "test_code");
        shop.addGoods("Shelves1", goods);
        String expected = "Goods: test_code is removed successfully!";
        String actual = shop.removeGoods("Shelves1", goods);
        Assert.assertEquals(expected, actual);
    }

    //2.4 test NUll good -> assertNull !!!!
    @Test
    public void testRemoveShouldSetShelvesToNull() throws OperationNotSupportedException {
        Goods goods = new Goods("test_good", "test_code");
        shop.addGoods("Shelves1", goods);
        shop.removeGoods("Shelves1", goods);

        Goods nullGood = shop.getShelves().get("Shelves1");
        Assert.assertNull(nullGood);

    }


}