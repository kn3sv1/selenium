public class Main {
    public static void main(String[] args) {
        String text = "Roma $1";
        String s2 = "Key1 hello";

        // group is - (...)
        String result = s2.replaceAll("(Key1)", text); // "Roma Key1 hello"
        //String result = s2.replace("Key1", text); // "Roma $0 hello"


        System.out.println(result);
    }
}
