@b2b_search @b2b
Feature: Check the B2B Search Result Page function

  Background: 
    Given The user is on AAH  Sign-in page as a guest
    And The AutoUser2 provide username and password
    And User clicks on Sign In button
    And User dismiss all notification in header
    When The user clicks on AAHEnterprise icon

  @devsmoketest @prodsmoketest
  Scenario: TC01. Check the Search Result page site header/footer/breadcrumb/subscription is displayed as design
    Given The user is on AAH home page as a user
    When The user inputs text as "Baby change" in search box
    And The user clicks on the Search button
    Then The search page breadcrumb "Search: Baby change" is displayed on search result page
    And The page header logo is displayed on search result page
    And The Search Result text is displayed on search result page
    And The page Footer is displayed on search result page

  Scenario: TC02. Check No Search Result page
    Given The user is on AAH home page as a user
    When The user inputs text as "NonExist" in search box
    And The user clicks on the Search button
    Then The search page breadcrumb "Search: NonExist" is displayed on search result page
    And The no results message "No results for “NonExist”" is showed on search result page

  @devsmoketest @prodsmoketest
  Scenario: TC03. Check search again function and its suggestions
    Given The user is on AAH home page as a user
    When The user inputs text as "NonExist" in search box
    And The user clicks on the Search button
    Then The search page breadcrumb "Search: NonExist" is displayed on search result page
    And The no results message "No results for “NonExist”" is showed on search result page
    And The input box of Search term displays value "NonExist"
    And verify that Search again button is displayed
    And verify that Suggestions section is displayed

  Scenario: TC04. Navigating to PDP from search result page on click of product image
    Given The user is on AAH home page as a user
    When The user inputs text as "Baby change" in search box
    And The user clicks on the Search button
    Then The search page breadcrumb "Search: Baby change" is displayed on search result page
    When The user selects "Brands" option from sort by list
    Then The products on the page should be displayed in sorted by "Brands"
    When The user clicks on the product image "Headboard" on SRP
    Then Check the product name "Headboard" and description displayed in PDP

  @prodsmoketest
  Scenario: TC05. Navigating to PDP from search result page on click of product name
    Given The user is on AAH home page as a user
    When The user inputs text as "Baby change" in search box
    And The user clicks on the Search button
    Then The search page breadcrumb "Search: Baby change" is displayed on search result page
    When The user selects "Brands" option from sort by list
    Then The products on the page should be displayed in sorted by "Brands"
    When The user clicks on the product name of product "Headboard"
    Then Check the product name "Headboard" and description displayed in PDP

  Scenario: TC06. Check Left hand navigation/Sort By/Items Per Page is displayed as design
    Given The user is on AAH home page as a user
    When The user inputs text as "Baby change" in search box
    And The user clicks on the Search button
    Then The search page breadcrumb "Search: Baby change" is displayed on search result page
    And The message of found seach result is showed on search result page
    And The "FILTER BY" is showed on Left hand navigation on search result page
    When The user clicks on "Grid View" option
    Then The page should be presented with "Grid View" of products
    When The user clicks on "List View" option
    Then The page should be presented with "List View" of products

  @devsmoketest
  Scenario: TC07. Check the "Sort By" function of dropdown list works fine
    Given The user is on AAH home page as a user
    When The user inputs text as "Baby change" in search box
    And The user clicks on the Search button
    Then The search page breadcrumb "Search: Baby change" is displayed on search result page
    #Sorting by Name ascending order
    When The user selects "Brands" option from sort by list
    Then The products on the page should be displayed in sorted by "Brands"
    #Sorting by Name ascending order
    When The user selects "Name" option from sort by list
    Then The products on the page should be displayed in sorted by "Name"
    #Sorting by price in Ascending order
    #   When The user selects "Price (Low to High)" option from sort by list
    #  Then The products on the page should be displayed in sorted by "Price (Low to High)"
    #Sorting by price in Descending order
    # When The user selects "Price (High to Low)" option from sort by list
    # Then The products on the page should be displayed in sorted by "Price (High to Low)"
    And The user clicks on Signout

  @devsmoketest
  Scenario: TC08. Check the function of pagination and "Items Per Page" dropdown list works fine
    Given The user is on AAH home page as a user
    When The user inputs text as "Baby change" in search box
    And The user clicks on the Search button
    Then The search page breadcrumb "Search: Baby change" is displayed on search result page
    When The user selects on "6" from Items per page drop down
    Then The user should be presented with "6" products page
    When The user selects on "12" from Items per page drop down
    Then The user should be presented with "12" products page
    And The user clicks on Signout
