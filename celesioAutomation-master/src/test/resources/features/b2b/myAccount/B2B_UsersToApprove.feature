@b2b_UsersToApprove @b2b
Feature: YOUR PROFILE - Users to approve page

  Background: 
    Given The user is on AAH home page
    And User login with userId "b2b.approver.username" and password "b2b.approver.password" from AAH home page
    When The user is on AAH home page with choosing account
    And The user clicks on AAHEnterprise icon
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on "User profiles to Approve" section from My Account page

  Scenario: TC01. Access Users to approve page and its header should display as per design
    Then The "User Profiles To Approve" page is displayed
    And Check header should be displayed in users to approve page

  Scenario: TC02. Register a new user and check for users to be approved table should display in correct format
    Given The user is on AAH  Sign-in page as a guest
    When User clicks on "Register" button
    And The register page should be displayed
    And The user provides all customized register info on the registration page
      | GenerateRandomData     | false                  |
      | username               | autoTestUserName       |
      | password               | temp1234               |
      | verifyPassword         | temp1234               |
      | accountId              | b2b.register.accountId |
      | firstName              | autoTestFirstName      |
      | lastName               | autoTestLastName       |
      | preferredContact       | autoTempContact        |
      | contactNumber          | 1234567890             |
      | preferEmail            | No                     |
      | preferSMSNotifications | No                     |
      | preferSMSPromotions    | No                     |
      | termsAccept            | Yes                    |
    And The user clicks on "Submit Request" button on registration page
    When The user is on AAH  Sign-in page as a guest
    And User login with userId "b2b.approver.username" and password "b2b.approver.password" from AAH home page
    When The user is on AAH home page with choosing account
    And The user clicks on AAHEnterprise icon
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on "User profiles to Approve" section from My Account page
    Then Check the header columns of user table displays in correct format
      | User                   |
      | Email Address          |
      | Primary Contact Number |
      | Submitted              |
    Then Check name "autoTestFirstName autoTestLastName" is found in user table

  Scenario: TC03. View details for a single user to enter approve details page
    Given User input users waiting for approval info to search
      | First Name              | autoTestFirstName |
      | Last Name               | autoTestLastName  |
      | Submitted before or on: | 2/12/2016         |
      | Submitted on or after:  | 2/1/2016          |
    When User view details for user "autoTestFirstName autoTestLastName" in search result
    Then The "Account Users To Approve Details" page is displayed

  Scenario: TC04. Search for account in users to approved page - happy path
    Given The user is on AAH  Sign-in page as a guest
    When User clicks on "Register" button
    And The register page should be displayed
    And The user provides all customized register info on the registration page
      | GenerateRandomData     | false                  |
      | username               | autoTestUserName       |
      | password               | temp1234               |
      | verifyPassword         | temp1234               |
      | accountId              | b2b.register.accountId |
      | firstName              | autoTestFirstName      |
      | lastName               | autoTestLastName       |
      | preferredContact       | autoTempContact        |
      | contactNumber          | 1234567890             |
      | preferEmail            | No                     |
      | preferSMSNotifications | No                     |
      | preferSMSPromotions    | No                     |
      | termsAccept            | Yes                    |
    And The user clicks on "Submit Request" button on registration page
    When The user is on AAH  Sign-in page as a guest
    And User login with userId "b2b.approver.username" and password "b2b.approver.password" from AAH home page
    When The user is on AAH home page with choosing account
    And The user clicks on AAHEnterprise icon
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on "User profiles to Approve" section from My Account page
    When User input users waiting for approval info to search
      | First Name              | autoTestFirstName |
      | Last Name               | autoTestLastName  |
      | Submitted before or on: | 2/12/2016         |
      | Submitted on or after:  | 2/1/2016          |
    Then Check name "autoTestFirstName autoTestLastName" is found in user table

  #    And Check user info is found in user to approved table
  #      | User                   | autoTestFirstName autoTestLastName |
  #      | Email Address          | test@mail.com                      |
  #      | Primary Contact Number | 1234567890                         |
  #      | Submitted              | 26/04/2016                         |
  
  Scenario: TC05. Search for an non-existing account - negative cases
    When User input users waiting for approval info to search
      | First Name              | noExisting |
      | Last Name               | noExisting |
      | Submitted before or on: | 2/6/2010   |
      | Submitted on or after:  | 2/6/2010   |
    And Check an empty user table should be displayed

  Scenario: TC06. Check breadcrumb on Users to Approve page
    Then Check breadcrumb should be displayed correctly
      | Home\\                   |
      | Your Profile\\           |
      | User profiles to approve |
    When Click link "Your Profile" on breadcrumb
    Then The "Your Profile" page is displayed
