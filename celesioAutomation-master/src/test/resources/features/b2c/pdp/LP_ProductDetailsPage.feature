@b2c_pdp @b2c
Feature: Lloyds Pharmacy Product Details Page(PDP)
  
  Scenario: Accessories Product details page(PDP) display
    Given The user is on "plp.product1" PDP
    Then Breadcrumb "Accessories" is displayed in PDP
    When The user inputs "Simple Baby" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Simple Baby Moisturising Wipes 4 x 80 Wipes"
    And Appropriate product details are displayed on RHS
      | Product Name | Simple Baby Moisturising Wipes 4 x 80 Wipes |
      | User Rating  | WRITE A REVIEW                              |
      | Price        | £2.54                                       |
      | Was Price    | Was £5.09                                   |
      | Save Price   | Save £2.55                                  |
      | Stock        | In stock                                    |
      | Quantity     | 1                                           |
      | productImage | true                                        |
    And All the info tabs are displayed and clickable
      | Description          |
      | How to use           |
      | Ingredients          |
      | Reviews              |
      | Deliveries & Returns |

  Scenario Outline: Navigate to checkout basket from PDP
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Simple Baby" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Simple Baby Moisturising Wipes 4 x 80 Wipes"
    When The user update product quantity to "<quantity>"
    And The user clicks on "Add to basket" in PDP
    Then "<Item(s)>" should be added to mini basket
    When The user clicks on "Checkout"
    Then The user should be navigated to checkout basket page

    Examples: 
      | quantity | Item(s) |
      | 1        | 1       |
      | 2        | 2       |

  #Lack of data. Not have multi-variant
  @b2c_blocked
  Scenario Outline: Multi-variant SKU in PDP
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Aquafresh" in the Search text box
    And The user clicks the Search button
    Then The PDP of "Aquafresh Little Teeth Soft Bristle Toothbrush" opens
    When The user selects colour "<Colour>"
    And The user selects size "<Size>"
    And The user update product quantity to "<quantity>"
    And The user clicks on "Add to basket" in PDP
    Then "<Item(s)>" should be added to mini basket
    When The user clicks on "Checkout"
    Then The user should be navigated to checkout basket page
    And The attributes should match the ones selected in PDP

    Examples: 
      | Colour         | Size  | quantity | Item(s) |
      #     | Orange         | Large | 1        | 1 item(s) |
      | Green and Blue | Small | 2        | 2       |

  Scenario Outline: Ceiling Limits for PMED and GSL Product on PDP
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "<searchCriteria>" in the Search text box
    And The user clicks the Search button
    When The user clicks "<productName>" on the PLP
    When The user update product quantity to "<quantity>"
    And The user clicks on "Add to basket" in PDP
    Then the "<errorMessage>" error message is displayed

    Examples: 
      | searchCriteria | productName                                    | quantity | errorMessage            |
      | lemsip         | Lemsip Max Cold and Flu Blackcurrant 5 Sachets | 5        | gsl.quanitity.maxError  |
      | benylin        | Benylin Dry Coughs Original 150ml              | 2        | pmed.quanitity.maxError |
