@b2c_account @b2c
Feature: Check My Accounts page for New User

  Scenario: As a newly registered user Check the LHS Menu on My accounts page
    Given The user is on Lloyds Pharmacy home page
    Given The user navigates to Registration page
    When The user enters all the relevant details on the registration page
      | generateRandomData | true |
    And The user clicks on "Create Account" on registration page
    Then The user should be navigated to "My Account" details page
    Then the LHS menu on My Account consists of
      | Settings      |
      | Orders        |
      | Prescriptions |
      | Coupons       |
    Then the LHS sub menu on My Account consists of
      | Personal Information           |
      | Address/Collection Preferences |
      | Partnership Programmes         |
      | Order History                  |
      | One Off Prescriptions          |
      | Repeat Prescriptions           |
      | Coupons                        |
    When I click "Address/Collection Preferences" from  My Account LHS sub menu
    When I Edit and Update Preferred Address with name "work"
    Then the Address/Collection Preferences for user "work.myaccount" is displayed
    When I "Add New" additional address with name "home"
    Then the additional address for "home.myaccount" is displayed
@b2c_failed
  Scenario: Check for Duplicate Nick Name
    Given The user is on Lloyds Pharmacy home page
    Given The user navigates to Registration page
    When The user enters all the relevant details on the registration page
      | generateRandomData | true |
    And The user clicks on "Create Account" on registration page
    Then The user should be navigated to "My Account" details page
    When I click "Address/Collection Preferences" from  My Account LHS sub menu
    When I "Add New" additional address with name "home"
    Then the additional address for "home.myaccount" is displayed
    When I "Add New" additional address with name "home"
    Then there is an address validation error message "address.validation.message"

