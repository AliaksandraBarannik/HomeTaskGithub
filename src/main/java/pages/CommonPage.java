package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonPage extends BasePage {
    private final String SIGN_IN_BUTTON = "//div[contains(@class,'HeaderMenu')]//a[@href='/login']";

    public CommonPage(WebDriver driver) {
        super(driver);
    }

    public WebElement signInButton() {
        return driver.findElement(By.xpath(SIGN_IN_BUTTON));
    }

}
