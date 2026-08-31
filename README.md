# Selenium Ecommerce POM Framework

A UI automation framework built using Selenium WebDriver, Java, TestNG, Maven, and Page Object Model (POM) design pattern.

## Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- WebDriverManager
- Extent Reports
- Git/GitHub

## Framework Features

- Page Object Model architecture
- Reusable page components and sections
- User flow abstraction for reusable business actions
- Explicit wait utilities
- Cross-browser execution support
- Configurable browser selection
- Data-driven testing using external JSON test data with TestNG DataProvider
- JSON data parsing
- TestNG listeners for execution monitoring and failure handling
- Extent Reports with automatic failure screenshots
- TestNG Retry Analyzer for retrying failed test executions
- TestNG-based test execution

## Test Coverage

The framework automates end-to-end e-commerce scenarios including:

- User registration
- Login with valid credentials
- Login with invalid credentials
- Logout functionality
- Existing email validation during registration
- Contact us form submission
- Product listing and product details validation
- Product search
- Subscription functionality
- Add products to cart
- Product quantity validation in cart
- Remove products from cart
- Category filtering
- Brand filtering
- Checkout flow
- Order placement

## Project Structure
The framework follows a Maven standard directory structure. Java classes are organized under the `automation.ecommerce` package.

```text
src
├── main
│   ├── java
│   │   └── automation.ecommerce
│   │       ├── abstractComponents
│   │       ├── components
│   │       ├── flows
│   │       ├── listeners
│   │       ├── models
│   │       ├── pages
│   │       ├── reports
│   │       ├── sections
│   │       └── utils
│   │
│   └── resources
│       └── GlobalData.properties
│
└── test
    ├── java
    │   └── automation.ecommerce
    │       ├── base
    │       ├── data
    │       └── tests
    │
    └── resources
        ├── data
        │   └── users.json
        └── test-files
            └── sampleFile.png
```

## Design Approach

The framework follows the Page Object Model design pattern:

- Each web page has a dedicated Page Object class.
- Common UI sections are implemented as reusable components.
- Business workflows are separated into flow classes.
- Utility classes handle reusable functionality such as waits and configuration.

## Test Execution

Run all tests using Maven:

```bash
mvn test

