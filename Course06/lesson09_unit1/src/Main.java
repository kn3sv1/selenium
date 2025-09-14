import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello from Main class");
        Server server = new Server(8080);
        server.start();


        // this line of code bellow means to pause the execution of code for 30 seconds then continue with the next line.
        // Thread.sleep(30* 1000); // 30 seconds
        // server.stop();
    }
}
