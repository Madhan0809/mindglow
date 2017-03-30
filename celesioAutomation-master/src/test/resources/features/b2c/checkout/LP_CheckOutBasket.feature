@b2c_checkout @b2c
Feature: Checkout basket page

  Scenario: Basket checkout page display
    Given The user is on basket checkout page
    Then Appropriate basket header details should be displayed
      | No of basket items | 1         |
      | Total basket price | £4.99      |
      | Mini basket        | 1 item(s) |
    And Appropriate basket items header text labels should be displayed
      | Items            |
      | Quantity         |
      | Each             |
      | Total            |
      | Delivery Options |
      | Remove           |
    And Appropriate basket items should be displayed
      | Item name        | Beautycare 5 Piece Brush Set |
      | Item Quantity    | 1                                          |
      | Each Item price  | £4.99                                       |
      | Total price      | £4.99                                       |
      | Delivery Options | Click and Collect, Deliver to UK address   |
      | Order sub total  | £4.99                                       |
      # | VAT Relief      | £x.xx                                      |
      #      | Discount         | £0.00                                      |
      | Order total      | £4.99                                       |

  Scenario: Update basket item quantity
    Given The user is on basket checkout page
    When The user updates item quantity to "2"
    Then Order details should be updated across the page
      | No of basket items | 2         |
      | Total basket price | £9.98     |
      | Item Quantity      | 2         |
      | Total price        | £9.98     |
      | Order sub total    | £9.98     |
      # | VAT Relief         | £x.xx  |
      #  | Discount           | £0.00     |
      | Order total        | £9.98     |
      | Mini basket        | 2 item(s) |

  Scenario: Remove basket item quantity
    Given The user is on basket checkout page
    #To verify add an item via continue shopping button
    When The user add an item to the basket
    And The user removes "1 item(s)" from basket
    Then Popup should be displayed with the relevant message
    And Order details should be updated across the page
      | No of basket items | 1         |
      | Total basket price | £4.99     |
      | Item Quantity      | 1         |
      | Total price        | £4.99     |
      | Order sub total    | £4.99     |
      #| VAT Relief         | £x.xx  |
      #| Discount           | £0.00     |
      | Order total        | £4.99     |
      | Mini basket        | 1 item(s) |
    #To verify if empty basket message is displayed
    When The user removes "last item" from basket
    Then The user should see empty shopping cart message

  Scenario: Onclick of secure checkout
    Given The user is on basket checkout page
    #To verify if header checkout button is working
    When The user clicks on "header checkout" button
    Then The user should be navigated to "Checkout signIn" page
    #To verify if actions checkout button is working
    When The user clicks on "basket actions checkout" button
    Then The user should be navigated to "Checkout signIn" page
    #To verify if continue shopping button is working
    When The user clicks on "continue shopping" button
    Then The user should be navigated to "Home" page

  Scenario: Apply promotional codes
    Given The user is on basket checkout page
    #Empty promotional code
    When The user clicks on "Apply" button
    Then Appropriate empty promo code message should be displayed
    When The user close a popup panel
    Then The popup panel should be closed
    #Invalid promotional code
    When The user enters "Invalid" promotional code
    And The user clicks on "Apply" button
    Then Popup should be displayed with the relevant error message
    #Case for closing popup panel
    When The user close a popup panel
    Then The popup panel should be closed

  Scenario: Registered user checkout - Sign-in from home page
    Given The user is on basket checkout page as a registered user
    When The user clicks on "header checkout" button
    Then The "Delivery options" page opens

  Scenario: Registered user checkout - Sign-in from checkout basket page
    Given The user is on basket checkout page
    When The user clicks Sign In button
    And The user inputs "test@mail.com" and "passw9rd" as logon ID and password on login page
    And The user clicks "Sign In" button on login page
    Then The user signs in successfully
    And The user clicks mini shop cart
    When The user clicks on "header checkout" button
    Then The "Delivery options" page opens

  Scenario: Ceiling Limits for GSL Product on CheckOutBasket Page
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "lemsip" in the Search text box
    And The user clicks the Search button
    When The user clicks "Lemsip Max Cold and Flu Capsules 8 Capsules" on the PLP
    When The user update product quantity to "1"
    And The user clicks on "Add to basket" in PDP
    And The user clicks mini shop cart
    When The user updates item quantity to "5"
    Then the "gsl.quanitity.maxError" error message is displayed

  Scenario: Ceiling Limits for PMED Product on CheckOutBasket Page
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Canesten Thrush Cream 20g" in the Search text box
    And The user clicks the Search button
    When The user clicks "Canesten Thrush Cream 20g" on the PLP
    When The user update product quantity to "1"
    And The user clicks on "Add to basket" in PDP
    Then The WWHAM Page opens 
    When The user selects "Yes" from the first dropdown list on WWHAM Page
    And The user inputs "Stomachache for 15 days" in the second question on WWHAM Page
    And The user selects "18" Years and "0" Month from the dropdown list on WWHAM Page
    And The user selects "No" from the fourth dropdown list on WWHAM Page
    And The user selects "No" from the sixth dropdown list on WWHAM Page
    And The user checks on the last checkbox on WWHAM Page
#    When The user selects "Yes" from the first dropdown list on WWHAM Page
#    And The user inputs "Test" in the second question on WWHAM Page
#    And The user selects "18" Years and "0" Month from the dropdown list on WWHAM Page
#   And The user selects "No" from the fourth dropdown list on WWHAM Page
#    And The user selects "No" from the sixth dropdown list on WWHAM Page
#  And The user checks on the last checkbox on WWHAM Page
    And The user clicks Continue button on WWHAM Page
    When The user updates item quantity to "2"
    Then the "pmed.quanitity.maxError" error message is displayed

  #Wish list is removed on systest and live. Wait for confirmation
  @b2c_blocked
  Scenario: Move to wish list - Guest user
    Given The user is on "plp.product1" PDP
    When The user clicks on "Wish List" button
    Then Sign In/Register link should be displayed
    When The user clicks on Sign In/Register link
    Then The user should be navigated to "" Sign-in page

  #Wish list is removed on systest and live. Wait for confirmation
  @b2c_blocked
  Scenario: Move to default wish list - Register user
    Given The user is on "plp.product1" PDP
    When The user clicks on "Wish List" button
    Then The product should be added to wish list
    And The product should be removed from the basket

  #Wish list is removed on systest and live. Wait for confirmation
  @b2c_blocked
  Scenario: Move to existing wish list - Register user
    Given The user is on "plp.product1" PDP
    When The user clicks on "Wish List" button
    Then The user should be shown all the available wish lists
    When The user selects one wish list
    Then The product should be added to wish list
    And The product should be removed from the basket
