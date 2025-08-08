package com.automationexercise.tests.api;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.automationexercise.api.UserApi;
import com.automationexercise.utils.TestUserGenerator;
import com.automationexercise.utils.TestUserGenerator.User;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginApiTest {

    private Playwright playwright;
    private APIRequestContext requestContext;
    private UserApi userApi;
    private User user;

    @SuppressWarnings("unused")
    @BeforeAll
    void setupAll() {
        playwright = Playwright.create();
        requestContext = playwright.request().newContext();
    }

    @SuppressWarnings("unused")
    @BeforeEach
    void setup() {
        io.github.cdimascio.dotenv.Dotenv dotenv = com.automationexercise.utils.TestUserGenerator.getDotenv();
        String baseUrl = dotenv.get("BASE_URL");
        com.automationexercise.utils.TestUserGenerator.validateBaseUrl(baseUrl);
        userApi = new UserApi(requestContext, baseUrl);
        user = TestUserGenerator.generateTestUser();
        userApi.createUser(user);
    }

    @SuppressWarnings("unused")
    @AfterAll
    void tearDownAll() {
        requestContext.dispose();
        playwright.close();
    }

    @Test
    void shouldLoginSuccessfullyWithValidCredentials() {
        userApi.verifyUserExists(user.email, user.password);
    }

    @Test
    void shouldDisplayErrorForInvalidLoginCredentials() {
        int random = (int) (Math.random() * 100000);
        userApi.verifyUserDoesNotExist(random + "@invalid.com", "WrongPassword");
    }
}