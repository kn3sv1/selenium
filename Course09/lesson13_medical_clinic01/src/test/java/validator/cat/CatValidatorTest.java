package validator.cat;

import dto.CatRequest;
import model.CatColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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

    @Test
    void negativeCaseInvalidName() {
        CatRequest request = new CatRequest();

        request.name = null; // invalid name
        request.age = 5;
        request.color = CatColor.GREY;
        request.vaccinated = true;
        request.attributes = Map.of("indoor", "yes");
        request.favoriteFood = List.of("tuna", "chicken");
        request.mood = "happy";
        request.feedingTimes = List.of();
        request.sleeps = false;

        Map<String, String> errors = validator.validate(request);
        assertEquals("Name is required.", errors.get("name"));
    }

    @Test
    void negativeCaseInvalidAge() {
        CatRequest request = new CatRequest();

        request.name = "Whiskers";
        request.age = -1; // invalid age
        request.color = CatColor.GREY;
        request.vaccinated = true;
        request.attributes = Map.of("indoor", "yes");
        request.favoriteFood = List.of("tuna", "chicken");
        request.mood = "happy";
        request.feedingTimes = List.of();
        request.sleeps = false;

        Map<String, String> errors = validator.validate(request);
        assertEquals("Age must be between 0 and 30.", errors.get("age"));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidNames")
    void testInvalidNames(String name, String expectedError) {

        CatRequest dto = new CatRequest();
        dto.name = name;

        CatValidator validator = new CatValidator();

        Map<String, String> errors = validator.validate(dto);

        assertEquals(expectedError, errors.get("name"));
    }

    static Stream<Arguments> provideInvalidNames() {
        return Stream.of(
                Arguments.of(null, "Name is required."),
                Arguments.of("", "Name is required."),
                Arguments.of("A", "Name must be at least 2 characters long."),
                Arguments.of(
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                        "Name must be less than 50 characters long."
                ),
                Arguments.of("Tom123", "Name cannot contain numbers.")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidAges")
    void testInvalidAges(int age, String expectedError) {
        CatRequest dto = new CatRequest();
        dto.age = age;

        CatValidator validator = new CatValidator();

        Map<String, String> errors = validator.validate(dto);

        assertEquals(expectedError, errors.get("age"));
    }

    static Stream<Arguments> provideInvalidAges() {
        return Stream.of(
                Arguments.of(0, "Age is required."),
                Arguments.of(-1, "Age must be between 0 and 30."),
                Arguments.of(31, "Age must be between 0 and 30.")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidAttributes")
    void testInvalidAttributes(Map<String, String> attributes, String expectedError) {
        CatRequest dto = new CatRequest();
        dto.attributes = attributes;

        CatValidator validator = new CatValidator();

        Map<String, String> errors = validator.validate(dto);

        assertEquals(expectedError, errors.get("attributes"));
    }

    static Stream<Arguments> provideInvalidAttributes() {
        return Stream.of(
                Arguments.of(null, "Attributes are required."),
                Arguments.of(Map.of(), "Attributes cannot be empty.")
        );
    }

}
