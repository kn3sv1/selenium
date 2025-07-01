package com.example1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome!");

        String name = "   Angie";
        System.out.println(name);
        System.out.println(name.length());
        System.out.println(name.trim());
        System.out.println(name.trim().length());
        System.out.println(name.indexOf("i"));
        System.out.println(name.trim().indexOf("i"));

        String[] letters = { "a","b","c","d","e" };
        System.out.println(letters);
        System.out.println(Arrays.toString(letters));
        System.out.println(letters.length);

        int[] numbers = { 2, 5, 3, 1, 4 };
        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers));

        int[][] numbers1 = new int[2][3];
        numbers1[0][0] = 1;
        System.out.println(Arrays.deepToString(numbers1));

        int[][][] numbers2 = new int[2][3][2];
        numbers2[0][0][0] = 1;
        numbers2[1][1][1] = 2;
        System.out.println(Arrays.deepToString(numbers2));
    }
}
