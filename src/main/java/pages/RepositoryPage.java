package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RepositoryPage extends BasePage {
    private final String REPOSITORY_NAME = "//strong[@itemprop='name']";
    private final String COMMIT_LINK = "//a[contains(@aria-label, 'commit')]";

    public RepositoryPage(WebDriver driver) {
        super(driver);
    }

    public WebElement repositoryName() {
        waiter.waitForVisibility(By.xpath(REPOSITORY_NAME));
        return driver.findElement(By.xpath(REPOSITORY_NAME));
    }

    public WebElement commitLink() {
        waiter.waitForVisibility(By.xpath(COMMIT_LINK));
        return driver.findElement(By.xpath(COMMIT_LINK));
    }
}
