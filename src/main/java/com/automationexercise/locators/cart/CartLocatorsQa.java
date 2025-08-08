package com.automationexercise.locators.cart;

public class CartLocatorsQa implements CartLocators {

    @Override
    public String proceedToCheckoutButton() {
        return "a[class=\"btn btn-default check_out\"]";
    }
}