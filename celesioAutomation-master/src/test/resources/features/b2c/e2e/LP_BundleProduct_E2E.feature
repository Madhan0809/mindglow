@b2c @b2c_e2e_deliverToUk @b2c_blocked
Feature: E2E Place Order for Bundle Product
  #Lack of bundle data
  Scenario: Place Order for Bundle Product
    Given The user is on Lloyds Pharmacy home page
    Given The user navigates to Registration page
    When The user enters all the relevant details on the registration page
      | generateRandomData | true |
    And The user clicks on "Create Account" on registration page
#    When The user inputs "phillipa reflex foam" in the Search text box
    When The user inputs "LloydsPharmacy Allergy Bundle" in the Search text box
    And The user clicks the Search button
    Then "Reviews" in product detail on PLP is displayed "true"
    When The user clicks More Info for "Phillipa Reflex Foam Adjustable Bed with Color" on the PLP
    Then Appropriate product bundle details are displayed on RHS
      | Product Name | Phillipa Reflex Foam Adjustable Bed with Color |
      | User Rating  | false                                          |
      | Price        | false                                          |
      | Quantity     | false                                          |
      | productImage | true                                           |
    Given I select and update quantity for a product in bundle for variant
      | Product Name          | Adjustable Base with Reflex Foam Mattress with Color |
      | Variant               | Orange,Medium                                        |
      | Price                 | £838.80                                              |
      | Price with VAT Relief | £699.00                                              |
      | Stock                 | In stock                                             |
      | Quantity              | 2                                                    |
      | Select                | true                                                 |
    When I click on add selected to basket
    And The user clicks mini shop cart
    Then Appropriate basket header details should be displayed
      | No of basket items | 4         |
      | Total basket price | £1994.39  |
      | Mini basket        | 4 item(s) |
    When The user clicks Secure Checkout button on shopcart page
    Then the VatRelief option is selected "true" for option "Me"
    Then the VatRelief option is selected "true" for option "Me"
    Then the VatRelief option is selected "false" for option "Me"
    Then The "Delivery options" page opens
    Given The user selects "Deliver to UK address" delivery radio button
    And select on preferred delivery method
      | preferredDeliveryMethod | Standard Delivery |
    When I click on Continue to payment button
    Then the Payment page opens
    Given I enter payment card details for card type "visa"
    When I submit order details and confirm terms
    Then the final order confirmation page is displayed
    And the order confirmation has "Standard Delivery" and shipping address as "home" for "Adjustable Base with Reflex Foam Mattress with Color"
    Then the order total on the confirmation page is displayed as
      | DeliveryCharge | £2.95     |
      | SubTotal       | £1,994.39 |
      | OrderTotal     | £1,824.74 |
