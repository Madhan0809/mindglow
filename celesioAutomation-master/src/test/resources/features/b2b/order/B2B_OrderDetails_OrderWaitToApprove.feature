@b2b_orderToApprove @b2b
Feature: Order Wait to approve page and its details

  Background: 
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    Then The user is on AAH home page as a user

  Scenario Outline: TC01. Approved/Reject an order from orders wait to approved page
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on Account Details link from My Account page
    When User click view details for account "b2b.default.aah.Contract"
    When User click on Order approvals with minimum amount
      | pounds | 1 |
      | pence  | 1 |
    And User save changes with order approvals
    When The user select "Create a new basket" from mini basket in "AAH" row
    And The user input basket detail information to create
      | Account        | b2b.defaultContract   |
      | DeliveryPoint  | Neoworks Ltd-EC2V 5DE |
      | SubmissionDate | 1/1/2017              |
    And Add one product to "b2b.new.aah.basket" basket from PLP
    Then The user click the AAH mini cart button and verify "Current Order" page is displayed
    And click on "Request Review" button and send for review
    And User close the mini basket popup
    When User go to "YOUR PROFILE" from main menu and click on "Orders Waiting For Approval" link from profile landing page
    And User select "<Action>" option in the action menu of order "Actions" from orders waiting for approval page
    And User input comments to continue "<Action>" after selecting on action button
    Then verify "<Verify text>" confirmation is displayed

    Examples: 
      | Action  | Verify text    |
      | Approve | Order Approved |
      | Reject  | Order Rejected |

  Scenario: TC02. Check it enters order to approve details page by clicking on target order link
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on Account Details link from My Account page
    When User click view details for account "b2b.default.aah.Contract"
    When User click on Order approvals with minimum amount
      | pounds | 1 |
      | pence  | 1 |
    And User save changes with order approvals
    When The user select "Create a new basket" from mini basket in "AAH" row
    When The user input basket detail information to create
      | Account        | b2b.defaultContract              |
      | DeliveryPoint  | Neoworks Enterprise Ltd-EC2V 5DE |
      | SubmissionDate | 1/1/2017                         |
    And Add one product to "b2b.new.aah.basket" basket from PLP
    Then The user click the AAH mini cart button and verify "Current Order" page is displayed
    And click on "Request Review" button and send for review
    And User close the mini basket popup
    And User go to "YOUR PROFILE" from main menu and click on "Orders Waiting For Approval" link from profile landing page
    And User click on "AAH" in orders to approved page
    Then The "Order to Approve Details" page is displayed
    And verify the "Approval Required for Order" message displayed

  Scenario: TC03. Check breadcrumb in orders waiting for approval is displayed
    When User click on "YOUR PROFILE" from main navigation menu
    When User click on "Orders Waiting For Approval" tab from profile landing page
    Then Check breadcrumb should be displayed correctly
      | Home\\                      |
      | Your Profile\\              |
      | Orders waiting for approval |
    When Click link "Your Profile" on breadcrumb
    Then The "Your Profile" page is displayed

  Scenario: TC04. Check order details content by clicking on view details actions
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on "Orders Waiting For Approval" tab from profile landing page
    And User select "View details" option in the action menu of order "Actions" from orders waiting for approval page
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
      #  | Extra Info          |
      | Unit Price          |
      | Quantity            |
    And The user clicks on Signout

  Scenario: TC05. Check view order details by click on sub option under Action button
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on "Orders Waiting For Approval" tab from profile landing page
    And User select "View details" option in the action menu of order "Actions" from orders waiting for approval page
    Then The "Order to Approve Details" page is displayed
    And verify the "Approval Required for Order" message displayed

  Scenario: TC06. Check order details content displaying after clicking on reject from action menu
    When The user select "Create a new basket" from mini basket in "AAH" row
    When The user input basket detail information to create
      | Account        | b2b.defaultContract             |
      | DeliveryPoint  | Neoworks Enterprise Ltd-EC2V 5DE |
      | SubmissionDate | 1/1/2017                         |
    And Add one product to "b2b.new.aah.basket" basket from PLP
    Then The user click the AAH mini cart button and verify "Current Order" page is displayed
    And click on "Request Review" button and send for review
    And User close the mini basket popup
    And User go to "YOUR PROFILE" from main menu and click on "Orders Waiting For Approval" link from profile landing page
    And User select "Reject" option in the action menu of order "Actions" from orders waiting for approval page
    Then The "Order to Approve Details" page is displayed
    And the order detail summary is display in correct format
      | Order Number       |
      | Account            |
      | Total Order Lines  |
      | Order Value        |
      | Delivery Point     |
      | Customer Reference |
      | Date Created       |
      | Account Number     |
      | Brand              |
    And Check the faux table and the content is displayed in correct format
      | Line                |
      | Product Code        |
      | Product Description |
      | Pack Size           |
      | Unit Price          |
      | Quantity            |
