@ContactUs
Feature: Contact Us

  Scenario: Submit Contact Us form successfully

    Given I landed on Ecommerce Page
    When I open the Contact Us page
    Then I should see the Get In Touch message
    And I fill the Contact Us form
    And I upload a sample file
    And I submit the Contact Us form
    Then I should see the contact form success message
    And I should be on the Home page