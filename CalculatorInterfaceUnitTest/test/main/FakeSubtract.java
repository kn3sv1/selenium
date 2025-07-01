package main;

public class FakeSubtract implements Operation {
    @Override
    public double operation(double a, double b) {
        return a - b;
    }
}
