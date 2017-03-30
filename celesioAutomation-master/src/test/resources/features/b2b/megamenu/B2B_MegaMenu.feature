@b2b_megaMenu @b2b
Feature: B2B Enterprise Mega Menu

  Background: 
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    Then User Navigate to "Account Selection" page
    When User can choose "first" account from accounts dropdown
    And clicks on "Shop complete" button
    And User dismiss all notification in header
    And User close the cookie policy bar on the page
    When The user clicks on AAHEnterprise icon
    And The user is on AAH home page as a user
    When The user hover on "Shop By Category" menu
    Then The list of values is displayed with full menu

  Scenario: TC01. On mouse hover of Great Offers
    When The user hover on "Baby & Children" department
    And The "Baby & Children" related category menu is displayed
    And Respective view all link "View all Baby & Children" should be displayed
    And The user clicks on Signout

  Scenario: TC02. On click of sub categories department
    When The user click on target department "Baby & Children" from mega menu
    Then The user should be navigated to "Baby & Children | Enterprise" department screen
    And The user clicks on Signout

  Scenario: TC03. Check hover on department and click on sub link
    When The user hover on "Baby & Children" department
    And The user clicked on  "View all test category" link
    Then The user should be navigated to "Baby & Children | Enterprise" department screen
    And The user clicks on Signout
