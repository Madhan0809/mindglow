@b2c_department @b2c
Feature: The Lloyds Pharmacy Department page

  Scenario: Beauty & Fragrance department page display
    Given The user is on "Beauty & Fragrance" department page
    Then appropriate breadcrumb "Beauty & Fragrance" is displayed
    Then The category menu is displayed as expected on LHS    
    When Click on "Up Arrow" indicator of category menu
    Then The category menu should be "Hidden".
    When Click on "Down Arrow" indicator of category menu
    Then The category menu should be "Visible".

  Scenario: On click of Beauty & Fragrance subcategory menu
    Given The user is on "Beauty & Fragrance" department page
    When The user clicks on category item "Accessories"
    Then The user should be navigated to "Accessories - Beauty & Fragrance | LloydsPharmacy" PLP page


