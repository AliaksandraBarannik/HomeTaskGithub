package actions;

import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class BaseActions {
    private BasePage basePage;
    private WebDriver driver;

    public BaseActions(WebDriver driver) {
        this.driver = driver;
        this.basePage = new BasePage(driver);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }
}
