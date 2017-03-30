@b2b_accountAdduser @b2b
Feature: Account Overview - Users tab - Add User page

  Background: 
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    Then The user is on AAH home page as a user
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on Account Details link from My Account page
    When User click view details for any account in account list
    #    When User click view details for account "b2b.defaultContract"
    And User click on "Users" tab in account details
    And User click "Add User" button in account details
    Then The "Account Add User" page is displayed

  Scenario: TC01. Check add user form content displaying as per design
    Then Check add user form content displays in correct format
      | * First Name               |
      | * Last Name                |
      | Email                      |
      | * Primary Contact Number   |
      | Alternative Contact Number |
      | Employee ID                |
      | * Username                 |
      | * Password                 |
      | * Verify Password          |
    And Check role assignment panel is displayed

  Scenario: TC02. Add a new user to save - Happy path
    When User inputs user details with random username to add
      | firstName      | autoTestFirstName  |
      | lastName       | autoTestLastName   |
      | primaryTel     | 1234567890         |
      | username       | random_NewUserName |
      | password       | passw9rd           |
      | verifyPassword | passw9rd           |
    And Assign role of "Buyer" for account "b2b.defaultContract" to add user
    When User click "Save" button in account details
    Then Check a new user is created

  Scenario: TC03. Search for account in add Users page - happy path
    And User input "b2b.default.aah.Contract" and search account in add user page
    Then Account "b2b.defaultContract" should be displayed in search result

  Scenario: TC04. Search for no existing account in add Users page - negative case
    And User input "noExisting" and search account in add user page
    Then Check empty table should display in search result

  Scenario: TC05. Check validation message when mandatory field are omited
    When User inputs user details with random username to add
      | firstName      |         |
      | lastName       |         |
      | primaryTel     | invalid |
      | username       |         |
      | password       |         |
      | verifyPassword |         |
    When User click "Save" button in account details
    Then Check error message in account details page
      | Please enter a first name                                                                     |
      | Please enter a last name                                                                      |
      | Please enter a primary contact number                                                         |
      | Please enter a username                                                                       |
      | Please enter a password                                                                       |
      | The verify password entered must be the same as the password entered above. Please try again. |

  Scenario: TC06. Check breadcrumb on Add User page
    Then Check breadcrumb should be displayed correctly
      | Home\\         |
      | Your Profile\\ |
      | Manage Users\\ |
      | Add User       |
    When Click link "Manage Users" on breadcrumb
    Then The "Manage Users" page is displayed
    When Click link "Your Profile" on breadcrumb
    Then The "Your Profile" page is displayed
