package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverUtils {


    // Global variable
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void open() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    public static void close() {
        driver.close();  // open window closed
        driver.quit();   // kill the driver process <-- time
        driver = null;
    }
}
