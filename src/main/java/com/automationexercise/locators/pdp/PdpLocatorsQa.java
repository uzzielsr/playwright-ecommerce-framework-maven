package com.automationexercise.locators.pdp;

public class PdpLocatorsQa implements PdpLocators {

    @Override
    public String addToCartButton() {
        return "button[class=\"btn btn-default cart\"]";
    }

    @Override
    public String viewCartLink() {
        return "a[href=\"/view_cart\"]:has-text(\"View Cart\")";
    }
}