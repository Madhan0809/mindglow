@b2c @b2c_e2e_prescriptions
Feature: Prescription E2E scenarios, Free, Paid, Mixed

  Scenario: TC01. One-Off Prescription can be paid via Payment Type as Free
    Given The user is on Lloyds Pharmacy home page
    Given The user navigates to Registration page
    When The user enters all the relevant details on the registration page
      | generateRandomData | true |
    And The user clicks on "Create Account" on registration page
    When The user inputs "Amoxicillin" in the Search text box
    And The user clicks the Search button
    When The user clicks "Add" for "Amoxicillin 250mg capsules" on the Prescriptions PLP
    And The user selects prescription frequency "oneoff"
    And The user selects prescription payment Type "free"
    And The user selects payment exempt reason "exemptReason.A"
    And The user clicks mini shop cart
    Then Appropriate basket header details should be displayed
      | No of basket items | 1         |
      | Total basket price | £0.00     |
      | Mini basket        | 1 item(s) |
    And Appropriate basket items should be displayed
      | Item name        | Amoxicillin 250mg capsules               |
      | Item Quantity    | 1                                        |
      | Each Item price  | £0.00                                    |
      | Total price      | £0.00                                    |
      | Delivery Options | Click and Collect, Deliver to UK address |
      | Order sub total  | £0.00                                    |
      | Order total      | £0.00                                    |
    And Check Item Description on basket checkout page "singlePrescription.item"
    When The user clicks Secure Checkout button on shopcart page
    Then The "Delivery options" page opens
    Given Get the information of order summary
    Then Check the result of order summary is correct
      | Total | £0.00 |
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
    And select on preferred delivery method
      | preferredDeliveryMethod | Standard Delivery |
    And input delivery instructions
      | deliveryInstructions | Thank you |
    When I click on Continue to payment button
    Then the Payment page opens
    Given Get the information of order summary
    Then Check the result of order summary is correct
      | Total | £0.00 |
    And delivery info is "Standard Delivery", charge "£0"  shipping address as "home"
    Then I confirm signed prescription terms and submit
    Then the final order confirmation page is displayed
    And the order confirmation has "Standard Delivery" and shipping address as "home" for "Amoxicillin 250mg capsules"
    Then the order total on the confirmation page is displayed as
      | SubTotal       | £0.00 |
      | DeliveryCharge | £0.00 |
      | OrderTotal     | £0.00 |


  Scenario: TC02. One-Off Prescription can be paid via Payment Type as Paid, and pay with maestro card
    Given The user is on Lloyds Pharmacy home page
    Given The user navigates to Registration page
    When The user enters all the relevant details on the registration page
      | generateRandomData | true |
    And The user clicks on "Create Account" on registration page
    When The user inputs "Amoxicillin" in the Search text box
    And The user clicks the Search button
    When The user clicks "Add" for "Amoxicillin 250mg capsules" on the Prescriptions PLP
    And The user selects prescription frequency "oneoff"
    And The user selects prescription payment Type "paid"
    And The user clicks mini shop cart
    And Check Item Description on basket checkout page "singlePrescription.item"
    When The user clicks Secure Checkout button on shopcart page
    Then The "Delivery options" page opens
    Given Get the information of order summary
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
    And select on preferred delivery method
      | preferredDeliveryMethod | Standard Delivery |
    And input delivery instructions
      | deliveryInstructions | Thank you |
    When I click on Continue to payment button
    Then the Payment page opens
    Given Get the information of order summary
    And delivery info is "Standard Delivery", charge "£0"  shipping address as "home"
    Given I enter payment card details for card type "maestro"
    When I confirm signed prescription terms and submit
