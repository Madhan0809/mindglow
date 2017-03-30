@b2b_ResetPassword @b2b
Feature: YOUR PROFILE - Users details - Reset password page

  Background: 
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    Then The user is on AAH home page as a user
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on "Manage Users" section from My Account page
    When User click "Add User" button in user management
    When User inputs user details with random username to add
      | firstName      | autoTestJustForReset |
      | lastName       | autoTestJustForReset |
      | primaryTel     | 1234567890           |
      | username       | autoTestJustForReset |
      | password       | temp1234             |
      | verifyPassword | temp1234             |
    And Assign role of "Buyer" for any account to add user
    When User click "Save" button in account details
    When Input account information to search in manage user page
      | Username   | autoTestJustForReset |
      | First Name | autoTestJustForReset |
      | Last Name  | autoTestJustForReset |
    When User click account name "autoTestJustForReset autoTestJustForReset" from account table in user management
    And User click reset password in user details page

  Scenario: TC01.Check the reset password header display in correct format
    Then The "Account User Details" page is displayed
    And Check reset password header of "Reset password for autoTestJustForReset autoTestJustForReset" display in correct format

  Scenario: TC02.Reset user password and check the reset successful popup should display - Happy path
    When User input a new random password in reset password page
    Then Check reset successful message should display

  Scenario: TC03.Check validation message when input no information in reset password page
    When User input reset password information in reset password page
      | Create a Password |  |
      | Verify Password   |  |
    Then Check validation message in reset password page
      | This password is not valid, please try again.                                                 |
      | The verify password entered must be the same as the password entered above. Please try again. |
