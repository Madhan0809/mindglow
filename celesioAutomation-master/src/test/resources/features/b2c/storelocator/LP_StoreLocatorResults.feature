@b2c_storeLocator @b2c
Feature: Store Locator Results Functionality

  Scenario: Store locator results page display
    Given The user is on Store locator results page
    Then Store Results Breadcrumb "Results" should be displayed
    Then "Pharmacies near "Wimbledon"" location is displayed
    And Appropriate store locator map is displayed
    And Relevant store locator results are displayed

  Scenario: Town or Postcode store result not found
    Given The user is on Store locator results page
    When The user enters a town or post code "NotFound" in the search box
    And Click on Find Pharmacy
    Then The message "Postcode or town name could not be found." should be displayed

  Scenario: Town or Postcode search in locator results page
    Given The user is on Store locator results page
    When The user enters "Paddington" in the stores results search box
    And Click on Find Pharmacy
    Then "Pharmacies near "Paddington"" location is displayed
    And Appropriate store locator map is displayed
    And Relevant store locator results are displayed

  Scenario: View store details
    Given The user is on Store locator results page
    When The user clicks on view store details link
    Then The user should be navigated to "Merton" store details page