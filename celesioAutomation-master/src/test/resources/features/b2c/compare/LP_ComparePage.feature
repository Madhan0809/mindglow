@b2c_compare @b2c
Feature: Check the Compare Page function

  Background: 
    Given The user is on "Beauty & Fragrance > Accessories" PLP
    And The user selects "Name (a-z)" from sort by list
    When The user selects the products to compare
      | Ardell Fashion Lashes 110 Black |
      | Beautycare 5 Piece Brush Set    |
      | Beautycare Large Powder Brush    |
    And The user clicks on compare button
    Then The compare page opens

  Scenario: Check the compare page displays as per design
    Then The header logo is displayed on compare page
    And The Footer is displayed on compare page
    And Appropriate text labels are displayed on compare page
      | Compare                  |
      | Back to Accessories      |
      | Compare up to 4 products |
    And The product attributes are displayed
      | Product   |
      | Price     |
      | Brand     |
      | SIZE      |
 #     | Pack Size |
    When The user clicks on "Back to Accessories" text label
    Then The user should be navigated to "Accessories - Beauty & Fragrance | LloydsPharmacy" PLP page

  Scenario: Test recently viewed panal after navigate to PDP from compare page
    When The user clicks on "Ardell Fashion Lashes 110 Black" product on the compare page
    Then The PDP of "Ardell Fashion Lashes 110 Black" opens
    When The user click on browser back button
    Then The compare page opens
    And The Recently Viewed panel is displayed on compare page

  Scenario Outline: Check the user can add to basket from the compare page and mini basket is updated
    When The user update product quantity to "<quantity>" in compare page
    And The user clicks on "Add to basket" in compare page
    Then "<Item(s)>" should be added to mini basket

    Examples: 
      | quantity | Item(s)   |
      | 1        | 1  |
      | 2        | 2  |

  Scenario: Check the remove function works on compare page
    When The user removes the "Ardell Fashion Lashes 110 Black" product on compare page
    Then The "Ardell Fashion Lashes 110 Black" product should be removed

  Scenario: Remove all the products on the compare page
    When The user removes all the products on compare page
    Then The empty message on the compare page shall be displayed
    When The user clicks on "Back to Accessories" text label
    Then The user should be navigated to "Accessories - Beauty & Fragrance | LloydsPharmacy" PLP page
