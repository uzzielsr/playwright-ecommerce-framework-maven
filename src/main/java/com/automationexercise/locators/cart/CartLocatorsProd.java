package com.automationexercise.locators.cart;

public class CartLocatorsProd implements CartLocators {

    @Override
    public String proceedToCheckoutButton() {
        return "a[class=\"btn btn-default check_out\"]";
    }
}