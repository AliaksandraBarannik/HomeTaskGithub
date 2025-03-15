package actions;

import models.User;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginActions extends BaseActions {

    private LoginPage loginPage;

    public LoginActions(WebDriver driver) {
        super(driver);
        this.loginPage = new LoginPage(driver);
    }

    public void login(User user) {
        loginPage.username().sendKeys(user.getUserName());
        loginPage.password().sendKeys(user.getPassword());
        loginPage.loginButton().click();
    }
}
