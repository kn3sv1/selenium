package main;

public class OperationFactory {

    public Operation getOperationByName(String name) {
        // now when we add new operation we should change this class as well
        // it is not cool. Can we automatically create class based on name?
        // this gives us ability not to touch this class at all - automatically we will register new class
//        if (name.equals("add")) {
//            return new Add();
//        } else if (name.equals("subtract")) {
//            return new Subtract();
//        } else if (name.equals("multiply")) {
//            return new Multiply();
//        } else if (name.equals("divide")) {
//            return new Divide();
//        } else {
//            throw new OperationException();
//        }
        //  CODE BELLOW MUCH BETTER THAN ABOVE - BECAUSE YOU DON'T NEED TO CREATE ANOTHER ELSE-IF FOR EACH NEW OPERATION CLASS
        try {
            // generic ? means unknown instance for now and will be detected in run time
            Class<?> clazz = Class.forName("main." + capitalizeFirst(name));
            // here we call constructor of dynamic class
            Object obj = clazz.getDeclaredConstructor().newInstance();
            // casting to interface
            return  (Operation)obj;
        } catch (Exception e) {
            // we don't want to print anything to console
            //e.printStackTrace();
            // we don't want error message from Java
            //throw new OperationException(e.getMessage());
            // we want our own message so ANGIE can understand
            throw new OperationException("Class for Oparation is not found!");
        }
    }

    /**
     * just additional function from ChatGPt that converts first letter of String to uppercase because classes from big letters
     * and from CLI command line we pass from small letter
     */
    public static String capitalizeFirst(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
