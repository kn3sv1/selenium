package main;

public class Divide implements Operation {
    @Override
    public double operation(double a, double b) {
        if (b == 0) throw new ArithmeticException("Cannot divide by zero");
        return a / b;
    }
}