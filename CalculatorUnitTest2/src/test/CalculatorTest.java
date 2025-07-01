import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}