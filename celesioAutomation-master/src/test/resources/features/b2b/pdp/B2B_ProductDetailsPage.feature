@b2b_pdp @b2b
Feature: B2b Product Detail Page(PDP)

  Background: 
    Given The user is on AAH  Sign-in page as a guest
    And The AutoUser2 provide username and password
    And User clicks on Sign In button
    And User dismiss all notification in header
    And User close the cookie policy bar on the page
    When The user clicks on AAHEnterprise icon
    And The user is on AAH home page as a user

  #Review star empty. Should be data issue and just hold on for the moment
  @systest_only @b2b_blocked
  Scenario: TC01. Check product with rating value details page(PDP) display
    Given The user is navigating to PDP on click "b2b.product.huggies" product Name
    Then Check the product name "b2b.product.huggies" and description display in PDP
    Then Check the product rating is 4 in PDP
    And All the information tabs are displayed and clickable
      | Details            |
      | Additional Details |
      | Reviews            |

  Scenario: TC02. Check the SKU details within the PDP can displayed
    Given The user is landing on page of "pdp" in AAH
    Then Verify that Breadcrumb "b2b.product.huggies" displayed in PDP
    And Check the SKU of "Recommended Retail Price:" display correctly
    And Check the SKU of "Special Selling Price:" display correctly
    And Check the SKU of "Add to favourites" display correctly
    And Appropriate product details are displayed in correct format
      | Recommended retail price: |
      | Special selling price:    |

  @prodsmoketest 
  Scenario: TC03. Verifying Create a new list and Add to Favourites from PDP
    Given The user is navigating to PDP on click "b2b.product.favourite" product Name
    Then Check the product name "b2b.product.favourite" and description display in PDP
    And Check the SKU of "Add to favourites" display correctly
    When User click on "Add to favourites" link from PDP
    Then The user navigates to "Add to a favourites list" pop-up
    When The user select "Create a new favourites list" option
    And add new favourite as "Test Favourite PDP" with random data
    When click on "Add to favourites" button
    Then verify added confirmation message displayed on page

  Scenario: TC04. Verifying Add to existing favourites and functional validation message from PDP
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on "Favourites" section from My Account page
    Given User setup a default favourite list "favourite.pdp.list"   
    When User click on name of "favourite.pdp.list" in favourite list page
    Given User clear all product content in the favourite details table   
    And The user is navigating to PDP on click "b2b.product.favourite" product Name
    Then Check the product name "b2b.product.favourite" and description display in PDP
    And Check the SKU of "Add to favourites" display correctly
    When User click on "Add to favourites" link from PDP
    Then The user navigates to "Add to a favourites list" pop-up
    When The user select "favourite.pdp.list" option
    Then click on "Add to favourites" button
    Then verify validation message of adding favourite list on page

  #The review stars have some env config issue related to bazzar voice
  @b2b_blocked
  Scenario: TC05. Verifying Reviews functionality from PDP
    Given The user is navigating to PDP on click "b2b.product.huggies" product Name
    Then Check the product name "b2b.product.huggies" and description display in PDP
    And Check the SKU of "Reviews" display correctly
    When User clicked on "Reviews" link from PDP
    Then The user navigates to "Reviews" tab
    And The user verify below sections
      | Rating Snapshot          |
      | Average Customer Ratings |
    Then verify correct number of reviews sections displayed or not
    And The user clicks on Signout

  Scenario: TC06: Check main product image in PDP page
    Given The user is navigating to PDP on click "b2b.product.huggies" product Name
    Then Check the product name "b2b.product.huggies" and description display in PDP
    Then Check product main picture can be shown in PDP
    And The user clicks on Signout  

  Scenario: TC07: Add to Basket from PDP should applicable and navigate to Current Order
    Given The user is navigating to PDP on click "b2b.product.huggies" product Name
    Then Check the product name "b2b.product.huggies" and description display in PDP
    When The user update the product quantity to "2"
    And The user clicks on "Add to basket" option from PDP
    And User close the mini basket popup
    When The user clicks on "Current Order" from PDP   
    Then The user should navigated to "Current Order" page

