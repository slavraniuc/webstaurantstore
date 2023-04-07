package com.webstaurantstore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationModal {
    By notificationBy = By.cssSelector("div.notification");
    By closeBy = By.cssSelector("div.notification button.close");
    private final WebDriver driver;

    public NotificationModal(WebDriver driver) {
        this.driver = driver;
    }

    public void close() {
        driver.findElement(closeBy).click();
    }

    public boolean isDisplayed(){
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(notificationBy));
        return true;
    }
}