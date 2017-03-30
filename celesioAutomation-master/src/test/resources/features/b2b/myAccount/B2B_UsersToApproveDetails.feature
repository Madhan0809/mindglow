@b2b_UsersToApproveDetails @b2b
Feature: Users to approve details page

  Background: 
    Given The user is on AAH  Sign-in page as a guest
    When User clicks on "Register" button
    And The register page should be displayed
    And The user provides all customized register info on the registration page
      | GenerateRandomData     | false                  |
      | username               | autoTempUser           |
      | password               | temp1234               |
      | verifyPassword         | temp1234               |
      | accountId              | b2b.register.accountId |
      | firstName              | autoTempFirst          |
      | lastName               | autoTempLast           |
      | preferredContact       | autoTempContact        |
      | contactNumber          | 1234567890             |
      | preferEmail            | No                     |
      | preferSMSNotifications | No                     |
      | preferSMSPromotions    | No                     |
      | termsAccept            | Yes                    |
    And The user clicks on "Submit Request" button on registration page   
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    When The user is on AAH home page with choosing account  
    When User click on "YOUR PROFILE" from main navigation menu
    And User click on "User profiles to Approve" section from My Account page
    When User input users waiting for approval info to search
      | First Name              | autoTempFirst |
      | Last Name               | autoTempLast  |
      | Submitted before or on: | 2/12/2017     |
      | Submitted on or after:  | 2/1/2016      |
    And User view details for user "autoTempFirst autoTempLast" in search result

  Scenario: TC01: Check users to approved details page displaying and its header
    Then The "Account Users To Approve Details" page is displayed
    And Check header should be displayed for user "autoTempFirst autoTempLast" in users to approve details page

  Scenario: TC02: Check whether the user details content displays correctly
    Then Check the content displaying in users to approved details page
      | Submitted:              |
      | Username:               |
      | User:                   |
      | Email:                  |
      | Primary contact number: |
      | Alternative number:     |
      | Employee ID:            |
      | Additional information: |

  Scenario Outline: TC03: Approve & Reject for a new registered user
    And Assign role of "Buyer" for any account to add user
    And Add comments "<Comments>" in users approved details page
    When User click on "<Button>" from users to approved details page
    Then The "User Profiles To Approve" page is displayed

    Examples: 
      | Comments                                | Button  |
      | For automation test: approved this user | Approve |
      | For automation test: reject this user   | Reject  |

  Scenario: TC04: Search for an account in users approve details page
    And User input "b2b.default.aah.Contract" and search account in add user page
    Then Account "b2b.defaultContract" should be displayed in search result
  
  Scenario: TC05: Check page title and breadcrumb on Users to Approve Details page
    Then The "Account Users To Approve Details" page is displayed
    Then Check breadcrumb should be displayed correctly
      | Home\\                                          |
      | Your Profile\\                                  |
      | User profiles to approve\\                      |
      | Approval Request for autoTempFirst autoTempLast |
    When Click link "User profiles to approve" on breadcrumb
    Then The "User Profiles To Approve" page is displayed
    When Click link "Your Profile" on breadcrumb
    Then The "Your Profile" page is displayed
