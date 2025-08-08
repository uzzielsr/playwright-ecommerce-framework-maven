package com.automationexercise.tests.ui;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.automationexercise.api.UserApi;
import com.automationexercise.pages.LoginPage;
import com.automationexercise.utils.TestUserGenerator;
import com.automationexercise.utils.TestUserGenerator.User;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginUiTest {

    private Playwright playwright;
    private Browser browser;
    private APIRequestContext requestContext;
    private UserApi userApi;
    private Page page;
    private LoginPage loginPage;
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
        loginPage = new LoginPage(page);
        user = TestUserGenerator.generateTestUser();
        userApi.createUser(user);
        loginPage.navigate();
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
    void shouldLoginSuccessfullyWithValidCredentials() {
        loginPage.login(user.email, user.password);
        loginPage.verifyLoginSuccess(user.name);
    }

    @Test
    void shouldDisplayErrorForInvalidLoginCredentials() {
        int random = (int) (Math.random() * 100000);
        loginPage.login(random + "@invalid.com", "WrongPassword");
        loginPage.verifyLoginFailure();
    }
}