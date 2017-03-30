@csr_find_ticklers @csr_blocked
Feature: Find Ticklers from CSR portal

  Background:
    Given The user is on CSR Signin page
    When The user inputs "csr.admin" and "csr.admin_pwd" as logon ID and password on CheckOut SignIn page
    And The user clicks "Sign In" button on CheckOut SignIn page
    Then The user is signed in and navigated to CSR Home Page

  Scenario: Find Ticklers from CSR Portal with Full Query and Partial Query
    When I click on Find  "ticklers" link from CSR LHS Menu

    #Perform full query search to return single order
    #No test data available for finding orders by order number
    When I enter the search criteria to find ticklers
      | ticklerID | logonID | orderNo | returnID | status |
      |           | ngee    |         |          |        |
    Then the search results contains a tickler with
      | iD | related | reason | remindOn | responsibility | status |
      |    |         |        |          |                |        |

    Given I clear the search results

    #Perform partial query search to return multiple orders
    When I enter the search criteria to find ticklers
      | ticklerID | logonID | orderNo | returnID | status |
      |           | ngee    |         |          |        |
    Then the search results contains a tickler with
      | iD | related | reason | remindOn | responsibility | status |
      |    |         |        |          |                |        |