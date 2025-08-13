package com.automationexercise.api;

import java.util.HashMap;
import java.util.Map;

import com.automationexercise.utils.TestUserGenerator.User;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.FormData;
import com.microsoft.playwright.options.RequestOptions;

public class UserApi {

    private final APIRequestContext request;
    private final String baseUrl;

    public UserApi(APIRequestContext request, String baseUrl) {
        this.request = request;
        this.baseUrl = baseUrl;
    }

    private Map<String, Object> parseJson(APIResponse response) {
        try {
            String json = response.text();
            // Simple JSON parsing (replace with a real parser if needed)
            Map<String, Object> map = new HashMap<>();
            json = json.replaceAll("[{}\"]", "");
            for (String pair : json.split(",")) {
                String[] kv = pair.split(":", 2);
                if (kv.length == 2) {
                    map.put(kv[0].trim(), kv[1].trim());
                }
            }
            return map;
        } catch (Exception e) {
            throw new AssertionError("Failed to parse JSON: " + e.getMessage());
        }
    }

    public void createUser(User user) {
        try {
            FormData form = FormData.create()
                    .set("name", user.name)
                    .set("email", user.email)
                    .set("title", user.title)
                    .set("password", user.password)
                    .set("birth_day", user.birth_day)
                    .set("birth_month", user.birth_month)
                    .set("birth_year", user.birth_year)
                    .set("firstname", user.firstname)
                    .set("lastname", user.lastname)
                    .set("company", user.company)
                    .set("address1", user.address1)
                    .set("address2", user.address2)
                    .set("country", user.country)
                    .set("zipcode", user.zipcode)
                    .set("state", user.state)
                    .set("city", user.city)
                    .set("mobile_number", user.mobile_number);
            APIResponse response = request.post(baseUrl + "/api/createAccount", RequestOptions.create().setForm(form));
            if (response.status() != 200) {
                throw new AssertionError("Expected status 200, got " + response.status());
            }
            Map<String, Object> body = parseJson(response);
            if (!"201".equals(body.get("responseCode"))) {
                throw new AssertionError("Expected responseCode 201, got " + body.get("responseCode"));
            }
            String actual = body.getOrDefault("message", "").toString();
            if (!actual.contains("User created!")) {
                throw new AssertionError("User creation message not found.");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void verifyUserExists(String email, String password) {
        try {
            FormData form = FormData.create()
                    .set("email", email)
                    .set("password", password);
            APIResponse response = request.post(baseUrl + "/api/verifyLogin", RequestOptions.create().setForm(form));
            if (response.status() != 200) {
                throw new AssertionError("Expected status 200, got " + response.status());
            }
            Map<String, Object> body = parseJson(response);
            String actual = body.getOrDefault("message", "").toString();
            if (!"200".equals(body.get("responseCode"))) {
                throw new AssertionError("Expected responseCode 200, got " + body.get("responseCode"));
            }
            if (!actual.contains("User exists!")) {
                throw new AssertionError("User exists message not found.");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void verifyUserDoesNotExist(String email, String password) {
        try {
            FormData form = FormData.create()
                    .set("email", email)
                    .set("password", password);
            APIResponse response = request.post(baseUrl + "/api/verifyLogin", RequestOptions.create().setForm(form));
            if (response.status() != 200) {
                throw new AssertionError("Expected status 200, got " + response.status());
            }
            Map<String, Object> body = parseJson(response);
            if (!"404".equals(body.get("responseCode"))) {
                throw new AssertionError("Expected responseCode 404, got " + body.get("responseCode"));
            }
            String actual = body.getOrDefault("message", "").toString();
            if (!actual.contains("User not found!")) {
                throw new AssertionError("User not found message not found.");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void readUser(String email) {
        APIResponse response = request.get(baseUrl + "/api/getUserDetailByEmail", RequestOptions.create().setQueryParam("email", email));
        if (response.status() != 200) {
            throw new AssertionError("Expected status 200, got " + response.status());
        }
        Map<String, Object> body = parseJson(response);
        if (!"200".equals(body.get("responseCode"))) {
            throw new AssertionError("Expected responseCode 200, got " + body.get("responseCode"));
        }
    }

    public void updateUser(String email, String password, Map<String, String> userUpdates) {
        try {
            FormData form = FormData.create();
            for (Map.Entry<String, String> entry : userUpdates.entrySet()) {
                form.set(entry.getKey(), entry.getValue());
            }
            form.set("email", email);
            form.set("password", password);
            APIResponse response = request.put(baseUrl + "/api/updateAccount", RequestOptions.create().setForm(form));
            if (response.status() != 200) {
                throw new AssertionError("Expected status 200, got " + response.status());
            }
            Map<String, Object> body = parseJson(response);
            if (!"200".equals(body.get("responseCode"))) {
                throw new AssertionError("Expected responseCode 200, got " + body.get("responseCode"));
            }
            String actual = body.getOrDefault("message", "").toString();
            if (!actual.contains("User updated!")) {
                throw new AssertionError("User updated message not found.");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteUser(String email, String password) {
        try {
            FormData form = FormData.create()
                    .set("email", email)
                    .set("password", password);
            APIResponse response = request.delete(baseUrl + "/api/deleteAccount", RequestOptions.create().setForm(form));
            if (response.status() != 200) {
                throw new AssertionError("Expected status 200, got " + response.status());
            }
            Map<String, Object> body = parseJson(response);
            if (!"200".equals(body.get("responseCode"))) {
                throw new AssertionError("Expected responseCode 200, got " + body.get("responseCode"));
            }
            String actual = body.getOrDefault("message", "").toString();
            if (!actual.contains("Account deleted!")) {
                throw new AssertionError("Account deleted message not found.");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}