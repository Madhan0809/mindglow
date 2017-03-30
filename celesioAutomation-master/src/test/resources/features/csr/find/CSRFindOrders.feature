@csr_find_orders @csr_blocked
Feature: Find Orders from CSR portal

  Background:
    Given The user is on CSR Signin page
    When The user inputs "csr.admin" and "csr.admin_pwd" as logon ID and password on CheckOut SignIn page
    And The user clicks "Sign In" button on CheckOut SignIn page
    Then The user is signed in and navigated to CSR Home Page
    When I click on Find  "orders" link from CSR LHS Menu
    #Then the "find.orderHeader" header is displayed for the search screen


  Scenario: Find Orders from CSR Portal with Full Query and Partial Query


    #Perform partial query search to return  order by order No
    When I enter the search criteria to find orders
      | orderNo      | logonID | firstName | lastName | email | postCode | orderStatus |
      | cus1.ord1.no |         |           |          |       |          |             |
    Then "SINGLE" matching "orders" label is displayed

    Then the search results contains a order with
      | orderNo      | logonID      | datePlaced       | orderStatus      | orderTotal      | select |
      | cus1.ord1.no | cus1.logonID | cus1.ord1.placed | cus1.ord1.status | cus1.ord1.total | false  |


    Given I clear the search results


    #Perform partial query search to return  order by LogonId and Order No
    When I enter the search criteria to find orders
      | orderNo      | logonID      | firstName | lastName | email | postCode | orderStatus |
      | cus1.ord1.no | cus1.logonID |           |          |       |          |             |
    Then "SINGLE" matching "orders" label is displayed

    Then the search results contains a order with
      | orderNo      | logonID      | datePlaced       | orderStatus      | orderTotal      | select |
      | cus1.ord1.no | cus1.logonID | cus1.ord1.placed | cus1.ord1.status | cus1.ord1.total | false  |

    Given I clear the search results

    #Perform partial query search to return  order by email
    When I enter the search criteria to find orders
      | orderNo | logonID | firstName | lastName | email      | postCode | orderStatus |
      |         |         |           |          | cus1.email |          |             |
    Then "MULTIPLE" matching "orders" label is displayed

    Then the search results contains a order with
      | orderNo      | logonID      | datePlaced       | orderStatus      | orderTotal      | select |
      | cus1.ord1.no | cus1.logonID | cus1.ord1.placed | cus1.ord1.status | cus1.ord1.total | false  |


    #Perform partial query search for  order by Last name and Post Code
    Given I clear the search results
    When I enter the search criteria to find orders
      | orderNo | logonID | firstName      | lastName      | email | postCode | orderStatus |
      |         |         | cus1.firstName | cus1.lastName |       |          |             |
    Then "MULTIPLE" matching "orders" label is displayed

    Then the search results contains a order with
      | orderNo      | logonID      | datePlaced       | orderStatus      | orderTotal      | select |
      | cus1.ord1.no | cus1.logonID | cus1.ord1.placed | cus1.ord1.status | cus1.ord1.total | false  |


    #Perform partial query search for  order by Logon ID and Order Status
    Given I clear the search results
    When I enter the search criteria to find orders
      | orderNo | logonID      | firstName | lastName | email | postCode | orderStatus      |
      |         | cus1.logonID |           |          |       |          | cus1.ord1.status |

    Then "MULTIPLE" matching "orders" label is displayed

    Then the search results contains a order with
      | orderNo      | logonID      | datePlaced       | orderStatus      | orderTotal      | select |
      | cus1.ord1.no | cus1.logonID | cus1.ord1.placed | cus1.ord1.status | cus1.ord1.total | false  |


    #Perform partial query search to return  order by LogonId
    Given I clear the search results

    When I enter the search criteria to find orders
      | orderNo | logonID      | firstName | lastName | email | postCode | orderStatus |
      |         | cus1.logonID |           |          |       |          |             |
    Then "MULTIPLE" matching "orders" label is displayed

    Then the search results contains a order with
      | orderNo      | logonID      | datePlaced       | orderStatus      | orderTotal      | select |
      | cus1.ord1.no | cus1.logonID | cus1.ord1.placed | cus1.ord1.status | cus1.ord1.total | false  |

    And the results page is paginated by 5 results per page



