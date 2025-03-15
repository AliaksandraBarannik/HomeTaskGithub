package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IssuePage extends BasePage {
    private final String NEW_ISSUE_BUTTON = "//a[contains(@href,'issues/new')]";

    public IssuePage(WebDriver driver) {
        super(driver);
    }

    public WebElement newIssueButton() {
        waiter.waitForVisibility(By.xpath(NEW_ISSUE_BUTTON));
        return driver.findElement(By.xpath(NEW_ISSUE_BUTTON));
    }
}
