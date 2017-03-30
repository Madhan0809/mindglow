@b2b @b2b_login
Feature: Login page

  Scenario: TC01. Check homepage and its header of logged in and not logged in user
    Given The user is on AAH  Sign-in page as a guest
    When User login with user name and password
#    Given The user is on AAH  Sign-in page as a guest
#    Then Header is displayed for "unlogged-in" user
#    When User login with userId "b2b.username" and password "b2b.password" from AAH home page
    And User click on continue in important messages page
    Then Login home page will be displayed
    Then Header is displayed for "logged-in" user

  Scenario: TC02. Login without account selection and check important message page is displayed
   Given The user is on AAH  Sign-in page as a guest
    Then Header is displayed for "unlogged-in" user
    When User login with userId "b2b.username" and password "b2b.password" from AAH home page
    Then The "Important Messages" page is displayed

  @devsmoketest @prodsmoketest @autouser1
  Scenario: TC03. Login with account selection and enter homepage with continue on important message page
    Given The user is on AAH home page
    When User login with userId "b2b.multiAccount.username" and password "b2b.multiAccount.password" from AAH home page
    When The user is on AAH home page with choosing account
    And User click on continue in important messages page
    Then The user is on AAH home page as a user

  Scenario Outline: TC04. Login negative path: Check validation messages for user unsuccessful login
    Given The user is on AAH home page
    And User login with userId "<loginId>" and password "<password>" from AAH home page
    Then User can see the validation message "<validationMessage>" from "AAH Home page"

    Examples: 
      | loginId   | password    | validationMessage                                                                             |
      |           | passw9rd    | Please type a valid username                                                                  |
      | systest2  |             | Please type a valid password                                                                  |
      | incorrect | correct1234 | Either the login ID or the password entered is incorrect. Please enter the information again. |
      | invalid   | invalid     | Either the login ID or the password entered is incorrect. Please enter the information again. |
