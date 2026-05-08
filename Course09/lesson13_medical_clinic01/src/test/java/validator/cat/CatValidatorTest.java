package validator.cat;

import dto.CatRequest;
import model.CatColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CatValidatorTest {
    private CatValidator validator;

    @BeforeEach
    void setUp() {
        this.validator = new CatValidator();
    }

    @Test
    void shouldValidatePositiveCase() {
        CatRequest request = new CatRequest();
        request.name = "Whiskers";
        request.age = 5;
        request.color = CatColor.GREY;
        request.vaccinated = true;
        request.attributes = Map.of("indoor", "yes");
        request.favoriteFood = List.of("tuna", "chicken");
        request.mood = "happy";
        request.feedingTimes = List.of();
        request.sleeps = false;

        Map<String, String> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }
}
