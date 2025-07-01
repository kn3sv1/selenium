package main;

public class Calculator {
    private Operation o;

    public void setOperation(Operation o) {
        this.o = o;
    }

    public double apply(double a, double b) {
        return this.o.operation(a, b);
    }
}
