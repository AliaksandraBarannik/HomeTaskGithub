package tests;

import actions.CommonActions;
import actions.LoginActions;
import config.EnvConfig;
import config.TestConfig;
import models.User;
import models.UserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.CipherManager;
import utils.DataUtils;
import utils.DriverFactory;


public class BaseTest {
    protected DataUtils dataUtils;
    protected EnvConfig envConfig;
    protected TestConfig testConfig;
    protected CipherManager cipherManager;
    private LoginActions loginActions;
    private CommonActions commonActions;
    private User user;

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        envConfig = new EnvConfig();
        testConfig = new TestConfig();
        dataUtils = new DataUtils();
        cipherManager = new CipherManager();
        DriverFactory.setupWebDriver();
        getDriver().get(envConfig.getBaseUrl());
        loginActions = new LoginActions(getDriver());
        commonActions = new CommonActions(getDriver());
        user = new UserFactory().getUser();
        commonActions.clickSignInButton();
        loginActions.login(user);
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        DriverFactory.closeBrowser();
    }
}