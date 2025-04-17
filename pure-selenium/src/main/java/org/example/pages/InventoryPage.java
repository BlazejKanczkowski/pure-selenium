package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class InventoryPage {

    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(InventoryPage.class);


    @FindBy(css = ".inventory_item_name")
    private List<WebElement> productTitles;

    @FindBy(css = "[data-test='product_sort_container']")
    private WebElement sortDropdown;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    public List<WebElement> getProductTitles() {
//        return productTitles;
//    }

    public void printAllProductTitles() {
        if (productTitles.isEmpty()) {
            logger.warn("No products found!");
        } else {
            logger.info("Products:");
            for (WebElement title : productTitles) {
                logger.info("- {}", title.getText());
            }
        }
    }
}

