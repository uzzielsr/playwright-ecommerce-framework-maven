package com.automationexercise.pages;

import com.automationexercise.locators.pdp.PdpLocators;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PdpPage {

    private final Page page;
    private final Locator addToCartButton;
    private final Locator viewCartLink;

    public PdpPage(Page page) {
        this.page = page;
        PdpLocators locators = PdpLocators.get();
        this.addToCartButton = page.locator(locators.addToCartButton());
        this.viewCartLink = page.locator(locators.viewCartLink());
    }

    public void addToCart() {
        addToCartButton.click();
        page.waitForLoadState();
        viewCartLink.click();
        page.waitForLoadState();
    }
}