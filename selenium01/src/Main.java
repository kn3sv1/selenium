
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:\\SELENIUM\\chromedriver-win64\\chromedriver.exe");

        // System.out.printf("Hello and welcome!");

        // Initialize the WebDriver instance
        WebDriver driver = new ChromeDriver();

        // Navigate to a website
        driver.get("https://www.example.com");

        driver.manage().window().maximize();

        // 1 minute
        Thread.sleep(60 * 1000);

        // driver.close();
        driver.quit();

    }
}