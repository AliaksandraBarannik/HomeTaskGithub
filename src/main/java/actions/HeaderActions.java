package actions;


import org.openqa.selenium.WebDriver;
import pages.HeaderPage;

public class HeaderActions extends BaseActions {
    private HeaderPage header;

    public HeaderActions(WebDriver driver) {
        super(driver);
        this.header = new HeaderPage(driver);
    }

    public boolean isUserLoggedIn() {
        return header.userIcon().isDisplayed();
    }
}
