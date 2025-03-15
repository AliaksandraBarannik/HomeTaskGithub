package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage extends BasePage {
    private final String NEW_REPOSITORY_BUTTON = "//div[@aria-label='Repositories']//a[@href='/new']";
    private final String REPOSITORY_SEARCH = "dashboard-repos-filter-left";
    private final String REPOSITORY_LINK = "//div[@class='wb-break-word']//a[contains(@href, '%s')]";

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public WebElement newRepositoryButton() {
        return driver.findElement(By.xpath(NEW_REPOSITORY_BUTTON));
    }

    public WebElement repositorySearch() {
        waiter.waitForVisibility(By.id(REPOSITORY_SEARCH));
        return driver.findElement(By.id(REPOSITORY_SEARCH));
    }

    public WebElement repositoryLink(String repositoryName) {
        return driver.findElement(By.xpath(String.format(REPOSITORY_LINK, repositoryName)));
    }
}
