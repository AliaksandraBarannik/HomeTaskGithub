package actions;


import org.openqa.selenium.WebDriver;
import pages.Header;

public class HeaderActions extends BaseActions{
    private Header header;

    public HeaderActions(WebDriver driver) {
        super(driver);
        this.header = new Header(driver);
    }

    public boolean isUserLoggedIn() {
        return header.userIcon().isDisplayed();
    }
}
