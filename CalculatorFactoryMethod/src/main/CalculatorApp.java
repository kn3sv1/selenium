package main;

import java.util.Scanner;

public class CalculatorApp {
    public static void main(String[] args) {

        try {
            //  from name of class as string create me an object of that class
            // main is name of package where classes exist
            Class<?> clazz = Class.forName("main.Add");
            // here we call constructor of dynamic class
            Object obj = clazz.getDeclaredConstructor().newInstance();
            Operation op = (Add)obj;

            // from CLASS create object
            // Operation op = new Add();


            double r = op.operation(1, 3);
            System.out.println("DEBUG r: " + r);
        } catch (Exception e) {
            System.err.println("ERROR!");
        }


        Calculator c = new Calculator();
        OperationFactory factory = new OperationFactory();

        Scanner scanner = new Scanner(System.in);  // Create Scanner object

        System.out.print("Enter operation: ");
        String nameOperation = scanner.nextLine();          // Read line of input

        System.out.print("First 1st number: ");
        double a = scanner.nextDouble();

        System.out.print("Enter 2nd number: ");
        double b = scanner.nextDouble();

        try {
            c.setOperation(factory.getOperationByName(nameOperation));
            System.out.println("Result: " + c.apply(a, b));
            System.exit(0); // Normal exit
        } catch (OperationException e) {
            System.err.println("Your operation is WRONG: " + nameOperation);
            System.err.println("Exiting with Error Code: 20");
            System.exit(20); // Give code to Operation System
        }


        // now entry point doesn't know which class will be used for operation.
        // we hided this information inside OperationFactory class
        // entry point now is clean and only knows about operation interface that returns OperationFactory class

        // looks like we still have a problem - it should know about words: subtract or add
        // it's temporary that we show it here. This word we get from terminal/CLI from user like numbers as well

//        Operation operation = factory.getOperationByName("subtract");
//        // Operation operation = factory.getOperationByName("addMinusOne");
//
//
//        c.setOperation(operation);
//        System.out.println("Result: " + c.apply(1, 3));

//        // just for fun - practice without extra variable operation. In real code we'll not use unnecessary variables
//        c.setOperation(factory.getOperationByName("add"));
//        System.out.println("Result: " + c.apply(1, 3));

//        c.setOperation(new Subtract());
//        System.out.println("Subtract: "  + c.apply(10, 8));
//
//        c.setOperation(new Multiply());
//        System.out.println("Multiply: "  + c.apply(2, 4));
//
//        c.setOperation(new Divide());
//        System.out.println("Divide: "  + c.apply(20, 5));
    }
}
