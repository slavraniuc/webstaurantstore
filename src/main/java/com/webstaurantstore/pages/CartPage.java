package com.webstaurantstore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage {
    private static final String PAGE_URL = "https://www.webstaurantstore.com/cart/";
    private final WebDriver driver;
    private final By emptyCartModalButton = By.xpath("//footer/button[contains(text(),'Empty')]");
    By cartItemBy = By.cssSelector("div.cartItem");
    By emptyCartMessageBy = By.cssSelector("div.empty-cart__text");
    @FindBy(css = "button.emptyCartButton")
    private WebElement emptyCartButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void get() {
        driver.get(PAGE_URL);
    }

    public List<CartItemComponent> getCartItems() {
        return driver.findElements(cartItemBy)
                .stream()
                .map(CartItemComponent::new)
                .collect(Collectors.toList());
    }

    public void emptyCart() {
        emptyCartButton.click();
        driver.findElement(emptyCartModalButton).click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(emptyCartMessageBy));
    }
}