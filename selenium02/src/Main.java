

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // System.setProperty("webdriver.chrome.driver", "E:\\SELENIUM\\chromedriver-win64\\chromedriver.exe");

        // System.out.printf("Hello and welcome!");

        // Initialize the WebDriver instance
        // ChromeDriver is like mongo API class. It gives access to chrome functions.
        WebDriver driver = new ChromeDriver();

        // driver = browser realy opens browser and pasts this here
        driver.get("http://localhost:3000/signup");

        String barcode ="NEW DELCHI 8A";
        WebElement element_enter = driver.findElement(By.xpath("/html/body/main/form/p[5]/input"));
        element_enter.sendKeys(barcode);

        //Delay 10 seconds.
        Thread.sleep(10 * 1000);
        WebElement element_enter2 = driver.findElement(By.xpath("/html/body/main/form/p[4]/input"));
        element_enter2.sendKeys("ANGIEE NEOPHYTOU");

        String login = "satreg@mail10.ru";
        String password = "Kn3sv555";


        driver
                .findElement(By.xpath("/html/body/main/form/p[1]/input"))
                .sendKeys(login);

        driver
                .findElement(By.xpath("/html/body/main/form/p[2]/input"))
                .sendKeys(login);

        driver
                .findElement(By.xpath("/html/body/main/form/p[3]/input"))
                .sendKeys(password);

        driver
                .findElement(By.xpath("/html/body/main/form/p[6]/input"))
                .sendKeys("31171");

        driver
                .findElement(By.xpath("/html/body/main/form/p[7]/input"))
                .sendKeys("LIMASSOL");

        driver.findElement(By.xpath("/html/body/main/form/button"))
            .sendKeys(Keys.RETURN);

        Thread.sleep(5 * 1000);

        if (driver.getTitle().trim().equalsIgnoreCase("Login")) {
            System.out.println("REGISTRATION SUCCESS");
        } else {
            System.err.println("REGISTRATION FAILED");
        }

        // LOGIN AND PRESS ENTER
        driver
                .findElement(By.xpath("/html/body/main/form/p[1]/input"))
                .sendKeys(login);

        driver
                .findElement(By.xpath("/html/body/main/form/p[2]/input"))
                .sendKeys(password);

        driver.findElement(By.xpath("/html/body/main/form/button"))
                .sendKeys(Keys.RETURN);

        Thread.sleep(5 * 1000);

        //see all my orders
        driver.get("http://localhost:3000/orders");

        if (driver.getTitle().trim().equalsIgnoreCase("All Your Orders")) {
            System.out.println("SUCCESS ALL MY ORDERS");
        } else {
            System.err.println("ORDERS PAGE PROBLEM");
        }



        // Navigate to a website
//        driver.get("https://www.example.com");
//
//        driver.manage().window().maximize();
//
//        String pageTitle = driver.getTitle();
//        System.out.println("Page Title main site example.com: " + pageTitle);
//
//        if (pageTitle.trim().equalsIgnoreCase("Example Domain")) {
//            System.out.println("Test Passed!");
//            driver.get("https://facebook.com");
//            System.out.println("Page Title from FACEBOOK: " + driver.getTitle());
//        } else {
//            System.out.println("Test Failed");
//        }
        // Assert.assertEquals(pageTitle.trim(), "Example Domain");

        // 1 minute
        Thread.sleep(10 * 1000);

        // driver.close();
        driver.quit();

    }
}