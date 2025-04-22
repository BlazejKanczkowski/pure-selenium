package tests;

import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class AddToCartTest extends AbstractTest {

    @Test
    public void testAddProductToCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Inventory page should be visible after login");

        inventoryPage.addProductsToCart(List.of("Sauce Labs Backpack"));

        int count = inventoryPage.getCartCount();
        Assert.assertEquals(count, 1, "Expected cart count to be 1 but was: " + count);
    }
}