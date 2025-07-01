package main;

public class CalculatorApp {
    public static void main(String[] args) {

        Calculator c = new Calculator();

        c.setOperation(new Add());
        System.out.println("Add: " + c.apply(1, 3));

        c.setOperation(new Subtract());
        System.out.println("Subtract: "  + c.apply(10, 8));

        c.setOperation(new Multiply());
        System.out.println("Multiply: "  + c.apply(2, 4));

        c.setOperation(new Divide());
        System.out.println("Divide: "  + c.apply(20, 5));
    }
}
