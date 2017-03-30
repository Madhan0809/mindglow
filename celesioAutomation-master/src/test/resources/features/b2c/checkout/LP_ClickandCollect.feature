@b2c @b2c_clickAndcollect
Feature: Check Click and Collect Option

  Background: navigate to checkout signin page with both C&C and delivery to UK option
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "skineffect" in the Search text box
    And The user clicks the Search button
    When The user clicks "SKINeffect Body Lotion 200ml" on the PLP
    And The user clicks Add to basket button
    And The user clicks mini shop cart
    When The user clicks Secure Checkout button on shopcart page
    Then The CheckOut SignIn page opens
    Then The user inputs "b2c.username" and "b2c.password" as logon ID and password on CheckOut SignIn page

  Scenario: TC05. Click & Collect User can change the selected collection point
    Given The user enter delivery option page with guest user
    Then The "Delivery options" page opens
    Given The user selects "Click and Collect" delivery radio button
    Then Check the "Click and Collect" text table and form in should display
    When Input "C&C" click and collect information for delivery
      | postCode     | london        |
      | emailAddress | test@mail.com |
      | phoneNumber  | 11111112222   |
    And Click on Find Address button in "Click and collect" delivery option
    Then Check user enter store locator lister page
    When User click store "A" from locator map
    When User select store "A" from google map
    When User change the collection point in delivery page
    Then The store collection point is "not displayed" in C&C from delivery page

  Scenario: TC06. Validate the error message is correct if C&C user enters invalid information
    Given The user enter delivery option page with guest user
    Then The "Delivery options" page opens
    Given The user selects "Click and Collect" delivery radio button
    Then Check the "Click and Collect" text table and form in should display
    And Input "Registered" click and collect information for delivery
      | postCode     |         |
      | emailAddress | invalid |
      | phoneNumber  | invalid |
    And Click on Find Address button in "Click and collect" delivery option
    Then Validate error message details in delivery option page
      | Please enter a valid Postcode or Town name |
      | The first name field should be Alphabetic  |
      | The last name field should be Alphabetic   |
      | Please enter a valid email address         |
      | Please enter a valid phone number          |

  Scenario: TC16. Check a guest user can get correct order summary information
    Given The user enter delivery option page with guest user
    Then The "Delivery options" page opens
    And Get the information of order summary
    Then Check the result of order summary is correct
      | VAT Relief | £0.00 |
      | Discount   | £0.00 |
      | Total      | £7.99 |

  Scenario: TC17. Check a guest user can get correct order detail information
    Given The user enter delivery option page with guest user
    Then The "Delivery options" page opens
    Then Check the result of order detail information is correct
      | SKINeffect Body Lotion 200ml | QTY: 1 | £7.99 |

  Scenario: TC18. C&C User can access store locator lister page
    Given The user enter delivery option page with guest user
    Then The "Delivery options" page opens
    When The user selects "Click and Collect" delivery radio button
    And Input "Guest User" click and collect information for delivery
      | postCode     | London        |
      | emailAddress | test@mail.com |
      | phoneNumber  | 11111112222   |
    And Click on Find Address button in "Click and collect" delivery option
    Then Check user enter store locator lister page
    And Check Store lister information can be shown

  Scenario: TC19. Check the store information popup can be shown from google map
    Given The user enter delivery option page with guest user
    Then The "Delivery options" page opens
    When The user selects "Click and Collect" delivery radio button
    And Input "Guest User" click and collect information for delivery
      | postCode | London |
    And Click on Find Address button in "Click and collect" delivery option
    Then Check user enter store locator lister page
    When User click store "A" from locator map
    Then The store detail information is displayed in popup from google map

  Scenario: TC20. The specific brand map pin is matched with google map pin
    Given The user enter delivery option page with guest user
    Then The "Delivery options" page opens
    When The user selects "Click and Collect" delivery radio button
    And Input "Guest User" click and collect information for delivery
      | postCode | London |
    And Click on Find Address button in "Click and collect" delivery option
    Then Check user enter store locator lister page
    When User click store "A" from locator map
    When User get the store detail information from google map
    Then User can get the matching store detail information of store "1" from store list

  Scenario: TC21. Check user can search store from store locator liter page
    Given The user enter delivery option page with guest user
    Then The "Delivery options" page opens
    When The user selects "Click and Collect" delivery radio button
    And Input "Guest User" click and collect information for delivery
      | postCode | London |
    And Click on Find Address button in "Click and collect" delivery option
    Then Check user enter store locator lister page
    When User search store "london" from store locator list page
    Then Check user enter store locator lister page

  Scenario: TC22. Check user can enter store locator details page to select store
    Given The user enter delivery option page with guest user
    Then The "Delivery options" page opens
    When The user selects "Click and Collect" delivery radio button
    And Input "Guest User" click and collect information for delivery
      | postCode | London |
    And Click on Find Address button in "Click and collect" delivery option
    Then Check user enter store locator lister page
    When User views the store details of "1"
    Then Store detail information can be shown
    When User select store from store details page
    Then The store collection point is "displayed" in C&C from delivery page

  Scenario: TC23. Check user can select store from result list page
    Given The user enter delivery option page with guest user
    # Given The user clicks "Sign In" button on CheckOut SignIn page
    Then The "Delivery options" page opens
    Given The user selects "Click and Collect" delivery radio button
    Then Check the "Click and Collect" text table and form in should display
    When Input "C&C" click and collect information for delivery
      | postCode | london |
    And Click on Find Address button in "Click and collect" delivery option
    Then Check user enter store locator lister page
    When User click store "A" from locator map
    When User select store "A" from store result list
    Then The store collection point is "displayed" in C&C from delivery page

  #PartV Mixed basket
  Scenario: TC24. Check a registered user with mixed basket which not eligible for C&C cannot get C&C Delivery option
    Given The user is on Lloyds Pharmacy home page without delete cookies
    When The user inputs "Summer Floral Invigorating Essentials Gift Set" in the Search text box
    And The user clicks the Search button
    When The user clicks "Summer Floral Invigorating Essentials Gift Set" on the PLP
    And The user clicks Add to basket button
    And The user clicks mini shop cart
    When The user clicks Secure Checkout button on shopcart page
    Then The CheckOut SignIn page opens
    Then The user inputs "b2c.username" and "b2c.password" as logon ID and password on CheckOut SignIn page
    Given The user clicks "Sign In" button on CheckOut SignIn page
    When The "Delivery options" page opens
    Given The user selects "Click and Collect" delivery radio button
    Then Check the "Click and Collect" exception table should display
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display

  Scenario: TC26. Check a registered user with mixed basket can get 'Delivery to UK address' option but it is not eligible for C&C
    Given The user is on Lloyds Pharmacy home page without delete cookies
    When The user inputs "cushioned" in the Search text box
    And The user clicks the Search button
    When The user clicks "Betterlife Rota-Cushion Standard" on the PLP
    And The user clicks Add to basket button
    And The user clicks mini shop cart
    When The user clicks Secure Checkout button on shopcart page
    Then the VatRelief option is selected "true"
    Then The CheckOut SignIn page opens
    Then The user inputs "b2c.username" and "b2c.password" as logon ID and password on CheckOut SignIn page
    And The user clicks "Sign In" button on CheckOut SignIn page
    Then The "Delivery options" page opens
    Given The user selects "Click and Collect" delivery radio button
    Then Check the "Click and Collect" exception table should display
    Given The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display

  Scenario: TC28. Check a guest user with mixed basket can get correct order detail information
    Given The user is on Lloyds Pharmacy home page without delete cookies
    When The user inputs "haircare" in the Search text box
    And The user clicks the Search button
    And The user clicks "Haircare - Hair Pony-Brown" on the PLP
    And The user clicks Add to basket button
    And The user clicks mini shop cart
    When The user clicks Secure Checkout button on shopcart page
    Then The CheckOut SignIn page opens
    When The user enter delivery option page with guest user
    Then The "Delivery options" page opens
    And Check the result of order detail information is correct
      | SKINeffect Body Lotion 200ml | QTY: 1 | £7.99 |
      | Haircare - Hair Pony-Brown   | QTY: 1 | £1.99 |

  Scenario: TC29. Check a guest user with mixed basket can get correct order summary information
    Given The user is on Lloyds Pharmacy home page without delete cookies
    When The user inputs "haircare" in the Search text box
    And The user clicks the Search button
    And The user clicks "Haircare - Hair Pony-Brown" on the PLP
    And The user clicks Add to basket button
    And The user clicks mini shop cart
    And The user clicks Secure Checkout button on shopcart page
    Then The CheckOut SignIn page opens
    When The user enter delivery option page with guest user
    And The user selects "Click and Collect" delivery radio button
    Then The "Delivery options" page opens
    And Get the information of order summary
    Then Check the result of order summary is correct
