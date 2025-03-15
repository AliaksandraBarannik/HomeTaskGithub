package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RepositoryNavigationPanel extends BasePage {
    private final String TAB = "//span[@data-content='%s']";
    private final String TAB_COUNT = "/following-sibling::span[contains(@id,'count')]";

    public RepositoryNavigationPanel(WebDriver driver) {
        super(driver);
    }

    public WebElement tab(String tabName) {
        return driver.findElement(By.xpath(String.format(TAB, tabName)));
    }

    public WebElement tabCount(String tabName) {
        return driver.findElement(By.xpath(String.format(TAB + TAB_COUNT, tabName)));
    }
}
