@b2b_FavouritesListOverlay @b2b
Feature: YOUR PROFILE - My basket - Favourites List Overlay page

  Scenario: TC01. Action menu in my basket page: Move to favorite list in favourite list page
    Given The user is on AAH  Sign-in page as a guest
    When User login with user name and password
    When User enter my basket page after login
    And user select "Move to favourites list" from action menu from a random basket  
 #   And user select "Move to favourites list" from action menu for "b2b.aah.currentBasket" from my basket page
    Then The page title "MOVE TO FAVOURITES LIST" is displayed on Favourites List Overlay page
    And The text "OR CREATE A NEW FAVOURITES LIST" is displayed on Favourites List Overlay page
    And The button text "Move To Favourites List" is displayed on Favourites List Overlay page
    And The text label "Select a list to Add to:" is displayed on Favourites List Overlay page