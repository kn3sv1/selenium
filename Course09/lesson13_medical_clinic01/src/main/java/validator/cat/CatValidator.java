package validator.cat;

import dto.CatRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CatValidator {
    public Map<String, String> validate(CatRequest dto) {
        Map<String, String> errors = new HashMap<>();

        if (dto.name == null) {
            errors.put("name", "Name is required.");
        }

        if (dto.name != null && dto.name.isEmpty()) {
            errors.put("name", "Name is required.");
        }

        if (dto.name != null && dto.name.length() == 1) {
            errors.put("name", "Name must be at least 2 characters long.");
        }

        if (dto.name != null && dto.name.length() > 50) {
            errors.put("name", "Name must be less than 50 characters long.");
        }

        if (dto.name != null && dto.name.matches(".*\\d.*")) {
            errors.put("name", "Name cannot contain numbers.");
        }

        if (dto.age == 0) {
            errors.put("age", "Age is required.");
        }

        if (dto.age < 0 || dto.age > 30) {
            errors.put("age", "Age must be between 0 and 30.");
        }

        if (!dto.vaccinated) {
            errors.put("vaccinated", "Vaccinated is required.");
        }

        if (dto.attributes == null) {
            errors.put("attributes", "Attributes are required.");
        }

        if (dto.attributes != null && dto.attributes.isEmpty()) {
            errors.put("attributes", "Attributes cannot be empty.");
        }

        if (dto.favoriteFood == null) {
            errors.put("favoriteFood", "Favorite food is required.");
        }

        if (dto.mood == null || dto.mood.isEmpty()) {
            errors.put("mood", "Mood is required.");
        }

        Set<String> allowedKeys = Set.of(
                "indoor",
                "favoriteToy",
                "gender",
                "weight"
        );

        Map<String, Set<String>> allowedValues = Map.of(
                "gender", Set.of("male", "female"),
                "indoor", Set.of("yes", "no"),
                "weight", Set.of("2kg", "5kg", "6kg", "7kg", "8kg", "9kg", "10kg")
        );

        for (Map.Entry<String, String> entry : dto.attributes.entrySet()) {

            String key = entry.getKey();
            String value = entry.getValue();

            if (!allowedKeys.contains(key)) {
                errors.put(
                        "attributes",
                        "Invalid attribute key: " + key
                );
            }

            if (allowedValues.containsKey(key)) {

                Set<String> validValues = allowedValues.get(key);

                if (!validValues.contains(value)) {

                    errors.put(
                            "attributes",
                            "Invalid value for " + key
                    );
                }
            }
        }

        return errors;
    }
}
