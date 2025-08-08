package com.automationexercise.locators.cart;

public class CartLocatorsUat implements CartLocators {

    @Override
    public String proceedToCheckoutButton() {
        return "a[class=\"btn btn-default check_out\"]";
    }
}