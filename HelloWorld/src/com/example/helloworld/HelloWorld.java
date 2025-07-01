package com.example.helloworld;

import java.util.Arrays;
import java.util.Objects;

public class HelloWorld {

    public static void main(String[] args) {
        interview1();
        //interview2();

        System.out.println("Hello World!");

        int[] numbers = { 1, 2, 3, 4, 5 };
        // We copy reference here not value
        int[] numbers2 = numbers;
        numbers[0] = 10;
        System.out.println(Arrays.toString(numbers));

        System.out.println(numbers);
        System.out.println(numbers2);
        // To make full copy with new address use method .clone();
        // https://www.geeksforgeeks.org/array-copy-in-java/
        int[] numbersCopy = numbers.clone();

        System.out.println(numbers);
        System.out.println(numbersCopy);

    }
    public static void interview2()
    {
        int[] myNum = {10, 20, 30, 40};
        int c4 = 1+20;
        System.out.println(myNum[c4]);
    }

    public static void interview1()
    {
        String text = "Roma woke up. Today Roma has mood and drink Rom. Roma started programming. Roma and Angie go to MyMall.";
        //junior knowledge - string as array
        // we need to convert string to array and after as array find sequence "Roma"


        //step 1: convert String to Array
        // Convert the string to an array of characters
        // https://www.freecodecamp.org/news/string-to-array-in-java-how-to-convert-a-string-to-an-array-in-java/
        char[] charArray = text.toCharArray();

        //step 2: go through array
        int counter = 0;
        for (int i = 0; i < charArray.length; i=i+1) {

            // step 3: create variable from current index plus 4 characters. Because word "Roma" has 4 indexes.
            int c1 = i;
            int c2 = c1+1;
            int c3 = c2+1;
            int c4 = c3+1;
            //ArrayIndexOutOfBoundsException to prevent possible exception (error) because we are out of boundaries of current array.
            // length -1 because we count index from 0.
            if (c4 > charArray.length -1) {
               break;
            }
            // charArray[c4] this will create exception if c4 value will be bigger than length-1 of array.
            // c4 will never be outside of array.
            //System.out.printf("%s-%s-%s-%s \n\n", charArray[c1], charArray[c2], charArray[c3], charArray[c4]);

            //check if sequence = "Roma" and increase counter. So we check if next 4 characters will be: 'R' 'o' 'm' 'a'.
            if (charArray[c1] == 'R' && charArray[c2] == 'o' && charArray[c3] == 'm' && charArray[c4] == 'a') {
                counter++;
                System.out.printf("%s-%s-%s-%s \n\n", charArray[c1], charArray[c2], charArray[c3], charArray[c4]);
                //when we find Roma the third time, we modify the characters in the array by index so that it will be replaced with Geor.
                if (counter == 3) {
                    charArray[c1] ='G';
                    charArray[c2] ='e';
                    charArray[c3] ='o';
                    charArray[c4] ='r';

// we can replace 4 letters with -99-  and after this unique word replace string
//                    charArray[c1] ='-';
//                    charArray[c2] ='9';
//                    charArray[c3] ='9';
//                    charArray[c4] ='-';
                }
            }


            //System.out.println(i);
        }
        //convert array of characters back to a string.
        // https://stackoverflow.com/questions/7655127/how-to-convert-a-char-array-back-to-a-string
        String newText = String.valueOf(charArray);

        // Senior knowledge. Maybe we checked before if it is only one word Geo.
        newText = newText.replace("Geor", "George");
        System.out.println(newText);

        //Find how many times a word exists in a sentence.
        String[] arr = newText.split(" ");
        System.out.println(Arrays.toString(arr));

        int counter2 = 0;
        for (int k = 0; k < arr.length; k++ ) {
            System.out.println(arr[k]);
            if (Objects.equals(arr[k], "George")) {
                counter2++;
            }
        }
        System.out.printf("amount of word George = %d", counter2);
        //same output.
        //System.out.println("amount of word George = " + counter2);

        // die all program - terminate all program.
        System.exit(0);
    }
}
