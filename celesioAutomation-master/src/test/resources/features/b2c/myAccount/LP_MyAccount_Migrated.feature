@b2c_account @b2c @b2c_migrated
Feature: Check My Accounts page for Migrated User

  Scenario: As a migrated registered user Check the LHS Menu on My accounts page
    Given The user is on Lloyds Pharmacy home page
    When The user clicks Sign In button
    Then The user inputs "b2c.username.migrated1" and "b2c.password.migrated1" as logon ID and password on CheckOut SignIn page
    When The user clicks "Sign In" button on login page
    Then The user should be navigated to "My Account" details page
    Then the LHS menu on My Account consists of
      | Settings      |
      | Orders        |
      | Prescriptions |
      | Coupons       |
    Then the LHS sub menu on My Account consists of
      | Personal Information           |
      | Address/Collection Preferences |
      | Partnership Programmes         |
      | Order History                  |
      | One Off Prescriptions          |
      | Repeat Prescriptions           |
      | Coupons                        |
    When I click "Personal Information" from  My Account LHS sub menu
    Then the Personal Information for user "migrated1" is displayed
    When I click "Address/Collection Preferences" from  My Account LHS sub menu
    Then the Address/Collection Preferences for user "migrated1" is displayed

  Scenario: ReOrder from MyAccounts Page for Migrated User using Visa Card
    Given The user is on Lloyds Pharmacy home page
    When The user clicks Sign In button
    Then The user inputs "b2c.username.migrated1" and "b2c.password.migrated1" as logon ID and password on CheckOut SignIn page
    When The user clicks "Sign In" button on login page
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
    And the order confirmation has "Standard Delivery" and shipping address as "migrated2" for "SKINeffect Body Lotion 200ml"
    #REORderFlow
    When I navigate to My Accounts Page
    Then The user should be navigated to "My Account" details page
    Given I click "Order History" from  My Account LHS sub menu
    When I click  "ReOrder" for the current order on My Accounts page
    Then the Order History Page Item Details consists of
      | itemName                     | inStock  | itemQuantity | eachItemPrice | totalPrice | orderSubTotal |
      | SKINeffect Body Lotion 200ml | In stock | 1            | £7.99         | £7.99      | £0.00         |
    Given I update the reorder quantity to "1"
    Given I select the the item to Order on ReOrder Page
    Then the Order History Page Item Details consists of
      | itemName                     | inStock  | itemQuantity | eachItemPrice | totalPrice | orderSubTotal |
      | SKINeffect Body Lotion 200ml | In stock | 1            | £7.99         | £7.99      | £7.99         |
    When I click on Add to basket on ReOrder Page
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
    And the order confirmation has "Standard Delivery" and shipping address as "migrated2" for "SKINeffect Body Lotion 200ml"
