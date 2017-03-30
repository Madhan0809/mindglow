@b2c_plp @b2c
Feature: Lloyds Pharmacy Product Lister Page(PLP) - Grid View

  Scenario: On click of Accessories Products
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    When The user click on the product image
    Then The user should be navigated to product details page(PDP)
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    When The user click on the product text
    Then The user should be navigated to product details page(PDP)

  Scenario: On click of Compare button in Grid view
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    When The user selects compare check boxes of "2"
    And the user clicks on compare button
    Then the user should be navigated to "LloydsPharmacy - Compare Products" compare page

  Scenario Outline: On Selection of Compare Products in Grid view
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    When The user selects compare check boxes of "<No of Products>"
    Then Labels/messages "<Label/Message>" for relevant "<No of Products>" should be displayed

    Examples: 
      | No of Products | Label/Message                 |
      | 1              | Add more to compare           |
      | 4              | Added                         |
      | 5              | Only 4 items can be compared. |

  Scenario: Navigate to checkout basket from PLP
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    When The user clicks on "Add to basket"
    Then "1 item(s)" should be added to mini basket
    When The user clicks on "Checkout"
    Then The user should be navigated to checkout basket page

  Scenario: Sort by functionality in PLP
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    #Sorting by Name ascending order
    When The user selects "Name (a-z)" from sort by list
    Then The products should be displayed in sorted by "Name (a-z)"
    #Sorting by Name descending order
    When The user selects "Name (z-a)" from sort by list
    #Then The products should be displayed in sorted by "Name (z-a)"
    #Sorting by price in Ascending order
    When The user selects "Price (Low to High)" from sort by list
    Then The products should be displayed in sorted by "Price (Low to High)"
    #Sorting by price in Descending order
    When The user selects "Price (High to Low)" from sort by list
    Then The products should be displayed in sorted by "Price (High to Low)"

  Scenario: List and Grid View
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    When The user clicks on "List View"
    Then The user should be presented with "List View" of products
    When The user clicks on "Grid View"
    Then The user should be presented with "Grid View" of products

  Scenario: Number of products per page
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    When The user selects on "6" from per page drop down
    Then The user should be presented with "6" products
    When The user selects on "12" from per page drop down
    Then The user should be presented with "12" products
    When The user selects on "18" from per page drop down
    Then The user should be presented with "15" products

  Scenario: Accessories Product lister page(PLP) display
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    Then Breadcrumb "Accessories" is displayed in PLP
  #    And Category title header label "Accessories" is displayed under department "Beauty & Fragrance"

  Scenario: Check the Facet element header and footer in grid view
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    Then there are 4 facet filters displays from facet menu
    Then the footer and header should be displayed and clickable

  Scenario: Price type Facet navigation without choose price range in grid view
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    When The user selects choose filter item "Less than £50" from "price" filter
    Then The user should be presented "Less than £50" product types only
    And Clear all link should appear on facet menu
    And Facet filter should be applied

  Scenario: Brand Type Facet navigation in grid view
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    When The user selects choose filter item "b2c.brand.filter" from "brand" filter
    Then The user should be presented "b2c.brand.filter" product types only
    And Clear all link should appear on facet menu
    And Facet filter should be applied

  Scenario: Choose your own price range Facet navigation in grid view
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    When The user enter price type from facet menu
      | From | To |
      | 1    | 2  |
    Then the products are displayed with From_Price 1 and To_Price 2
    And Clear all link should appear on facet menu
    And Facet filter should be applied

  Scenario: Verify if show more link works in grid view
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    Then there are 5 rows displays from "Brand" from facet menu
    When The user click on "Show more" link from "Brand" facet menu
    Then there are 9 rows displays from "Brand" from facet menu
    When The user click on "Show less" link from "Brand" facet menu
    Then there are 5 rows displays from "Brand" from facet menu

  Scenario: Choose for multiple filters element in grid view
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    When The user selects choose filter item "b2c.brand.filter" from "brand" filter
    Then The user should be presented "b2c.brand.filter" product types only
    When The user enter price type from facet menu
      | From | To |
      | 1    | 5  |
    Then the products are displayed with From_Price 1 and To_Price 5
    And Clear all link should appear on facet menu
    And Facet filter should be applied
  
  Scenario: Clear filter by element for multiple filters in grid view
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    When The user selects choose filter item "b2c.brand.filter" from "brand" filter
    Then The user should be presented "b2c.brand.filter" product types only
    When The user enter price type from facet menu
      | From | To |
      | 1    | 5  |
    Then the products are displayed with From_Price 1 and To_Price 5
    When Clear "brand" filter from facet menu
    Then Facet filter should be applied
    Then the products are displayed with From_Price 1 and To_Price 5

  Scenario: Click on clear all in facet menu in grid view
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    When The user selects choose filter item "Less than £50" from "price" filter
    And The user selects choose filter item "Nailcare" from "brand" filter
    When The user clear all filters
    Then All filters should be cleared
