package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Header extends BasePage{
    private final String USER_ICON = "AppHeader-user";

    public Header(WebDriver driver) {
        super(driver);
    }

    public WebElement userIcon() {
        return driver.findElement(By.className(USER_ICON));
    }
}
