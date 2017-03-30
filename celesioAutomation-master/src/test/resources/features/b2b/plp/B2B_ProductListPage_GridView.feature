@b2b @b2b_plp_gridView
Feature: B2b Product Lister Page(PLP) Grid View

  Background: 
    Given User login with default multi account
 #   Given The user is on AAH  Sign-in page as a guest
 #   And The AutoUser2 provide username and password
 #   And User clicks on Sign In button
 #   And User dismiss all notification in header
 #   And The user clicks on AAHEnterprise icon
 #   When The user hover on "Shop By Category" menu
 #   When The user hover on "Baby & Children" department
 #   And The user Select "Baby Changing" Category
    Then The user is on AAH home page as a user
    When User enters PLP by selecting default category
 #   Then The user should be navigated to "Baby Changing" Sub Category screen
    When The user clicks on "Grid View" option

  Scenario: TC01. Check that the selected page view persists when page changed
    Then The page should be presented with "Grid View" of products
    When The user clicks on "next" page selection
    Then The page should be presented with "Grid View" of products
    When The user clicks on "previous" page selection
    Then The page should be presented with "Grid View" of products
    When User refresh the page
    Then The view returns to the default grid view

  Scenario: TC02. Check that Product Details view is displayed on click product image
    Then The page should be presented with "Grid View" of products
    When The user clicks on the product image
    Then The user should be navigated to the product details page

  Scenario: TC03. Check that Product Details view is displayed on click product text
    Then The page should be presented with "Grid View" of products
    When The user clicks on the product text
    Then The user should be navigated to the product details page

  Scenario: TC04. Choose for multiple filters element and clear all - Grid view
    When The user selects the filter item "first option" from "PRICE" filter
    And The user selects the filter item "first option" from "star" filter
    And The user selects clear all filters
    Then All filters must be cleared

  Scenario Outline: TC05. Check sorting function of products in grid view products
    Then Check the text displays Number of Products on page
    And Total number of products is displayed accordingly
    When The user selects "<ItemToBeSoreted>" option from sort by list
    Then The products on the page should be displayed in sorted by "<SortedResult>"

    Examples: 
      | ItemToBeSoreted     | SortedResult        |
      | Brands              | Brands              |
      | Name                | Name                |

  Scenario Outline: TC06. Checking function of pagination and "Items Per Page" dropdown list works fine - Grid view
    Then Check the text displays "Items Per Page" on page
    When The user selects on "<DropDownOption>" from Items per page drop down
    Then The user should be presented with "<ProductNumbers>" products page
    When The user clicks on "List View" option
    Then The page should be presented with "List View" of products

    Examples: 
      | DropDownOption | ProductNumbers |
      | 6              | 6              |
      | 12             | 12             |

  Scenario: TC07. To ensure that pagination works - Grid view
    When The user clicks on "next" page selection
    #And  The user inspects that "next" and "previous" are displayed
    Then The user navigate to respective page
    When The user clicks on "previous" page selection
    Then The user navigate to respective page

  Scenario: TC08. Check mini-shop cart display accordingly in PLP while adding product into basket
    Given User clear the all content in default basket "b2b.aah.currentBasket"
    When The user hover on "Shop By Category" menu
    When The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    Then The user should be navigated to "Baby Changing" Sub Category screen
    When Add product "b2b.product.rimmel" into target basket "b2b.aah.currentBasket"
    And The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    Then "1" items should be added into "AAH" basket

  Scenario: TC09. Check that Product Details view is displayed on click product image
    Then The page should be presented with "Grid View" of products
    When The user clicks on the product image
    Then The user should be navigated to the product details page

  #For dev test, not in regression
  @devsmoketestplp
  Scenario: TC10. Check that Product Details view is displayed when click product image local test
    Then The page should be presented with "Grid View" of products
    When The user clicks on the product image
    Then The user should be navigated to the product details page
