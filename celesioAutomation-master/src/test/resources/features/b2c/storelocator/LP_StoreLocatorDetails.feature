@b2c_storeLocator @b2c
Feature: Store Locator Details Functionality

  Scenario: Store locator details page display
    Given The user is on Store locator details page
    Then Store details Breadcrumb "Merton" should be displayed
    And Appropriate store details map is displayed
    And Appropriate Store Labels are displayed
      | LloydsPharmacy |
      | Opening Times  |
      | Services       |
    And Relevant store locator details are displayed
    And Appropriate services are displayed
      | Diabetes Testing.           |
      | Click & Collect             |
      | Vaccinations                |
      | Stop Smoking Service        |
      | Medicines Check-up          |
      | Respiratory Support Service |
      | Online Doctor Pick-up       |
      | Blood Pressure Testing      |

  #      | Asthma Review Service  |
  Scenario: Town or Postcode search in locator details page
    Given The user is on Store locator details page
    When The user enters a town or post code "Wimbledon" in the search box
    And Click on Find Pharmacy
    Then The user should be navigated to store results page
