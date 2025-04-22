package tests;

import org.example.pages.CartPage;
import org.example.pages.CheckoutPage;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckoutTest extends AbstractTest{

    @Test
    public void testProceedToCheckout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isPageDisplayed(), "Inventory page should be visible");

        inventoryPage.addBackpackToCart();
        inventoryPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillForm("John", "Doe", "12345");
        checkoutPage.finishOrder();

        Assert.assertTrue(checkoutPage.isConfirmationDisplayed(), "Order confirmation should be displayed");
    }
}