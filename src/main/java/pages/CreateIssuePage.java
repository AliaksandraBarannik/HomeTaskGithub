package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateIssuePage extends BasePage {
    private final String TITLE_INPUT = "//input[@placeholder='Title']";
    private final String CREATE_BUTTON = "//button[@data-testid='create-issue-button']";
    private final String DESCRIPTION = "//textarea[contains(@placeholder,'description')]";

    public CreateIssuePage(WebDriver driver) {
        super(driver);
    }

    public WebElement titleInput() {
        waiter.waitForVisibility(By.xpath(TITLE_INPUT));
        return driver.findElement(By.xpath(TITLE_INPUT));
    }

    public WebElement createButton() {
        return driver.findElement(By.xpath(CREATE_BUTTON));
    }

    public WebElement description() {
        return driver.findElement(By.xpath(DESCRIPTION));
    }
}
