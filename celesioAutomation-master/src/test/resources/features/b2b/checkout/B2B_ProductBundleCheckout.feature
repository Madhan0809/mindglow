@b2b_productbundlecheckout @b2b 
Feature: Product Bundle Checkout function

  Background: 
    Given The user is on AAH  Sign-in page as a guest
    And The user provide multi account username and password
    And User clicks on Sign In button
    And The user is on AAH home page with choosing account

  Scenario: TC01: Search a bundle and navigate to the PDP page
    When The user inputs text as "bundle" in search box
    And The user clicks on the Search button    
    And The user clicks "b2b.product.bundle" bundle link
    Then The user is on "b2b.product.bundle" bundle PDP
    And The bundle name "b2b.product.bundle" is shown correctly
	And The message "To buy this item simply select from the product options below & click 'Add selected to my basket'" is shown on the grey panel
	And The basket dropdown titles are shown correctly on the grey panel
		| AAH Basket:        |
		| ENTERPRISE Basket: |
	And The products in this bundle are listed correctly
		| ADJUSTABLE BASE WITH REFLEX FOAM MATTRESS |		
		| MASSAGE SYSTEM                            |
		| HEADBOARD                                 |
	And The product detail table titles are listed correctly
		| Variant Name   |
		| Options        |
		| Price exc. VAT |
		| You Make*      |
		| Availability   |
		| Quantity       |
		| Select         |

  Scenario: TC02: Add a bundle to a basket
    When User enter my basket page after login
    And User cleanup the basket "b2b.aah.currentBasket" in my basket page
    When The user inputs text as "bundle" in search box
    And The user clicks on the Search button
    And The user clicks "b2b.product.bundle" bundle link
    Then The user is on "b2b.product.bundle" bundle PDP
    And The user checks on all products for AAH
    When The user select "b2b.aah.currentBasket" from the "AAH" basket dropdown on the grey panel
    And The user clicks "Add Selected To Basket" on the grey panel
    And The user select "b2b.aah.currentBasket" from mini basket in "AAH" row
    And The user click the AAH mini cart button
    Then The "Current Order" page is displayed
    Then The products in this basket are listed correctly
    	| Adjustable Base with Reflex Foam Mattress |
		| Headboard                                 |
		| Massage System                            |
