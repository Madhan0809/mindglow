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

  Scenario: TC01. Check on breadcrumb on order reply and order reply details page
    Then Check breadcrumb should be displayed correctly
      | Home\\         |
      | Your Profile\\ |
      | Sent Orders\\  |
      | Order Details  |
    When Click link "Order Details" on breadcrumb
    Then The "Account Order Details" page is displayed
    When Click link "Sent Orders" on breadcrumb
    Then The "Account Order History" page is displayed
    When Click link "Your Profile" on breadcrumb
    Then The "Your Profile" page is displayed

  #Both summary and header text are not fully keep consistent as per spec, wait for confirmation
  @b2b_failed
  Scenario: TC02. Check the order details data table display in correct format
    Then Check the order summary should be displayed in correct format
      | Date Sent         |
      | Delivery point    |
      | Order Number      |
      | Order Reference   |
      | Account           |
      | Delivery Date     |
      | Delivery Details  |
      | Date Created      |
      | Total Order Lines |
      | Send Order        |
    And Check the order data table is displayed correctly
      | Line              |
      | Sent Product Code |
      | Sent Description  |
      | Pack Size         |
      | Quantity          |
