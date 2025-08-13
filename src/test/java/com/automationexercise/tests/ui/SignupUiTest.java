package com.automationexercise.tests.ui;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.automationexercise.api.UserApi;
import com.automationexercise.pages.SignupPage;
import com.automationexercise.utils.TestUserGenerator;
import com.automationexercise.utils.TestUserGenerator.User;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Account Creation UI")
public class SignupUiTest {

    private Playwright playwright;
    private Browser browser;
    private APIRequestContext requestContext;
    private UserApi userApi;
    private Page page;
    private SignupPage signupPage;
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
        signupPage = new SignupPage(page);
        user = TestUserGenerator.generateTestUser();
        signupPage.navigate();
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
    @DisplayName("Successful Account Creation with Valid Data")
    void successfulAccountCreationWithValidData() {
        signupPage.signUp(user);
        signupPage.verifyAccountCreated();
    }
}