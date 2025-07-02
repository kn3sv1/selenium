package main;

public class OperationException extends RuntimeException {
    public OperationException() {
        this("Operation class not found!");
    }

    public OperationException(String message) {
        super(message);
    }
}