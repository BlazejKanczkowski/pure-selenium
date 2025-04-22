package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage extends AbstractPage{


    @FindBy(xpath = "//span[text()='Products']")
    private WebElement pageTitle;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCartButton;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement addBikeLightToCartButton;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(className = "inventory_item_price")
    private java.util.List<WebElement> productPrices;

    public void sortByPriceHighToLow() {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText("Price (high to low)");
    }

    public java.util.List<Double> getDisplayedPrices() {
        return productPrices.stream()
                .map(WebElement::getText)
                .map(price -> price.replace("$", ""))
                .map(Double::parseDouble)
                .toList();
    }


    public void clickCartIcon() {
        cartIcon.click();
    }

    public void addBikeLightToCart() {
        addBikeLightToCartButton.click();
    }

    public void addBackpackToCart() {
        addToCartButton.click();
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
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logoutLink.click();
    }

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageDisplayed() {
        return pageTitle.isDisplayed();
    }
}