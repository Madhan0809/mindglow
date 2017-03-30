@csr_add_tickler @csr_blocked
Feature: View and Edit Customer Information

  Background:
    Given The user is on CSR Signin page
    When The user inputs "csr.admin" and "csr.admin_pwd" as logon ID and password on CheckOut SignIn page
    And The user clicks "Sign In" button on CheckOut SignIn page
    Then The user is signed in and navigated to CSR Home Page
    When I click on Find  "customers" link from CSR LHS Menu

  Scenario: View and Edit Customer Information

    #Perform full query search to return single customer
    When I enter the search criteria to find customers
      | logonID       | firstName       | lastName       | email       | postCode       |
      | cus00.logonID | cus00.firstName | cus00.lastName | cus00.email | cus00.postCode |

 #   Then "SINGLE" matching "customer" label is displayed

    And the search results contains a customer with
      | name       | logonID       | email       | telephone       | address        | select |
      | cus00.name | cus00.logonID | cus00.email | cus00.telephone | cus00.postCode | true   |

    When I click on  "customers" link from CSR LHS Menu which contains the result "cus00.lhsNav"
    And I click  on "ticklers" tab for the selected result
    When I Add new customer tickler from  customer Details













