@b2b @b2b_passwordExpired
Feature: Password expired page

  Scenario: TC01. Check password expired page header and content should displayed accordingly
    Given The user is on AAH home page
    And User login with userId "b2b.expriedUserName" and password "b2b.expriedPassword" from AAH home page
    Then The "Change Password" page is displayed
    And Header for password expired page is displayed

  Scenario Outline: TC02. Validate negative conditions that password is expired
    Given The user is on AAH home page
    And User login with userId "b2b.expriedUserName" and password "b2b.expriedPassword" from AAH home page
    When User input current password "<current password>" new password "<new password>" and "<verify password>" in password expired form
    Then User can see the validation message "<validationMessage>" from "expired password"

    Examples: 
      | current password | new password    | verify password   | validationMessage                                                                             |
      |                  | password        | password          | Please enter your current password and try again.                                             |
      | test1234         |                 |                   | This password is not valid, please try again.                                                 |
      | test1234         | validpass55word | NotSamepass55word | The verify password entered must be the same as the password entered above. Please try again. |
