package tests;

import actions.CommonActions;
import actions.LoginActions;
import models.User;
import models.UserFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {

    @Test(groups = {"sanity"})
    public void loginTest() {
        boolean isUserLoggedIn = headerActions.isUserLoggedIn();
        Assert.assertTrue(isUserLoggedIn, "User is not logged in");
    }
}
