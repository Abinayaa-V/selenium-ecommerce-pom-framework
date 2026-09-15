@Registration
Feature: User Registration

  Scenario: Register a new user successfully

    Given I landed on Ecommerce Page
    When I register a new user
    Then I should see the user logged in

  Scenario: Register with an existing email

    Given I landed on Ecommerce Page
    When I register a new user and logout
    And I try to register with the same email
    Then I should see the existing email error
    And I login with the registered user