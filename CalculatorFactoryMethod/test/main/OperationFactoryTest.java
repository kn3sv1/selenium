package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OperationFactoryTest {
    @Test
    void testAddOperation() {
        OperationFactory factory = new OperationFactory();

        Operation operation = factory.getOperationByName("Add");
        boolean b = operation instanceof Add;

        assertTrue(b);
        // assertFalse(operation instanceof Subtract);
    }

    @Test
    void testAddOperationNegativeCase() {
        OperationFactory factory = new OperationFactory();

        Operation operation = factory.getOperationByName("Add");
        assertFalse(operation instanceof Subtract);
    }

    @Test
    void testInstanceOf() {
        OperationFactory factory = new OperationFactory();

        Operation operation = factory.getOperationByName("Add");
        assertInstanceOf(Add.class, operation);
    }

    @Test
    void testPositiveArrayAllOperationInstanceOf() {
        OperationFactory factory = new OperationFactory();

        // Uppercase
        assertInstanceOf(Add.class, factory.getOperationByName("Add"));
        assertInstanceOf(Subtract.class, factory.getOperationByName("Subtract"));
        assertInstanceOf(Multiply.class, factory.getOperationByName("Multiply"));
        assertInstanceOf(Divide.class, factory.getOperationByName("Divide"));

        // Lowercase
        assertInstanceOf(Add.class, factory.getOperationByName("add"));
        assertInstanceOf(Subtract.class, factory.getOperationByName("subtract"));
        assertInstanceOf(Multiply.class, factory.getOperationByName("multiply"));
        assertInstanceOf(Divide.class, factory.getOperationByName("divide"));
    }


    @Test
    void testNegativeArrayAllOperationInstanceOf() {
        OperationFactory factory = new OperationFactory();

        assertFalse(factory.getOperationByName("Add") instanceof Subtract);
        assertFalse(factory.getOperationByName("Subtract") instanceof Add);
        assertFalse(factory.getOperationByName("Multiply") instanceof Divide);
        assertFalse(factory.getOperationByName("Divide") instanceof Multiply);

    }


    @Test
    void testException() {
        OperationFactory factory = new OperationFactory();

        // When we call "factory.getOperationByName("Add111");" we check if exception will be OperationException.class
        Exception exception = assertThrows(OperationException.class, () -> {
            factory.getOperationByName("Add111");
        });

        // optional assertion to check if exception message contains text bellow:
        assertTrue(exception.getMessage().contains("Class for Operation is not found!"));

    }
}
