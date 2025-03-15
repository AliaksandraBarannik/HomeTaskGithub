package actions;

import org.openqa.selenium.WebDriver;
import pages.CommonPage;

public class CommonActions extends BaseActions {
    private CommonPage commonPage;

    public CommonActions(WebDriver driver) {
        super(driver);
        commonPage = new CommonPage(driver);
    }

    public void clickSignInButton() {
        commonPage.signInButton().isEnabled();
        commonPage.signInButton().click();
    }
}
