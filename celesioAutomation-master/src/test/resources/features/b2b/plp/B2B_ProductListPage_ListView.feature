@b2b_plp_listView @b2b
Feature: B2B Product Lister Page(PLP) - List View

  Background: 
    Given The user is on AAH  Sign-in page as a guest
    When User login with user name and password

  Scenario: TC01. Navigate to target PLP list view SubCategory page
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    Then The user should be navigated to "Baby Changing" Sub Category screen

  Scenario Outline: TC02.Checking that Category product list view products and Sorting
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    Then The user should be navigated to "Baby Changing" Sub Category screen
    Then Check the text displays Number of Products on page
    And Total number of products is displayed accordingly
    When The user selects "<ItemToBeSoreted>" option from sort by list
    Then The products on the page should be displayed in sorted by "<SortedResult>"
    
      Examples:
      | ItemToBeSoreted     | SortedResult        |
      | Brands              | Brands              |
      | Name                | Name                |
      | Price (Low to High) | Price (Low to High) |
      | Price (High to Low) | Price (High to Low) |
    

  @prodsmoketest
  Scenario Outline: TC03.Checking function of pagination and "Items Per Page" dropdown list works fine - List view
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    Then The user should be navigated to "Baby Changing" Sub Category screen
    Then Check the text displays "Items Per Page" on page
    When The user selects on "<DropDownOption>" from Items per page drop down
    Then The user should be presented with "<ProductNumbers>" products page
    When The user clicks on "Grid View" option
    Then The page should be presented with "Grid View" of products

    Examples: 
      | DropDownOption | ProductNumbers |
      | 6              | 6              |
      | 12             | 12             |

  Scenario: TC04.To ensure that pagination works - List view
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    Then The user should be navigated to "Baby Changing" Sub Category screen
    When The user clicks on "next" page selection
    Then The user navigate to respective page
    When The user clicks on "previous" page selection
    Then The user navigate to respective page

  Scenario: TC05.Choose for multiple filters element and clear all - List view
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    Then The user should be navigated to "Baby Changing" Sub Category screen
    When The user selects the filter item "Single" from "SINGLE_OUTER" filter
    Then The user should be presented with "GSL" product types only
    When The user enter price range type from facet menu
      | From | To |
      | 10   | 90 |
    #    Then There is totally 5 products in PLP
    And Verify that Facet filter should be applied
    When The user selects clear all filters option
    Then All filters must be cleared

  Scenario: TC06.Select elements for multiple filters and clear filter in List view
    When The user hover on "Shop By Category" menu
    When The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    When The user selects the filter item "Single" from "SINGLE_OUTER" filter
    Then The user should be presented with "GSL" product types only
    When The user enter price range type from facet menu
      | From | To |
      | 50   | 80 |
    And Clear the "GSL" filter from facet menu
    Then Verify that Facet filter should be applied

  Scenario: TC07.Click on clear all in facet menu in List view
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    Then The user should be navigated to "Baby Changing" Sub Category screen
    When The user selects the filter item "Between £50 and £80" from "PRICE" filter
    When The user selects the filter item "Single" from "SINGLE_OUTER" filter
    When User refresh the page
    When The user selects clear all filters option
    Then All Selected filters should be cleared

  Scenario: TC08.Price type Facet navigation without choose price range in List view
    When The user hover on "Shop By Category" menu
    When The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    Then The user should be navigated to "Baby Changing" Sub Category screen
    #And  The user clicks on "List View" option
    When The user selects the filter item "Less than £6" from "PRICE" filter
    Then The user should be presented with "Less than £6" product types only
    And Clear all link should display on facet menu
    And Verify that Facet filter should be applied
    When User refresh the page
    And The user selects clear all filters option
    And The user clicks on Signout

  @prodsmoketest
  Scenario: TC09.Check that the Faceted navigation Displayed correctly
    When The user hover on "Shop By Category" menu
    When The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    Then The user should be navigated to "Baby Changing" Sub Category screen
    Then Check the "Filter By" text label
    And User can select "-" sign and Inspect the "-" sign
    And User can Click the "+" sign and inspect the "Filter By"
    And Verify the heading text for the displayed attributes
    And user can verify the price range attribute
    #Selecting  Grid View option
    When The user clicks on "Grid View" option
    Then The page should be presented with "Grid View" of products
    And The user clicks on Signout

  @devsmoketestplp
  Scenario: Navigate to SubCategory page local test
    When The user hover on "Shop By Category" menu
    When The user hover on "Great Offers" department
    And The user Select "Half Price" Category
    And The user should be navigated to "Half Price" Sub Category screen

  #To be refactored by utilizing outline
  @devsmoketestplp
  Scenario: Check that the Faceted navigation Displayed correctly local test
    When The user hover on "Shop By Category" menu
    And The user hover on "Great Offers" department
    And The "Great Offers" related category menu is displayed
    #  | Half Price    |
    #  | Great Savings |
    And The user Select "Half Price" Category
    And The user should be navigated to "Half Price" Sub Category screen
    Then Check the "Filter By" text label
    And User can select "-" sign and Inspect the "-" sign
    And User can Click the "+" sign and inspect the "Filter By"
    And Verify the heading text for the displayed attributes
    And user can verify the price range attribute
    #Selecting  Grid View option
    When The user clicks on "Grid View" option
    Then The page should be presented with "Grid View" of products
    #Selecting  List View option
    When The user clicks on "List View" option
    Then The page should be presented with "List View" of products
    And The user clicks on Signout

  Scenario: TC10.Verifying Create and Add to Favourites functionality from PLP
    Given The user is navigating to PLP
    And go to "b2b.product.huggies" product and verify "Add to favourites" display correctly
    When User click on "Add to favourites" link from PLP
    Then The user navigates to "Add to a favourites list" pop-up
    When The user select "Create a new favourites list" option
    And add new favourite as "Test Favourite PDP" with random data
    When click on "Add to favourites" button
    Then verify added confirmation message displayed on page
    #Data cleaning: Delete the newly created fav list
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on "Favourites" section from My Account page
    And User click on "Delete list" under action menu for favourite list "plp.random.list"

  Scenario: TC11.Verifying Create and Add to Favourites verify message from PLP
    Given The user is navigating to PLP
    And go to "b2b.product.huggies" product and verify "Add to favourites" display correctly
    When User click on "Add to favourites" link from PLP
    Then The user navigates to "Add to a favourites list" pop-up
    When The user select "Create a new favourites list" option
    And add new favourite as "Test Favourite PLP"
    When click on "Add to favourites" button
    Then verify added confirmation message displayed on page
    
  Scenario: TC12.Verifying Add to Favourites functionality from PLP
    Given The user is navigating to PLP
    And go to "b2b.product.huggies" product and verify "Add to favourites" display correctly
    When User click on "Add to favourites" link from PLP
    Then The user navigates to "Add to a favourites list" pop-up
    When The user select "autoDefaultList" option
    When click on "Add to favourites" button
    Then verify validation message of adding favourite list on page
