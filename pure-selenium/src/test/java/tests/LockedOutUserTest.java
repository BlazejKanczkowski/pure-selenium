package tests;

import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class LockedOutUserTest extends AbstractTest{

    @Test
    public void testLoginWithLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
        Assert.assertTrue(loginPage.getErrorMessageText().toLowerCase().contains("locked out"),
                "Error message should mention user is locked out");
    }
}