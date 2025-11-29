import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
        URL api = new URL("http://angie:pass123@docs.oracle.com/en/java/javase/23/docs/api/java.base/java/net/URL.html?name=Angie&age=41#constructor-summary");

        System.out.println(api.getHost());
        System.out.println(api.getRef());

        System.out.println(api.getQuery());

        System.out.println(api.getPath());
        System.out.println(api.getUserInfo());
    }
}
