package com.automationexercise.locators.signup;

public class SignupLocatorsProd implements SignupLocators {

    @Override
    public String nameInput() {
        return "input[data-qa=\"signup-name\"]";
    }

    @Override
    public String emailInput() {
        return "input[data-qa=\"signup-email\"]";
    }

    @Override
    public String signUpButton() {
        return "button[data-qa=\"signup-button\"]";
    }

    @Override
    public String titleMrRadio() {
        return "input[id=\"id_gender1\"]";
    }

    @Override
    public String titleMrsRadio() {
        return "input[id=\"id_gender2\"]";
    }

    @Override
    public String passwordInput() {
        return "input[data-qa=\"password\"]";
    }

    @Override
    public String birthDaySelect() {
        return "select[data-qa=\"days\"]";
    }

    @Override
    public String birthMonthSelect() {
        return "select[data-qa=\"months\"]";
    }

    @Override
    public String birthYearSelect() {
        return "select[data-qa=\"years\"]";
    }

    @Override
    public String firstNameInput() {
        return "input[data-qa=\"first_name\"]";
    }

    @Override
    public String lastNameInput() {
        return "input[data-qa=\"last_name\"]";
    }

    @Override
    public String companyInput() {
        return "input[data-qa=\"company\"]";
    }

    @Override
    public String address1Input() {
        return "input[data-qa=\"address\"]";
    }

    @Override
    public String address2Input() {
        return "input[data-qa=\"address2\"]";
    }

    @Override
    public String countrySelect() {
        return "select[data-qa=\"country\"]";
    }

    @Override
    public String stateSelect() {
        return "input[data-qa=\"state\"]";
    }

    @Override
    public String cityInput() {
        return "input[data-qa=\"city\"]";
    }

    @Override
    public String zipcodeInput() {
        return "input[data-qa=\"zipcode\"]";
    }

    @Override
    public String mobileNumberInput() {
        return "input[data-qa=\"mobile_number\"]";
    }

    @Override
    public String createAccountButton() {
        return "button[data-qa=\"create-account\"]";
    }

    @Override
    public String accountCreatedMessage() {
        return "h2[data-qa=\"account-created\"]";
    }
}