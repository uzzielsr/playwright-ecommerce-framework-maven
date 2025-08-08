package com.automationexercise.pages;

import com.automationexercise.locators.signup.SignupLocators;
import com.automationexercise.utils.TestUserGenerator.User;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SignupPage {

    private final Page page;
    private final Locator nameInput;
    private final Locator emailInput;
    private final Locator signUpButton;
    private final Locator titleMrRadio;
    private final Locator titleMrsRadio;
    private final Locator passwordInput;
    private final Locator birthDaySelect;
    private final Locator birthMonthSelect;
    private final Locator birthYearSelect;
    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator companyInput;
    private final Locator address1Input;
    private final Locator address2Input;
    private final Locator countrySelect;
    private final Locator stateSelect;
    private final Locator cityInput;
    private final Locator zipcodeInput;
    private final Locator mobileNumberInput;
    private final Locator createAccountButton;
    private final Locator accountCreatedMessage;

    public SignupPage(Page page) {
        this.page = page;
        SignupLocators locators = SignupLocators.get();
        this.nameInput = page.locator(locators.nameInput());
        this.emailInput = page.locator(locators.emailInput());
        this.signUpButton = page.locator(locators.signUpButton());
        this.titleMrRadio = page.locator(locators.titleMrRadio());
        this.titleMrsRadio = page.locator(locators.titleMrsRadio());
        this.passwordInput = page.locator(locators.passwordInput());
        this.birthDaySelect = page.locator(locators.birthDaySelect());
        this.birthMonthSelect = page.locator(locators.birthMonthSelect());
        this.birthYearSelect = page.locator(locators.birthYearSelect());
        this.firstNameInput = page.locator(locators.firstNameInput());
        this.lastNameInput = page.locator(locators.lastNameInput());
        this.companyInput = page.locator(locators.companyInput());
        this.address1Input = page.locator(locators.address1Input());
        this.address2Input = page.locator(locators.address2Input());
        this.countrySelect = page.locator(locators.countrySelect());
        this.stateSelect = page.locator(locators.stateSelect());
        this.cityInput = page.locator(locators.cityInput());
        this.zipcodeInput = page.locator(locators.zipcodeInput());
        this.mobileNumberInput = page.locator(locators.mobileNumberInput());
        this.createAccountButton = page.locator(locators.createAccountButton());
        this.accountCreatedMessage = page.locator(locators.accountCreatedMessage());
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

    public void signUp(User user) {
        nameInput.fill(user.name);
        emailInput.fill(user.email);
        signUpButton.click();
        page.waitForLoadState();
        if (user.title != null && user.title.equalsIgnoreCase("Mrs")) {
            titleMrsRadio.check();
        } else {
            titleMrRadio.check();
        }
        passwordInput.fill(user.password);
        birthDaySelect.selectOption(user.birth_day);
        birthMonthSelect.selectOption(user.birth_month);
        birthYearSelect.selectOption(user.birth_year);
        firstNameInput.fill(user.firstname);
        lastNameInput.fill(user.lastname);
        companyInput.fill(user.company);
        address1Input.fill(user.address1);
        address2Input.fill(user.address2);
        countrySelect.selectOption(user.country);
        stateSelect.fill(user.state);
        cityInput.fill(user.city);
        zipcodeInput.fill(user.zipcode);
        mobileNumberInput.fill(user.mobile_number);
        createAccountButton.click();
        page.waitForLoadState();
    }

    public void verifyAccountCreated() {
        String text = accountCreatedMessage.textContent();
        if (text == null || !text.contains("Account Created!")) {
            throw new AssertionError("Account creation message not found. Actual: " + text);
        }
    }
}