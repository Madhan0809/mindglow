@b2c_checkoutsignin @b2c
Feature: Check the Check Out Sign In function
  @b2c_failed
  Scenario: Input wrong logonID, correct error message is displayed as design.
    Given The user is on CheckOut SignIn page
    When The user inputs "test@mail" and "passw9rd" as logon ID and password on CheckOut SignIn page
    And The user clicks "Sign In" button on CheckOut SignIn page
    Then The error message for "Incorrect Credentials" is displayed on CheckOut SignIn page.

  Scenario: Input wrong pasword, correct error message is displayed as design.
    Given The user is on CheckOut SignIn page
    When The user inputs "test@mail.com" and "passw0rd" as logon ID and password on CheckOut SignIn page
    And The user clicks "Sign In" button on CheckOut SignIn page
    Then The error message for "Incorrect Credentials" is displayed on CheckOut SignIn page.

  Scenario: Empty username and password, correct error message is displayed as design.
    Given The user is on CheckOut SignIn page
    And The user clicks "Sign In" button on CheckOut SignIn page
    Then The error message for "Type a logon ID in the Logon ID field." is displayed on CheckOut SignIn page.




