@b2b_multideliverypoint @b2b
Feature: Multiple Delivery Point Selection function

  Background: 
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    Then Check create basket from header is displayed
    Given User enter my basket page after login
    And User cleanup the basket "b2b.new.aah.basket" in my basket page
    When The user select "Create a new basket" from mini basket in "AAH" row
    And The user input basket detail information to create
      | Account        | b2b.defaultContract   |
      | DeliveryPoint  | Neoworks Ltd-EC2V 5DE |
      | SubmissionDate | 1/1/2017              |
    Then The new AAH basket is created with name as "b2b.new.aah.basket"
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    And Add product "b2b.product.huggies" into target basket "b2b.new.aah.basket"

  Scenario: TC01: Add Multiple Delivery Point Selection and then verifiy the basket details is updated accordingly
    When The user select "b2b.new.aah.basket" from mini basket in "AAH" row
    And The user click the AAH mini cart button
    And The user click Multiple Delivery Point link on basket page
    Then The user is navigate to "SEND ORDER TO MULTIPLE ACCOUNTS / DELIVERY POINTS" page
    Then The column headers are as design
      | Account                          |
      | Delivery Point                   |
      | Submission Date / Current Basket |
    When User input information from multiple delivery points
    And The user click on "Done" button in current order
    Then The "Current Order" page is displayed
    And The checkout account headers are displayed as per design
      | Account Number:                  |
      | Account Name:                    |
      | Delivery Point:                  |
      | Submission Date / Current Basket |
    And The account details are displayed with 8 field

  Scenario: TC02: Verify the link of Replicate order should be hidden after there is already a added delivery point
    And The user select "b2b.new.aah.basket" from mini basket in "AAH" row
    And The user click the AAH mini cart button
    Then The link Replicate order for multiple accounts/delivery points should be "Visible"
    When The user click on "Replicate order for multiple accounts/delivery points" button in current order
    Then The user is navigate to "SEND ORDER TO MULTIPLE ACCOUNTS / DELIVERY POINTS" page
    When User input information from multiple delivery points
    And The user click on "Done" button in current order
    Then The "Current Order" page is displayed
    And The link Replicate order for multiple accounts/delivery points should be "Hidden"

  Scenario: TC03: Click Cancel button on Multiple Delivery Point Selection page will go back to the previous page
    When The user select "b2b.new.aah.basket" from mini basket in "AAH" row
    And The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    And Add one product into "b2b.new.aah.basket" basket in PLP
    And The user select "b2b.new.aah.basket" from mini basket in "AAH" row
    And The user click the AAH mini cart button
    And The user click Multiple Delivery Point link on basket page
    Then The user is navigate to "SEND ORDER TO MULTIPLE ACCOUNTS / DELIVERY POINTS" page
    When The user click Cancel button on Multiple Delivery Point Selection page
    Then The "Current Order" page is displayed

  Scenario: TC04: Click Edit button will go back to Multiple Delivery Point Selection page
    When The user select "b2b.new.aah.basket" from mini basket in "AAH" row
    And The user click the AAH mini cart button
    And The user click Multiple Delivery Point link on basket page
    When User input information from multiple delivery points
    And The user click on "Done" button in current order
    When The user click on Edit button on basket view page
    Then The user is navigate to "SEND ORDER TO MULTIPLE ACCOUNTS / DELIVERY POINTS" page
