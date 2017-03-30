@csr_home
Feature: CSR Home page

  Background:
    Given The user is on CSR Signin page
    When The user inputs "csr.admin" and "csr.admin_pwd" as logon ID and password on CheckOut SignIn page
    And The user clicks "Sign In" button on CheckOut SignIn page
    Then The user is signed in and navigated to CSR Home Page

  Scenario: CSR Home Page left hand side menu links
    And the CSR menu is displayed is on the LHS
      | Your Open Ticklers (1) |
      | Customers              |
      | Orders                 |
      | Returns                |
      | Ticklers               |
      | Patients               |
      | Meds Orders            |
      | VAT Exempt Orders      |
      | Responsible Pharmacist |

    #Below  step is an examples to help future automation for clicking on the desired LHS menu by providing the link name
    When I click on "VAT Exempt Orders" link from CSR LHS Menu

  @csr_blocked
  Scenario: CSR Agent is able to navigate back to a relevant store brand for a selected channel and Store brand from the Header
    When I choose channel "Web Channel" and store brand "LloydsPharmacy"
    Then the relevant store brand go to "LloydsPharmacy" is displayed
    When I click on the relevant store brand got to "LloydsPharmacy"
    Then The user signs in LloydsPharmacy successfully




