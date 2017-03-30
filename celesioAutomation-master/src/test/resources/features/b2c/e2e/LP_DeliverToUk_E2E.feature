@b2c @b2c_e2e_deliverToUk
Feature: E2E Place Order,Pay via Card Payments with Delivery to Uk Option, Vat Relief scenarios, and new Billing address

  Scenario Outline: TC01. E2E Place Order with vat relief,pay with Different Card Types except Maestro using Delivery to Uk Option
    Given The user is on Lloyds Pharmacy home page
    Given The user navigates to Registration page
    When The user enters all the relevant details on the registration page
      | generateRandomData | true |
    And The user clicks on "Create Account" on registration page
    When The user inputs "Grab" in the Search text box
    And The user clicks the Search button
    When The user clicks Add to basket for "Betterlife Superior Adjustable Suction Grab Rails Medium" on the PLP
    And The user clicks mini shop cart
    When The user clicks Secure Checkout button on shopcart page
    Then the VatRelief option is selected "true" for option "<vatReliefFor>"
    Then The "Delivery options" page opens
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
    And select on preferred delivery method
      | preferredDeliveryMethod | <preferredDelivery> |
    And input delivery instructions
      | deliveryInstructions | Thank you |
    When I click on Continue to payment button
    Then the Payment page opens
    Given I enter payment card details for card type "<cardType>"
    When I submit order details and confirm terms
    And I complete secure verification for card type "<cardType>"
    Then the final order confirmation page is displayed

    Examples: 
      | preferredDelivery | vatReliefFor        | cardType |
      | Standard Delivery | Me                  | visa     |
      | Next Day Delivery | SomeoneElse         | master   |
      | Standard Delivery | UKRegisteredCharity | visa3d   |

  Scenario Outline: TC02. Registered user E2E Place Order: Pay with Maestro Card Type using Next Day and New Billing Address
    Given The user is on Lloyds Pharmacy home page
    Given The user navigates to Registration page
    When The user enters all the relevant details on the registration page
      | generateRandomData | true |
      | addressType        | home |
    And The user clicks on "Create Account" on registration page
    When The user inputs "<searchCriteria1>" in the Search text box
    And The user clicks the Search button
    When The user clicks Add to basket for "<productName1>" on the PLP
    And The user clicks mini shop cart
    When The user clicks Secure Checkout button on shopcart page
    Then The "Delivery options" page opens
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
    And Input "Registered User" detail information for delivery
      | firstName    | testFirstName |
      | lastName     | testLastName  |
      | emailAddress | test@mail.com |
      | phoneNumber  | 11111112222   |
    And select on valid delivery address
      | chooseAddress | test@mail.com |
    And select on preferred delivery method
      | preferredDeliveryMethod | <preferredDelivery> |
    And input delivery instructions
      | deliveryInstructions | Thank you |
    When I click on Continue to payment button
    Then the Payment page opens
    Given I enter payment card details for card type "<cardType>"
    When I "Add New" billing address with name "work"
    Then the payment screen contains "work" billing address details
    #Refill Card Details as it is not stored
    Given I enter payment card details for card type "<cardType>"
    When I submit order details and confirm terms
    Then the final order confirmation page is displayed
    And the order confirmation has "<preferredDelivery>" and shipping address as "home" for "<productName1>"

    Examples: 
      | searchCriteria1 | productName1                 | preferredDelivery | cardType |
      | Skineffect      | SKINeffect Body Lotion 200ml | Standard Delivery | maestro  |
      | Skineffect      | SKINeffect Body Lotion 200ml | Standard Delivery | visa     |

  Scenario Outline: TC03. Guest user E2E Place Order: Pay with Maestro Card using standard delivery and New Billing Address
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "<searchCriteria1>" in the Search text box
    And The user clicks the Search button
    When The user clicks Add to basket for "<productName1>" on the PLP
    And The user clicks mini shop cart
    When The user clicks Secure Checkout button on shopcart page
    Given The user clicks "Checkout Now" button on CheckOut SignIn page
    Then The "Delivery options" page opens
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
    And Input "Guest User" detail information for delivery
      | firstName    | testFirstName  |
      | lastName     | testLastName   |
      | emailAddress | test@mail.com  |
      | phoneNumber  | 11111112222    |
      | postCode     | M2 5BQ         |
      | address1     | streetABC      |
      | address2     | streetDEF      |
      | city         | CityABC        |
      | state        | countyABC      |
      | country      | United Kingdom |
    And select on preferred delivery method
      | preferredDeliveryMethod | <preferredDelivery> |
    And input delivery instructions
      | deliveryInstructions | Thank you |
    When I click on Continue to payment button
    Then the Payment page opens
    Given I enter payment card details for card type "<cardType>"
    When I choose "<guestBillingAddress>" billing address for guest user on payment details
      | firstName | billFirstName   |
      | lastName  | billLastName    |
      | postCode  | SL1 3TE         |
      | address1  | guest bill add1 |
      | address2  | guest bill add2 |
      | city      | guestCity       |
      | state     |                 |
    When I submit order details and confirm terms
    Then the final order confirmation page is displayed
    And the order confirmation has "<preferredDelivery>" and shipping address as "guest" for "<productName1>"

    Examples: 
      | searchCriteria1 | productName1                 | preferredDelivery | cardType | guestBillingAddress |
      | skineffect      | SKINeffect Body Lotion 200ml | Standard Delivery | maestro  | SAME                |
      | skineffect      | SKINeffect Body Lotion 200ml | Standard Delivery | maestro  | New                 |
      | skineffect      | SKINeffect Body Lotion 200ml | Standard Delivery | visa     | New                 |

  Scenario: TC04. Reorder with mixed product quantity and multiple payment type: visa and master card
    Given The user is on Lloyds Pharmacy home page
    Given The user navigates to Registration page
    When The user enters all the relevant details on the registration page
      | generateRandomData | true |
    And The user clicks on "Create Account" on registration page
    Then The user should be navigated to "My Account" details page
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
    And input delivery instructions
      | deliveryInstructions | Thank you |
    When I click on Continue to payment button
    Then the Payment page opens
    Given I enter payment card details for card type "visa"
    When I submit order details and confirm terms
    Then the final order confirmation page is displayed
    And the order confirmation has "Standard Delivery" and shipping address as "home" for "SKINeffect Body Lotion 200ml"
    #REORderFlow
    When I navigate to My Accounts Page
    Then The user should be navigated to "My Account" details page
    Given I click "Order History" from  My Account LHS sub menu
    When I click  "ReOrder" for the current order on My Accounts page
    Then the Order History Page Item Details consists of
      | itemName                     | inStock  | itemQuantity | eachItemPrice | totalPrice | orderSubTotal |
      | SKINeffect Body Lotion 200ml | In stock | 1            | £7.99         | £7.99      | £0.00         |
    Given I update the reorder quantity to "2"
    Given I select the the item to Order on ReOrder Page
    Then the Order History Page Item Details consists of
      | itemName                     | inStock  | itemQuantity | eachItemPrice | totalPrice | orderSubTotal |
      | SKINeffect Body Lotion 200ml | In stock | 2            | £7.99         | £15.98     | £15.98        |
    When I click on Add to basket on ReOrder Page
    When The user clicks Secure Checkout button on shopcart page
    Then The "Delivery options" page opens
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
    And select on preferred delivery method
      | preferredDeliveryMethod | Next Day Delivery |
    And input delivery instructions
      | deliveryInstructions | Thank you |
    When I click on Continue to payment button
    Then the Payment page opens
    Given I enter payment card details for card type "master"
    When I submit order details and confirm terms
    Then the final order confirmation page is displayed
    And the order confirmation has "Next Day Delivery" and shipping address as "home" for "SKINeffect Body Lotion 200ml"
