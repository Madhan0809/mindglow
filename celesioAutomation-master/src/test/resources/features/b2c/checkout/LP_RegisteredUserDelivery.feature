@b2c @b2c_registeredUserDelivery
Feature: Check for Newly Registered user Delivery options

  Background: Navigate to checkout signin page with both C&C and delivery to UK option
    Given The user is on Lloyds Pharmacy home page
    Given The user navigates to Registration page
    When The user enters all the relevant details on the registration page
      | GenerateRandomData | true |
    And The user clicks on "Create Account" on registration page
    Then The user should be navigated to "My Account" details page
    #  Given The user is on "Beauty & Fragrance > Accessories" PLP
    When The user inputs "skineffect" in the Search text box
    And The user clicks the Search button
    When The user clicks "SKINeffect Body Lotion 200ml" on the PLP
    And The user clicks Add to basket button
    And The user clicks mini shop cart
    When The user clicks Secure Checkout button on shopcart page

  Scenario: TC01. Registered user edit valid delivery address and press back to abandon the change
    Then The "Delivery options" page opens
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
    And Input "Registered" detail information for delivery
      | firstName    | test          |
      | lastName     | test          |
      | emailAddress | test@mail.com |
      | phoneNumber  | 11111112222   |
    And Click on "Add a new address" and create or edit delivery address
      | nickName  | test           |
      | firstName | testABC        |
      | lastName  | testABC        |
      | zipCode   | test@mail.com  |
      | address1  | streetABC      |
      | address2  | streetDEF      |
      | city      | CityABC        |
      | state     | countyABC      |
      | country   | United Kingdom |
    When Click on "Back" in the delivery address
    Then Check the "Deliver to UK address" text table and form in should display
    And select on preferred delivery method
      | preferredDeliveryMethod | Standard Delivery |
    When I click on Continue to payment button
    Then the Payment page opens

  Scenario: TC02. Default delivery option, and header and footer can be displayed correctly
    Then The "Delivery options" page opens
    And The header information of delivery page is displayed
    And The footer information of delivery page is displayed
    When The user selects "Click and Collect" delivery radio button
    And Input "Registered" click and collect information for delivery
      | postCode     |         |
      | emailAddress | invalid |
      | phoneNumber  | invalid |
    Then Validate error message details in delivery option page
      | Please enter a valid Postcode or Town name |
      | Please enter a valid email address         |
      | Please enter a valid phone number          |

  Scenario: TC03. Check a registered user can get correct order summary information
    Then The "Delivery options" page opens
    Given Get the information of order summary
    Then Check the result of order summary is correct
      | VAT Relief | £0.00 |
      | Discount   | £0.00 |
      | Total      | £7.99 |

  Scenario: TC04. Registered user add invalid delivery address and check on error information
    Then The "Delivery options" page opens
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
    And Input "Registered" detail information for delivery
      | firstName    | test_ABC      |
      | lastName     | test_DEF      |
      | emailAddress | test@mail.com |
      | phoneNumber  | 11111112222   |
    And Click on "Add a new address" and create or edit delivery address
      | nickName  | invalid123 |
      | firstName | invalid123 |
      | lastName  | invalid123 |
      | zipCode   | invalid123 |
      | address1  |            |
      | address2  |            |
      | city      |            |
      | state     |            |
    When Click on "Save address" in the delivery address
    Then Validate error message details in delivery option page
      #bug as address type field validation has been changed from alpha to alpha numeric
      | Please enter an alpha value               |
      | The first name field should be Alphabetic |
      | The last name field should be Alphabetic  |
      | Please enter a valid postcode             |
      | The street address field cannot be empty  |
      | The town or city field cannot be empty    |

  Scenario: TC05. Check a registered user with mixed basket which is eligible for C&C can get C&C and 'delivery to UK' Delivery option
    Given The user is on Lloyds Pharmacy home page without delete cookies
    When The user inputs "skineffect" in the Search text box
    And The user clicks the Search button
    When The user clicks "SKINeffect Anti-Ageing Face & Eye Serum 15ml" on the PLP
    And The user clicks Add to basket button
    And The user clicks mini shop cart
    When The user clicks Secure Checkout button on shopcart page
    #    Then the VatRelief option is selected "true"
    Then The "Delivery options" page opens
    Given The user selects "Click and Collect" delivery radio button
    Then Check the "Click and Collect" text table and form in should display
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display

  Scenario: TC06. Check a registered user with mixed basket can get correct order detail & summary information
    Given The user is on Lloyds Pharmacy home page without delete cookies
    When The user inputs "haircare" in the Search text box
    And The user clicks the Search button
    When The user clicks "Haircare - Hair Pony-Brown" on the PLP
    And The user clicks Add to basket button
    And The user clicks mini shop cart
    When The user clicks Secure Checkout button on shopcart page
    Then The "Delivery options" page opens
    Then Check the result of order detail information is correct
      | SKINeffect Body Lotion 200ml | QTY: 1 | £7.99 |
      | Haircare - Hair Pony-Brown   | QTY: 1 | £1.99 |
    Given The user selects "Click and Collect" delivery radio button
    And Get the information of order summary
    Then Check the result of order summary is correct
      | Total | £9.98 |

  Scenario: TC07. Check a single product when the C&C is not available for delivery option
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Summer Floral Invigorating Essentials Gift Set" in the Search text box
    And The user clicks the Search button
    And The user clicks "Summer Floral Invigorating Essentials Gift Set" on the PLP
    And The user clicks Add to basket button
    And The user clicks mini shop cart
    When The user clicks Secure Checkout button on shopcart page
    When The user enter delivery option page with guest user
    Then The "Delivery options" page opens
    And The "Click and Collect" option is not available in delivery option page
    When The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
