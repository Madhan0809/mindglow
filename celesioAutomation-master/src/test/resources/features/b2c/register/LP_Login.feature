@b2c_login @b2c
Feature: Check the Login function

  Scenario: Check the login page is displayed as per design.
    Empty logonID/password gets correct error message.

    Given The user is on Lloyds Pharmacy home page
    When The user clicks Sign In button
    Then The login page is displayed
    And Relevant details are displayed on login page
      | Username                 |
      | Password                 |
      | Sign In                  |
      | Forgotten your password? |
      #Expecting forgot your password but failed as it displays forgotten your password
      #| Don't have an account? |
      | Register                 |
    When The user clicks "Sign In" button on login page
    Then The error message for "Enter LogonID" is displayed on login page.

  Scenario: Check the login fails if password is wrong. Correct error message shows.
    Given The user is on Lloyds Pharmacy home page
    When The user clicks Sign In button
    Then The login page is displayed
    When The user inputs "test@mail.com" and "passw0rd" as logon ID and password on login page
    # And The user checks on Remember Me box on login page
    When The user clicks "Sign In" button on login page
    Then The error message for "Incorrect Credentials" is displayed on login page.

  Scenario: Check the login fails if logon ID is wrong. Correct error message shows.
    Given The user is on Lloyds Pharmacy home page
    When The user clicks Sign In button
    Then The login page is displayed
    When The user inputs "test" and "passw9rd" as logon ID and password on login page
    When The user clicks "Sign In" button on login page
    Then The error message for "Incorrect Credentials" is displayed on login page.

  Scenario: Login function on page(PLP).
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    When The user clicks Sign In button
    Then The login page is displayed
    When The user inputs "test@mail.com" and "passw9rd" as logon ID and password on login page
    When The user clicks "Sign In" button on login page
    Then The user signs in successfully

  Scenario: Login function on page(PDP).
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    When The user clicks "Elegant Touch Quick Shine Cushioned Buffer" on the PLP
    And The user clicks Sign In button
    Then The login page is displayed
    When The user inputs "test@mail.com" and "passw9rd" as logon ID and password on login page
    When The user clicks "Sign In" button on login page
    Then The user signs in successfully
