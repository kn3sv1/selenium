package  main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void add() {
        Calculator c = new Calculator();
        assertEquals(4, c.add(2, 2));
    }

    @Test
    void subtract() {
        Calculator c = new Calculator();
        assertEquals(0, c.subtract(2, 2));
    }

    @Test
    void multiply() {
        Calculator c = new Calculator();
        assertEquals(20, c.multiply(1, 20));
    }

    @Test
    void divide() {
        Calculator c = new Calculator();
        assertEquals(2, c.divide(10, 5));
    }

    @Test
    void angieTestRomaCase() {
        Calculator c = new Calculator();
        assertEquals(7, c.angie(3, 4));
    }

    @Test
    void testNewMethod() {
        Calculator c = new Calculator();
        assertEquals(9, c.newMethod(5, 5));
    }
}