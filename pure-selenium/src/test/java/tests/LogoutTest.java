package tests;

import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LogoutTest extends AbstractTest{

    @Test
    public void testLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Inventory page should be visible after login");

        inventoryPage.logout();


        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "User should be redirected to login page after logout");
    }
}