#    And I confirm signed prescription
#    When I submit order details and confirm terms
    Then the final order confirmation page is displayed
    And the order confirmation has "Standard Delivery" and shipping address as "home" for "Amoxicillin 250mg capsules"

  @b2c_failed
  Scenario: TC03. Mixed Paid with One-Off Prescription,Payment Type Exempt, Paid Delivery due to Paid product
    Given The user is on Lloyds Pharmacy home page
    Given The user navigates to Registration page
    When The user enters all the relevant details on the registration page
      | generateRandomData | true |
    And The user clicks on "Create Account" on registration page
    When The user inputs "skineffect" in the Search text box
    And The user clicks the Search button
    When The user clicks Add to basket for "SKINeffect Body Lotion 200ml" on the PLP
    When The user inputs "Amoxicillin" in the Search text box
    And The user clicks the Search button
    When The user clicks "Add" for "Amoxicillin 250mg capsules" on the Prescriptions PLP
    And The user selects prescription frequency "oneoff"
    And The user selects prescription payment Type "free"
    And The user selects payment exempt reason "exemptReason.A"
    And The user clicks mini shop cart
    When The user clicks Secure Checkout button on shopcart page
    Then The "Delivery options" page opens
    Given Get the information of order summary
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
    And select on preferred delivery method
      | preferredDeliveryMethod | Standard Delivery |
    And input delivery instructions
      | deliveryInstructions | Thank you |
    When I click on Continue to payment button
    Then the Payment page opens
    Given Get the information of order summary
    And delivery info is "Standard Delivery", charge "Not Zero"  shipping address as "home"
    Given I enter payment card details for card type "maestro"
    When I confirm signed prescription terms and submit
  #    And I confirm signed prescription
  #    When I submit order details and confirm terms
    Then the final order confirmation page is displayed
    And the order confirmation has "Standard Delivery" and shipping address as "home" for "SKINeffect Body Lotion 200ml"

  Scenario: TC04. Mixed Paid with One-Off Prescription,Payment Type PAID, Paid Delivery due to Paid product
    Given The user is on Lloyds Pharmacy home page
    Given The user navigates to Registration page
    When The user enters all the relevant details on the registration page
      | generateRandomData | true |
    And The user clicks on "Create Account" on registration page
    When The user inputs "skineffect" in the Search text box
    And The user clicks the Search button
    When The user clicks Add to basket for "SKINeffect Body Lotion 200ml" on the PLP
    When The user inputs "Amoxicillin" in the Search text box
    And The user clicks the Search button
    When The user clicks "Add" for "Amoxicillin 250mg capsules" on the Prescriptions PLP
    And The user selects prescription frequency "oneoff"
    And The user selects prescription payment Type "paid"
    And The user clicks mini shop cart
    When The user clicks Secure Checkout button on shopcart page
    Then The "Delivery options" page opens
    Given Get the information of order summary
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
    And select on preferred delivery method
      | preferredDeliveryMethod | Standard Delivery |
    And input delivery instructions
      | deliveryInstructions | Thank you |
    When I click on Continue to payment button
    Then the Payment page opens
    Given Get the information of order summary
    And delivery info is "Standard Delivery", charge "Not Zero"  shipping address as "home"
    Given I enter payment card details for card type "maestro"
    When I confirm signed prescription terms and submit
 #   And I confirm signed prescription
 #   When I submit order details and confirm terms
    Then the final order confirmation page is displayed
    And the order confirmation has "Standard Delivery" and shipping address as "home" for "SKINeffect Body Lotion 200ml"

  Scenario: TC05. Repeat Prescription for Patient,Payment Type Exempt, Free Delivery
    Given The user is on Lloyds Pharmacy home page
    Given The user navigates to Registration page
    When The user enters all the relevant details on the registration page
      | generateRandomData | true |
    And The user clicks on "Create Account" on registration page
    When The user inputs "Amoxicillin" in the Search text box
    And The user clicks the Search button
    When The user clicks "Add" for "Amoxicillin 250mg capsules" on the Prescriptions PLP
    And The user selects prescription frequency "repeat"
    When The user selects for repeat prescription patient name as "This is a new prescription"
    And the user enters new prescription details for "patient"
    And The user selects prescription payment Type "free"
    And The user selects payment exempt reason "exemptReason.A"
    And The user clicks mini shop cart
    Then Appropriate basket header details should be displayed
      | No of basket items | 1         |
      | Total basket price | £0.00     |
      | Mini basket        | 1 item(s) |
    And Appropriate basket items should be displayed
      | Item name        | Amoxicillin 250mg capsules               |
      | Item Quantity    | 1                                        |
      | Each Item price  | £0.00                                    |
      | Total price      | £0.00                                    |
      | Delivery Options | Click and Collect, Deliver to UK address |
      | Order sub total  | £0.00                                    |
      | Order total      | £0.00                                    |
    And Check Item Description on basket checkout page "repeatPrescription.item"
    When The user clicks Secure Checkout button on shopcart page
    Then The "Delivery options" page opens
    Given Get the information of order summary
    Then Check the result of order summary is correct
      | Total | £0.00 |
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
    And select on preferred delivery method
      | preferredDeliveryMethod | Standard Delivery |
    And input delivery instructions
      | deliveryInstructions | Thank you |
    When I click on Continue to payment button
    Then the Payment page opens
    Given Get the information of order summary
    Then Check the result of order summary is correct
      | Total | £0.00 |
    And delivery info is "Standard Delivery", charge "£0"  shipping address as "home"
    Then I confirm signed prescription terms and submit
    Then the final order confirmation page is displayed
    And the order confirmation has "Standard Delivery" and shipping address as "home" for "Amoxicillin 250mg capsules"
    Then the order total on the confirmation page is displayed as
      | SubTotal       | £0.00 |
      | DeliveryCharge | £0.00 |
      | OrderTotal     | £0.00 |

  Scenario: TC06. Repeat Prescription for representative,Payment Type Exempt, Free Delivery
    Given The user is on Lloyds Pharmacy home page
    Given The user navigates to Registration page
    When The user enters all the relevant details on the registration page
      | generateRandomData | true |
    And The user clicks on "Create Account" on registration page
    When The user inputs "Amoxicillin" in the Search text box
    And The user clicks the Search button
    When The user clicks "Add" for "Amoxicillin 250mg capsules" on the Prescriptions PLP
    And The user selects prescription frequency "repeat"
    When The user selects for repeat prescription patient name as "This is a new prescription"
    And the user enters new prescription details for "representative"
    And The user selects prescription payment Type "free"
    And The user selects payment exempt reason "exemptReason.A"
    And The user clicks mini shop cart
    Then Appropriate basket header details should be displayed
      | No of basket items | 1         |
      | Total basket price | £0.00     |
      | Mini basket        | 1 item(s) |
    And Appropriate basket items should be displayed
      | Item name        | Amoxicillin 250mg capsules               |
      | Item Quantity    | 1                                        |
      | Each Item price  | £0.00                                    |
      | Total price      | £0.00                                    |
      | Delivery Options | Click and Collect, Deliver to UK address |
      | Order sub total  | £0.00                                    |
      | Order total      | £0.00                                    |
    And Check Item Description on basket checkout page "repeatPrescription.item"
    When The user clicks Secure Checkout button on shopcart page
    Then The "Delivery options" page opens
    Given Get the information of order summary
    Then Check the result of order summary is correct
      | Total | £0.00 |
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
    And select on preferred delivery method
      | preferredDeliveryMethod | Standard Delivery |
    And input delivery instructions
      | deliveryInstructions | Thank you |
    When I click on Continue to payment button
    Then the Payment page opens
    Given Get the information of order summary
    Then Check the result of order summary is correct
      | Total | £0.00 |
    And delivery info is "Standard Delivery", charge "£0"  shipping address as "home"
    Then I confirm signed prescription terms and submit
    Then the final order confirmation page is displayed
    And the order confirmation has "Standard Delivery" and shipping address as "home" for "Amoxicillin 250mg capsules"
    Then the order total on the confirmation page is displayed as
      | SubTotal       | £0.00 |
      | DeliveryCharge | £0.00 |
      | OrderTotal     | £0.00 |
