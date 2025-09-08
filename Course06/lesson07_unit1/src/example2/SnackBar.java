package example2;

import java.util.Arrays;

public class SnackBar {

    public void processOrder(Person person) {
        if (person.getAmountOfOrders() > 0) {
            System.out.println("Hi " + person.getName() + "! We have received your order and you will receive your food shortly.");
            System.out.println("Your order:");
            System.out.println(Arrays.toString(person.getOrders()));
        } else {
            System.out.println("Im sorry... you have exceeded the limit.");
        }

    }
}

