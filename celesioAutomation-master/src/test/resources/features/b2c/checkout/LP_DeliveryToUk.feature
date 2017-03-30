@b2c @b2c_deliveryToUk
Feature: Check Delivery TO Uk Option

  Background: navigate to checkout signin page with both C&C and delivery to UK option
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "skineffect" in the Search text box
    And The user clicks the Search button
    When The user clicks "SKINeffect Body Lotion 200ml" on the PLP
    And The user clicks Add to basket button
    And The user clicks mini shop cart

    When The user clicks Secure Checkout button on shopcart page
    Then The CheckOut SignIn page opens
    Then The user inputs "b2c.username" and "b2c.password" as logon ID and password on CheckOut SignIn page

   #PartII: Delivery to UK address
  Scenario: TC07. Registered user input invalid and empty delivery information and check whether error message is displayed
    Given The user clicks "Sign In" button on CheckOut SignIn page
    Then The "Delivery options" page opens
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
    And Input "Registered User" detail information for delivery
      | firstName    | 123invalid     |
      | lastName     | 456invalid     |
      | emailAddress | 123_456invalid |
      | phoneNumber  |                |
    And select on valid delivery address
      | chooseAddress | test@mail.com |
    And select on preferred delivery method
      | preferredDeliveryMethod | Standard Delivery |
    Then Validate error message details in delivery option page
      | The first name field should be Alphabetic |
      | The last name field should be Alphabetic  |
      | Please enter a valid email address        |
      | Please enter a valid phone number         |

  #The Edit functionality of Address from Checkout is removed, hence the below test is no longer valid, keeping this test as backup
  @b2c_blocked
  Scenario: TC08. Registered user input valid delivery information and edit valid delivery address to save
    Given The user clicks "Sign In" button on CheckOut SignIn page
    Then The "Delivery options" page opens
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
    And Input "Registered" detail information for delivery
      | firstName    | test          |
      | lastName     | test          |
      | emailAddress | test@mail.com |
      | phoneNumber  | 11111112222   |
    And Click on "Edit Address" and create or edit delivery address
      | firstName | testABC        |
      | lastName  | testABC        |
      | zipCode   | M2 5BQ         |
      | address1  | streetABC      |
      | address2  | streetDEF      |
      | city      | CityABC        |
      | state     | countyABC      |
      | country   | United Kingdom |
    When Click on "Save address" in the delivery address
    Then Check the "Deliver to UK address" text table and form in should display
    And select on preferred delivery method
      | preferredDeliveryMethod | Standard Delivery |
    When I click on Continue to payment button
    Then the Payment page opens

    # Incident #82045, The preferred delivery option field is not displayed for a guest user
  Scenario: TC13. Check whether guest user can input valid delivery information and go to payment page
    Given The user enter delivery option page with guest user
    Then The "Delivery options" page opens
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
    And Input "Guest User" detail information for delivery
      | firstName    | testFirstName |
      | lastName     | testLastName  |
      | emailAddress | test@mail.com |
      | phoneNumber  | 11111112222   |
      | postCode     | M2 5BQ        |
      | address1     | abcd          |
      | address2     | abcd          |
      | city         | London        |
      | state        | ENG           |
    And select on preferred delivery method
      | preferredDeliveryMethod | Standard Delivery |
    And input delivery instructions
      | deliveryInstructions | Thank you |
    When I click on Continue to payment button
    Then the Payment page opens


  Scenario: TC15. Check a guest user inputs invalid delivery information and check whether error message comes
    Given The user enter delivery option page with guest user
    Then The "Delivery options" page opens
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
    And Input "Guest User" detail information for delivery
      | firstName    | invalid123 |
      | lastName     | invalid123 |
      | emailAddress | invalid123 |
      | phoneNumber  | invalid123 |
      | postCode     | invalid123 |
      | address1     |            |
      | address2     |            |
      | city         |            |
      | state        |            |
#    And select on preferred delivery method
#      | preferredDeliveryMethod | Standard Delivery |
    And input delivery instructions
      | deliveryInstructions | Thank you |
    Then Validate error message details in delivery option page
      | The first name field should be Alphabetic |
      | The last name field should be Alphabetic  |
      | Please enter a valid email address        |
      | Please enter a valid phone number         |
      | Please enter a valid postcode             |

  Scenario: TC30. Check a guest user with mixed basket can input valid delivery UK address info and go to payment
    Given The user is on Lloyds Pharmacy home page without delete cookies
    When The user inputs "haircare" in the Search text box
    And The user clicks the Search button
    And The user clicks "Haircare - Hair Pony-Brown" on the PLP
    And The user clicks Add to basket button
    And The user clicks mini shop cart
    When The user clicks Secure Checkout button on shopcart page
    Then The CheckOut SignIn page opens
    When The user enter delivery option page with guest user
    Then The "Delivery options" page opens
    When The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
    And Input "Guest User" detail information for delivery
      | firstName    | testFirstName  |
      | lastName     | testLastName   |
      | emailAddress | test@mail.com  |
      | phoneNumber  | 11111112222    |
      | zipCode      | test@mail.com  |
      | postCode     | M2 5BQ         |
      | address1     | streetABC      |
      | address2     | streetDEF      |
      | city         | CityABC        |
      | state        | countyABC      |
      | country      | United Kingdom |
    And select on preferred delivery method
      | preferredDeliveryMethod | Standard Delivery |
    And input delivery instructions
      | deliveryInstructions | Thank you |
    When I click on Continue to payment button
    Then the Payment page opens

