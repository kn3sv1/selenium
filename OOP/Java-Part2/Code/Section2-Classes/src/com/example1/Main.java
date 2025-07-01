package com.example1;

public class Main {
    public static void main(String[] args) {
        var employee = new Employee(1200, 6);

        int wage = employee.calculateWage();
        System.out.println(wage);
    }
}
