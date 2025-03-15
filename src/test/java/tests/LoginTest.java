package tests;

import actions.HeaderActions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {
    private HeaderActions headerActions;

    @BeforeMethod(alwaysRun = true)
    public void setupMethod() {
        headerActions = new HeaderActions(getDriver());
    }

    @Test(groups = {"sanity"})
    public void loginTest() {
        boolean isUserLoggedIn = headerActions.isUserLoggedIn();
        Assert.assertTrue(isUserLoggedIn, "User is not logged in");
    }
}
