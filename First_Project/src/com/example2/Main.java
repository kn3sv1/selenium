package com.example2;

public class Main {
    public static void main(String[] args) {
        Object obj = "456";
        int value = 0;

        if (obj instanceof String) {
            try {
                value = Integer.parseInt((String) obj);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
            }
        } else if (obj instanceof Integer) {
            value = (Integer) obj;
        }

        System.out.println("Value: " + value);

    }
}
