package tests;

import actions.CommonActions;
import actions.HeaderActions;
import actions.LoginActions;
import config.EnvConfig;
import config.TestConfig;
import models.User;
import models.UserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.CipherManager;
import utils.DataUtils;
import utils.DriverManager;


public class BaseTest {
    protected WebDriver driver;
    protected HeaderActions headerActions;
    protected DataUtils dataUtils;
    protected EnvConfig envConfig;
    protected TestConfig testConfig;
    protected CipherManager cipherManager;
    private LoginActions loginActions;
    private CommonActions commonActions;
    private User user;

    @BeforeClass
    public void setup() {
        envConfig = new EnvConfig();
        testConfig = new TestConfig();
        DriverManager.initDriver();
        driver = DriverManager.getDriver();
        driver.get(envConfig.getBaseUrl());
        headerActions = new HeaderActions(driver);
        dataUtils = new DataUtils();
        cipherManager = new CipherManager();
        loginActions = new LoginActions(driver);
        commonActions = new CommonActions(driver);
        user = new UserFactory().getUser();
        commonActions.clickSignInButton();
        loginActions.login(user);
    }

    @AfterClass
    public void teardown() {
        DriverManager.quitDriver();
    }
}
