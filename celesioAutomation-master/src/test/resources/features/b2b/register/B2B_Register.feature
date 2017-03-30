@b2b @b2b_register
Feature: Check in registration page with a new user

  #Enter user can enter register landing page from AAH home page
  Scenario: Navigation to Registration Page from AAH point Access Websites link
    Given The user is on AAH Point page
    When User clicks on "Sign In" link on right section
    Then User Navigate to Site Access page
    When User clicks on "Go To Wholesale OTC Online" site button
    Then User navigate to "Sign In" page
    And User clicks on "Register" button
    And The register page should be displayed

  Scenario: Navigation to Registration Page from AAH point Register link
    Given The user is on AAH Point page
    When User click on "Register" link on right section
    Then The register page should be displayed

  Scenario: Create an user account with address entered manually
    Given The user is on AAH  Sign-in page as a guest
    When User clicks on "Register" button
    And The register page should be displayed
    When The user provides all the relevant details on the registration page
      | GenerateRandomData | true |
    And The user clicks on "Submit Request" button on registration page
    Then The user should get "Request Submitted" message on Register confirmation page

  Scenario: Verify labels and validation errors
    Given The user is on AAH  Sign-in page as a guest
    When User clicks on "Register" button
    And The register page should be displayed
    Given All the relevant labels should be displayed
      | *Username                                             |
      | *Password                                             |
      | *Verify password                                      |
      | *Your AAH Account Number                              |
      | *First Name                                           |
      | *Last Name                                            |
      | Email address                                         |
      | *Primary contact number                               |
      | Alternative contact number (optional)                 |
      | Employee ID (optional)                                |
      | Additional Information (optional)                     |
      | Send me e-mails about store specials                  |
      | Send SMS notifications to mobile phone                |
      | Send SMS promotions to mobile phone                   |
      | * I accept the Terms & Conditions and Privacy Policy. |
    When The user clicks on "Submit Request" button on registration page
    Then The user should see error messages for all the mandatory fields
      | Please enter a user name                                                                      |
      | Please enter a password                                                                       |
      | The verify password entered must be the same as the password entered above. Please try again. |
      | Please enter an Account ID                                                                    |
      | Please enter a valid first name                                                               |
      | Please enter a valid last name                                                                |
      | Please enter a primary contact number                                                         |
      | You must accept the terms and conditions to continue                                          |

  Scenario: Try to register with existing User Account
    Given The user is on AAH  Sign-in page as a guest
    When User clicks on "Register" button
    And The register page should be displayed
    And The user provides all the relevant details on the registration page
      | GenerateRandomData | true |
      | ExistingUser       | true |
    And The user clicks on "Submit Request" button on registration page
    Then Appropriate error message "register.errorMsg" should be displayed on page
