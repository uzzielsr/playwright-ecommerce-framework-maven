package com.automationexercise.pages;

import com.automationexercise.locators.plp.PlpLocators;
import com.automationexercise.utils.TestUserGenerator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PlpPage {

    private final Page page;
    private final Locator firstProductLink;

    public PlpPage(Page page) {
        this.page = page;
        PlpLocators locators = PlpLocators.get();
        this.firstProductLink = page.locator(locators.firstProductLink()).first();
    }

    public void navigate() {
        String baseUrl = TestUserGenerator.getDotenv().get("BASE_URL");
        if (baseUrl == null) {
            throw new RuntimeException("BASE_URL is not defined in environment variables");
        }
        page.navigate(baseUrl + "/products");
        page.waitForLoadState();
    }

    public void selectProduct() {
        firstProductLink.click();
        page.waitForLoadState();
    }
}