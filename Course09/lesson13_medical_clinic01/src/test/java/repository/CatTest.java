package repository;

import model.Cat;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CatTest {
    @Test
    public void testFinalConstructor() {
        UUID id1 = UUID.randomUUID();
        Map<String, String> attributes = new HashMap<>(Map.of(
                "indoor", "yes",
                "favoriteToy", "small ball",
                "gender", "female",
                "weight", "5kg"
        ));
        List<String> favoriteFood = new ArrayList<>(List.of(
                "tuna",
                "chicken",
                "whiskas"
        ));

        Cat cat1 = new Cat(
                id1,
                "Ginger",
                6,
                "orange",
                true,
                attributes,
                favoriteFood,
                "bite"
        );

        // bypass final constructor and try to modify attributes and favoriteFood, but it should not affect the original object.
        attributes.put("fakeAttribute", "fakeValue");
        assertFalse(cat1.getAttributes().containsKey("fakeAttribute"));

        favoriteFood.add("fakeFood");
        assertFalse(cat1.getFavoriteFoods().contains("fakeFood"));

        Map<String, String> catAttributes = cat1.getAttributes();
        List<String> catFavoriteFoods = cat1.getFavoriteFoods();

        assertThrows(UnsupportedOperationException.class, () -> {
            catAttributes.put("anotherFakeAttribute", "anotherFakeValue");
        });


        assertThrows(UnsupportedOperationException.class, () -> {
            catFavoriteFoods.add("fakeFood");
        });
    }

    @Test
    public void testFinalGetterModify() {
        // we need to test if we can set getter.
        // we did this in previous test not to duplicate logic of creation of Cat
        // because it's very simple. If it was big logic we would create separate test case.
        // so programmer will be able to easily understand test case.
    }

    @Test
    public void testFinalModifyTypeInsideList() {
        // type inside is String.
        // we don't write test case for String because we know that String is immutable.
        // but for our custom object we need to write test case.
    }
}
