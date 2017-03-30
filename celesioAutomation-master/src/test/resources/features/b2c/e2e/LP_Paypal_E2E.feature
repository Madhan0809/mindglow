@b2c @b2c_paypal
Feature: E2E Place Order, via Paypal

  Scenario: E2E place order: Paypal with guest user with C&C delivery option(Using find store option)
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "skineffect" in the Search text box
    And The user clicks the Search button
    When The user clicks "SKINeffect Body Lotion 200ml" on the PLP
    And The user clicks Add to basket button
    And The user clicks mini shop cart
    When The user clicks Secure Checkout button on shopcart page
    Then The CheckOut SignIn page opens
    Given The user enter delivery option page with guest user
    Then The "Delivery options" page opens
    When The user selects "Click and Collect" delivery radio button
    Then Check the "Click and Collect" text table and form in should display
    When Input "C&C" click and collect information for delivery
      | postCode | london |
    And Click on Find Address button in "Click and collect" delivery option
    Then Check user enter store locator lister page
    When User click store "A" from locator map
    And User select store "A" from google map
    Then The store collection point is "displayed" in C&C from delivery page
    When Input "C&C" click and collect information for delivery
      | firstName    | auto          |
      | lastName     | guest         |
      | emailAddress | test@mail.com |
      | phoneNumber  | 11111112222   |
    And I click on Continue to payment button
    Then the Payment page opens
    Given I submit order using Paypal Payment Option
    When I enter payPalUsername "paypal.username1" and payPalPassword "paypal.password1"
    Then the delivery address is firstName "auto" lastName "guest" and addressType "store.london" on paypal review screen
    And i click on continue with PayPal Balance
    Then the final order confirmation page is displayed

  Scenario: E2E Place Order: Paypal with registered order with C&C delivery option(Using find store option)
    Given The user is on Lloyds Pharmacy home page
    Given The user navigates to Registration page
    When The user enters all the relevant details on the registration page
      | generateRandomData | true |
    And The user clicks on "Create Account" on registration page
    When The user inputs "skineffect" in the Search text box
    And The user clicks the Search button
    When The user clicks Add to basket for "SKINeffect Body Lotion 200ml" on the PLP
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
    Given I submit order using Paypal Payment Option
    When I enter payPalUsername "paypal.username1" and payPalPassword "paypal.password1"
    And i click on continue with PayPal Balance
    Then the final order confirmation page is displayed

  Scenario: E2E Place Order: Paypal with registered order with delivery to UK option
    Given The user is on Lloyds Pharmacy home page
    Given The user navigates to Registration page
    When The user enters all the relevant details on the registration page
      | generateRandomData | true |
    And The user clicks on "Create Account" on registration page
    When The user inputs "skineffect" in the Search text box
    And The user clicks the Search button
    When The user clicks Add to basket for "SKINeffect Body Lotion 200ml" on the PLP
    And The user clicks mini shop cart
    When The user clicks Secure Checkout button on shopcart page
    Then The "Delivery options" page opens
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
    And select on preferred delivery method
      | preferredDeliveryMethod | Standard Delivery |
    When I click on Continue to payment button
    Then the Payment page opens
    Given I submit order using Paypal Payment Option
    When I enter payPalUsername "paypal.username1" and payPalPassword "paypal.password1"
    And i click on continue with PayPal Balance
    Then the final order confirmation page is displayed
    And the order confirmation has "Standard Delivery" and shipping address as "home" for "SKINeffect Body Lotion 200ml"
