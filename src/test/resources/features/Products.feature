@Products
Feature: Products

    Scenario: Verify products and product details page

    Given I landed on Ecommerce Page
    When I open the Products page
    Then I should see the Products page
    And I should see products listed
    When I open the first product
    Then I should see the Product Details page
    And the product details should be displayed

    Scenario: Search for a product

    Given I landed on Ecommerce Page
    When I open the Products page
    Then I should see the Products page
    When I search for a product named "Top"
    Then I should see the "SEARCHED PRODUCTS" title
    And I should see search results

    Scenario: Verify subscription on Home page

    Given I landed on Ecommerce Page
    Then I should see the Home page
    When I scroll to the footer
    Then I should see the "SUBSCRIPTION" text
    When I subscribe with email "aj@gmail.com"
    Then I should see the subscription success message

    Scenario: Verify subscription on Cart page

    Given I landed on Ecommerce Page
    Then I should see the Home page
    When I open the Cart page
    When I scroll to the footer
    Then I should see the "SUBSCRIPTION" text
    When I subscribe with email "aj@gmail.com"
    Then I should see the subscription success message

    Scenario: Add products to cart

    Given I landed on Ecommerce Page
    Then I should see the Home page
    When I open the Products page
    When I add the first product to the cart
    And I continue shopping
    When I add the second product to the cart
    And I view the cart
    Then I should see 2 products in the cart
    And the cart product details should be correct

    Scenario: Verify product quantity in cart

    Given I landed on Ecommerce Page
    Then I should see the Home page
    When I open the third featured product
    And I set the product quantity to "4"
    And I add the product to the cart
    And I view the cart
    Then the cart product quantity should be "4"

    Scenario: Remove products from cart

    Given I landed on Ecommerce Page
    Then I should see the Home page
    When I add product 7 to the cart and remember it
    And I continue shopping
    When I add product 4 to the cart
    And I continue shopping
    When I open the Cart page
    Then I should be on the Cart page
    And I should see the selected product in the cart
  When I remove the selected product from the cart
  Then I should not see the selected product in the cart
    When I remove all products from the cart

    Scenario: Verify category filter from left sidebar
  Given I landed on Ecommerce Page
  Then I should see the Home page
  And I should see the category sidebar
  When I select the "Women" category
  And I select the "Dress" subcategory under "Women"
  Then I should see the "WOMEN - DRESS PRODUCTS" title
  When I select the "Men" category
  And I select the "Tshirts" subcategory under "Men"
  Then I should see the "MEN - TSHIRTS PRODUCTS" title

  Scenario: Verify brand filter from left sidebar
  Given I landed on Ecommerce Page
  When I open the Products page
  Then I should see the brand sidebar
  When I select the "Polo" brand
  Then I should see the "BRAND - POLO PRODUCTS" brand title
  When I select the "H&M" brand
  Then I should see the "BRAND - H&M PRODUCTS" brand title