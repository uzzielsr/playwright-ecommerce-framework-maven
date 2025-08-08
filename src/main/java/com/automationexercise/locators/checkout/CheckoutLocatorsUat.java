package com.automationexercise.locators.checkout;

public class CheckoutLocatorsUat implements CheckoutLocators {

    @Override
    public String placeOrderButton() {
        return "a[class=\"btn btn-default check_out\"]";
    }

    @Override
    public String nameInput() {
        return "input[data-qa=\"name-on-card\"]";
    }

    @Override
    public String ccNumberInput() {
        return "input[data-qa=\"card-number\"]";
    }

    @Override
    public String cvcInput() {
        return "input[data-qa=\"cvc\"]";
    }

    @Override
    public String expMonthInput() {
        return "input[data-qa=\"expiry-month\"]";
    }

    @Override
    public String expYearInput() {
        return "input[data-qa=\"expiry-year\"]";
    }

    @Override
    public String confirmOrderButton() {
        return "button[data-qa=\"pay-button\"]";
    }

    @Override
    public String orderSuccessMessage() {
        return "h2[data-qa=\"order-placed\"]";
    }
}