#      | VAT Relief | £0.00 |
#      | Discount   | £0.00 |
      | Total      | £9.98 |

  Scenario: TC31. Check a guest user with mixed basket can input valid C&C info and go to payment
    Given The user is on Lloyds Pharmacy home page without delete cookies
    When The user inputs "skineffect" in the Search text box
    And The user clicks the Search button
    And The user clicks "SKINeffect Anti-Ageing Face & Eye Serum 15ml" on the PLP
    And The user clicks Add to basket button
    And The user clicks mini shop cart
    When The user clicks Secure Checkout button on shopcart page
#    Then the VatRelief option is selected "true"
    Then The CheckOut SignIn page opens
    When The user enter delivery option page with guest user
    Then The "Delivery options" page opens
    When The user selects "Click and Collect" delivery radio button
    And Check the "Click and Collect" text table and form in should display
    When Input "C&C" click and collect information for delivery
      | postCode | london |
    And Click on Find Address button in "Click and collect" delivery option
    Then Check user enter store locator lister page
    When User click store "A" from locator map
    And User select store "A" from google map
    Then The store collection point is "displayed" in C&C from delivery page
    And Input "C&C" click and collect information for delivery
      | firstName    | guestAuto     |
      | lastName     | guestUser     |
      | emailAddress | test@mail.com |
      | phoneNumber  | 11111112222   |
    When I click on Continue to payment button
    Then the Payment page opens

  Scenario: Check a registered user use address lookup to find store and continue to payment
    Given The user clicks "Sign In" button on CheckOut SignIn page
    When The "Delivery options" page opens
    Given The user selects "Click and Collect" delivery radio button
    When Input "C&C" click and collect information for delivery
      | postCode | HERTFORDSHIRE |
    And Click on Find Address button in "Click and collect" delivery option
    Then Check user enter store locator lister page
    When User click store "A" from locator map
    When User select store "A" from store result list
    Then The store collection point is "displayed" in C&C from delivery page
    And Input "C&C" click and collect information for delivery
      | firstName    | guestAuto     |
      | lastName     | guestUser     |
      | emailAddress | test@mail.com |
      | phoneNumber  | 11111112222   |
    When I click on Continue to payment button
    Then the Payment page opens

  Scenario: TC33. Check whether remove and continue works for C&C option with mixed basket
    Given The user is on Lloyds Pharmacy home page without delete cookies
    When The user inputs "Summer Floral Invigorating Essentials Gift Set" in the Search text box
    And The user clicks the Search button
    And The user clicks "Summer Floral Invigorating Essentials Gift Set" on the PLP
    And The user clicks Add to basket button
    And The user clicks mini shop cart
    And The user clicks Secure Checkout button on shopcart page
    Then The CheckOut SignIn page opens
    Then The user inputs "b2c.username" and "b2c.password" as logon ID and password on CheckOut SignIn page
    And The user clicks "Sign In" button on CheckOut SignIn page
    Then The "Delivery options" page opens
    When The user selects "Click and Collect" delivery radio button
    Then Check the "Click and Collect" exception table should display
    And Check error message for exception table in "Click and Collect" form is correct
      | Sorry, Click and Collect is not available for the following items(s). |
      | Please remove the following from your basket to continue              |
    When Click on remove and continue button in "Click and Collect" form in delivery option page
    And The user selects "Click and Collect" delivery radio button
    Then Check the "Click and Collect" text table and form in should display
    When The user selects "Deliver to UK address" delivery radio button
    Then Check the "Deliver to UK address" text table and form in should display
