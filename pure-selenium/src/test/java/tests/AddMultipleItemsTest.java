package tests;

import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class AddMultipleItemsTest extends AbstractTest{

    @Test
    public void testAddMultipleProductsToCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Inventory page should be visible after login");

        inventoryPage.addProductsToCart(List.of("Sauce Labs Backpack", "Sauce Labs Bike Light"));

        int count = inventoryPage.getCartCount();
        Assert.assertEquals(count, 2, "Expected cart count to be 2 but was: " + count);
    }
}