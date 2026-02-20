public class Main {
    public static void main(String[] args) {
        //String is immutable, so when we assign a new value to b,
        // it creates a new string object and assigns it to b,
        // while a still references the original string "123".
        String a = new String("123");
        String b = a;
        b = "888"; // This creates a new string object "888" and assigns it to b, while a still references the original string "123".
        System.out.println("a=" + a + " b=" + b);


        // In this case, Test is a MUTABLE class because it has a public field prop that can be modified.
        // When we assign t1 to t2, both t1 and t2 reference the same Test object in memory.
        // Therefore, when we change the value of prop through t2, it also changes the t1.prop because t1 and t2 are referencing the same object.
        Test t1 = new Test("123");
        // Varaible - just label to memory address, not the object itself.
        Test t2 = t1;
        t2.prop = "888";
        System.out.println("t1=" + t1 + " t2=" + t2);



        TestImmutable t3 = new TestImmutable("123");
        TestImmutable t4 = t3;
        System.out.println("HERE the same address. t3=" + t3 + " t4=" + t4);
        // When we call t4.setProp("888"), it does not modify the existing object that t4 references.
        // Instead, it creates a new TestImmutable object with the updated property value "888" and returns it.
        // Therefore, t3 still references the original TestImmutable object with prop "123",
        // while t4 now references the new TestImmutable object with prop "888".
        t4 = t4.setProp("888");
        System.out.println("HERE Will be another address. t3=" + t3 + " t4=" + t4);

    }
}

class Test {
    public String prop;

    public Test(String prop) {
        this.prop = prop;
    }

    @Override
    public String toString() {
        return "prop: " + this.prop + ", Memory Address:" + Integer.toHexString(System.identityHashCode(this));
    }
}

final class TestImmutable {
    public final String prop;

    public TestImmutable(String prop) {
        this.prop = prop;
    }

    // Setter-like method that returns a new object.
    // This method does not modify the existing object but instead creates and returns a new instance of TestImmutable with the updated property value.
    public TestImmutable setProp(String newProp) {
        return new TestImmutable(newProp);
    }


    @Override
    public String toString() {
        return "prop: " + this.prop + ", Memory Address:" + Integer.toHexString(System.identityHashCode(this));
    }
}