import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatorTest {
    @Test
    public void testInit()
    {
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> {
            new Creator("1111");
        });
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> {
            new Creator("111yy122342353¬¬¬¬");
        });
    }

    @Test
    public void testAddDelete()
    {
        Creator.createdAnimals = Creator.addToAnimals(Creator.createdAnimals, new Ape("Gorilla", 180.0f, 10.0f, false, 14));
        assertEquals(Creator.createdAnimals.length, 1);

        Creator.createdAnimals = Creator.deleteFromAnimals(Creator.createdAnimals, 1);
        assertEquals(Creator.createdAnimals.length, 0);
    }

}