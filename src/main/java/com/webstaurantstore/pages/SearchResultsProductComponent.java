package com.webstaurantstore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchResultsProductComponent {
    private final WebElement componentRootElement;
    private final By descriptionBy = By.cssSelector("a[data-testid='itemDescription']");
    private final By addToCartBy = By.cssSelector("input[name='addToCartButton']");

    public SearchResultsProductComponent(WebElement componentRootElement) {
        this.componentRootElement = componentRootElement;
    }

    public String getDescription() {
        return componentRootElement.findElement(descriptionBy).getText();
    }

    public void addToCart() {
        componentRootElement.findElement(addToCartBy).click();
    }
}