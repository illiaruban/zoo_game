import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZooTest {
    //test zoo functions

    @Test
    void testZooInit()
    {
        Zoo zoo = new Zoo();
        assertEquals(2500, Zoo.budget);
        assertEquals(0, Zoo.dictFoodAmount.get("Ape Food"));
        assertEquals(0, Zoo.dictFoodAmount.get("Bird Food"));
        assertEquals(0, Zoo.dictFoodAmount.get("Reptile Food"));
        assertEquals(0, Zoo.dictFoodAmount.get("Cat Food"));
        assertEquals(0, Zoo.animals.length);
    }

    @Test
    void testBuySell()
    {
        Zoo zoo = new Zoo();
        Zoo.animals = new Animal[0];
        Creator.createdAnimals = Creator.addToAnimals(Creator.createdAnimals, new Ape("Gorilla", 180.0f, 10.0f, false, 14));
        Creator.createdAnimals = Creator.addToAnimals(Creator.createdAnimals, new Bird("Bald Eagle", 400.0f, 14.0f, true, 5));
        Creator.createdAnimals = Creator.addToAnimals(Creator.createdAnimals,  new Reptile("Crocodile", 600.0f, 17.0f, false, 10));
        Creator.createdAnimals = Creator.addToAnimals(Creator.createdAnimals, new Cat("African Lion", 820.0f, 20.0f, true, 12));
        //test the decreasing of the budget/amount in area for one animal
        zoo.zooBuyAnimal(1);
        assertEquals(2500 - Zoo.animals[0].price, Zoo.budget);
        System.out.println(Zoo.animals[0].amountInArea);
        //todo control the amount of animals and where they change
        assertEquals(1, Zoo.animals[0].amountInArea);
        //test if IDs are not same for the same animal
        zoo.zooBuyAnimal(1);
        assertNotEquals(Zoo.animals[0].id, Zoo.animals[1].id);
        //test the increased amount in area
        assertEquals(2, Zoo.animals[0].amountInArea);

        //test selling the animal
        zoo.zooSellAnimal(1);
        assertEquals(1, Zoo.animals[0].amountInArea);

    }

    @Test
    void testStartEndDay()
    {
        Zoo zoo = new Zoo();
        Creator.createdAnimals = Creator.addToAnimals(Creator.createdAnimals, new Ape("Gorilla", 180.0f, 10.0f, false, 14));
        zoo.zooBuyAnimal(1);
        Zoo.isOpened = true;
        zoo.moveToNext();
        assertEquals(0, Zoo.animals.length);

        zoo.zooBuyAnimal(1);
        Zoo.dictFoodAmount.put("Ape Food", 1);
        Creator.dictFoodSpec.put("Gorilla", "Ape Food");
        float budget = Zoo.budget;
        zoo.feedAnimal();
        zoo.startDay();
        zoo.endDay();
        assertNotEquals(budget, Zoo.budget);
        assertTrue(Zoo.budget > budget);

    }

}