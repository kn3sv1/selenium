package main;

public class AddMinusOne implements Operation {
    @Override
    public double operation(double a, double b) {
        return a + b - 1;
    }
}
