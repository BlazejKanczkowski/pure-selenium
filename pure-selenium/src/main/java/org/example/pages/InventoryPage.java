package org.example.pages;

import org.example.components.ProductComponent;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.example.enums.SortOption;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage extends AbstractPage{

    @FindBy(xpath = "//span[contains(@data-test,'title')]")
    private WebElement pageTitle;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(className = "inventory_item")
    private List<WebElement> productElements;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void sortBy(SortOption option) {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(option.getVisibleText());
    }

    public List<Double> getDisplayedPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement element : productElements) {
            ProductComponent product = new ProductComponent(driver, element);
            String priceText = product.getPrice().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }

    public CartPage clickCartIcon() {
        cartIcon.click();
        return new CartPage(driver);
    }

    public void addProductToCartByName(String productName) {
        for (WebElement element : productElements) {
            ProductComponent product = new ProductComponent(driver, element);
            if (product.getName().equalsIgnoreCase(productName)) {
                product.clickAddToCart();
                break;
            }
        }
    }

    public int getCartCount() {
        if (cartBadge.isDisplayed()) {
            return Integer.parseInt(cartBadge.getText());
        } else {
            return 0;
        }
    }

    public void logout() {
        menuButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(logoutLink))
                .click();
    }

    public boolean isPageDisplayed() {
        return pageTitle.isDisplayed();
    }

    public void addProductsToCart(List<String> productNames) {
        for (String name : productNames) {
            addProductToCartByName(name);
        }
    }
}