@b2b @b2b_forgotUsername
Feature: Forgot user name page
 
  @devsmoketest
  Scenario: Forgot username: Check the forgot username page displayed
    Given The user is on AAH home page
    When The user clicks on "Forgot username" button in login page
    Then The "Send a Username reminder" page is displayed

  Scenario Outline: Forgot username: Check validation message for send reminder in username forgot page
    Given The user is on AAH home page
    When The user clicks on "Forgot username" button in login page
    And User input "<email address>" in forgot username page
    When User click on "Set Reminder" link in forgot username page
    Then User can see the validation message "<validationMessage>" from "Forgot username page"

    Examples: 
      | email address    | validationMessage                                                          |
      |                  | Please enter the email address associated with your account and try again. |
      | invalidFormat    | Please enter the email address associated with your account and try again. |
      | noExist@test.com | If there are any accounts registered to the e-mail you've provided, we'll send a reminder to that e-mail address |

 
  Scenario: Forgot username: User click on register link in forgot username page
    Given The user is on AAH home page
    When The user clicks on "Forgot username" button in login page
    When User click on "Register" link in forgot username page
    Then The "Register" page is displayed