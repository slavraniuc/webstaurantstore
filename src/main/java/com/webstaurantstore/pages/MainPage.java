package com.webstaurantstore.pages;

import com.google.common.collect.Iterables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainPage {
    private static final String PAGE_URL = "https://www.webstaurantstore.com";
    private final WebDriver driver;
    By productsBy = By.cssSelector("div#ProductBoxContainer");
    @FindBy(id = "searchval")
    private WebElement searchBox;
    @FindBy(css = "button[value='Search']")
    private WebElement searchButton;
    @FindBy(css = "nav[aria-label='pagination'] li a")
    private List<WebElement> paginationButtons;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<SearchResultsProductComponent> getSearchResults() {
        return driver.findElements(productsBy)
                .stream()
                .map(SearchResultsProductComponent::new)
                .collect(Collectors.toList());
    }

    public void get() {
        driver.get(PAGE_URL);
    }

    public void searchForProduct(String product) {
        searchBox.clear();
        searchBox.sendKeys(product);
        searchButton.click();
    }

    public int getCurrentPage() {
        Optional<WebElement> currentPageButton = paginationButtons
                .stream()
                .filter(e -> e.getAttribute("aria-label").contains("current page"))
                .findFirst();

        return currentPageButton.map(element -> Integer.parseInt(element.getText())).orElse(1);
    }

    public int getNumberOfPages() {
        List<WebElement> pageButtons = paginationButtons
                .stream()
                .filter(b -> !b.getText().isEmpty())
                .collect(Collectors.toList());
        return Integer.parseInt(Iterables.getLast(pageButtons).getText());
    }

    public void selectNextPage() {
        if (getCurrentPage() != getNumberOfPages()) Iterables.getLast(paginationButtons).click();
    }
}