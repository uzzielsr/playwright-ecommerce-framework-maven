package com.automationexercise.locators.cart;

public interface CartLocators {

    String proceedToCheckoutButton();

    static CartLocators get() {
        String env = System.getProperty("env", "prod").toLowerCase();
        return switch (env) {
            case "qa" ->
                new CartLocatorsQa();
            case "uat" ->
                new CartLocatorsUat();
            default ->
                new CartLocatorsProd();
        };
    }
}