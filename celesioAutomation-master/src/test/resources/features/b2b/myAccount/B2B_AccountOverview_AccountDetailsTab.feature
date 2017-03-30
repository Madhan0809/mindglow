@b2b_accountOverview_accountDetails @b2b
Feature: Account Overview Page - Account details tab

  Background: 
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on Account Details link from My Account page
    When User click view details for any account in account list
 #   When User click view details for account "b2b.defaultContract"

  Scenario: TC01. Account details tab: Verify account contact details should display per design
    Then Check if access to "AccountDisplay" page
    Then Check the account name "b2b.defaultContract" displays in account tab header
    And Account address details is displayed in account tab
      | Email        |
      | Phone Number |
      | Fax Number   |
      | Address      |

  Scenario: TC02. Account details tab: Input approve orders limiting value and save changes
    When User click on Order approvals with minimum amount
      | pounds | 1 |
      | pence  | 1 |
    And User save changes with order approvals
    Then The approved required for all orders should be over
      | 1 |
      | 1 |

  Scenario: TC03. Delivery point tab: Veify delivery points table content display as per design
    When User click on "Delivery Points" tab in account details
    Then Check the account name "b2b.defaultContract" displays in account tab header
    And Delivery point info is found in table
      | Code           |
      | Delivery Point |

  Scenario: TC04. Back to account list page from account detail
    When User click "Back" button in account details
    Then The "Accounts List" page is displayed

  Scenario: TC05. Check breadcrumb on Account Details page
    Then Check breadcrumb within account details
      | Home\\            |
      | Your Profile\\    |
      | Account Details\\ |
    When Click link "Your Profile" on breadcrumb
    Then The "Your Profile" page is displayed
