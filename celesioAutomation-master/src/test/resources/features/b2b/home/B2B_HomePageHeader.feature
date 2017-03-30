@b2b_home @b2b
Feature: AAH Home Page Header

  @devsmoketest @prodsmoketest @autouser2
  Scenario: TC01. Navigating to AAH HomePage from Sign-in page verify Sign-in and Sign-out buttons
    Given The user is on AAH  Sign-in page as a guest
    And The user provide default username and password
    When User clicks on Sign In button
    And User dismiss all notification in header
    And  The user clicks on Signout

  @devsmoketest @prodsmoketest @autouser2
  Scenario: TC02. Click on AAH Enterprise icon
    Given The user is on AAH  Sign-in page as a guest
    And The user provide default username and password
    And User clicks on Sign In button
    And User dismiss all notification in header
    When The user clicks on AAHEnterprise icon
    Then The user shall be navigated to the AAH home page
    And  The user clicks on Signout

  Scenario: TC03. Navigating to AAH HomePage from Sign-in page Multi Account User
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    When User clicks on Sign In button
    Then User Navigate to "Account Selection" page
    When User can choose "second" account from accounts dropdown
    And  clicks on "Shop complete" button
    And The user is on AAH home page as a user
    And User dismiss all notification in header
 #   Then User Navigate to "Important Messages" page
    And  The user clicks on Signout

  @devsmoketest @prodsmoketest
  Scenario: TC04. Navigating to AAH HomePage from Access websites page as multi account user
    Given The user is on AAH Point page
    When User clicks on "Sign In" button on right section
    Then User Navigate to Site Access page
    When User clicks on "Shop Complete OTC" site button
    Then User Navigate to "Sign In" page
    And The user provide multi account username and password
    When User clicks on Sign In button
    Then User Navigate to "Account Selection" page
    When User can choose "second" account from accounts dropdown
    And clicks on "Shop Now" button
 #   Then User Navigate to "Important Messages" page
    And User dismiss all notification in header
    When The user clicks on AAHEnterprise icon
    And The user is on AAH home page as a user
    And The user clicks on Signout

  @prodsmoketest @autouser2
  Scenario: TC05. Navigating to AAH HomePage from Access websites page as single account user
    Given The user is on AAH Point page
    When User clicks on "Sign In" link on right section
    Then User Navigate to Site Access page
    When User clicks on "Shop Complete OTC" site button
    Then User Navigate to "Sign In" page
    And The user provide default username and password
    When User clicks on Sign In button
    And User dismiss all notification in header
    When The user clicks on AAHEnterprise icon
    And The user is on AAH home page as a user
    And  The user clicks on Signout

  Scenario: TC05. Account selector from AAH homepage header
    Given The user is on AAH  Sign-in page as a guest
   # And The user provide default username and password
   And The user provide multi account username and password
    And User clicks on Sign In button
    Then User Navigate to "Account Selection" page
    When User can choose "first" account from accounts dropdown
    And  clicks on "Shop complete" button
    And User dismiss all notification in header
    Then The user clicks on Signout

  @devsmoketest @prodsmoketest
  Scenario: TC06. Search box
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    Then User Navigate to "Account Selection" page
    When User can choose "first" account from accounts dropdown
    And  clicks on "Shop complete" button
    And User dismiss all notification in header
    When The user inputs text as "test" in search box
    Then the service returned 4 auto suggest product results list
    And User dismiss all notification in header
    And The user clicks on Signout




