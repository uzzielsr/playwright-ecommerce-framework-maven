package com.automationexercise.pages;

import com.automationexercise.locators.checkout.CheckoutLocators;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutPage {

    private final Page page;
    private final Locator placeOrderButton;
    private final Locator nameInput;
    private final Locator ccNumberInput;
    private final Locator cvcInput;
    private final Locator expMonthInput;
    private final Locator expYearInput;
    private final Locator confirmOrderButton;
    private final Locator orderSuccessMessage;

    public CheckoutPage(Page page) {
        this.page = page;
        CheckoutLocators locators = CheckoutLocators.get();
        this.placeOrderButton = page.locator(locators.placeOrderButton());
        this.nameInput = page.locator(locators.nameInput());
        this.ccNumberInput = page.locator(locators.ccNumberInput());
        this.cvcInput = page.locator(locators.cvcInput());
        this.expMonthInput = page.locator(locators.expMonthInput());
        this.expYearInput = page.locator(locators.expYearInput());
        this.confirmOrderButton = page.locator(locators.confirmOrderButton());
        this.orderSuccessMessage = page.locator(locators.orderSuccessMessage());
    }

    public void proceedToPayment() {
        placeOrderButton.click();
        page.waitForLoadState();
    }

    public void fillPaymentDetails(Card card) {
        nameInput.fill(card.name);
        ccNumberInput.fill(card.number);
        cvcInput.fill(card.cvc);
        expMonthInput.fill(card.expMonth);
        expYearInput.fill(card.expYear);
    }

    public void confirmOrder() {
        confirmOrderButton.click();
        page.waitForLoadState();
    }

    public void verifyOrderSuccess() {
        String text = orderSuccessMessage.textContent();
        if (text == null || !text.contains("Order Placed!")) {
            throw new AssertionError("Order placed message not found. Actual: " + text);
        }
    }

    public static class Card {

        public String name, number, cvc, expMonth, expYear;
    }
}