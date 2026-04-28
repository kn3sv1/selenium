package repository;

import model.Cat;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CatRepositoryTest {
    @Test
    void testAddCats()  {
        CatRepository repository = new CatRepository(CatRepository.DATABASE_TEST);
        repository.clear();
        // check that the repository is empty before adding cats
        assertEquals(0, repository.getCats().size());

        UUID id1 = UUID.randomUUID();
        Cat cat1 = new Cat(
                id1,
                "Ginger",
                6,
                "orange",
                true,
                Map.of(
                    "indoor", "yes",
                    "favoriteToy", "small ball",
                    "gender", "female",
                    "weight", "5kg"
                ),
                List.of(
                        "tuna",
                        "chicken",
                        "whiskas"
                ),
                "bite"
        );

        // add cat to repository and check that it is added correctly.
        repository.add(cat1);
        Cat dbCat = repository.getById(id1);
        assertNotNull(dbCat);

        // check all properties
        assertEquals(id1, dbCat.getId());
        assertEquals("Ginger", dbCat.getName());
        assertEquals(6, dbCat.getAge());
        assertEquals("orange", dbCat.getColor());
        assertTrue(dbCat.isVaccinated());
        assertEquals("yes", dbCat.getAttributes().get("indoor"));
        assertEquals("small ball", dbCat.getAttributes().get("favoriteToy"));
        assertEquals("female", dbCat.getAttributes().get("gender"));
        assertEquals("5kg", dbCat.getAttributes().get("weight"));
        assertTrue(dbCat.getFavoriteFoods().contains("tuna"));
        assertTrue(dbCat.getFavoriteFoods().contains("chicken"));
        assertTrue(dbCat.getFavoriteFoods().contains("whiskas"));
        assertTrue(dbCat.isAggressive());
    }


    @Test
    public void testEatingTime() {
        CatRepository repository = new CatRepository(CatRepository.DATABASE_TEST);
        repository.clear();

        UUID id1 = UUID.randomUUID();
        Cat cat1 = new Cat(
                id1,
                "Ginger",
                6,
                "orange",
                true,
                Map.of(
                        "indoor", "yes",
                        "favoriteToy", "small ball",
                        "gender", "female",
                        "weight", "5kg"
                ),
                List.of(
                        "tuna",
                        "chicken",
                        "whiskas"
                ),
                "bite"
        );

        // test dynamic property.
        cat1.eatAtTime("12:00");
        cat1.eatAtTime("18:00");
        cat1.sleep();

        repository.add(cat1);
        Cat dbCat = repository.getById(id1);
        assertNotNull(dbCat);

        assertTrue(dbCat.isSleeps());
        assertTrue(dbCat.getFeedingTimes().contains("12:00"));
        assertTrue(dbCat.getFeedingTimes().contains("18:00"));
        assertFalse(dbCat.getFeedingTimes().contains("10:00"));

        // test speak and cry methods
        assertEquals("Meow: Ginger", dbCat.speak());
        assertEquals("Cry: Ginger to Angie", dbCat.cry("Angie"));
    }

    // test update method
    @Test
    public void testUpdateCat() {
        CatRepository repository = new CatRepository(CatRepository.DATABASE_TEST);
        repository.clear();

        UUID id1 = UUID.randomUUID();
        Cat cat1 = new Cat(
                id1,
                "Ginger",
                6,
                "orange",
                true,
                Map.of(
                        "indoor", "yes",
                        "favoriteToy", "small ball",
                        "gender", "female",
                        "weight", "5kg"
                ),
                List.of(
                        "tuna",
                        "chicken",
                        "whiskas"
                ),
                "bite"
        );

        repository.add(cat1);

        // update cat's name and color
        cat1.setName("Fluffy");
        cat1.setColor("black");
        repository.update(cat1);
        Cat dbCat = repository.getById(id1);
        assertNotNull(dbCat);
        assertEquals("Fluffy", dbCat.getName());
        assertEquals("black", dbCat.getColor());
    }

    // test delete method
    @Test
    public void testDeleteCat() {
        CatRepository repository = new CatRepository(CatRepository.DATABASE_TEST);
        repository.clear();

        UUID id1 = UUID.randomUUID();
        Cat cat1 = new Cat(
                id1,
                "Ginger",
                6,
                "orange",
                true,
                Map.of(
                        "indoor", "yes",
                        "favoriteToy", "small ball",
                        "gender", "female",
                        "weight", "5kg"
                ),
                List.of(
                        "tuna",
                        "chicken",
                        "whiskas"
                ),
                "bite"
        );

        repository.add(cat1);

        // delete cat and check that it is deleted correctly.
        repository.deleteById(cat1.getId());
        Cat dbCat = repository.getById(id1);
        assertNull(dbCat);
    }
}
