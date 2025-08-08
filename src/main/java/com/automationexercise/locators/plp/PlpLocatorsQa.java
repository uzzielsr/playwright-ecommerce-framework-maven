package com.automationexercise.locators.plp;

public class PlpLocatorsQa implements PlpLocators {

    @Override
    public String firstProductLink() {
        return "a:has-text(\"View Product\")";
    }
}