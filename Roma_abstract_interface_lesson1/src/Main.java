// Java Program to implement To display the
// method print the addition and subtraction
// by using abstraction


import java.util.Arrays;

interface KaterinaInterface {
    public void Katerina();
}

// Abstract Class
abstract class arithmetic_operation implements KaterinaInterface {
    abstract void printInfo();
}

// Class add
class add extends arithmetic_operation{
    // class add must override printInfo() method
    // otherwise, compile-time
    // exception will be thrown
    public void printInfo()
    {
        int a = 3;
        int b = 4;
        System.out.println(a + b);
    }
    public void Katerina()
    {
        System.out.println("Hello ADD Katerina");
    }

    public void roma()
    {
        System.out.println("Hello ROMA");
    }

    @Override
    public String toString() {
        return "add{Hello ROMA}";
    }
}

// Class sub
class sub extends arithmetic_operation {
    // class sub must override printInfo() method
    // otherwise, compile-time
    // exception will be thrown
    public void printInfo()
    {
        int c = 4;
        int d = 5;
        System.out.println(c - d);
    }
    public void Katerina()
    {
        System.out.println("Hello SUB Katerina");
    }

    public void angie()
    {
        System.out.println("Hello Angie");
    }

    @Override
    public String toString() {
        return "sub{Hello Angie}";
    }
}

// Driver Class
class Main {
    // Main Function
    public static void main(String args[])
    {
        arithmetic_operation n = new add();
        n.printInfo();

        //LEFT SIDE - ABSTACT(arithmetic_operation) - who does not have ANGIE
        arithmetic_operation y = new sub();
        y.printInfo(); // HERE WE DONT HAVE - ANGIE

        // one line solution: Downcasting
        //((sub)y).angie();

        // more longe version for BEGINNERS: Downcasting
        if (y instanceof sub) {
            sub a = (sub)y;
            a.angie();
        }

        arithmetic_operation y2 = new add();
        y2.Katerina();

        KaterinaInterface y3 = new add();
        y3.Katerina();


        KaterinaInterface y4 = new sub();
        y4.Katerina();

        KaterinaInterface[] arr = {new add(), new sub()};
        System.out.println(Arrays.toString(arr));

        KaterinaInterface[] arr2 = new KaterinaInterface[]{new add(), new sub(), new sub()};
        System.out.println(Arrays.toString(arr2));

        var arr3 = new KaterinaInterface[]{new add(), new sub(), new sub()};
        System.out.println(Arrays.toString(arr3));


        var arr4 = new arithmetic_operation[]{new add(), new sub(), new sub()};
        System.out.println(Arrays.toString(arr4));


        arithmetic_operation[] arr5 = {new sub(), new sub()};
        System.out.println(Arrays.toString(arr5));

//        var arr6 = {new sub(), new sub()};
//        System.out.println(Arrays.toString(arr6));
    }
}