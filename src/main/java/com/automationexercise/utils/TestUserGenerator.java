package com.automationexercise.utils;

import java.util.Random;

import io.github.cdimascio.dotenv.Dotenv;

public class TestUserGenerator {

    private static Dotenv dotenvInstance;

    /**
     * Validates that the base URL is accessible before running the tests.
     * Throws RuntimeException if it does not respond.
     */
    public static void validateBaseUrl(String baseUrl) {
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new RuntimeException("BASE_URL is not defined in environment variables");
        }
        try {
            java.net.URL url = new java.net.URL(baseUrl);
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            int responseCode = connection.getResponseCode();
            if (responseCode >= 400) {
                throw new RuntimeException("BASE_URL did not respond correctly (HTTP " + responseCode + "): " + baseUrl);
            }
        } catch (java.net.MalformedURLException e) {
            throw new RuntimeException("Malformed URL for BASE_URL: " + baseUrl + "\n" + e.getMessage());
        } catch (java.io.IOException e) {
            throw new RuntimeException("Could not connect to BASE_URL: " + baseUrl + "\n" + e.getMessage());
        }
    }

    public static Dotenv getDotenv() {
        if (dotenvInstance == null) {
            String env = System.getProperty("env", "prod");
            String envFile = ".env." + env;
            String projectRoot = System.getProperty("user.dir");
            dotenvInstance = Dotenv.configure()
                    .directory(projectRoot)
                    .filename(envFile)
                    .ignoreIfMissing()
                    .load();
        }
        return dotenvInstance;
    }

    public static User generateTestUser() {
        Dotenv dotenv = getDotenv();
        int random = new Random().nextInt(100000);
        User user = new User();
        user.name = dotenv.get("TEST_NAME");
        user.email = random + "@example.com";
        user.title = dotenv.get("TEST_TITLE");
        user.password = dotenv.get("TEST_PASSWORD");
        user.birth_day = dotenv.get("TEST_BIRTH_DAY");
        user.birth_month = dotenv.get("TEST_BIRTH_MONTH");
        user.birth_year = dotenv.get("TEST_BIRTH_YEAR");
        user.firstname = dotenv.get("TEST_FIRST_NAME");
        user.lastname = dotenv.get("TEST_LAST_NAME");
        user.company = dotenv.get("TEST_COMPANY");
        user.address1 = dotenv.get("TEST_ADDRESS1");
        user.address2 = dotenv.get("TEST_ADDRESS2");
        user.country = dotenv.get("TEST_COUNTRY");
        user.zipcode = dotenv.get("TEST_ZIPCODE");
        user.state = dotenv.get("TEST_STATE");
        user.city = dotenv.get("TEST_CITY");
        user.mobile_number = dotenv.get("TEST_MOBILE_NUMBER");
        user.cc_name = dotenv.get("TEST_CC_NAME");
        user.cc_number = dotenv.get("TEST_CC_NUMBER");
        user.cc_cvc = dotenv.get("TEST_CC_CVC");
        user.cc_exp_month = dotenv.get("TEST_CC_EXP_MONTH");
        user.cc_exp_year = dotenv.get("TEST_CC_EXP_YEAR");
        return user;
    }

    public static class User {

        public String name, email, title, password, birth_day, birth_month, birth_year, firstname, lastname, company, address1, address2, country, zipcode, state, city, mobile_number;
        public String cc_name, cc_number, cc_cvc, cc_exp_month, cc_exp_year;
    }
}