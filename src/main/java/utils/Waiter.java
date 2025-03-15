package utils;

import config.EnvConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {
    private WebDriverWait wait;
    private EnvConfig envConfig;

    public Waiter(WebDriver driver) {
        this.envConfig = new EnvConfig();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(envConfig.getWaitTime())));
    }

    public WebElement waitForVisibility(By element) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

}
