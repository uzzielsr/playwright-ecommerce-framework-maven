package com.automationexercise.pages;

import com.automationexercise.locators.cart.CartLocators;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage {

    private final Page page;
    private final Locator proceedToCheckoutButton;

    public CartPage(Page page) {
        this.page = page;
        CartLocators locators = CartLocators.get();
        this.proceedToCheckoutButton = page.locator(locators.proceedToCheckoutButton());
    }

    public void proceedToCheckout() {
        proceedToCheckoutButton.click();
        page.waitForLoadState();
    }
}