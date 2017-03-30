@b2b_orderReply @b2b
Feature: Order reply - Check order reply details page

  Background: 
    Given The user is on AAH home page
    And User login with user name and password
    And User click on continue in important messages page
    And User click on "YOUR PROFILE" from main navigation menu
    And User click on "Sent Orders" section from My Account page
    Then The "Account Order History" page is displayed
    And User select "View all orders" for order in sent order page
    When User click a order link in sent order data table
    And User view order reply from order details page

  Scenario: TC01. Verify order reply page and header display correctly
    Then The "Account Order Reply Details" page is displayed
    And Check order reply page header "ORDER REPLY:" for default order is displayed

  Scenario: TC02. Check on breadcrumb on order reply and order reply details page
    Then Check breadcrumb should be displayed correctly
      | Home\\          |
      | Your Profile\\  |
      | Sent Orders\\   |
      | Order Details\\ |
      | Order Reply     |
    When Click link "Order Details" on breadcrumb
    Then The "Account Order Details" page is displayed
    When Click link "Sent Orders" on breadcrumb
    Then The "Account Order History" page is displayed
    When Click link "Your Profile" on breadcrumb
    Then The "Your Profile" page is displayed

  Scenario: TC03. Check the order details data table and view exception
    Then Check order details label should be displayed in correct format
      | Account Number        |
      | Date Created          |
      | Your Order Reference  |
      | Order Tracking Number |
      | Date Sent             |
      | Order Number          |
      | Supplier              |
      | Total Order Lines     |
    And Check the order data table is displayed correctly
      | Line                |
      | Product Description |
      | Product Code        |
      | Pack Size           |
      | Quantity            |
      | Each Price          |
      | Total               |
      | Reply               |
      | Description         |
    Then Check all data in order reply table is not empty and get reply codes
    When Click on view exceptions only checkbox
    Then Check all reply codes greater than zero shall be shown
