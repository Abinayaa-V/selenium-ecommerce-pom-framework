# Selenium Ecommerce POM Framework

A UI automation framework built using Selenium WebDriver, Java, TestNG, Maven, and Page Object Model (POM) design pattern.

## Tech Stack

- Java
- Selenium WebDriver
- Cucumber
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
- Data-driven testing using external JSON test data
- JSON data parsing
- Cucumber BDD feature files written in Gherkin
- Reusable Cucumber step definitions
- TestNG integration for Cucumber execution
- Cucumber HTML execution report
- TestNG listeners for execution monitoring and failure handling
- Extent Reports with automatic failure screenshots
- TestNG Retry Analyzer for retrying failed test executions

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

selenium-ecommerce-pom-framework/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── automation/
│   │   │       └── ecommerce/
│   │   │           ├── abstractComponents/
│   │   │           ├── components/
│   │   │           ├── flows/
│   │   │           ├── listeners/
│   │   │           ├── models/
│   │   │           ├── pages/
│   │   │           ├── reports/
│   │   │           ├── sections/
│   │   │           └── utils/
│   │   └── resources/
│   │       └── GlobalData.properties
│   │
│   └── test/
│       ├── java/
│       │   └── automation/
│       │       └── ecommerce/
│       │           ├── base/
│       │           ├── data/
│       │           ├── runner/
│       │           │   └── TestNGTestRunner.java
│       │           └── steps/
│       │               └── LoginSteps.java
│       │
│       └── resources/
│           ├── data/
│           │   └── users.json
│           ├── features/
│           │   ├── Checkout.feature
│           │   ├── ContactUs.feature
│           │   ├── Login.feature
│           │   ├── Products.feature
│           │   ├── Registration.feature
│           │   └── TestCases.feature
│           └── test-files/
│               └── sampleFile.png

```

## BDD Approach

The test automation layer uses Cucumber and Gherkin to describe application behavior in a readable format.

- Feature files contain business-readable test scenarios.
- Step definitions connect Gherkin steps to Java automation code.
- Page Objects encapsulate page-specific UI interactions.
- Reusable components and sections encapsulate common UI elements.
- User flow classes encapsulate reusable business workflows.
- TestNG is used to execute Cucumber scenarios.
- Cucumber tags are used to organize and selectively execute scenarios.

## Test Execution

Run all tests using Maven:

```bash
mvn test
```

Cucumber HTML results are generated at:

```bash
target/cucumber.html
```

## Browser Selection

The browser can be configured through GlobalData.properties or overridden from the command line.

Example:

```bash
mvn test -Dbrowser=chrome
```

Supported browsers:

- Chrome
- Firefox
- Edge
- Safari

