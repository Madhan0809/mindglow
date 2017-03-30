@b2b_favourites_list_details @b2b
Feature: YOUR PROFILE - FavouriteListDetails

  Background: Check add user form content displaying
    Given The user is on AAH  Sign-in page as a guest
    When User login with user name and password
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on "Favourites" section from My Account page
    Given User setup a default favourite list "favourite.default.list"

  Scenario: TC01. Check on favourite list details content
    When User click on name of "favourite.default.list" in favourite list page
    Then The "Favourites List Details" page is displayed
    And Favourite details header of "favourite.default.list" is displayed correctly
    And Check favourite details table content header is displayed as expected
      | Line                |
      | Product Code        |
      | Product Description |
      | Pack Size           |
      | Unit Price          |
      | Quantity            |
      | Select              |

  Scenario: TC02. Add valid product code and qty into favourite list and check favorite detail table content
    When User click on name of "favourite.default.list" in favourite list page
    Given User clear all product content in the favourite details table
    When User enter product code "quick.order.sku" and quantity 1 to add into favourite list
    Then The creating message info "Item added to the Favourite list successfully" is displayed in header
    Then Check product "b2b.product.favourite" is added into favourite details table

  Scenario: TC03. Update the qty of product and check on favourit list page
    When User click on name of "favourite.default.list" in favourite list page
    Given User clear all product content in the favourite details table
    When User enter product code "quick.order.sku" and quantity 1 to add into favourite list
    And User update the quantity of product "b2b.product.favourite" to 2 and apply change
    Then Check the value of quantity for "b2b.product.favourite" is 2

  Scenario: TC04. Make a public/private favourite list and check it in its own table
    When User create a new dynamic favourite list "favourite.list"
    And User click on name of "dynamic.favourite.list" in favourite list page
    And User make the favourite list with "public" status
    And User click on breadCrumb link "Favourites" in favourite details page
    Then Check the favourite list "favourite.list" is not found in current table
    When User click on tab "Organisation Favourites" in favourite list page
    Then Check favourite list "favourite.list" is existed in current table
    When User click on name of "dynamic.favourite.list" in favourite list page
    And User make the favourite list with "private" status
    And User click on breadCrumb link "Favourites" in favourite details page
    Then Check favourite list "favourite.list" is existed in current table
    And User click on "Delete list" under action menu for favourite list "favourite.list"

  Scenario: TC05. Update the favourite list name which is not empty
    When User click on name of "favourite.default.list" in favourite list page
    And User update favourite list as dynamic name "autoTest_DefaultList_Update" and apply the change
    And User click on breadCrumb link "Favourites" in favourite details page
    Then Check updated favourite list is existed in favourite list page
    And Data cleaning to delete the new updated favourite list

  Scenario: TC06. Add favourite list into current order
    Given User clear the all content in default basket "b2b.aah.currentBasket"
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on "Favourites" section from My Account page
    When User click on name of "favourite.default.list" in favourite list page
    Given User clear all product content in the favourite details table
    And User enter product code "quick.order.sku" and quantity 1 to add into favourite list
    When User click on add to current order button
    When The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    And The user click the AAH mini cart button
    Then The product name "b2b.product.favourite" is found in current order
  
  Scenario: TC07. Check breadcrumb on Favourite List Detals page
    When User click on name of "favourite.default.list" in favourite list page
    Then The "Favourites List Details" page is displayed
    Then Check breadcrumb should be displayed correctly
      | Home\\          |
      | Your Profile\\  |
      | Favourites\\    |
      | autoDefaultList |
    When Click link "Favourites" on breadcrumb
    Then The "Favourites" page is displayed
    When Click link "Your Profile" on breadcrumb
    Then The "Your Profile" page is displayed
