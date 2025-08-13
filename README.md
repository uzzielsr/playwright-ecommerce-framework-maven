# Playwright Ecommerce Framework (Java + Maven)

This project is an end-to-end and API automation framework for [automationexercise.com](https://www.automationexercise.com/), built with Playwright for Java and Maven.

## Getting Started (for Beginners)

Follow these steps if this is your first time using the project:

1. **Clone the repository:**

   ```sh
   git clone <repo-url>
   cd playwright-ecommerce-framework-maven
   ```

2. **Install Java and Maven:**

   - Download and install [Java 17+](https://adoptium.net/) and [Maven 3.8+](https://maven.apache.org/download.cgi) if you don't have them.
   - Verify with:
     ```sh
     java -version
     mvn -version
     ```

3. **Install Java dependencies:**

   ```sh
   mvn clean install
   ```

4. **Set up environment variables:**

   - Copy and edit the `.env.prod`, `.env.qa`, and `.env.uat` files with your test data (users, passwords, etc.).

5. **Install Playwright browsers:**

   - Only the first time (or if you change machines):
     ```sh
     mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install --with-deps"
     ```
   - This downloads Chromium, Firefox, and WebKit for Playwright Java.

6. **Run the tests:**

   - For example, to run all tests in the prod environment (headless):
     ```sh
     mvn test -Dsurefire.printSummary=true -Denv=prod
     ```
   - To see the browser:
     ```sh
     mvn test -Dsurefire.printSummary=true -Denv=prod -Dheadless=false
     ```

7. **View reports:**
   - Open the file `target/surefire-reports/index.html` in your browser to see the results.

Done! You can now automate and run tests.

## Objectives

1. **Automate Login, Account Creation, and Checkout UI** - Full coverage of critical user flows
2. **Automate Login & User CRUD via API** - API tests for user operations
3. **Use API to support UI tests** - Efficient data setup and cleanup
4. **Learn and enjoy** - Explore modern automation with best practices

## Prerequisites

- **Java 17+**
- **Maven 3.8+**

## Installation

```sh
mvn clean install # Installs dependencies and compiles the project
```

## Running Tests

### Quick Commands

```sh
# Run all tests (headless by default)
mvn test -Dsurefire.printSummary=true

# Run all tests with browser visible
mvn test -Dsurefire.printSummary=true -Dheadless=false

# Run only UI tests
env=prod mvn test -Dsurefire.printSummary=true -Dtest=*UiTest

# Run only API tests
env=prod mvn test -Dsurefire.printSummary=true -Dtest=*ApiTest

# Run a specific test
mvn test -Dsurefire.printSummary=true -Dtest=LoginUiTest#loginWithValidCredentials
```

### Change Environment

```sh
# QA
env=qa mvn test -Dsurefire.printSummary=true
# UAT
env=uat mvn test -Dsurefire.printSummary=true
```

### Reports and Debug

```sh
# View HTML report (if configured)
open target/surefire-reports/index.html

# Step-by-step debug (browser visible)
mvn test -Dheadless=false -Dtest=LoginUiTest#loginWithValidCredentials
```

## Project Structure

```
playwright-ecommerce-framework-maven/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/automationexercise/
│   │           ├── api/           # API utilities
│   │           ├── locators/      # Environment selectors (by env)
│   │           ├── pages/         # Page Objects (UI flows)
│   │           ├── utils/         # Utilities and helpers
│   │           └── listeners/     # Custom JUnit listeners
│   └── test/
│       └── java/
│           └── com/automationexercise/
│               └── tests/
│                   ├── api/       # API test classes
│                   └── ui/        # UI test classes
├── test.sh                        # Script to run tests with pretty output
├── pom.xml                        # Maven dependencies and configuration
├── .env.prod                      # Production environment variables
├── .env.qa                        # QA environment variables
├── .env.uat                       # UAT environment variables
├── README.md                      # This file
```

Key files:

- `RunWithDisplayNameListener.java`: Custom JUnit Platform launcher for pretty output
- `MinimalConsoleListener.java`: Listener for Playwright-style output
- `test.sh`: Main script to run tests with minimal, colored output

## Features

- **Multi-environment support** (`prod`, `qa`, `uat`) with dedicated `.env` files
- **UI/API separation**
- **Page Object Model (POM)**
- **Dynamic selectors by environment**
- **Multi-browser support** (Chromium, Firefox, WebKit)
- **Utilities for data generation and user management**
- **Advanced reports** (Surefire, HTML, screenshots)
- **API integration to support UI tests**
- **100% Java and Maven**

## Pretty Output & Custom Test Runner

- All test output is minimal and Playwright-style: colored ✔/✘, test group and test names, and only the main error message.
- Uses a custom JUnit Platform launcher (`RunWithDisplayNameListener`) and listener (`MinimalConsoleListener`) for pretty output.
- Run tests with the included `test.sh` script for the best experience:
  ```sh
  ./test.sh                # Runs all tests with pretty output
  ./test.sh --select-class com.automationexercise.tests.ui.LoginUiTest
  ./test.sh --select-method com.automationexercise.tests.ui.LoginUiTest#shouldLoginSuccessfullyWithValidCredentials
  ```
- All code, comments, and error messages are in English for full consistency.

## Test Coverage

### UI

- **Login** (`tests/ui/LoginUiTest.java`)
- **Signup** (`tests/ui/SignupUiTest.java`)
- **Checkout** (`tests/ui/CheckoutUiTest.java`)

### API

- **Login API** (`tests/api/LoginApiTest.java`)
- **User CRUD API** (`tests/api/UserCrudApiTest.java`)

### Page Objects

- **LoginPage**
- **SignupPage**
- **CartPage**
- **CheckoutPage**
- **PLP/PDP**

---

> Inspired by the Playwright + TypeScript framework, adapted to Java + Maven for maximum robustness and flexibility.