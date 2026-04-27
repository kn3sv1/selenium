package validator.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorValidatorTest {
    private CalculatorValidator validator;

    @BeforeEach
    void setUp() {
        this.validator = new CalculatorValidator();
    }
//   // long version of test cases. We can use parameterized test to make it more concise and readable.
     // only needed for detailed test cases (specific errors), for example, if we want to check the error messages in negative cases, then we can use long version of test cases.
//    @Test
//    void shouldAddNumbers() {
//        // possitive add numbers
//        Map<String, String> form = Map.of(
//                "a", "5",
//                "b", "10",
//                "operation", "+"
//        );
//        Map<String, String> errors = validator.validate(form);
//        assertTrue(errors.isEmpty());
//
//    }
//
//    @Test
//    void shouldSubtractNumbers() {
//        // possitive add numbers
//        Map<String, String> form = Map.of(
//                "a", "5",
//                "b", "10",
//                "operation", "-"
//        );
//        Map<String, String> errors = validator.validate(form);
//        assertTrue(errors.isEmpty());
//
//    }

    /**
     * this method will be called for each test case provided by the providePositiveCasesData method,
     * and we will check that there are no errors for each test case, and we will use parameterized test
     * to make it more concise and readable, and we will use method source to provide test cases data,
     * and we will use map to represent form data, and we will check that there are no errors for each test case.
     * method source is like for each with list of ready maps.
     * @param form
     */
    @ParameterizedTest
    @MethodSource("providePositiveCasesData")
    void testPositive(Map<String, String> form) {
        Map<String, String> errors = validator.validate(form);
        assertTrue(errors.isEmpty());
    }

    static Stream<Map<String, String>> providePositiveCasesData() {
        return Stream.of(
                Map.of(
                        "a", "5",
                        "b", "10",
                        "operation", "+"
                ),
                Map.of(
                        "a", "5",
                        "b", "10",
                        "operation", "-"
                ),
                Map.of(
                        "a", "5",
                        "b", "10",
                        "operation", "*"
                )
        );
    }


    @ParameterizedTest
    @MethodSource("provideNegativeCasesData")
    void testNegative(Map<String, String> form) {
        Map<String, String> errors = validator.validate(form);
        assertFalse(errors.isEmpty());
    }

    static Stream<Map<String, String>> provideNegativeCasesData() {
        return Stream.of(
                Map.of(
                ),
                Map.of(
                        "b", "10",
                        "operation", "+"
                ),
                Map.of(
                        "a", "5",
                        "operation", "-"
                ),
                Map.of(
                        "a", "5",
                        "b", "10"
                ),
                Map.of(
                        "a", "5",
                        "b", "10",
                        "operation", "not valid"
                ),
                Map.of(
                        "a", "Hello",
                        "b", "angie",
                        "operation", "*"
                )
        );
    }

    @Test
    void testNegativeForEach() {
        List<Map<String, String>> list = Arrays.asList(
                Map.of(
                ),
                Map.of(
                        "b", "10",
                        "operation", "+"
                ),
                Map.of(
                        "a", "5",
                        "operation", "-"
                ),
                Map.of(
                        "a", "5",
                        "b", "10"
                ),
                Map.of(
                        "a", "5",
                        "b", "10",
                        "operation", "not valid"
                ),
                Map.of(
                        "a", "Hello",
                        "b", "angie",
                        "operation", "*"
                )
        );

       //for (Map<String, String> form : list) {
        // To see ERRORS in OUTPUT TERMINAL what was wrong.
        for (int i = 0; i < list.size(); i++) {
           Map<String, String> form = list.get(i);
           Map<String, String> errors = validator.validate(form);
            System.out.println("Test case #" + i + " should have errors. Form: " + form + " Errors amount:" + errors.size() + " Errors: " + errors);
           assertFalse(errors.isEmpty(), "Test case #" + i + " should have errors. Form: " + form);
       }
    }

    /**
     * why it's separate method and not in negative test case method?
     * answer: because we want to check the specific error message for division
     * by zero case, and we don't want to check it for other negative cases,
     * that's why we separate it from other negative test cases.
     * because we don't have assertEquals for error message in other negative test cases,
     * we just check that errors are not empty, but in this case we want to check the specific error message,
     * that's why we separate it from other negative test cases.
     */
    @Test
    void shouldDivideBy0() {
        // possitive add numbers
        Map<String, String> form = Map.of(
                "a", "5",
                "b", "0",
                "operation", "/"
        );
        Map<String, String> errors = validator.validate(form);
        assertFalse(errors.isEmpty());
        // extra validation - that's why this case we don't add to negative test cases.
        assertEquals(errors.get("b"), "Parameter b cannot be zero for division");
    }
}


