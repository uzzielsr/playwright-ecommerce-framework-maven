package com.automationexercise.locators.login;

public class LoginLocatorsQa implements LoginLocators {

    @Override
    public String emailInput() {
        return "input[data-qa=\"login-email\"]";
    }

    @Override
    public String passwordInput() {
        return "input[data-qa=\"login-password\"]";
    }

    @Override
    public String loginButton() {
        return "button[data-qa=\"login-button\"]";
    }

    @Override
    public String loggedInIndicator() {
        return "a:has-text(\"Logged in as\")";
    }

    @Override
    public String loginErrorMessage() {
        return "p:has-text(\"Your email or password is incorrect!\")";
    }
}