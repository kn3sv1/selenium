package validator.cat;

import dto.CatRequest;

import java.util.HashMap;
import java.util.Map;

public class CatValidator {
    public Map<String, String> validateEmptyFields(CatRequest dto) {
        Map<String, String> errors = new HashMap<>();

        if (dto.name == null || dto.name.isEmpty()) {
            errors.put("name", "Name is required.");
        }

        if (dto.age == 0) {
            errors.put("age", "Age is required.");
        }

        if (dto.color == null || dto.color.isEmpty()) {
            errors.put("color", "Color is required.");
        }

        if (!dto.vaccinated) {
            errors.put("vaccinated", "Vaccinated is required.");
        }

        if (dto.attributes == null) {
            errors.put("attributes", "Attributes is required.");
        }

        if (dto.favoriteFood == null) {
            errors.put("favoriteFood", "Favorite food is required.");
        }

        if (dto.mood == null || dto.mood.isEmpty()) {
            errors.put("mood", "Mood is required.");
        }

        return errors;
    }
}
