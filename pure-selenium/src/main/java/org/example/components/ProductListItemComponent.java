package org.example.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductListItemComponent {

    private final WebElement root;

    public ProductListItemComponent(WebDriver driver, WebElement root) {
        this.root = root;
    }

    public String getName() {
        return root.findElement(By.className("inventory_item_name")).getText();
    }

    public String getPrice() {
        return root.findElement(By.className("inventory_item_price")).getText();
    }

    public void clickAddToCart() {
        root.findElement(By.cssSelector("button.btn_inventory")).click();
    }
}
