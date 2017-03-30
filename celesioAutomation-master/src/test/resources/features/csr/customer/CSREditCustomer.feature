@csr_edit_customer @csr_blocked
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
      | logonID      | firstName      | lastName      | email      | postCode      |
      | cus1.logonID | cus1.firstName | cus1.lastName | cus1.email | cus1.postCode |

 #   Then "SINGLE" matching "customer" label is displayed

    And the search results contains a customer with
      | name      | logonID      | email      | telephone      | address       | select |
      | cus1.name | cus1.logonID | cus1.email | cus1.telephone | cus1.postCode | true   |

    When I click on  "customers" link from CSR LHS Menu which contains the result "cus1.lhsNav"
    And I click  on "details" tab for the selected result
    Then I can EDIT the customer Details
      | logonId    | cus1.logonID   |
      | title      | cus1.title     |
      | firstName  | cus1.firstName |
      | middleName |                |
      | lastName   | cus1.lastName  |
      | email1     | cus1.email     |
      | phone1     | cus1.telephone |
      | nickName   | cus1.nickName  |
      | address1   | cus1.address1  |
      | address2   |                |
      | address3   |                |
      | city       | cus1.city      |
      | state      | cus1.state     |
      | country    | cus1.country   |
      | zipCode    | cus1.postCode  |


    When I submit the edited details
    Then the customer details are saved successfully










