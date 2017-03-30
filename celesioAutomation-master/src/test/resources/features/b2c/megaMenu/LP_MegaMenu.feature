@b2c_megaMenu @b2c
Feature: Lloyds Pharmacy Mega Menu

  Background: 
    Given The user is on Lloyds Pharmacy home page
    When The user hover over "Shop By Category" menu
    Then The list is displayed with its full menu
      | Great Offers                   |
      | Medicines & Treatments         |
      | Health & Wellbeing             |
      | Electrical Health              |
      | Vitamins & Supplements         |
      | Betterlife from LloydsPharmacy |
      | Mother & Baby                  |
      | Skincare                       |
      | Toiletries                     |
      | Beauty & Fragrance             |
      | Pet Care                       |
      | Shop by Condition              |

  Scenario: On hover of Beauty & Fragrance
    When The user hover over "Beauty & Fragrance" department
    Then The "Beauty & Fragrance" category menu is displayed
      | Offers            |
      | Top 10 Fragrances |
      | Fragrance         |
      | Aftershave        |
      | Make Up           |
      | Tanning           |
      | Accessories       |
    # And Relevant category content header "Beauty & Fragrance" should be displayed
    And Appropriate view all link "View all Beauty & Fragrance" should be displayed

  Scenario: On click of Beauty & Fragrance
    When The user clicks on "Beauty & Fragrance" department
    Then The user should be navigated to "Beauty & Fragrance | LloydsPharmacy" department page
