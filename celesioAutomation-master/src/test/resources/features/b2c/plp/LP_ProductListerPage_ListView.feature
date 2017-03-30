@b2c_plp @b2c
Feature: Lloyds Pharmacy Product Lister Page(PLP) - List View

  Background: 
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    When The user clicks on "List View"
    Then The user should be presented with "List View" of products

  Scenario: On click of Accessories Products in List view
    When The user click on the product image
    Then The user should be navigated to product details page(PDP)
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    When The user clicks on "List View"
    Then The user should be presented with "List View" of products
    When The user click on the product text
    Then The user should be navigated to product details page(PDP)

  Scenario: On click of Compare button in List view
    When The user selects compare check boxes of "2"
    And the user clicks on compare button
    Then the user should be navigated to "LloydsPharmacy - Compare Products" compare page

  Scenario Outline: On Selection of Compare Products in List view
    When The user selects compare check boxes of "<No of Products>"
    Then Labels/messages "<Label/Message>" for relevant "<No of Products>" should be displayed

    Examples: 
      | No of Products | Label/Message                 |
      | 1              | Add more to compare           |
      | 4              | Added                         |
      | 5              | Only 4 items can be compared. |

  Scenario Outline: Navigate to checkout basket from PLP List view
    When The user selects "Name (a-z)" from sort by list
    When The user update product quantity to "<quantity>" in PLP
    And The user clicks on "Add to basket"
    Then "<Item(s)>" should be added to mini basket
    When The user clicks on "Checkout"
    Then The user should be navigated to checkout basket page

    Examples: 
      | quantity | Item(s)   |
      | 1        | 1 item(s) |
      | 2        | 2 item(s) |

  #Lack of multi-variant data
  @b2c_blocked
  Scenario Outline: Multi-variant SKU in PLP list view
    When The user inputs "Aquafresh Little Teeth" in the Search text box
    And The user clicks the Search button
    And The user clicks on "List View"
    And The user selects "Name (a-z)" from sort by list
    And The user selects colour "<Colour>"
    And The user selects size "<Size>"
    And The user update product quantity to "<quantity>" in PLP
    And The user clicks on "Add to basket"
    Then "<Item(s)>" should be added to mini basket
    When The user clicks on "Checkout"
    Then The user should be navigated to checkout basket page
    And The attributes should match the ones selected in PDP

    Examples: 
      | Colour         | Size  | quantity | Item(s)   |
      | Orange         | Large | 1        | 1 item(s) |
      | Green and Blue | Small | 1        | 1 item(s) |

  Scenario: Sort by functionality in PLP List view
    #Sorting by Name ascending order
    When The user selects "Name (a-z)" from sort by list
    Then The products should be displayed in sorted by "Name (a-z)"
    #Sorting by Name descending order
    When The user selects "Name (z-a)" from sort by list
    Then The products should be displayed in sorted by "Name (z-a)"
    #Sorting by price in Ascending order
    When The user selects "Price (Low to High)" from sort by list
    Then The products should be displayed in sorted by "Price (Low to High)"
    #Sorting by price in Descending order
    When The user selects "Price (High to Low)" from sort by list
    Then The products should be displayed in sorted by "Price (High to Low)"

  Scenario: Number of products per page in List view
    When The user selects on "6" from per page drop down
    Then The user should be presented with "6" products
    When The user selects on "12" from per page drop down
    Then The user should be presented with "12" products
    When The user selects on "18" from per page drop down
    Then The user should be presented with "15" products

  Scenario: Check the Facet element header and footer in List view
    Then there are 4 facet filters displays from facet menu
    Then the footer and header should be displayed and clickable

  Scenario: Price type Facet navigation without choose price range in List view
    When The user selects choose filter item "Less than £50" from "price" filter
    Then The user should be presented "Less than £50" product types only
    And Clear all link should appear on facet menu
    And Facet filter should be applied

  Scenario: Brand Type Facet navigation in List view
    When The user selects choose filter item "b2c.brand.filter" from "brand" filter
    Then The user should be presented "b2c.brand.filter" product types only
    And Clear all link should appear on facet menu
    And Facet filter should be applied

  Scenario: Choose your own price range Facet navigation in List view
    When The user enter price type from facet menu
      | From | To |
      | 1    | 2  |
    Then the products are displayed with From_Price 1 and To_Price 2
    And Clear all link should appear on facet menu
    And Facet filter should be applied

  Scenario: Verify if show more link works in List view
    Then there are 5 rows displays from "Brand" from facet menu
    When The user click on "Show more" link from "Brand" facet menu
    Then there are 9 rows displays from "Brand" from facet menu
    When The user click on "Show less" link from "Brand" facet menu
    Then there are 5 rows displays from "Brand" from facet menu

  Scenario: Multiple filters element in List view and clear filter by element and clear all filter
    When The user selects choose filter item "b2c.brand.filter" from "brand" filter
    Then The user should be presented "b2c.brand.filter" product types only
    When The user enter price type from facet menu
      | From | To |
      | 1    | 5  |
    Then the products are displayed with From_Price 1 and To_Price 5
    And Clear all link should appear on facet menu
    And Facet filter should be applied
    When Clear "Nailcare" filter from facet menu
    Then Facet filter should be applied
    Then the products are displayed with From_Price 1 and To_Price 5

  Scenario: Click on clear all in facet menu in list view
    When The user selects choose filter item "Less than £50" from "price" filter
    And The user selects choose filter item "Lloydspharmacy" from "brand" filter
    When The user clear all filters
    Then All filters should be cleared
