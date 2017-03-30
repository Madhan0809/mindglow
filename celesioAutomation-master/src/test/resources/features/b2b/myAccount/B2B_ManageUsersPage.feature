@b2b_manageusers @b2b
Feature: YOUR PROFILE - Manage Users page

  Background: 
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    And The user clicks on AAHEnterprise icon
    Then The user is on AAH home page as a user
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on "Manage Users" section from My Account page

  Scenario: TC01: Check user account table display in correct format and back to you account
    Then The "Manage Users" page is displayed
    Then Check the account table is in correct format
      | Name     |
      | Username |
      | Account  |
      | Role     |
      | Status   |
    When User click "Back" button in user management
    Then The "Your Profile" page is displayed

  Scenario: TC02: Click the account to enter user details page
    When User click a random account in user management
    Then The "Account User Details" page is displayed

  Scenario: TC03: Click on Add User and check add new user page is displayed
    When User click "Add User" button in user management
    Then The "Account Add User" page is displayed

  Scenario: TC04: Search for account in Manage Users page
    When User click "Add User" button in user management
    When User inputs user details with random username to add
      | firstName      | autoTestMangeUser |
      | lastName       | autoTestMangeUser |
      | primaryTel     | 1234567890        |
      | username       | autoTestMangeUser |
      | password       | temp1234          |
      | verifyPassword | temp1234          |
    And Assign role of "Buyer" for any account to add user
    When User click "Save" button in account details
    When Input account information to search in manage user page
      | Username            | autoTestMangeUser |
      | First Name          | autoTestMangeUser |
      | Last Name           | autoTestMangeUser |
      | Role                | Buyer             |
      | User Account Status | Enabled           |
    Then Check account "autoTestMangeUser autoTestMangeUser" can be searched out in manager user table

  Scenario: TC05: Search with noExisting info and verify on empty search result - Negative case
    When Input account information to search in manage user page
      | Username            | noExisting    |
      | First Name          | noExisting    |
      | Last Name           | noExisting    |
      | Role                | Administrator |
      | User Account Status | Enabled       |
    Then Check empty table content should display in manage user page

  Scenario: TC06: Action menu: View details for a single user
    When User select "View Details" from action menu for user "random" in manage user page
    Then The "Account User Details" page is displayed

  Scenario: TC07: Action menu: Disable/Enable for a new creating user
    Given User click "Add User" button in user management
    When User inputs user details with random username to add
      | firstName      | autoTestJustForDisable |
      | lastName       | autoTestJustForDisable |
      | primaryTel     | 1234567890             |
      | username       | random_NewUserName     |
      | password       | passw9rd               |
      | verifyPassword | passw9rd               |
    And Assign role of "Buyer" for account "b2b.defaultContract" to add user
    When User click "Save" button in account details
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on "Manage Users" section from My Account page
    When Search for a new creating user in manage user table
    And User select "Disable User Account" from action menu for user "random_NewUserName" in manage user page
    Then Check the account info should be found in manage user table
      | Name     | autoTestJustForDisable autoTestJustForDisable |
      | Username | random_NewUserName                            |
      | Role     | Buyer (buy-side)                              |
      | Status   | Disabled                                      |
    When User select "Enable User Account" from action menu for user "random_NewUserName" in manage user page
    Then Check the account info should be found in manage user table
      | Name     | autoTestJustForDisable autoTestJustForDisable |
      | Username | random_NewUserName                            |
      | Role     | Buyer (buy-side)                              |
      | Status   | Enabled                                       |

  Scenario: TC09: Check breadcrumb on Manage Users page
    Then Check breadcrumb should be displayed correctly
      | Home\\         |
      | Your Profile\\ |
      | Manage Users   |
    When Click link "Your Profile" on breadcrumb
    Then The "Your Profile" page is displayed
