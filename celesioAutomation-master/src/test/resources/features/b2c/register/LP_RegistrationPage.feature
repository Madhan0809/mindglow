@b2c @b2c_register
Feature: Lloyds Pharmacy Registration Page

  Background: 
    Given The user is on Lloyds Pharmacy home page
    When The user navigates to Registration page

  Scenario: Create an user account with address entered manuall
    When The user enters all the relevant details on the registration page
      | GenerateRandomData | true |
    And The user clicks on "Create Account" on registration page
    Then The user should be navigated to "My Account" details page

  Scenario: Create an user account with address populated with Post Code finder
    When The user enters all the relevant details on the registration page
      | GenerateRandomData | true |
      | PostCodeFinder     | home |
    And The user clicks on "Create Account" on registration page
    Then The user should be navigated to "My Account" details page

  Scenario: Existing User Account
    When The user enters all the relevant details on the registration page
      | GenerateRandomData | true |
      | ExistingUser       | true |
    And The user clicks on "Create Account" on registration page
    Then Appropriate error message "register.errorMsg" should be displayed

  Scenario: Verify labels and validation errors
    Then All the relevant text labels should be displayed
      | *Username                                             |
      | *Email                                                |
      | *Password                                             |
      | Show password                                         |
      | *Title                                                |
      | *First Name                                           |
      | *Last Name                                            |
      | *Postcode                                             |
      | *Address Line 1                                       |
      | Address Line 2                                        |
      | *Town/City                                            |
      | County                                                |
      | Country                                               |
      | *Preferred Contact Number                             |
      | *Date of Birth                                        |
      | *Gender                                               |
      | Mail                                                  |
      | Phone                                                 |
      | Email                                                 |
      | SMS                                                   |
      | * I accept the Terms & Conditions and Privacy Policy. |
    When The user clicks on "Create Account" on registration page
    Then The user should see error messages for all the mandatory fields on the page
      | Please enter a Username                                       |
      | Please enter a valid Email                                    |
      | The password field cannot be empty                            |
      | Please select a title                                         |
      #Firstname
      | Please enter alphabetical characters only                     |
      #Lastname
      | Please enter alphabetical characters only                     |
      | Please enter a valid postcode                                 |
      | Please enter a valid contact number (numbers and dashes only) |
      | Please select a day                                           |
      | Please select a month                                         |
      | Please select a year                                          |
      | You must accept the terms and conditions                      |


  Scenario: Invalid Postcode with valid format
    When The user enters postcode "invalidPostcode" on registration page and clicks postcode finder
    Then Appropriate error message "invalidPostcodeError" should be displayed

  Scenario: Entering Address Manually
    When The user clicks on "Enter address manually" on registration page
    Then Appropriate fields for entering address should be "shown"
    When The user clicks on "Enter address manually" on registration page
    Then Appropriate fields for entering address should be "hidden"
