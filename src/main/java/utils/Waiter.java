package utils;

import config.EnvConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Waiter {
    private static final Logger logger = LoggerFactory.getLogger(Waiter.class);
    private FluentWait<WebDriver> wait;
    private EnvConfig envConfig;

    public Waiter(WebDriver driver) {
        this.envConfig = new EnvConfig();
        this.wait = new FluentWait<>(driver);
    }

    public void waitForVisibility(By locator) {
        logger.info("Waiting for visibility of element located by: {}", locator);
        wait.withTimeout(Duration.ofSeconds(Long.parseLong(envConfig.getWaitTime())))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
