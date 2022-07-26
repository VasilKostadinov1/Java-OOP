package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PetStoreTests {

    private PetStore petStore;
    private Animal first;
    private Animal second;
    private Animal third;

    @Before
    public void setUp(){
        petStore = new PetStore();
        first = new Animal("Dog", 50, 100.5);
        second= new Animal("Cat", 20, 90.5);
        third =new Animal("Parrot", 2, 170.5);

        petStore.addAnimal(first);
        petStore.addAnimal(second);
        petStore.addAnimal(third);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testShouldThrowExceptionForTryingToModifyUnmodifiableCollection(){
        petStore.getAnimals().clear();
    }

    //2. add animal
    // 2.1  if (animal == null)  throw
    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalNullThrows(){
        petStore.addAnimal(null);
    }
    //2.2 add if there is space
    @Test
    public void addAnimal(){
        Assert.assertEquals(3, petStore.getCount());
    }

    //animal count check
    @Test
    public void animalCountInPetStore(){
        Assert.assertEquals(3, petStore.getCount());
    }

    @Test
    public void testTheMostExpensiveAnimal(){
        Assert.assertEquals(third, petStore.getTheMostExpensiveAnimal());
    }

    @Test
    public void findAllAnimalBySpecieDog(){
        //Animal four = new Animal("Dog", 80, 120.5);
        List<Animal> expected = new ArrayList<>();
        expected.add(first);
        //expected.add(four);

        List<Animal> actual = petStore.findAllAnimalBySpecie("Dog");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findAllAnimalsWithMaxKilograms(){
        List<Animal> expected = new ArrayList<>();
        expected.add(first);
        expected.add(second);

        List<Animal> actual = petStore.findAllAnimalsWithMaxKilograms(10);
        Assert.assertEquals(expected, actual);
    }



}

