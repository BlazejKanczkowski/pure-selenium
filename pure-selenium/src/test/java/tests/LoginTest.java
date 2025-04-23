package tests;

import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends AbstractTest {

    @Test
    public void testLoginWithStandardUser() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Inventory page should be visible after login");
    }
}