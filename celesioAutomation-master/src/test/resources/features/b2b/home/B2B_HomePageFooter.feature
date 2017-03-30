@b2b_footer @b2b
Feature: Check the AAH Home Page Footer function

  Background: 
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    Then User Navigate to "Account Selection" page
    When User can choose "first" account from accounts dropdown
    And clicks on "Shop complete" button
    And User dismiss all notification in header
    When The user clicks on AAHEnterprise icon
    And The user is on AAH home page as a user

  #Footer is not displayed as expected Defect 90640
  @b2b_failed
  Scenario: TC01. Check that AAH Home page footer header and menu links are displayed and function
    Given The user is on AAH home page as a user
    Then The home page footer menu headers are displayed
      | CUSTOMER SERVICE |
      | OUR SERVICES     |
      | WHAT'S NEW       |
      | SHOPPING ONLINE  |
      | CELESIO          |
    Then The B2B footer menu links are displayed
      | Help               |
      | Site Map           |
      | Training Room      |
      | Contact Us         |
      | Delivery           |
      | Returns Policy     |
      | Cookies            |
      | Terms & Conditions |
      | Privacy Policy     |
      | Great Offers       |
      | Deal Of The Week   |
      | Latest Promotions  |
      | Shop By Category   |
      | Order Status       |
      | My Account         |
      | Quick Order        |
      | About AAH          |
      | About Enterprise   |

  Scenario: TC02. Check that social media images are displayed and link functions
    Given The user is on AAH home page as a user
    Then The expected social media images are displayed for B2B
      | facebook       |
      | twitter        |
      | plus.google    |
      | pinterest      |
      | youtube        |
      | mailto:celesio |
