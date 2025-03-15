package pages;

import org.openqa.selenium.WebDriver;
import utils.Waiter;

public class BasePage {
    protected WebDriver driver;
    protected Waiter waiter;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
    }

}
