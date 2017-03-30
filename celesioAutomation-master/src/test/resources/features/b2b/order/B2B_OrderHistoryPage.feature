@b2b_orderhistory @b2b
Feature: YOUR PROFILE - Sent orders - Check order history page

  Background:
    Given User login with default multi account   
    And User click on continue in important messages page
    And User click on "YOUR PROFILE" from main navigation menu
    And User click on "Sent Orders" section from My Account page
    Then The "Account Order History" page is displayed
    And User select "View all orders" for order in sent order page

  Scenario: TC01. Check the title and order list table display correctly in sent orders page
    Then Check the title of "SENT ORDERS" can be displayed correctly
    Then Check the order list table display in correct format
      | Created            |
      | Tracking Number    |
      | Customer Reference |
      | Order              |
      | Delivery point     |
      | Raised             |
      | Sent               |
      | Order Status       |

  Scenario: TC02. Sent a new order and view the order raised today
    When The user select "Create a new basket" from mini basket in "AAH" row
    And The user input basket detail information to create
      | Account        | b2b.default.enterprise.Contract  |
      | DeliveryPoint  | Neoworks Enterprise Ltd-EC2V 5DE |
      | SubmissionDate | 1/1/2017                         |
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    When Add product "b2b.product.huggies" into target basket "b2b.new.enterprise.basket"
    When The user select "b2b.new.enterprise.basket" from mini basket in "ENTERPRISE" row
    And The user click the ENTERPRISE mini cart button
    And The user click Send Order button
    And User click on "YOUR PROFILE" from main navigation menu
    And User click on "Sent Orders" section from My Account page
    And User select "View orders raised today" for order in sent order page
    Then The date of all orders in current list is equals to today

  Scenario: TC03. View all orders in order history page
    Then Check the order history records is displayed accordingly in table

  #No test data on failed sent order with the new test account
  @b2b_blocked
  Scenario: TC04. Check on Resend order function for order in failure
    When User select "View all orders" for order in sent order page
    When User resend a order in current order history table
    Then The "Account Order History" page is displayed
 
  #No test data on failed sent order with the new test account
  @b2b_blocked
  Scenario: TC05. See the order status overlay popup comes when click status of a order
    #    When User click link "Sent - Failure" for order "69035" in sent order page
    When User clicks a status link of "Failure" order in sent order table
    Then Check the "Failure" message popup comes in sent order page

  Scenario: TC06. Search orders in order details table  
    When The user select "Create a new basket" from mini basket in "AAH" row
    And The user input basket detail information to create
      | Account        | b2b.default.enterprise.Contract  |
      | DeliveryPoint  | Neoworks Enterprise Ltd-EC2V 5DE |
      | SubmissionDate | 1/1/2017                         |
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    When Add product "b2b.product.huggies" into target basket "b2b.new.enterprise.basket"   
    And User click on "YOUR PROFILE" from main navigation menu
    And User click on "Sent Orders" section from My Account page
    And Input search order info and search for order
      | Order Tracking Number |            |
      #      | Account               | All Accounts |
      | Order Number          |            |
      | Status                | All        |
      | Customer Reference    |            |
      | Created before or on: | 01/11/2016 |
      | Delivery Point        | All        |
      | Created on or after:  | 01/01/2016 |
    Then Check the order history records is displayed accordingly in table

  Scenario: TC07. Click one order to enter order details page
    When User click a order link in sent order data table
    Then The "Account Order Details" page is displayed

  Scenario: Check breadcrumb on Sent Orders page
    Then Check breadcrumb should be displayed correctly
      | Home\\         |
      | Your Profile\\ |
      | Sent Orders    |
    When Click link "Your Profile" on breadcrumb
    Then The "Your Profile" page is displayed
