package main;

public class OperationException extends RuntimeException {
    // without arguments constructor calls itself bellow with an argument and a default message:
    //"Operation class not found!"
    public OperationException() {
        this("Operation class not found!");
    }

    public OperationException(String message) {
        super(message);
    }
}