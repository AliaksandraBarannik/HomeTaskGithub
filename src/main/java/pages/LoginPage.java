package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private final String USERNAME_FIELD = "login_field";
    private final String PASSWORD_FIELD = "password";
    private final String LOGIN_BUTTON = "commit";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public WebElement username() {
        return driver.findElement(By.id(USERNAME_FIELD));
    }

    public WebElement password() {
        return driver.findElement(By.id(PASSWORD_FIELD));
    }

    public WebElement loginButton() {
        return driver.findElement(By.name(LOGIN_BUTTON));
    }
}
