package com.automationexercise.locators.login;

public interface LoginLocators {

    String emailInput();

    String passwordInput();

    String loginButton();

    String loggedInIndicator();

    String loginErrorMessage();

    static LoginLocators get() {
        String env = System.getProperty("env", "prod").toLowerCase();
        return switch (env) {
            case "qa" ->
                new LoginLocatorsQa();
            case "uat" ->
                new LoginLocatorsUat();
            default ->
                new LoginLocatorsProd();
        };
    }
}