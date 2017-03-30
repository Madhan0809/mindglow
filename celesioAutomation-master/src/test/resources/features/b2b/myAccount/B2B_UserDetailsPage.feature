@b2b_UsersDetails @b2b
Feature: YOUR PROFILE - Users details page

  Background: 
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    And The user clicks on AAHEnterprise icon
    Then The user is on AAH home page as a user
    And User close the cookie policy bar on the page
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on "Manage Users" section from My Account page

  Scenario: TC01. Check on order details content format
    When User click a random account in user management
    Then The "Account User Details" page is displayed
    Then User details page content should be displayed in correct format

  Scenario: TC02. Verify search user functionality in user details page - happy path
    When User click a random account in user management
    And User input "b2b.default.aah.Contract" and search account in add user page
    Then Account "b2b.defaultContract" should be displayed in search result

  Scenario: TC03. Search for an no existing user in user details page - negative path
    When User click a random account in user management
    And User input "noExisting" and search account in add user page
    Then Check empty table should display in search result

  Scenario: TC04. Add a new user and assign role in user details page and save changes
    When User click "Add User" button in user management
    When User inputs user details with random username to add
      | firstName      | autoTestJustForSearching |
      | lastName       | autoTestJustForSearching |
      | primaryTel     | 1234567890               |
      | username       | autoTestJustForSearching |
      | password       | temp1234                 |
      | verifyPassword | temp1234                 |
    And Assign role of "Buyer" for any account to add user
    When User click "Save" button in account details
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on "Manage Users" section from My Account page
    When Input account information to search in manage user page
      | Username            | autoTestJustForSearching |
      | First Name          | autoTestJustForSearching |
      | Last Name           | autoTestJustForSearching |
      | Role                | Buyer                    |
      | User Account Status | Enabled                  |
    When User select "View Details" from action menu for user "random_NewUserName" in manage user page
    #    And User click account name "autoTestJustForSearching autoTestJustForSearching" from account table in user management
    And Assign role of "Approver" for any account to add user
    When User save changes in user details page
    When Input account information to search in manage user page
      | Username            | autoTestJustForSearching |
      | First Name          | autoTestJustForSearching |
      | Last Name           | autoTestJustForSearching |
      | Role                | Approver                 |
      | User Account Status | Enabled                  |
    Then Check account "autoTestJustForSearching autoTestJustForSearching" can be searched out in manager user table

  Scenario: TC05. Check user details should display as expected
    When User click "Add User" button in user management
    When User inputs user details with random username to add
      | firstName      | autoTestJustForSearching |
      | lastName       | autoTestJustForSearching |
      | primaryTel     | 1234567890               |
      | email          | test@salmon.com          |
      | username       | autoTestJustForSearching |
      | password       | temp1234                 |
      | verifyPassword | temp1234                 |
    And Assign role of "Buyer" for any account to add user
    When User click "Save" button in account details
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on "Manage Users" section from My Account page
    When Input account information to search in manage user page
      | Username            | autoTestJustForSearching |
      | First Name          | autoTestJustForSearching |
      | Last Name           | autoTestJustForSearching |
      | Role                | Buyer                    |
      | User Account Status | Enabled                  |
    When User click account name "autoTestJustForSearching autoTestJustForSearching" from account table in user management
    Then User details info should display as expected format
      | Username:               |
      | Name:                   |
      | Email:                  |
      | Primary contact number: |
      | Alternative number:     |
      | Employee ID:            |

  Scenario: TC06. Click on reset password to enter reset user password page
    When User click a random account in user management
    And User click reset password in user details page
    Then The "Account User Details" page is displayed

  Scenario: TC07. Check breadcrumb on User Details page
    When User click a random account in user management
    Then The "Account User Details" page is displayed
    Then Check breadcrumb should be displayed correctly
      | Home\\         |
      | Your Profile\\ |
      | Manage Users\\ |
      | User Details   |
    When Click link "Manage Users" on breadcrumb
    Then The "Manage Users" page is displayed
    When Click link "Your Profile" on breadcrumb
    Then The "Your Profile" page is displayed

  Scenario: TC08. Check breadcrumb on Reset User Password page
    When User click a random account in user management
    Then The "Account User Details" page is displayed
    When User click reset password in user details page
    Then Check breadcrumb should be displayed correctly
      | Home\\              |
      | Your Profile\\      |
      | Manage Users\\      |
      | User Details\\      |
      | Reset User Password |
    When Click link "User Details" on breadcrumb
    Then The "Account User Details" page is displayed
    When Click link "Users" on breadcrumb
    Then The "Manage Users" page is displayed
    When Click link "Your Profile" on breadcrumb
    Then The "Your Profile" page is displayed
