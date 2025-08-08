package com.automationexercise.pages;

import com.automationexercise.locators.login.LoginLocators;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {

    private final Page page;
    private final Locator emailInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator loggedInIndicator;
    private final Locator loginErrorMessage;

    public LoginPage(Page page) {
        this.page = page;
        LoginLocators locators = LoginLocators.get();
        this.emailInput = page.locator(locators.emailInput());
        this.passwordInput = page.locator(locators.passwordInput());
        this.loginButton = page.locator(locators.loginButton());
        this.loggedInIndicator = page.locator(locators.loggedInIndicator());
        this.loginErrorMessage = page.locator(locators.loginErrorMessage());
    }

    public void navigate() {
        io.github.cdimascio.dotenv.Dotenv dotenv = com.automationexercise.utils.TestUserGenerator.getDotenv();
        String baseUrl = dotenv.get("BASE_URL");
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new RuntimeException("BASE_URL is not defined in environment variables");
        }
        page.navigate(baseUrl + "/login");
        page.waitForLoadState();
    }

    public void login(String email, String password) {
        emailInput.fill(email);
        passwordInput.fill(password);
        loginButton.click();
        page.waitForLoadState();
    }

    public void verifyLoginSuccess(String username) {
        String text = loggedInIndicator.textContent();
        if (text == null || !text.contains("Logged in as " + username)) {
            throw new AssertionError("Login success message not found. Actual: " + text);
        }
    }

    public void verifyLoginFailure() {
        String text = loginErrorMessage.textContent();
        if (text == null || !text.contains("Your email or password is incorrect!")) {
            throw new AssertionError("Login error message not found. Actual: " + text);
        }
    }
}