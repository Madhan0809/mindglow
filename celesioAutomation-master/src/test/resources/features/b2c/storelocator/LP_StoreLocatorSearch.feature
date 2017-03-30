@b2c_storeLocator @b2c
Feature: Store Locator Search Functionality

  Scenario: Store locator search page display
    Given The user is on Store locator search page
    Then Store search breadcrumb "Store Locator" should be displayed
    And The "Opening times and collection" services list is displayed
      | Click & Collect     |
      | Open on Sundays     |
      | Late Night Pharmacy |
    And The "Lloyds pharmacy" services list is displayed
      #| Stop Smoking Service                   |
      | Cholesterol and Heart Check            |
      | Online Doctor HPV Vaccine              |
      #| Diabetes Testing                       |
      | Diabetes Testing                       |
      | Online Doctor ED                       |
      | Online Doctor Flu Vaccine              |
      | Online Doctor Travel Vaccine           |
      | Online Doctor EHC Dispense Single      |
      | Online Doctor Contra Pill Dispense     |
      | Prescription MOT                       |
      #| Online Doctor Travel Pack Dispense     |
      #| Online Doctor Jetlag Dispensing        |
      | Blood Pressure Testing                 |
      | Click & Collect                        |
      | Beauty and Fragrance                   |
      #| Online Doctor Anti Malarial Dispensing |
      #| Pet Medicines                          |
      #| Online Doctor Cystitis Dispensing      |

  Scenario: Town or Postcode search in locator search page
    Given The user is on Store locator search page
    When The user enters a town or post code "Wimbledon" in the search box
    And Click on Find Pharmacy
    Then The user should be navigated to store results page

  Scenario: Town or Postcode not found
    Given The user is on Store locator search page
    When The user enters a town or post code "NotFound" in the search box
    And Click on Find Pharmacy
    Then The message "Postcode or town name could not be found." should be displayed
