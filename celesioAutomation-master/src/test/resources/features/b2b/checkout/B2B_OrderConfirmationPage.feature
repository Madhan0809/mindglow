@b2b_orderconfirmation @b2b
Feature: Order Confirmation Page function

  Background: 
    Given The user is on AAH  Sign-in page as a guest
    When User login with userId "b2b.approver.username" and password "b2b.approver.password" from AAH home page
    When The user is on AAH home page with choosing account
    Then Check create basket from header is displayed
    When The user select "Create a new basket" from mini basket in "AAH" row
    And The user input basket detail information to create
      | Account        | b2b.default.enterprise.Contract  |
      | DeliveryPoint  | NORTHERN PHARMACIES LTD-BT22 2NF |
      | SubmissionDate | 1/1/2017                         |
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    And Add one product into "b2b.new.enterprise.basket" basket in PLP
    When The user select "b2b.new.enterprise.basket" from mini basket in "ENTERPRISE" row
    And The user click the ENTERPRISE mini cart button
    Then The "Current Order" page is displayed

  Scenario: TC01: Sent order for a new Enterprise point to navigate to Order Confirmation Page
    And User dismiss all notification in header
    When The user click Send Order button
    Then The "YOUR ENTERPRISE ORDER HAS BEEN SENT" title is displayed on Order Confirm Page
    And The "Print" button is displayed on Order Confirm Page
    When The click Continue button on Order Confirm Page
    Then The user is on AAH home page as a user

  Scenario: TC02. Click order reply and check it should be directed to order reply details page
    And User dismiss all notification in header
    When The user click Send Order button
    And User click view order reply on order confirm page
    Then The "Account Order Reply Details" page is displayed
