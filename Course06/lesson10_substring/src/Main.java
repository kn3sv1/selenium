public class Main {
    public static void main(String[] args) {
        String s = "/api/ginger.json";


        String result = s.replace(".json","");
        result = result.replaceFirst("/api/","");
        System.out.println(result);
    }
}
