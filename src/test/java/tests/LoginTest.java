package tests;

import actions.HeaderActions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
    private HeaderActions headerActions;

    @BeforeMethod(alwaysRun = true)
    public void setupMethod() {
        headerActions = new HeaderActions(getDriver());
    }

    @Test(groups = {"sanity"})
    public void loginTest() {
        log.info("Verify if the user is logged in");
        boolean isUserLoggedIn = headerActions.isUserLoggedIn();
        Assert.assertTrue(isUserLoggedIn, "User is not logged in");
    }
}
