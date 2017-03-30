@b2b_checkoutbasket @b2b
Feature: Checkout basket page

  Background: 
    Given The user is on AAH home page
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    Then The user is on AAH home page as a user

  Scenario: TC01: Add a product into current basket and check mini basket quantity should be updated accordingly
    Given User clear the all content in default basket "b2b.aah.currentBasket"
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    Then The user should be navigated to "Baby Changing" Sub Category screen
    When The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    When Add product "b2b.product.rimmel" into target basket "b2b.aah.currentBasket"
    When User refresh the page
    And The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    Then "1" items should be added into "AAH" basket
    Then User clear the all content in default basket "b2b.aah.currentBasket"

  @devsmoketest @prodsmoketest
  Scenario Outline: TC02: Clean basket and check mini basket in header for both AAH and Enterprise
    When User clear the all content in default basket "<DefaultBasketName>"
    And The user select "<DefaultBasketName>" from mini basket in "<MiniShopCartName>" row
    Then Check there are "0" items in mini shop cart in "<MiniShopCartName>" row

    Examples: 
      | DefaultBasketName            | MiniShopCartName |
      | b2b.aah.currentBasket        | AAH              |
      | b2b.enterprise.currentBasket | ENTERPRISE       |

  @devsmoketest @prodsmoketest
  Scenario: TC03: Check order summary header should be displayed correctly in current order
    When The user click the AAH mini cart button
    Then The "Current Order" page is displayed
    And Check order summary header displayed in correct format
      | Account Number:                  |
      | Account Name:                    |
      | Delivery Point:                  |
      | Submission Date / Current Basket |

  Scenario: TC04: User adds product into basket to checkout from search result page.
    Given User clear the all content in default basket "b2b.aah.currentBasket"
    When The user inputs text as "baby changing" in search box
    And The user clicks on the Search button
    When The search page breadcrumb "Search: baby changing" is displayed on search result page
    When The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    When Add product "b2b.product.rimmel" into target basket "b2b.aah.currentBasket"
    When User hover on "AAH" mini cart button
    Then The mini shop cart is displayed in header
    And check product "b2b.product.rimmel" is found in mini shop cart

  Scenario: TC05: Check curernt order content info is displayed correctly
    Given User clear the all content in default basket "b2b.aah.currentBasket"
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    Then The user should be navigated to "Baby Changing" Sub Category screen
    When The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    When Add product "b2b.product.huggies" into target basket "b2b.aah.currentBasket"
    And The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    And The user click the AAH mini cart button
    Then The product name "b2b.product.huggies" is found in current order
    And The checkout account headers are displayed as per design
      | Account Number:                  |
      | Account Name:                    |
      | Delivery Point:                  |
      | Submission Date / Current Basket |
    And The account details are displayed with 4 field

  Scenario: TC06: User clicks on continue shopping in current basket page
    Given User clear the all content in default basket "b2b.aah.currentBasket"
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    When The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    When Add product "b2b.product.huggies" into target basket "b2b.aah.currentBasket"
    And The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    And The user click the AAH mini cart button
    When The user click on "Continue Shopping" button in current order
    Then Login home page will be displayed

  Scenario: TC07: Check PDP should be displayed after user click on a specific product from current basket
    Given User clear the all content in default basket "b2b.aah.currentBasket"
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    When The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    When Add product "b2b.product.huggies" into target basket "b2b.aah.currentBasket"
    And User close the mini basket popup
    When The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    And The user click the AAH mini cart button
    When The user click on product "b2b.product.huggies" in current order page
    Then The user should be navigated to the product details page

  Scenario: TC08: Check product Sku image and description is displayed in current basket
    Given User clear the all content in default basket "b2b.aah.currentBasket"
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    Then The user should be navigated to "Baby Changing" Sub Category screen
    When The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    And Add one product into "b2b.aah.currentBasket" basket in PLP
    When The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    And The user click the AAH mini cart button
    Then Check the image for all products can be displayed
    And Check Sku description for all products can be displayed

  Scenario: TC09: Remove basket item from current basket
    Given User clear the all content in default basket "b2b.aah.currentBasket"
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    When The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    When Add product "b2b.product.huggies" into target basket "b2b.aah.currentBasket"
    And User close the mini basket popup
    And Add product "b2b.product.rimmel" into target basket "b2b.aah.currentBasket"
    And The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    And The user click the AAH mini cart button
    And The user removes "b2b.product.huggies" from current order
    Then Popup should be displayed with the relevant message "The item was removed from the Current Order." in b2b checkOutBasket process

  Scenario: TC10: Update the quanlity in basket and request review to clear current basket
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on Account Details link from My Account page
    When User click view details for account "b2b.default.aah.Contract"
    When User click on Order approvals with minimum amount
      | pounds | 1 |
      | pence  | 1 |
    And User save changes with order approvals
    When The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    And Add product "b2b.product.rimmel" into target basket "b2b.aah.currentBasket"
    When The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    And The user click the AAH mini cart button
    Then The "Current Order" page is displayed
    When The user update the amount for product "b2b.product.rimmel" set quanlity as 5
    And The user click Request Review button
    When User refresh the page
    And The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    Then Check there are "0" items in mini shop cart in "AAH" row

  #Test data changes, awaiting stock is displayed

  Scenario: TC11: E2E: User update product amount and verify basket details should be updated accordingly
    Given User clear the all content in default basket "b2b.aah.currentBasket"
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    When The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    When Add product "b2b.product.huggies" into target basket "b2b.aah.currentBasket"
    And The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    And The user click the AAH mini cart button
    When The user update the amount for product "b2b.product.huggies" set quanlity as 2
    Then The product information in basket result is found in "OTC order" page
      | Product      | b2b.product.huggies |
      | Availability | Awaiting Stock      |
      | Quantity     | 2                   |
      | Each         | £4.40               |
      | Total        | £8.80               |

  Scenario Outline: TC12: Add a reference to order and check it in sent order page
    Given User clear the all content in default basket "b2b.enterprise.currentBasket"
    When The user inputs text as "baby changing" in search box
    And The user clicks on the Search button
    When The search page breadcrumb "Search: baby changing" is displayed on search result page
    And The user select "b2b.enterprise.currentBasket" from mini basket in "ENTERPRISE" row
    And Add one product into "b2b.enterprise.currentBasket" basket in PLP
    And The user select "b2b.enterprise.currentBasket" from mini basket in "ENTERPRISE" row
    When The user click the ENTERPRISE mini cart button
    And User input reference data "<refData>" in current basket
    Then The user click Send Order button
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on "Sent Orders" section from My Account page
    And User select "View all orders" for order in sent order page
    Then Check the new order reference is found in table

    Examples: 
      | refData              |
      | MyReference          |
      | ReferenceNumber12345 |

  Scenario: TC13.Change the basket quantity and check adding to Basket from PLP
    Given User clear the all content in default basket "b2b.aah.currentBasket"
    When The user is navigating to PLP
    When The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    When The user add product "b2b.product.huggies" with quantity 2 into basket "b2b.aah.currentBasket" in PLP
    When User refresh the page
    And The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    Then Check there are "2" items in mini shop cart in "AAH" row
    And The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    And The user click the AAH mini cart button
    Then The user should navigated to "Current Order" page
