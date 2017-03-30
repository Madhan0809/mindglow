@b2b @b2b_department
Feature: The Wholesale OTC Online Departments Page

  Background: 
    Given The user is on AAH home page
    When User login with user name and password
    And User dismiss all notification in header
    When The user hover on "Shop By Category" menu
    And The user click on target department "Baby & Children" from mega menu
    Then The target department page "Baby & Children" is displayed correctly

  Scenario: TC01. Verify department section carousel click to enter sub category
    Then Check the panel of Carousel is displayed from department page
    When User click on product item "Baby Changing" in panel "Carousel" from department page
    Then The user should be navigated to "Baby Changing" Sub Category screen

  Scenario: TC02. Verify Best seller carousel and click it to enter PDP
    Given All images in current view in subcategory can be displayed in department page
    When User click on product item "randomOne" in panel "Best Seller" from department page
    Then The user should be navigated to the product details page

  Scenario: TC03. Check all sub categories under facet navigation should be displayed
    Given Check that all categories in facet navigation are in "Display" status
    Then Check there should be 2 sub categories under facet navigation

  @prodsmoketest
  Scenario: TC04. Facet menu of toggle catagory menu is functioning correctly
    Given User select the "Collapse" sign on the category section
    Then Check that all categories in facet navigation are in "Hidden" status
    When User select the "Elapse" sign on the category section
    Then Check that all categories in facet navigation are in "Visual" status

  Scenario: TC05. Check it can be redirected to PLP after select catagory from facet menu
    Given The user Select "Baby Changing" Category from Facet navigation
    Then The user should be navigated to "Baby Changing" Sub Category screen
