package actions;

import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.JavaScriptHelper;

public class BaseActions {
    private BasePage basePage;
    protected JavaScriptHelper js;

    public BaseActions(WebDriver driver) {
        this.basePage = new BasePage(driver);
        this.js = new JavaScriptHelper(driver);
    }

}
