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
}