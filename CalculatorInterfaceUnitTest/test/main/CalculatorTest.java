package  main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void testAddOperation() {
        Calculator calc = new Calculator();
        calc.setOperation(new Add());
        assertEquals(8.0, calc.apply(5, 3));
    }

    @Test
    void testSubtractOperation() {
        Calculator calc = new Calculator();
        calc.setOperation(new Subtract());
        assertEquals(2.0, calc.apply(5, 3));
    }

    @Test
    void testMultiplyOperation() {
        Calculator calc = new Calculator();
        calc.setOperation(new Multiply());
        assertEquals(15.0, calc.apply(5, 3));
    }

    @Test
    void testDivideOperation() {
        Calculator calc = new Calculator();
        calc.setOperation(new Divide());
        assertEquals(2.0, calc.apply(6, 3));
    }

    @Test
    void testDivideByZeroThrowsException() {
        Calculator calc = new Calculator();
        calc.setOperation(new Divide());

        assertThrows(ArithmeticException.class, () -> calc.apply(5, 0));
    }

    @Test
    void testCalculatorWithCustomMockOperation() {
        Calculator calc = new Calculator();
        // "(a, b) -> 42" - Very small amount of text inline, so lambda expression is the smallest amount of
        //copy/paste text.
        calc.setOperation((a, b) -> 42); // mock: always return 42
        assertEquals(42.0, calc.apply(10, 5));
    }

    @Test
    void testFakeAddInline() {
        // This is a better way - anonymous Class. We just copy/paste text and no additional file created.
        // inline class - because I use it only here - ONCE
        Operation fakeAdd = new Operation() {
            @Override
            public double operation(double a, double b) {
                return a + b;
            }
        };

        Calculator calc = new Calculator();
        calc.setOperation(fakeAdd);
        assertEquals(15, calc.apply(10, 5));
    }


    @Test
    void testFakeSubtract() {
        // the most unprofessional way is to create extra file! Because we use only ONCE.
        Operation subtract = new FakeSubtract();

        Calculator calc = new Calculator();
        calc.setOperation(subtract);
        assertEquals(5, calc.apply(10, 5));
    }


    @Test
    void testLambdaMultiply() {
        Calculator calc = new Calculator();
        calc.setOperation((a, b) -> a * b);
        assertEquals(50, calc.apply(10, 5));
    }


    /**
     * for this test case we don't have ready class Add in our project, and we don't want to create an extra class
     * just for testing so lambda is a more professional way and we can use lambda.
     */
    @Test
    void testLambdaAddMinusOne() {
        Calculator calc = new Calculator();
        //Just complains that I don't need extra variable because It's extra text and used only in one place.
        Operation add = (a, b) -> a + b - 1;
        calc.setOperation(add);
        assertEquals(14, calc.apply(10, 5), "Check monkey numbers");
    }
}