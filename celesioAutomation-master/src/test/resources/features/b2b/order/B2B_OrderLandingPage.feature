@b2b_Generic_Ordering_Landing_Page @b2b
Feature: Generic Ordering Landing Page for OTC Orders

  @prodsmoketest
  Scenario: TC01. Verify OTC Ordering Landing page labels and look and feel
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    When User clicks on Sign In button
    And The user is on AAH home page with choosing account
    And The user is on AAH home page as a user
    When User click on "YOUR PROFILE" from main navigation menu
    Then The "Your Profile" page is displayed
    And verify the Your Profile headers are displayed
      | YOUR DETAILS |
      | YOUR ORDERS  |
      | ADMIN        |
    And verify profile links are displayed
      | YOUR DETAILS                |
      | ACCOUNT DETAILS             |
      | SENT ORDERS                 |
      | MY BASKETS                  |
      | FAVOURITES                  |
      | MANAGE USERS                |
      | USER PROFILES TO APPROVE    |
      | ORDERS WAITING FOR APPROVAL |

  @prodsmoketest
  Scenario: TC02. Verify OTC Ordering Landing page - User profiles to Approve link
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    When User clicks on Sign In button
    And The user is on AAH home page with choosing account
    And User click on continue in important messages page
    Then The user is on AAH home page as a user
    And User dismiss all notification in header
    And User close the cookie policy bar on the page
    When User click on "YOUR PROFILE" from main navigation menu
    Then User navigate to "Your Profile" page
    And User clicks on "USER PROFILES TO APPROVE" selection
    And User Navigate to "User Profiles To Approve" page

  @prodsmoketest
  Scenario: TC03. Verify OTC Ordering Landing page - Orders waiting for approval link
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    When User clicks on Sign In button
    Then User Navigate to "Account Selection" page
    When User can choose "first" account from accounts dropdown
    And clicks on "Shop Now" button
    And User click on continue in important messages page
    And User dismiss all notification in header
    And User close the cookie policy bar on the page
    And The user is on AAH home page as a user
    When User click on "YOUR PROFILE" from main navigation menu
    Then The "Your Profile" page is displayed
    And User clicks on "ORDERS WAITING FOR APPROVAL" selection
    And User Navigate to "Orders Waiting for Approval" page
