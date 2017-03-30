@b2c_home @b2c
Feature: Lloyds Pharmacy Home Page

  Scenario: Home icon
    Given The user is on home page as a guest
    When The user clicks on Home icon
    Then The user shall be navigated to the home page

  Scenario: Store locator icon
    Given The user is on home page as a guest
    When The user clicks on Store locator icon
    Then The user shall be navigated to store locator page

  Scenario: Sign-in icon
    Given The user is on home page as a guest
    When The user clicks on Sign-in icon
    Then sign-in/register panel will be displayed

  Scenario: Sign into Online Doctor
    Given The user is on home page as a guest
    When The user clicks on Sign into Online Doctor icon
    Then The user shall be navigated to the Online Doctor website with the title "Online Doctor | LloydsPharmacy Online Doctor UK".

  Scenario: View our Blog icon
    Given The user is on home page as a guest
    When The user clicks on View our Blog icon
    Then The user shall be navigated to the blog page with the title "LloydsPharmacy Blog | How Are You Today?".

  Scenario: Contact Us icon
    Given The user is on home page as a guest
    When The user clicks on Contact us icon
    Then The user shall be navigated to the contact us page with the title "Contact Us".

  Scenario: Brand logo image
    Given The user is on home page as a guest
    When The user clicks on Brand logo image
    Then The user shall be navigated to the home page

  Scenario: Sign-out icon
    Given The user is on Lloyds Pharmacy home page
    Given The user navigates to Registration page
    When The user enters all the relevant details on the registration page
      | generateRandomData | true |
    And The user clicks on "Create Account" on registration page
    When The user click on Sign-out text link
    Then The user should be logged out of site
