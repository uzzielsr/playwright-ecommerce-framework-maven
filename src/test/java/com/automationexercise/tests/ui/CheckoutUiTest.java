package com.automationexercise.tests.ui;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.automationexercise.api.UserApi;
import com.automationexercise.pages.CartPage;
import com.automationexercise.pages.CheckoutPage;
import com.automationexercise.pages.CheckoutPage.Card;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.pages.PdpPage;
import com.automationexercise.pages.PlpPage;
import com.automationexercise.utils.TestUserGenerator;
import com.automationexercise.utils.TestUserGenerator.User;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Checkout UI")
public class CheckoutUiTest {

    private Playwright playwright;
    private Browser browser;
    private APIRequestContext requestContext;
    private UserApi userApi;
    private Page page;
    private LoginPage loginPage;
    private PlpPage plpPage;
    private PdpPage pdpPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private User user;

    @SuppressWarnings("unused")
    @BeforeAll
    void setupAll() {
        playwright = Playwright.create();
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
        requestContext = playwright.request().newContext();
    }

    @SuppressWarnings("unused")
    @BeforeEach
    void setup() {
        page = browser.newPage();
        io.github.cdimascio.dotenv.Dotenv dotenv = com.automationexercise.utils.TestUserGenerator.getDotenv();
        String baseUrl = dotenv.get("BASE_URL");
        com.automationexercise.utils.TestUserGenerator.validateBaseUrl(baseUrl);
        userApi = new UserApi(requestContext, baseUrl);
        user = TestUserGenerator.generateTestUser();
        userApi.createUser(user);
        loginPage = new LoginPage(page);
        plpPage = new PlpPage(page);
        pdpPage = new PdpPage(page);
        cartPage = new CartPage(page);
        checkoutPage = new CheckoutPage(page);
        loginPage.navigate();
        loginPage.login(user.email, user.password);
    }

    @SuppressWarnings("unused")
    @AfterEach
    void tearDown() {
        userApi.deleteUser(user.email, user.password);
        page.close();
    }

    @SuppressWarnings("unused")
    @AfterAll
    void tearDownAll() {
        requestContext.dispose();
        browser.close();
        playwright.close();
    }

    @Test
    @DisplayName("Successful Checkout Flow (UI end-to-end)")
    void successfulCheckoutFlowUiEndToEnd() {
        plpPage.navigate();
        plpPage.selectProduct();
        pdpPage.addToCart();
        cartPage.proceedToCheckout();
        checkoutPage.proceedToPayment();
        Card card = new Card();
        card.name = user.cc_name;
        card.number = user.cc_number;
        card.cvc = user.cc_cvc;
        card.expMonth = user.cc_exp_month;
        card.expYear = user.cc_exp_year;
        checkoutPage.fillPaymentDetails(card);
        checkoutPage.confirmOrder();
        checkoutPage.verifyOrderSuccess();
    }
}