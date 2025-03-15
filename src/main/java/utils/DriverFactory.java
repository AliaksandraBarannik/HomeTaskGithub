package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setupWebDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeBrowser() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}