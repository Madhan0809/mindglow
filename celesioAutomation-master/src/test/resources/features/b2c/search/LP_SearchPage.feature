@b2c_search @b2c
Feature: Check the Search Result Page function

  Scenario: Check the site header/footer/breadcrumb/subscription is displayed as design
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "accessories" in the Search text box
    And The user clicks the Search button
    Then The search breadcrumb "Search: accessories" is displayed on search result page
    And The header logo is displayed on search result page
    And The Footer is displayed on search result page
    And The "Get the latest offers and product news from Lloyds Pharmacy" subscribe_part is displayed
    And The "Your email address" subscribe_part is displayed
    And The "Subscribe" subscribe_part is displayed

  Scenario: Check No Search Result page
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "NonExist" in the Search text box
    And The user clicks the Search button
    Then The search breadcrumb "Search: NonExist" is displayed on search result page
    And The no results message "Sorry, we didn't find anything for "NonExist"" is displayed on search result page

  Scenario: Check Left hand navigation/Sort By/Items Per Page is displayed as design
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "accessories" in the Search text box
    And The user clicks the Search button
    Then The search breadcrumb "Search: accessories" is displayed on search result page
    And The message "We found (\d+) results for   "accessories"" is displayed on search result page
    And The "Filter by" is displayed on Left hand navigation on search result page
    And The side bar panel is displayed on Left hand navigation on search result page
      | BetterlifefromLloydsPharmacy |
      | KeepingMobile                |
      | BeautyFragrance              |
      | MakeUp                       |
      | Toiletries                   |
    And The dropdown list is displayed on search result page
      | Sort By:  |
      | Per Page: |
    When The user clicks on "List View"
    Then The user should be presented with "List View" of products
    When The user clicks on "Grid View"
    Then The user should be presented with "Grid View" of products

  Scenario: On click of products on search page is displayed as per design
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "accessories" in the Search text box
    And The user clicks the Search button
    Then The search breadcrumb "Search: accessories" is displayed on search result page
    When The user clicks the image of prodcut "Ardell Fashion Lashes 105 Black"
    Then The PDP of "Ardell Fashion Lashes 105 Black" opens
    When The user inputs "accessories" in the Search text box
    And The user clicks the Search button
    When The user clicks the name of product "3M Littmann Classic II S.E Stethoscope Black Edition 28 in / 71 cm"
    Then The PDP of "3M Littmann Classic II S.E Stethoscope Black Edition 28 in / 71 cm" opens

  Scenario: Check the function of "Sort By" dropdown list works fine
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "eye" in the Search text box
    And The user clicks the Search button
    Then The search breadcrumb "Search: eye" is displayed on search result page
    #Sorting by Name ascending order
    When The user selects "Name (a-z)" from sort by list
    Then The products should be displayed in sorted by "Name (a-z)"
    #Sorting by Name ascending order
    When The user selects "Name (z-a)" from sort by list
    Then The products should be displayed in sorted by "Name (z-a)"
    #Sorting by price in Ascending order
    When The user selects "Price (Low to High)" from sort by list
    Then The products should be displayed in sorted by "Price (Low to High)"
    #Sorting by price in Descending order
#    When The user selects "Price (High to Low)" from sort by list
#    Then The products should be displayed in sorted by "Price (High to Low)"

  Scenario: Check the function of pagination & "Per Page" dropdown list works fine
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "accessories" in the Search text box
    And The user clicks the Search button
    Then The search breadcrumb "Search: accessories" is displayed on search result page
    When The user selects on "6" from per page drop down
    Then The user should be presented with "6" products
    When The user selects on "12" from per page drop down
    Then The user should be presented with "12" products
    When The user selects on "18" from per page drop down
    Then The user should be presented with "18" products

  Scenario: Check the function of Tab works fine
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "accessories" in the Search text box
    And The user clicks the Search button
    Then The search breadcrumb "Search: accessories" is displayed on search result page
    When The user clicks "Products" tab on search result page
    Then "Products" are shown on search result page
    When The user clicks "Prescriptions" tab on search result page
    Then "Prescriptions" are shown on search result page
    When The user inputs "smoking" in the Search text box
    And The user clicks the Search button
    When The user clicks "Help and Advice" tab on search result page
    Then "Articles" are shown on search result page
