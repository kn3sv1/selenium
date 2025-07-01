
public class Main {
    public int getNumber1(String name) {
        // name we take from class name
        if (name.equals("Angie")) {
            return 41;
        }
        if (name.equals("Roma")) {
            return 40;
        }
        if (name.equals("Natasha")) {
            return 20;
        }
        if (name.equals("Georgia")) {
            return 30;
        }
        // if no matches we need to throw exception because method requires int
        // and we don't have any int for unknown people
        throw new RuntimeException("Name is not implemented");
    }

    public int getNumber2(String name) {
        if (name.equals("Angie")) {
            return 40;
        }
        if (name.equals("Roma")) {
            return 39;
        }
        if (name.equals("Natasha")) {
            return 20;
        }
        if (name.equals("Georgia")) {
            return 30;
        }

        throw new RuntimeException("Name is not implemented");
    }

    public int add(String name) {
        if (name.equals("Angie")) {
            return this.getNumber1(name) + this.getNumber2(name) + 1000;
        }
        if (name.equals("Roma")) {
            return this.getNumber1(name) + this.getNumber2(name);
        }
        if (name.equals("Natasha")) {
            return this.getNumber1(name) + this.getNumber2(name);
        }
        if (name.equals("Georgia")) {
            return this.getNumber1(name) + this.getNumber2(name);
        }

        throw new RuntimeException("Name is not implemented");
    }

    public int sub(String name) {
        if (name.equals("Angie")) {
            return this.getNumber1(name) - this.getNumber2(name);
        }
        if (name.equals("Roma")) {
            return this.getNumber1(name) - this.getNumber2(name);
        }
        if (name.equals("Natasha")) {
            return 0;
        }
        if (name.equals("Georgia")) {
            return 0;
        }

        throw new RuntimeException("Name is not implemented");
    }

    public static void main(String[] args)
    {
        Main m = new Main();

        System.out.println("Angie. add two numbers: " + m.add("Angie"));
        System.out.println("Angie. subtract two numbers: " + m.sub("Angie"));

        System.out.println("Roma. add two numbers: " + m.add("Roma"));
        System.out.println("Roma. subtract two numbers: " + m.sub("Roma"));


        System.out.println("Natasha. add two numbers: " + m.add("Natasha"));
        System.out.println("Natasha. subtract two numbers: " + m.sub("Natasha"));
        System.out.println("Georgia. add two numbers: " + m.add("Georgia"));
        System.out.println("Georgia. subtract two numbers: " + m.sub("Georgia"));


    }
}
