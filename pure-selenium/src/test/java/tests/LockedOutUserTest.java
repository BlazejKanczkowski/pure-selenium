package tests;

import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

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