package main;

public class Calculator {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is undefined.");
        }
        return a / b;
    }

    public double angie(double a, double b) {
        return a + b;
    }

    public double newMethod(double a, double b) {
        return a + b -1;
    }
}
