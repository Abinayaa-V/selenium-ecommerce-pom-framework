@Checkout
Feature: Checkout

  Scenario: Register while checking out and place an order

    Given I landed on Ecommerce Page
    Then I should see the Home page
    When I add product 4 to the cart
    And I continue shopping
    When I add product 5 to the cart
    And I view the cart
    When I proceed to checkout as a new user
    And I register a new user
    Then I should see the logged in username
    When I proceed to checkout
    Then I should see the address details
    And I should see the review order section
    When I enter the order comment "Please deliver after 6 PM."
    And I place the order
    And I enter the payment details
    And I confirm the order
    Then I should see the order confirmation message
    When I continue after the order

    Scenario: Register before checkout and place an order

    Given I landed on Ecommerce Page
    Then I should see the Home page
    When I register a new user
    Then I should see the logged in username
    When I add product 7 to the cart
    And I continue shopping
    When I add product 2 to the cart
    And I view the cart
    Then I should be on the Cart page
    When I proceed to checkout
    Then I should see the address details
    And I should see the review order section
    When I enter the order comment "Please deliver on Saturday."
    And I place the order
    And I enter the payment details
    And I confirm the order
    Then I should see the order confirmation message
    When I continue after the order

    Scenario: Login before checkout and place an order

    Given I landed on Ecommerce Page
    When I register a new user and logout
    And I login with the registered user
    Then I should see the logged in username
    When I add product 7 to the cart
    And I continue shopping
    When I add product 4 to the cart
    And I continue shopping
    When I add product 2 to the cart
    And I continue shopping
    When I open the Cart page
    Then I should be on the Cart page
    When I proceed to checkout
    Then I should see the address details
    And I should see the review order section
    When I place the order
    And I enter the payment details
    And I confirm the order
    Then I should see the order confirmation message
    When I continue after the order