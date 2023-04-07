package com.webstaurantstore;

import com.webstaurantstore.pages.CartPage;
import com.webstaurantstore.pages.MainPage;
import com.webstaurantstore.pages.NotificationModal;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class WebTest {
    // Pages
    protected MainPage mainPage;
    protected CartPage cartPage;
    protected NotificationModal notificationModal;
    private WebDriver driver;

    @BeforeMethod
    public void testInit() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-fullscreen");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        // Init pages
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
        notificationModal = new NotificationModal(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void testCleanup() {
        driver.quit();
    }
}