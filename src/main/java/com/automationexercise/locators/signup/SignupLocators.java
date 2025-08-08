package com.automationexercise.locators.signup;

public interface SignupLocators {

    String nameInput();

    String emailInput();

    String signUpButton();

    String titleMrRadio();

    String titleMrsRadio();

    String passwordInput();

    String birthDaySelect();

    String birthMonthSelect();

    String birthYearSelect();

    String firstNameInput();

    String lastNameInput();

    String companyInput();

    String address1Input();

    String address2Input();

    String countrySelect();

    String stateSelect();

    String cityInput();

    String zipcodeInput();

    String mobileNumberInput();

    String createAccountButton();

    String accountCreatedMessage();

    static SignupLocators get() {
        String env = System.getProperty("env", "prod").toLowerCase();
        return switch (env) {
            case "qa" ->
                new SignupLocatorsQa();
            case "uat" ->
                new SignupLocatorsUat();
            default ->
                new SignupLocatorsProd();
        };
    }
}