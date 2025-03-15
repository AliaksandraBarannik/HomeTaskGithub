package utils;

import enums.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    public static WebDriver createDriver(String browser) {
        DriverType driverType = DriverType.valueOf(browser.toUpperCase());

        switch (driverType) {
            case CHROME:
                return new ChromeDriver();
            case FIREFOX:
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("Invalid browser type: " + browser);
        }
    }
}
