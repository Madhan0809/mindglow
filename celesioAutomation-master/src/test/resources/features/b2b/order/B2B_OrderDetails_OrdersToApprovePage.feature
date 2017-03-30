@b2b_orderdetails @b2b
Feature: Order to Approve page and its details

  Background: 
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    And The user is on AAH home page with choosing account

  Scenario: TC01. Check it can enter order details page by clicking on target order link
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on Account Details link from My Account page
    When User click view details for account "b2b.default.aah.Contract"
    When User click on Order approvals with minimum amount
      | pounds | 1 |
      | pence  | 1 |
    And User save changes with order approvals
    When The user select "Create a new basket" from mini basket in "AAH" row
    And The user input basket detail information to create
      | Account        | b2b.defaultContract  |
      | DeliveryPoint  | Neoworks Ltd-EC2V 5DE |
      | SubmissionDate | 1/1/2017              |
    And Add one product to "b2b.new.aah.basket" basket from PLP
    Then The user click the AAH mini cart button and verify "Current Order" page is displayed
    And click on "Request Review" button and send for review
    And User close the mini basket popup
    And User go to "YOUR PROFILE" from main menu and click on "Orders Waiting For Approval" link from profile landing page
    Then Check breadcrumb should be displayed correctly
      | Home\\                      |
      | Your Profile\\              |
      | Orders waiting for approval |
    And User click on "AAH" in orders to approved page
    Then The "Order to Approve Details" page is displayed
    And verify the "Approval Required for Order" message displayed
    And the order detail summary is display in correct format
      | Order Number       |
      | Account            |
      | Total Order Lines  |
      | Order Value        |
      | Brand              |
      | Delivery Point     |
      | Customer Reference |
      | Date Created       |
      | Account Number     |
    And Check the faux table and the content is displayed in correct format
      | Line                |
      | Product Code        |
      | Product Description |
      | Pack Size           |
      | Unit Price          |
      | Quantity            |
    And The user clicks on Signout

  Scenario: TC02. Check order details content from View Details option in orders to approved page
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on Account Details link from My Account page
    When User click view details for account "b2b.default.aah.Contract"
    When User click on Order approvals with minimum amount
      | pounds | 1 |
      | pence  | 1 |
    And User save changes with order approvals
    When The user select "Create a new basket" from mini basket in "AAH" row
    And The user input basket detail information to create
      | Account        | b2b.defaultContract  |
      | DeliveryPoint  | Neoworks Ltd-EC2V 5DE |
      | SubmissionDate | 1/1/2017              |
    And Add one product to "b2b.new.aah.basket" basket from PLP
    Then The user click the AAH mini cart button and verify "Current Order" page is displayed
    When click on "Request Review" button and send for review
    And User close the mini basket popup
    And User go to "YOUR PROFILE" from main menu and click on "Orders Waiting For Approval" link from profile landing page
    And User select "View details" option in the action menu of order "Actions" from orders waiting for approval page
    Then verify the "Approval Required for Order" message displayed
    And the order detail summary is display in correct format
      | Order Number       |
      | Account            |
      | Total Order Lines  |
      | Order Value        |
      | Brand              |
      | Delivery Point     |
      | Customer Reference |
      | Date Created       |
      | Account Number     |
    And Check the faux table and the content is displayed in correct format
      | Line                |
      | Product Code        |
      | Product Description |
      | Pack Size           |
      # | Extra Info          |
      | Unit Price          |
      | Quantity            |
    And The user clicks on Signout

  Scenario Outline: TC03. Approve and Reject an order by clicking on target order link
    And User go to "YOUR PROFILE" from main menu and click on "Orders Waiting For Approval" link from profile landing page
    Then User click on "AAH" in orders to approved page
    And User enter a comment "<Comments>" of this buyer
    Then User click on "<buttonToClick>" in order details page
    And verify "<messageText>" confirmation is displayed

    Examples: 
      | Comments            | buttonToClick | messageText    |
      | Approved this order | Approve       | Order Approved |
      | Reject this order   | Reject        | Order Rejected |
