@b2b @b2b_forgotPassword
Feature: Forgot password page

  @devsmoketest
  Scenario: Forgot password: Check the forgot user password page display
    Given The user is on AAH home page
    When The user clicks on "Forgot password" button in login page
    Then The "Forget Password?" page is displayed

  Scenario: Forgot password: User select register in forgot password page
    Given The user is on AAH home page
    When The user clicks on "Forgot password" button in login page
    When User click on "register" link in forgot password page
    Then The "Register" page is displayed

  Scenario Outline: Forgot password: Check validation message for negative cases
    Given The user is on AAH home page
    When The user clicks on "Forgot password" button in login page
    And User input "<loginId>" in forgot password page
    When User click on "Send me my reset code" link in forgot password page
    Then User can see the validation message "<validationMessage>" from "Forgot password page"

    Examples: 
      | loginId     | validationMessage                                                          |
      |             | Please type a valid username                                               |
      | invalid!123 | The logon ID that you entered is not valid. Check your entry and try again. |

  @devsmoketest
  Scenario: Forgot password: Check password reset page is displayed from forgot password page
    Given The user is on AAH home page
    When The user clicks on "Forgot password" button in login page
    And User input "negative.testuser" in forgot password page
    When User click on "Send me my reset code" link in forgot password page
    Then The "Password" page is displayed
    And Password sent to user page content is displayed correctly
    When Continue to login page from password sent page
    Then The "Sign In" page is displayed 
