package com.automationexercise.locators.plp;

public interface PlpLocators {

    String firstProductLink();

    static PlpLocators get() {
        String env = System.getProperty("env", "prod").toLowerCase();
        return switch (env) {
            case "qa" ->
                new PlpLocatorsQa();
            case "uat" ->
                new PlpLocatorsUat();
            default ->
                new PlpLocatorsProd();
        };
    }
}