public class ReplaceAllExample {
    public void run() {
        String text = "Hello to %NAME% and say hi to %NAME2%";

        text = text.replaceAll("%NAME%", "Roma");
        text = text.replaceAll("%NAME2%", "Angie");

        System.out.println(text);
    }
}
