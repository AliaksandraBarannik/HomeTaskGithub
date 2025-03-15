package tests;

import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {

    @Test(groups = {"sanity"})
    public void loginTest() {
        boolean isUserLoggedIn = headerActions.isUserLoggedIn();
        Assert.assertTrue(isUserLoggedIn, "User is not logged in");
    }
}
