package com.example1;

public class Employee {
    private int baseSalary;
    private int hourlyRate;

    public Employee(int baseSalary, int hourlyRate) {
        setBaseSalary(baseSalary);
        setHourlyRate(hourlyRate);
    }

    public int calculateWage(int extraHours) {
        return baseSalary + (getHourlyRate() * extraHours);
    }
    //method overloading
    public int calculateWage() {
        return calculateWage(0);
    }

    private void setBaseSalary(int baseSalary) {
        if (baseSalary <= 0)
            throw new IllegalArgumentException("Salary cannot be 0 or less.");
        this.baseSalary = baseSalary;
    }


    private void setHourlyRate(int hourlyRate) {
        if (hourlyRate <= 0)
            throw new IllegalArgumentException("Hourly rate cannot be 0 or negative.");
        this.hourlyRate = hourlyRate;
    }


    private int getBaseSalary() {
        return baseSalary;
    }

    private int getHourlyRate() {
        return hourlyRate;
    }
}
