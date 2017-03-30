@b2b_createnewbasket @b2b
Feature: Create new basket page

  Background: 
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    Then Check create basket from header is displayed

  @prodsmoketest
  Scenario: TC00. E2E: Create a new Enterprise type basket from header, add product and verify it exists in my basket page
    Given User enter my basket page after login
    And User cleanup the basket "b2b.new.enterprise.basket" in my basket page
    And The user select "Create a new basket" from mini basket in "AAH" row
    And The user input basket detail information to create
      | Account        | b2b.default.enterprise.Contract  |
      | DeliveryPoint  | Neoworks Enterprise Ltd-EC2V 5DE |
      | SubmissionDate | 1/1/2017                         |
    Then The new ENTERPRISE basket is created with name as "b2b.new.enterprise.basket"
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    And The user should be navigated to "Baby Changing" Sub Category screen
    And Add product "b2b.product.huggies" into target basket "b2b.new.enterprise.basket"
    When The user select "b2b.new.enterprise.basket" from mini basket in "ENTERPRISE" row
    And User close the mini basket popup
    When User enter my basket page after login
    Then Check basket "b2b.new.enterprise.basket" is found in my basket page
    And User cleanup the basket "b2b.new.enterprise.basket" in my basket page

  Scenario: TC01: Create a new AAH type basket from header
    Given User enter my basket page after login
    And User cleanup the basket "b2b.new.aah.basket" in my basket page
    When The user select "Create a new basket" from mini basket in "AAH" row
    And The user input basket detail information to create
      | Account        | b2b.defaultContract   |
      | DeliveryPoint  | Neoworks Ltd-EC2V 5DE |
      | SubmissionDate | 1/1/2017              |
    Then The new AAH basket is created with name as "b2b.new.aah.basket"

  Scenario: TC02: Add one OTC product into basket from PLP and check mini cart is updated
    When The user select "Create a new basket" from mini basket in "AAH" row
    And The user input basket detail information to create
      | Account        | b2b.defaultContract   |
      | DeliveryPoint  | Neoworks Ltd-EC2V 5DE |
      | SubmissionDate | 1/1/2017              |
    Then The new AAH basket is created with name as "b2b.new.aah.basket"
    Given The user clear all products from basket "b2b.new.aah.basket" in "AAH" row
    And The user select "b2b.new.aah.basket" from mini basket in "AAH" row
    And The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    When Add product "b2b.product.huggies" into target basket "b2b.new.aah.basket"
    And The user select "b2b.new.aah.basket" from mini basket in "AAH" row
    And The user click the AAH mini cart button
    Then The "Current Order" page is displayed
    And The checkout account headers are displayed as per design 
      | Account Number:                  |
      | Account Name:                    |
      | Delivery Point:                  |
      | Submission Date / Current Basket |
    And The account details are displayed with 4 field

  Scenario: TC03: Verify Request Review function of AAH basket
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on Account Details link from My Account page
    When User click view details for account "b2b.default.aah.Contract"
    When User click on Order approvals with minimum amount
      | pounds | 1 |
      | pence  | 1 |
    And User save changes with order approvals
    When User enter my basket page after login
    And User cleanup the basket "b2b.new.aah.basket" in my basket page
    When The user select "Create a new basket" from mini basket in "AAH" row
    And The user input basket detail information to create
      | Account        | b2b.defaultContract   |
      | DeliveryPoint  | Neoworks Ltd-EC2V 5DE |
      | SubmissionDate | 1/1/2017              |
    When The user clear all products from basket "b2b.new.aah.basket" in "AAH" row
    And The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    When Add product "b2b.product.huggies" into target basket "b2b.new.aah.basket"
    And User close the mini basket popup
    And The user click the AAH mini cart button
    Then The "Current Order" page is displayed
    When The user click Request Review button
    Then The AAH basket "b2b.new.aah.basket" is removed from mini basket row

  Scenario: TC04: Create a new ENTERPRISE type basket from header
    Given User enter my basket page after login
    And User cleanup the basket "b2b.new.enterprise.basket" in my basket page
    And The user select "Create a new basket" from mini basket in "AAH" row
    And The user input basket detail information to create
      | Account        | b2b.default.enterprise.Contract  |
      | DeliveryPoint  | Neoworks Enterprise Ltd-EC2V 5DE |
      | SubmissionDate | 1/1/2017                         |
    Then The new ENTERPRISE basket is created with name as "b2b.new.enterprise.basket"

  Scenario: TC05: Verify Send Order function of ENTERPRISE basket
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on Account Details link from My Account page
    When User click view details for account "b2b.default.aah.Contract"
    When User de-select on order approval checkbox
    And User save changes with order approvals
    And The user select "Create a new basket" from mini basket in "AAH" row
    And The user input basket detail information to create
      | Account        | b2b.default.enterprise.Contract  |
      | DeliveryPoint  | Neoworks Enterprise Ltd-EC2V 5DE |
      | SubmissionDate | 1/1/2017                         |
    And The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    When Add one product into "b2b.new.enterprise.basket" basket in PLP
    And The user select "b2b.new.enterprise.basket" from mini basket in "ENTERPRISE" row
    And The user click the ENTERPRISE mini cart button
    Then The "Current Order" page is displayed
    And The user click Send Order button
    Then The "Enterprise - Order Confirmation" page is displayed
    And The ENTERPRISE basket "b2b.new.enterprise.basket" is removed from mini basket row

  Scenario: TC06: Verify create new ENTERPRISE basket from mini cart button and then Send Order
    When User hover on "AAH" mini cart button
    And User click Create New Basket button on mini cart
    And The user input basket detail information to create
      | Account        | b2b.default.enterprise.Contract  |
      | DeliveryPoint  | Neoworks Enterprise Ltd-EC2V 5DE |
      | SubmissionDate | 1/1/2017                         |
    Then The new ENTERPRISE basket is created with name as "b2b.new.enterprise.basket"
    And The user select "b2b.new.enterprise.basket" from mini basket in "ENTERPRISE" row
    And The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    When Add one product into "b2b.new.enterprise.basket" basket in PLP
    And The user select "b2b.new.enterprise.basket" from mini basket in "ENTERPRISE" row
    And The user click the ENTERPRISE mini cart button
    And The user click Send Order button
    And The ENTERPRISE basket "b2b.new.enterprise.basket" is removed from mini basket row

    
    