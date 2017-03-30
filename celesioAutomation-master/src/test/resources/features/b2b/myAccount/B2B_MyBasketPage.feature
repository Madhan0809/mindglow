@b2b_MyBasketPage @b2b
Feature: YOUR PROFILE - My basket page

  @prodsmoketest
  Scenario: TC01. Adding an item to existing current AAH basket and verify data in my basket page
    Given The user enter "My Basket" from B2B landing page
    When User clear the all content in default basket "b2b.aah.currentBasket"
    And The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    When Add product "b2b.product.huggies" into target basket "b2b.aah.currentBasket"
    And The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    When User enter my basket page after login
    Then Check basket "b2b.aah.currentBasket" is found in my basket page
    And Check "b2b.aah.currentBasket" basket has product added in my basket page

  Scenario: TC02. Verify my basket data table header and basket details
    Given The user enter "My Basket" from B2B landing page
    And User clear the all content in default basket "b2b.aah.currentBasket"
    When User enter my basket page after login
    Then Check basket table header is displayed
      | Basket Name                             |
      | Account                                 |
      | Delivery Point                          |
      | Send Order On / Next Scheduled Delivery |
      | Total                                   |

  Scenario: TC03. Enter basket page by clicking of basket name from My basket page
    Given The user enter "My Basket" from B2B landing page
    When The user click on "b2b.aah.currentBasket" from my basket process
    Then user navigated to current order page and checkout basket process is displayed
    Then The "Current Order" page is displayed

  @autouser1
  Scenario: TC04. Action menu in my basket page: View current order/View basket details
    Given User login with default multi account
    And User clear the all content in default basket "b2b.aah.currentBasket"
    When User enter my basket page after login
    When user select "View current order" from action menu for "b2b.aah.currentBasket" from my basket page
    Then user navigated to current order page and checkout basket process is displayed
    When The user select "Create a new basket" from mini basket in "AAH" row
    And The user input basket detail information to create
      | Account        | b2b.defaultContract   |
      | DeliveryPoint  | Neoworks Ltd-EC2V 5DE |
      | SubmissionDate | 1/1/2017              |
    When User enter my basket page after login
    When user select "View current order" from action menu for "b2b.aah.currentBasket" from my basket page
    Then user navigated to current order page and checkout basket process is displayed

  @autouser1
  Scenario: TC05. Action menu in my basket page: Select send order for an enterprise order and order is no longer exists
    Given User login with default multi account
    When The user select "Create a new basket" from mini basket in "AAH" row
    And The user input basket detail information to create
      | Account        | b2b.default.enterprise.Contract |
      | DeliveryPoint  | Neoworks Ltd-EC2V 5DE           |
      | SubmissionDate | 1/1/2017                        |
    When The user select "b2b.new.enterprise.basket" from mini basket in "ENTERPRISE" row
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    Then The user should be navigated to "Baby Changing" Sub Category screen
    When Add one product into "b2b.new.enterprise.basket" basket in PLP
    And User close the mini basket popup
    When User enter my basket page after login
    When user select "Send order now" from action menu for "b2b.new.enterprise.basket" from my basket page
#    And User enter my basket page after login
    Then The basket "b2b.new.enterprise.basket" cannot be found in my basket page

  @autouser1
  Scenario: TC06. Action menu in my basket page: Delete a new basket from my basket page
    Given User login with default multi account
    When The user select "Create a new basket" from mini basket in "AAH" row
    And The user input basket detail information to create
      | Account        | b2b.defaultContract   |
      | DeliveryPoint  | Neoworks Ltd-EC2V 5DE |
      | SubmissionDate | 1/1/2017              |
    When User enter my basket page after login
    Then Check basket "b2b.new.aah.basket" is found in my basket page
    When user select "Delete basket" from action menu for "b2b.new.aah.basket" from my basket page
    When Click "Ok" on delete basket confirmation popup
    Then The basket "b2b.new.aah.basket" cannot be found in my basket page

  @autouser1
  Scenario: TC07. Action menu in my basket page: Move to favorite list - default list in my basket page
    Given User login with default multi account    
    And User clear the all content in default basket "b2b.aah.currentBasket"
    When The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    When The user hover on "Shop By Category" menu
    And The user hover on "Baby & Children" department
    And The user Select "Baby Changing" Category
    Then The user should be navigated to "Baby Changing" Sub Category screen
    When Add one product into "b2b.aah.currentBasket" basket in PLP
    And User close the mini basket popup
    When User enter my basket page after login
    And user select "Move to favourites list" from action menu for "b2b.aah.currentBasket" from my basket page
    When User select "Or Create a new favourites list" in popup of favourites
    And User input favourite list name "favList_TestMyBasket" and click on add to favourites
    Then Check add to favourite list popup message "List added to Favourites" comes in header
    #Data cleaning
    And User clear the all content in default basket "b2b.aah.currentBasket"

  @autouser1 
  Scenario: TC08. Action menu in my basket page: Duplicate the basket from My basket page
    Given User login with default multi account
    And User clear the all content in default basket "b2b.aah.currentBasket"
    When User enter my basket page after login
    And user select "Duplicate basket" from action menu for "b2b.aah.currentBasket" from my basket page
    Then The "Send Order to Multiple Delivery Points / Accounts" page is displayed in checkout basket process
    When User input information for duplicate basket
      | Account                          | b2b.duplicatedAccount       |
      | Delivery Point                   | b2b.duplicatedDeliveryPoint |
      | Submission Date / Current Basket | Current Basket - Exists     |
    And The user click on "Done" button in current order
    Then The "Current Order" page is displayed in checkout basket process
    When User switch the location as "b2b.duplicatedAccount" from header   
    When User enter my basket page after login
    Then Check basket "b2b.duplicate.basket" is found in my basket page
    #Data cleaning: delete the duplicated basket
    When user select "Delete basket" from action menu for "b2b.duplicate.basket" from my basket page
    When Click "Ok" on delete basket confirmation popup

  @autouser1
  Scenario: TC09. Create a new basket and check it can be found in my basket page
    Given User login with default multi account
    When User enter my basket page after login
    And User cleanup the basket "b2b.new.aah.basket" in my basket page   
    When The user click on "Create basket" from my basket process
    Then The "Create a Basket" page is displayed in checkout basket process
    When The user input basket detail information and create basket from my basket
      | Account        | b2b.defaultContract   |
      | DeliveryPoint  | Neoworks Ltd-EC2V 5DE |
      | SubmissionDate | 1/1/2017              |
    When User enter my basket page after login
    Then Check basket "b2b.new.aah.basket" is found in my basket page
    #Data cleaning: delete the newly created basket
    And user select "Delete basket" from action menu for "b2b.new.aah.basket" from my basket page
    And Click "Ok" on delete basket confirmation popup

  Scenario: TC10: Check breadcrumb on My Basket page
    Given The user enter "My Basket" from B2B landing page
    Then Check breadcrumb should be displayed correctly
      | Home\\         |
      | Your Profile\\ |
      | My Baskets     |
    When Click link "Your Profile" on breadcrumb
    Then The "Your Profile" page is displayed
