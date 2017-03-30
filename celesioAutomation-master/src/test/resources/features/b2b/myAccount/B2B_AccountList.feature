@b2b_accountlist @b2b
Feature: YOUR PROFILE - Account list page

  Scenario: TC01. Check account list name, logo, account number and content displaying correctly
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    And User close the cookie policy bar on the page
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on Account Details link from My Account page
    Then Check the account name "b2b.defaultContract" should display in above of the account list
    And Account logo and content should display in account list

  Scenario: TC02. Check breadcrumb in account list page
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    And User close the cookie policy bar on the page
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on Account Details link from My Account page
    Then Check breadcrumb should be displayed correctly
      | Home\\          |
      | Your Profile\\  |
      | Account Details |
    When Click link "Your Profile" on breadcrumb
    Then The "Your Profile" page is displayed

  Scenario: TC03. Check preferences is greyed out for account which is not a primer user
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    Then The user is on AAH home page as a user
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on Account Details link from My Account page
    Then Check preferences should be displayed with "enabled" status
    And User select "Sweep basket automatically" in account preferences and save changes
    Then Check the option is set to "Sweep basket automatically" in account preferences
    And User select "Manually send orders" in account preferences and save changes
    Then Check the option is set to "Manually send orders" in account preferences

  Scenario: TC04. Check preferences displaying and select on radios with multi-account contracts
    Given The user is on AAH  Sign-in page as a guest
    When User login with userId "b2b.approver.username" and password "b2b.approver.password" from AAH home page
    And The user is on AAH home page with choosing account
    And The user clicks on AAHEnterprise icon
    And The user is on AAH home page as a user
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on Account Details link from My Account page
    Then Check preferences should be displayed with "grey" status

  Scenario: TC05. Search area is displayed for account which is linked to AAH and Enterprise account
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    Then The user is on AAH home page as a user
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on Account Details link from My Account page
    Then Check search area displaying status is "true" in account list

  Scenario Outline: TC06. Search for account with valid and invalid search data
    Given The user is on AAH  Sign-in page as a guest
    When The user provide multi account username and password
    And User clicks on Sign In button
    And The user is on AAH home page with choosing account
    And The user clicks on AAHEnterprise icon
    Then The user is on AAH home page as a user
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on Account Details link from My Account page
    When User input "<searchText>" and search account in account details page
    Then Check the account name "<searchResult>" should display in above of the account list
    When User clear results in search form
    Then Check the account list should display as valid value

    Examples: 
      | searchText               | searchResult        |
      | b2b.default.aah.Contract | b2b.defaultContract |
      | noExisting               |                     |

  Scenario: TC07. Back to your profile page from account overview
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    And The user clicks on AAHEnterprise icon
    Then The user is on AAH home page as a user
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on Account Details link from My Account page
    When User click "Back" button in account details
    Then The "Your Profile" page is displayed
