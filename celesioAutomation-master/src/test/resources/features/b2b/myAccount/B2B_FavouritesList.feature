@b2b @b2b_favourites_list
Feature: YOUR PROFILE - FavouriteList

  Background: Check add user form content displaying
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on "Favourites" section from My Account page
    Given User setup a default favourite list "autoTest_DefaultList"

  Scenario: TC01. Check favourties page is displayed and its header
    Then The "Favourites" page is displayed
    And The page header of "Favourites" display in correct format

  Scenario: TC02. Check favorites table displays in correct format
    Then Check "Your Favourites" table is displayed in correct format
      | Name         |
      | Items        |
      | Status       |
      | Last Updated |
      | Created By   |
    When User click on tab "Organisation Favourites" in favourite list page
    And Check "Organisation Favourites" table is displayed in correct format
      | Name         |
      | Items        |
      | Status       |
      | Last Updated |
      | Created By   |

  Scenario: TC03. Create and delete a new favourite list and check it on favourite list table
    When User create a new dynamic favourite list "favourite.list"
    Then Check a new favourite list should be created
    When User click on "Delete List" under action menu for favourite list "favourite.list"
    Then Check the favourite list "favourite.list" is not found in current table

  Scenario: TC04. Actions: Add list into current order
    Given User clear the all content in default basket "b2b.aah.currentBasket"
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    Then The user should be navigated to "Baby Changing" Sub Category screen
    When Add one product into "b2b.aah.currentBasket" basket in PLP
    And User close the mini basket popup
    When User enter my basket page after login   
#    And user select "Move to favourites list" from action menu from a random basket    
    And user select "Move to favourites list" from action menu for "b2b.aah.currentBasket" from my basket page
    And User select "autoTest_DefaultList" in popup of favourites
    And User click move to favourite list in popup
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on "Favourites" section from My Account page
    When User click on "Add list to current order" under action menu for favourite list "autoTest_DefaultList"
    Then The popup of order has been updated successfully display in header

  Scenario: TC05. Actions: Duplicate a favorite list and check it on favourite list table
    Given User setup a default favourite list "autoTest_ForDuplicateTesting"
    When User click on "Duplicate list" under action menu for favourite list "autoTest_ForDuplicateTesting"
    Then Check a duplicate list for "autoTest_ForDuplicateTesting" is created
    #Data cleaning: delete the duplicating basket
    And Data cleaning for a newly duplicating basket "Copy_of_autoTest_ForDuplicateTesting"

  Scenario: TC06. Actions: Make a pulic favorite list and check it should exist in organization tab
    Given User create a new dynamic favourite list "favourite.list"
    And User click on "Make List Public" under action menu for favourite list "favourite.list"
    When User click on tab "Organisation Favourites" in favourite list page
    Then Check favourite list "favourite.list" is existed in current table

  Scenario: TC07. Click on details button and check it can enter favourite details page
    Given User click on details for favourite list "autoTest_DefaultList" in favourite list
    Then The "Favourites List Details" page is displayed

  Scenario: TC08. Click on a target favorite list and cehck it can enter favourite details page
    When User click on name of "autoTest_DefaultList" in favourite list page
    Then The "Favourites List Details" page is displayed

  Scenario: TC09. Check breadcrumb on Favourite List page
    Then Check breadcrumb should be displayed correctly
      | Home\\         |
      | Your Profile\\ |
      | Favourites     |
    When Click link "Your Profile" on breadcrumb
    Then The "Your Profile" page is displayed
