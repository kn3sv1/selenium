package main;

import java.util.Scanner;

public class CalculatorUnitTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calc = new Calculator();

        System.out.println("Simple Calculator");
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter an operator [+, -, *, /, a(ANGIE), n(newMethod)]: ");
        //charAt(0) only takes first character
        char operator = scanner.next().charAt(0);

        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();

        try {
            double result;
            switch (operator) {
                case '+':
                    result = calc.add(num1, num2);
                    break;
                case '-':
                    result = calc.subtract(num1, num2);
                    break;
                case '*':
                    result = calc.multiply(num1, num2);
                    break;
                case '/':
                    result = calc.divide(num1, num2);
                    break;
                case 'a':
                    result = calc.angie(num1, num2);
                    break;
                case 'n':
                    result = calc.newMethod(num1, num2);
                    break;
                default:
                    System.out.println("Error: Invalid operator.");
                    scanner.close();
                    return;
            }
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
