import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;

public class Main {


    public static void main(String[] args) {
        //calculatorExample();
        //hello();

        //ageCategoryWithClassExample();
        //ageCategoryWithLambdaExample();

        streamExample();
    }

    public static void streamExample() {
        StreamExample streamExample = new StreamExample();
        streamExample.example();
    }

    // we return function from method not data structure like we did before. So we have now dynamic programming
    public static Function<Integer, String> getCategoryByAgeLambdaFunction() {
        Predicate<Integer> invalidAge = age -> age < 0;
        Predicate<Integer> child = age -> age <= 12;
        Predicate<Integer> teenager = age -> age <= 19;
        Predicate<Integer> adult = age -> age <= 64;

        // lambda function return string and accepts integer
        Function<Integer, String> getCategoryByAge = age -> {
            String category = "";
            if (invalidAge.test(age)) {
                category = "Invalid age";
            } else if (child.test(age)) {
                category = "Child";
            } else if (teenager.test(age)) {
                category = "Teenager";
            } else if (adult.test(age)) {
                category = "Adult";
            } else {
                category = "Senior";
            }

            return category;
        };

        return getCategoryByAge;
    }

    public static void ageCategoryWithLambdaExample() {
        // get the lambda function
        Function<Integer, String> getCategoryByAge = getCategoryByAgeLambdaFunction();
        int[] testAges = {-5, 0, 5, 12, 13, 19, 20, 35, 64, 65, 80};
        for (int age : testAges) {
            /*
            String category = "";
            if (invalidAge.test(age)) {
                category = "Invalid age";
            } else if (child.test(age)) {
                category = "Child";
            } else if (teenager.test(age)) {
                category = "Teenager";
            } else if (adult.test(age)) {
                category = "Adult";
            } else {
                category = "Senior";
            }
            */
            //String category = getCategoryByAge.apply(age);
            //String category = getCategoryByAgeLambdaFunction().apply(age);

            // use the lambda function
            String category = getCategoryByAge.apply(age);
            System.out.println("Age: " + age + " - Category: " + category);
        }
    }

    public static void ageCategoryWithClassExample() {
        AgeService ageService = new AgeService();

        int[] testAges = {-5, 0, 5, 12, 13, 19, 20, 35, 64, 65, 80};

        for (int age : testAges) {
            String category = ageService.categorizeAge(age);
            System.out.println("Age: " + age + " - Category: " + category);
        }
    }

    public static void hello() {
        Hello h = new Hello();
        String name = "Angie";

        Consumer<String> greeter = h::goodMorning;
        greeter.accept(name);

        greeter = h::goodEvening;
        greeter.accept(name);

        greeter = h::goodNight;
        greeter.accept(name);

        greeter = h::goodAllTimeChatGPT;
        greeter.accept(name);
    }

    public static void calculatorExample() {
        Calculator calculator = new Calculator();

        int a = 10;
        int b = 5;

        System.out.println("Addition: " + calculator.add(a, b));
        System.out.println("Subtraction: " + calculator.subtract(a, b));
        System.out.println("Multiplication: " + calculator.multiply(a, b));
        System.out.println("Division: " + calculator.divide(a, b));


        // Problem with dynamic programming approach
        // Here we simulate a dynamic operation selection
        String operation = "add";
        if (operation == "add") {
            System.out.println("Dynamic Addition: " + calculator.add(a, b));
        } else if (operation == "subtract") {
            System.out.println("Dynamic Subtraction: " + calculator.subtract(a, b));
        } else if (operation == "multiply") {
            System.out.println("Dynamic Multiplication: " + calculator.multiply(a, b));
        } else if (operation == "divide") {
            System.out.println("Dynamic Division: " + calculator.divide(a, b));
        } else {
            System.out.println("Unknown operation");
        }

//        Consumer<Integer> noProblem = System.out::println;
//        noProblem.accept(2000);
        // Wrong value for type: IntBinaryOperator
        // IntBinaryOperator  error = System.out::println;

//        // the same like:
//        Integer i = "Hello world";

        // another aproach using lambda expressions
        IntBinaryOperator  operatorLambda = calculator::add; // you can change this to any operation like add, subtract, multiply
        System.out.println("Calculation lambda add : " + operatorLambda.applyAsInt(a, b));

        // change operation dynamically
        operatorLambda = calculator::subtract;
        System.out.println("Calculation lambda subtract : " + operatorLambda.applyAsInt(a, b));

        operatorLambda = calculator::divide;
        System.out.println("Calculation lambda divide : " + operatorLambda.applyAsInt(a, b));


        operatorLambda = calculator::multiply;
        System.out.println("Calculation lambda multiply : " + operatorLambda.applyAsInt(a, b));

        // using method to print result
        printCalculatorResult(calculator::add, 100, 20);
        printCalculatorResult(calculator::subtract, 150, 10);
        printCalculatorResult(calculator::multiply, 5, 5);
        printCalculatorResult(calculator::divide, 200, 4);
    }

    public static void printCalculatorResult(IntBinaryOperator operator, int a, int b) {
        System.out.println("Calculation result: " + operator.applyAsInt(a, b));
    }
}
