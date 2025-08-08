package com.automationexercise.locators.checkout;

public interface CheckoutLocators {

    String placeOrderButton();

    String nameInput();

    String ccNumberInput();

    String cvcInput();

    String expMonthInput();

    String expYearInput();

    String confirmOrderButton();

    String orderSuccessMessage();

    static CheckoutLocators get() {
        String env = System.getProperty("env", "prod").toLowerCase();
        return switch (env) {
            case "qa" ->
                new CheckoutLocatorsQa();
            case "uat" ->
                new CheckoutLocatorsUat();
            default ->
                new CheckoutLocatorsProd();
        };
    }
}