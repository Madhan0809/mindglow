@b2b @b2b_site_selection
Feature: Site selection page

  @devsmoketest @prodsmoketest
  Scenario Outline: Select site page: User can enter login landing page from different entry point
    Given The user is on landing page of entry point
    Then The "Site Select Page" page is displayed
    And User select entry point of "<Enter website name>"
    Then The "Sign In" page is displayed

    Examples: 
      | Enter website name |
      | My AAH Orders      |
      | Shop Now           |

  Scenario: Click new user button to enter register page
    Given The user is on landing page of entry point
    When User click on "New User" button in header in site selection page
    Then The "Register" page is displayed

  Scenario: Check page header and logos are displayed correctly
    Given The user is on landing page of entry point
    Then Page header of "HOW CAN WE HELP YOU TODAY?" is displayed
    And Check if the logo image for AAH and Enterprise is displayed
