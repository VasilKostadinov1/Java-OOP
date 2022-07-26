package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeroRepositoryTests {

    private HeroRepository heroRepository;
    private Hero first;
    private Hero second;
    private Hero third;

    @Before
    public void setup() {
        heroRepository = new HeroRepository();
        first = new Hero("Ivan", 2);
        second = new Hero("Pesho", 4);
        third = new Hero("Gosho", 5);

        heroRepository.create(first);
        heroRepository.create(second);
        heroRepository.create(third);
    }

    // 1. count check
    @Test
    public void testCountCheck() {
        Assert.assertEquals(3, heroRepository.getCount());
    }

    // 2. create hero
    //2.1 hero = null throw

    @Test(expected = NullPointerException.class)
    public void testNullHero() {
        heroRepository.create(null);
    }

    //2.2 hero throw Illegal if already exists
    @Test(expected = IllegalArgumentException.class)
    public void testShouldThrowForHeroAlreadyExists() {
        heroRepository.create(first);
    }

    //2.3 add hero  == 1. count check
    @Test
    public void testAddHero() {
        Assert.assertEquals(3, heroRepository.getCount());
    }

    // 3. remove
    // 3.1 name null or empty
    @Test(expected = NullPointerException.class)
    public void testRemoveHeroNameNull() {
        heroRepository.remove(null);
        //heroRepository.remove("PeshoGosho"); - > doesn't exist
    }
    // 3.2 remove
    @Test
    public void testHeroIsRemoved(){
        heroRepository.remove("Ivan");
        Assert.assertEquals(2, heroRepository.getCount());
    }
    // 4. highest level
    @Test
    public void testHeroWithHighestLevel(){
        Assert.assertEquals(third, heroRepository.getHeroWithHighestLevel());
    }

    // 5. get hero by name
    @Test
    public void testGetHeroByName(){
        Assert.assertEquals(first, heroRepository.getHero("Ivan"));
    }
    // 6. Collections.unmodifiableCollection(this.data)
    @Test(expected = UnsupportedOperationException.class)
    public void testThrowsForCollectionUnmodifiable(){
        heroRepository.getHeroes().clear();
    }

}
