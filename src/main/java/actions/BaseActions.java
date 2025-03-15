package actions;

import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.JavaScriptHelper;
import utils.Waiter;

public class BaseActions {
    private BasePage basePage;
    protected Waiter waiter;
    protected JavaScriptHelper js;

    public BaseActions(WebDriver driver) {
        this.basePage = new BasePage(driver);
        this.waiter = new Waiter(driver);
        this.js = new JavaScriptHelper(driver);
    }

}
