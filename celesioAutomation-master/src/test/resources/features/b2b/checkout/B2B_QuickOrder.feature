@b2b_quickOrder @b2b
Feature: Quick order page

  Background: 
    Given The user is on AAH home page
    And User login with user name and password
    And User click on continue in important messages page
    When User select "Quick Order" from main navigation
    Then The "Quick Shop" page is displayed in checkout basket process

  Scenario: TC01. Check quick order page can be displayed correctly
    Then Check whether can display correct page content in quick order
    And Check the form header is displayed as expected
      | Link or Enterprise code |
      | Quantity                |

  Scenario: TC02. Add product into basket from quick order and display the related basket
    #Data cleaning
    And User clear the all content in default basket "b2b.aah.currentBasket"
    #Add one product via quick order into current order
    When User select "Quick Order" from main navigation
    And The user enters SKU codes "quick.order.sku" and quantities "1" in quick order and add to basket
    Then The "Current Order" page is displayed
    Then The product name "b2b.product.favourite" is found in current order
    #Data cleaning
    And User clear the all content in default basket "b2b.aah.currentBasket"

  Scenario Outline: TC03. Check validation message in quick order page
    When The user enters SKU codes "<Product Code>" and quantities "<Quantity>" in quick order and add to basket
    Then Check popup messages in checkout basket process "<Popup Message>"

    Examples: 
      | Product Code                     | Quantity | Popup Message                                                              |
      |                                  |          | Error:Ensure you have entered a valid code and quantity, please try again. |
      |                                  | 1        | Error:Please ensure you have entered a valid code and try again.           |
      | invalid                          |          | Error:A quantity is required.                                              |
      | invalid                          | 1        | Error:Please ensure you have entered a valid code and try again.           |
      | Deluxe Transit WheelchairRed-JS8 |          | Error:A quantity is required.                                              |
      | Deluxe Transit WheelchairRed-JS8 | invalid  | Error:A quantity is required.                                              |
