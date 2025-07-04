package com.mortgagecalc;

import java.util.Scanner;

public class Console {
  private static Scanner scanner = new Scanner(System.in);

  //Second implementation without validation - method overloading
  public static double readNumber(String prompt) {
      System.out.print(prompt);
      return scanner.nextDouble();
  }

    public static double readNumber(String prompt, double min, double max) {
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }
}
