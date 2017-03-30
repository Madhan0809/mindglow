@b2c @b2c_e2e_CandC
Feature: E2E Place Order, Card Payments with click and collect Option

  Scenario Outline: E2E Place Order, with Different Card Types except Maestro click and collect Option
    Given The user is on Lloyds Pharmacy home page
    Given The user navigates to Registration page
    When The user enters all the relevant details on the registration page
      | generateRandomData | true |
    And The user clicks on "Create Account" on registration page
    When The user inputs "<searchCriteria1>" in the Search text box
    And The user clicks the Search button
    When The user clicks Add to basket for "<productName1>" on the PLP
    And The user clicks mini shop cart
    When The user clicks Secure Checkout button on shopcart page
    Then The "Delivery options" page opens
    When The user selects "Click and Collect" delivery radio button
    Then Check the "Click and Collect" text table and form in should display
    When Input "C&C" click and collect information for delivery
      | postCode     | london        |
      | emailAddress | test@mail.com |
      | phoneNumber  | 11111112222   |
    And Click on Find Address button in "Click and collect" delivery option
    Then Check user enter store locator lister page
    When User click store "A" from locator map
    And User select store "A" from google map
    Then The store collection point is "displayed" in C&C from delivery page
    And I click on Continue to payment button
    Then the Payment page opens
    Given I enter payment card details for card type "<cardType>"
    When I submit order details and confirm terms
    And I complete secure verification for card type "<cardType>"
    Then the final order confirmation page is displayed

    Examples: 
      | searchCriteria1 | productName1             | cardType |
      | skineffect      | SKINeffect Body Lotion 200ml | visa     |
      | skineffect      | SKINeffect Body Lotion 200ml | visa3d   |
      | skineffect      | SKINeffect Body Lotion 200ml | master   |

  Scenario: E2E Place Order, with Maestro Card Type click and collect Option
    Given The user is on Lloyds Pharmacy home page
    Given The user navigates to Registration page
    When The user enters all the relevant details on the registration page
      | generateRandomData | true |
    And The user clicks on "Create Account" on registration page
    When The user inputs "skineffect" in the Search text box
    And The user clicks the Search button
    When The user clicks "SKINeffect Body Lotion 200ml" on the PLP
    And The user adds the same product "1" times to the basket
    And The user clicks mini shop cart
    When The user clicks Secure Checkout button on shopcart page
    Then The "Delivery options" page opens
    When The user selects "Click and Collect" delivery radio button
    Then Check the "Click and Collect" text table and form in should display
    When Input "C&C" click and collect information for delivery
      | postCode     | london        |
      | emailAddress | test@mail.com |
      | phoneNumber  | 11111112222   |
    And Click on Find Address button in "Click and collect" delivery option
    Then Check user enter store locator lister page
    When User click store "A" from locator map
    And User select store "A" from google map
    Then The store collection point is "displayed" in C&C from delivery page
    And I click on Continue to payment button
    Then the Payment page opens
    Given I enter payment card details for card type "maestro"
    When I submit order details and confirm terms
    Then the final order confirmation page is displayed
