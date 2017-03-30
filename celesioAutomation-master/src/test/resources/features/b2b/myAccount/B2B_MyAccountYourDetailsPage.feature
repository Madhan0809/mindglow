@b2b_yourdetails @b2b
Feature: YOUR PROFILE - Your Details page (Account details)

  Scenario: TC01: Verify Your Details page labels
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    When User enter Your Details page after login
    Then The header "YOUR DETAILS" is shown on Your Details page
    And Section lables are displayed on Your Details page
      | CHANGE PASSWORD |
      | PREFERENCES     |
    And Information labels are displayed on Your Details page
      | * First Name               |
      | * Last Name                |
      | * Email                    |
      | * Primary contact number   |
      | Alternative contact number |
      | Employee ID (optional)     |
    And Change password labels are displayed on Your Details page
      | Current Password |
      | New Password     |
      | Verify Password  |
    And Mandatory lable is displayed on Your Details page
    And "Marketing Preferences" title is displayed on Your Details page
    And Preferences Options are is displayed on Your Details page
      | Send me e-mails about store specials   |
      | Send SMS notifications to mobile phone |
      | Send SMS promotions to mobile phone    |
    And "PERMISSIONS" label is displayed on Your Details page
    And Account Table Titles are displayed on Your Details page
      | Account               |
      | Buyer                 |
      | Approver              |
      | Administrator         |
      | Primary Administrator |

  Scenario: TC02: Verify the details of autouser1
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    When User enter Your Details page after login
    And First Name is shown as valid value on Your Details page
    And Last Name is shown as valid value on Your Details page
    And Email is shown as valid value on Your Details page
    And Primary contact number is shown as valid value on Your Details page
    And Check the Permissions table information on Your Details page
      | Account               |
      | Buyer                 |
      | Approver              |
      | Administrator         |
      | Primary Administrator |

  Scenario: TC03: Verify Back button and navigate to My Account page
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    When User enter Your Details page after login
    When User click "Back" on Your Details page
    Then The "Your Profile" page is displayed

  @autouser2
  Scenario: TC04: Verify the details of autouser2 which registers and is approved on the site
    Given The user is on AAH  Sign-in page as a guest
    And User login with userId "b2b.username" and password "b2b.password" from AAH home page
    And User click on continue in important messages page
    When User dismiss all notification in header
    When User enter Your Details page after login
    And First Name is shown as valid value on Your Details page
    And Last Name is shown as valid value on Your Details page
    And Email is shown as valid value on Your Details page
    And Primary contact number is shown as valid value on Your Details page
    And Check the Permissions table information on Your Details page
      | Account       |
      | Buyer         |
      | Approver      |
      | Administrator |
  #      | Primary Administrator |
  
  Scenario: TC05: Update the details of a user
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    When User enter Your Details page after login
    When Clear First Name on Your Details page
    And Clear Last Name on Your Details page
    And Clear Email on Your Details page
    And Clear Primary contact number on Your Details page
    And Clear EmployeeID on Your Details page
    Then Error messages are displayed on Your Details page
      | Please enter a valid first name               |
      | Please enter a valid last name                |
      | Please enter a valid email address            |
      | Please enter a valid primary telephone number |
    When Update First Name as "autouserOne" on Your Details page
    And Update Last Name as "autouserOne" on Your Details page
    And Update Email as "autouser1@salmon.com" on Your Details page
    And Update Primary contact number as "01923320000" on Your Details page
    Then User click "Update" on Your Details page
    And First Name is shown as valid value on Your Details page
    And Last Name is shown as valid value on Your Details page
    And Email is shown as valid value on Your Details page
    And Primary contact number is shown as valid value on Your Details page

  Scenario: TC06: Check breadcrumb on Your Details page
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account
    And User enter Your Details page after login
    Then Check breadcrumb should be displayed correctly
      | Home\\         |
      | Your Profile\\ |
      | Your Details   |
    When Click link "Your Profile" on breadcrumb
    Then The "Your Profile" page is displayed
