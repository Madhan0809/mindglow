@csr_find_customers
Feature: Find Customers from CSR portal

  Background:
    Given The user is on CSR Signin page
    When The user inputs "csr.admin" and "csr.admin_pwd" as logon ID and password on CheckOut SignIn page
    And The user clicks "Sign In" button on CheckOut SignIn page
    Then The user is signed in and navigated to CSR Home Page
    When I click on Find  "customers" link from CSR LHS Menu
    Then the "find.customerHeader" header is displayed for the search screen


  Scenario: Find Customer from CSR Portal with Partial Query

#Perform partial query search to return customer  with Logon ID
    When I enter the search criteria to find customers
      | logonID      | firstName | lastName | email | postCode |
      | cus1.logonID |           |          |       |          |

    Then "SINGLE" matching "customers" label is displayed

    And the search results contains a customer with
      | name      | logonID      | email      | telephone      | address       | select |
      | cus1.name | cus1.logonID | cus1.email | cus1.telephone | cus1.postCode | false  |

    When I clear the search results
    #Then the results are cleared and "find.noCustomersMessage" message is displayed


    #Perform partial query search to return customer  with Email Address
    When I enter the search criteria to find customers
      | logonID | firstName | lastName | email      | postCode |
      |         |           |          | cus1.email |          |

    Then "SINGLE" matching "customer" label is displayed

    And the search results contains a customer with
      | name      | logonID      | email      | telephone      | address       | select |
      | cus1.name | cus1.logonID | cus1.email | cus1.telephone | cus1.postCode | false  |

    When I clear the search results
    #Then the results are cleared and "find.noCustomersMessage" message is displayed

    #Perform partial query search to return customer  with First Name and Last Name
    When I enter the search criteria to find customers
      | logonID | firstName      | lastName      | email | postCode |
      |         | cus1.firstName | cus1.lastName |       |          |

    And the search results contains a customer with
      | name      | logonID      | email      | telephone      | address       | select |
      | cus1.name | cus1.logonID | cus1.email | cus1.telephone | cus1.postCode | false  |

    When I clear the search results
    #Then the results are cleared and "find.noCustomersMessage" message is displayed

#Perform partial query search to return customer  with Last Name and Post Code
    When I enter the search criteria to find customers
      | logonID | firstName | lastName      | email | postCode      |
      |         |           | cus1.lastName |       | cus1.postCode |


    And the search results contains a customer with
      | name      | logonID      | email      | telephone      | address       | select |
      | cus1.name | cus1.logonID | cus1.email | cus1.telephone | cus1.postCode | false  |


  Scenario: Find Customer from CSR Portal with Partial Query to return Multiple Results and test Pagination

    #Perform partial query search to return multiple customers with Post Code
    When I enter the search criteria to find customers
      | logonID | firstName | lastName | email | postCode      |
      |         |           |          |       | cus2.postCode |

    Then "MULTIPLE" matching "customers" label is displayed
    And the search results contains a customer with
      | name      | logonID      | email      | telephone      | address       | select |
      | cus2.name | cus2.logonID | cus2.email | cus2.telephone | cus2.postCode | false  |

    And the results page is paginated by 5 results per page


  Scenario: No Matching customers found
    When I enter the search criteria to find customers
      | logonID | firstName | lastName | email | postCode |
      |         | invalid   |          |       |          |
    #Then the form validation message "find.noCustomersMessage" is displayed

