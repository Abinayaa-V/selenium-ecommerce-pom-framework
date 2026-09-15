@Login
Feature: User Login

    Scenario: Login with valid registered user

    Given I landed on Ecommerce Page
    When I register a new user
    And I logout from the application
    And I login with the registered user
    Then I should be logged in successfully

    Scenario: Login with incorrect email and password

    Given I landed on Ecommerce Page
    When I register a new user
    And I logout from the application
    And I try to login with incorrect credentials
    Then I should see the incorrect login error
    And I login with the registered user

    Scenario: Logout user successfully

    Given I landed on Ecommerce Page
    When I register a new user and logout
    And I login with the registered user
    Then I should be logged in successfully
    When I logout from the application
    Then I should see the login page