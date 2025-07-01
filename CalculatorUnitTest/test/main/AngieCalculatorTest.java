package main;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AngieCalculatorTest {
    @Test
    void testNewMethod() {
        Calculator c = new Calculator();
        assertEquals(99, c.newMethod(50, 50));
    }

    @Test
    void testNewMethod2() {
        Calculator c = new Calculator();
        var a = 50;
        var b = 50;
        //var result = a + b - 1;
        var result = 99;
        assertEquals(result, c.newMethod(a , b));
    }


    @Test
    void testNewMethod3() {
        Calculator c = new Calculator();

        // Each row is {a, b, expectedResult}
        int[][] testCases = {
                {50, 50, 99}, // one line one test case. First element = a, second = b third = result.
                {10, 5, 14},
                {0, 0, -1},
                {1, 2, 2},
                {100, 200, 299}
        };

        for (int[] testCase : testCases) {
            System.out.println("LINE: " + Arrays.toString(testCase));

            int a = testCase[0];
            int b = testCase[1];
            int expected = testCase[2];
            double actual = c.newMethod(a, b);
            assertEquals(expected, actual, "Failed for input: a=" + a + ", b=" + b);
        }
    }

    @Test
    void testMyAddFromArray() {
        Calculator c = new Calculator();
        int[][] testCases = {
                {1, 1, 2},
                {5, 0, 5},
                {3, 2, 5},
                {100, 200, 300}
        };
        System.out.println("Hello from angie");

        for (int[] testCase : testCases) {
            System.out.println("LINE: " + Arrays.toString(testCase));
            System.out.println("Expected:" + testCase[2] + " first:" + testCase[0] + " Second:" + testCase[1] + " Actual:" + c.add(testCase[0], testCase[1]));


            assertEquals(testCase[2], c.add(testCase[0], testCase[1]), "Failed for input: a=" + testCase[0] + ", b=" + testCase[1]);
        }

    }


    @Test
    void testMyAddFromArrayNegative() {
        Calculator c = new Calculator();
        int[][] testCases = {
                {1, 1, 2},
                {5, 0, 5},
        };
        System.out.println("Hello from angie");

        for (int[] testCase : testCases) {
            System.out.println("LINE: " + Arrays.toString(testCase));
            System.out.println("Expected:" + testCase[2] + " first:" + testCase[0] + " Second:" + testCase[1] + " Actual:" + c.add(testCase[0], testCase[1]));


            // we play with another method from test cases - should not be equal
            assertNotEquals(testCase[2], c.add(testCase[0], testCase[1]), "Failed for input: a=" + testCase[0] + ", b=" + testCase[1]);
        }

    }
}
