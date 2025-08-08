package com.automationexercise.tests.api;

import java.util.HashMap;
import java.util.Map;

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
public class UserCrudApiTest {

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
        String env = System.getProperty("env", "prod");
        String dotenvFile = ".env." + env;
        io.github.cdimascio.dotenv.Dotenv dotenv = io.github.cdimascio.dotenv.Dotenv.configure().filename(dotenvFile).load();
        String baseUrl = dotenv.get("BASE_URL");
        com.automationexercise.utils.TestUserGenerator.validateBaseUrl(baseUrl);
        userApi = new UserApi(requestContext, baseUrl);
        user = TestUserGenerator.generateTestUser();
    }

    @SuppressWarnings("unused")
    @AfterAll
    void tearDownAll() {
        requestContext.dispose();
        playwright.close();
    }

    @Test
    void performsFullCrudFlowForUserViaApi() {
        userApi.createUser(user);
        userApi.readUser(user.email);
        Map<String, String> updates = new HashMap<>();
        updates.put("city", "Updated City");
        userApi.updateUser(user.email, user.password, updates);
        userApi.deleteUser(user.email, user.password);
    }
}