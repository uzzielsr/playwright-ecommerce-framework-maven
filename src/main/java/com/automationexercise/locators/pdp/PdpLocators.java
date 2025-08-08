package com.automationexercise.locators.pdp;

public interface PdpLocators {

    String addToCartButton();

    String viewCartLink();

    static PdpLocators get() {
        String env = System.getProperty("env", "prod").toLowerCase();
        return switch (env) {
            case "qa" ->
                new PdpLocatorsQa();
            case "uat" ->
                new PdpLocatorsUat();
            default ->
                new PdpLocatorsProd();
        };
    }
}