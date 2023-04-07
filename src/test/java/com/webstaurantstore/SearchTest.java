package com.webstaurantstore;

import com.google.common.collect.Iterables;
import com.webstaurantstore.pages.SearchResultsProductComponent;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchTest extends WebTest {
    private final SoftAssert softAssertion = new SoftAssert();

    private void checkProductTitles() {
        for (SearchResultsProductComponent product : mainPage.getSearchResults()) {
            String productDescription = product.getDescription();
            softAssertion.assertTrue(
                    productDescription.contains("Table"),
                    String.format(
                            "Page %d product %s should contain Table",
                            mainPage.getCurrentPage(),
                            product.getDescription()
                    )
            );
        }
    }

    @Test
    public void searchBy() {
        mainPage.get();
        mainPage.searchForProduct("stainless work table");

        // Check each page that the products has the word Table in the description
        boolean allPagesAreChecked = false;
        while (!allPagesAreChecked) {
            checkProductTitles();
            if (mainPage.getCurrentPage() < mainPage.getNumberOfPages()) {
                mainPage.selectNextPage();
            } else allPagesAreChecked = true;
        }

        // Add the last product from the last page to the cart
        SearchResultsProductComponent product = Iterables.getLast(mainPage.getSearchResults());
        String productDescription = product.getDescription();
        product.addToCart();

        if (notificationModal.isDisplayed()) notificationModal.close();

        // Go to cart page and empty the cart
        cartPage.get();

        softAssertion.assertTrue(
                cartPage.getCartItems()
                        .stream()
                        .anyMatch(i -> i.getItemDescription().equals(productDescription)),
                String.format("Product %s is not present in the cart", productDescription)
        );

        cartPage.emptyCart();

        softAssertion.assertTrue(
                cartPage.getCartItems().isEmpty(),
                "The shopping cart has not been emptied"
        );

        softAssertion.assertAll();
    }
}