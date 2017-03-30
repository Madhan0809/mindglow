@b2b_accountOverview_users @b2b
Feature: Account Overview Page - Users tab

  Background: 
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    And The user clicks on AAHEnterprise icon
    Then The user is on AAH home page as a user
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on Account Details link from My Account page
    When User click view details for any account in account list
 #   When User click view details for account "b2b.defaultContract"
    Then Check if access to "AccountDisplay" page
    And User click on "Users" tab in account details

  Scenario: TC01. Check for users table content display in correct format
    Then Check user table header columns show correctly
      | Name     |
      | Username |
      | Role     |
      | Status   |

  Scenario: TC02. Click on user in users table and verify it can enter user details page
    When User clicks a user from user table
    And The "Account User Details" page is displayed

  Scenario: TC03. Create a new user and verify search user functionality in Users tab
    When User click "Add User" button in account details
    And User inputs user details with random username to add
      | firstName      | autoTestFirstName  |
      | lastName       | autoTestLastName   |
      | primaryTel     | 1234567890         |
      | username       | random_NewUserName |
      | password       | passw9rd           |
      | verifyPassword | passw9rd           |
    And Assign role of "Buyer" for any account to add user
    When User click "Save" button in account details
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on Account Details link from My Account page
    When User click view details for any account in account list
#    When User click view details for account "b2b.defaultContract"
    Then Check if access to "AccountDisplay" page
    And User click on "Users" tab in account details
    When Input account information to search
      | Username            | random_NewUserName |
      | First name          | autoTestFirstName  |
      | Last name           | autoTestLastName   |
      | Role                | Buyer              |
      | User account status | Enabled            |
    Then Check the account name "autoTestFirstName autoTestLastName" should display in users table
    And Check user information is included in table
      | Name     | autoTestFirstName autoTestLastName |
      | Username | random_NewUserName                 |
      | Role     | Buyer (buy-side),                  |
      | Status   | Enabled                            |

  Scenario: TC04. Search for no existing account in users tab and check an empty result should be returned
    When Input account information to search
      | Username            | noExisting |
      | First name          | noExisting |
      | Last name           | noExisting |
      | Role                | Buyer      |
      | User account status | Enabled    |
    Then Check empty table content should display
