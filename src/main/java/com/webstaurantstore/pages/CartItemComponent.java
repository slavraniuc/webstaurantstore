package com.webstaurantstore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartItemComponent {
    private final WebElement componentRootElement;

    private final By itemDescriptionBy = By.cssSelector("span.itemDescription");
    private final By removeButtonBy = By.cssSelector("button.deleteCartItemButton");

    public CartItemComponent(WebElement componentRootElement) {
        this.componentRootElement = componentRootElement;
    }

    public String getItemDescription() {
        return componentRootElement.findElement(itemDescriptionBy).getText();
    }

    public void removeItem() {
        componentRootElement.findElement(removeButtonBy).click();
    }
}