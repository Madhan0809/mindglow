@b2c_footer @b2c
Feature: Check the Footer function

  Scenario: 001A - Check that newsletter section is displayed as design
    Given The user is on Lloyds Pharmacy home page
    Then The "Subscribe to our newsletter" subscribe_part is displayed
    And The "Get the latest offers and product news from Lloyds Pharmacy" subscribe_part is displayed
    And The "Your email address" subscribe_part is displayed
    And The "Subscribe" subscribe_part is displayed

  Scenario: 001B - Check that newsletter section functions as design
    Given The user is on Lloyds Pharmacy home page
    When The user clicks on "Your email address" subscribe_part
    Then The "Your email address" subscribe_part is displayed
    And The type of Email Address is "email"
    When The user enters an email address "valid@mail.com"
    And The user clicks on "Subscribe" subscribe_part

  Scenario: 002A - Check that footer header and menu links are displayed and function
    Given The user is on Lloyds Pharmacy home page
    Then The footer menu headers are displayed
      | Customer Service |
      | Shop With Us     |
      | Our Services     |
      | What's New       |
      | Partner Websites |
    Then The footer menu links are displayed
      | Contact Us                   |
      | My Account                   |
      | Returns                      |
      | Site Map                     |
      | Store Locator                |
      | Delivery and Click & Collect |
      | Cookies                      |
      | Terms and Conditions         |
      | Pharmacy Services            |
      | One-Off Prescriptions        |
      | Repeat Prescriptions         |
      | Stop Smoking Services        |
      | Great Offers                 |
      | Online Pharmacy              |
      | Pain Management Service      |
      | Ratings & Reviews            |
      | Online Doctor                |
      | John Bell & Croyden          |
      | Betterlife                   |
      | Celesio Careers              |

  Scenario: 003 - Check that Registered pharmacy logo is displayed and link functions
    Given The user is on Lloyds Pharmacy home page
    Then The Registered pharmacy logo is displayed
    When The user clicks on "Registered Pharmacy" subscribe_part
    Then Switch to new window and check if "Registers | General Pharmaceutical Council" page is displayed

  Scenario: 004 - Check that social media images are displayed and link functions
    Given The user is on Lloyds Pharmacy home page
    Then The social media images are displayed
      | Facebook    |
      | Twitter     |
      | Google-Plus |
      | Pinterest   |
      | Youtube     |
      | Our Blog    |
