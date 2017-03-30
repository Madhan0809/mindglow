@b2c @b2c_pmed1
Feature: Check the PMED and WWHAM Page Functionality Part 1

  Scenario: 1. Check the site header/footer/breadcrumb/subscription is displayed as design on PMED page
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Canesten Thrush Cream 20g" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Canesten Thrush Cream 20g"
    Then The PDP of "Canesten Thrush Cream 20g" opens
    And The "Get the latest offers and product news from Lloyds Pharmacy" subscribe_part is displayed
    And The "Your email address" subscribe_part is displayed
    And The "Subscribe" subscribe_part is displayed
    Then The footer menu headers are displayed
      | Customer Service |
      | Shop With Us     |
      | Our Services     |
      | What's New       |
      | Partner Websites |
    Then The footer menu links are displayed
      | Contact Us                   |
      | My Account                   |
      | Returns                      |
      | Site Map                     |
      | Store Locator                |
      | Delivery and Click & Collect |
      | Cookies                      |
      | Terms and Conditions         |
      | Pharmacy Services            |
      | One-Off Prescriptions        |
      | Repeat Prescriptions         |
      | Stop Smoking Services        |
      | Great Offers                 |
      | Online Pharmacy              |
      | Pain Management Service      |
      | Ratings & Reviews            |
      | Online Doctor                |
      | John Bell & Croyden          |
      | Betterlife                   |
      | Celesio Careers              |
    Then The social media images are displayed
      | Facebook    |
      | Twitter     |
      | Google-Plus |
      | Pinterest   |
      | Youtube     |
      | Our Blog    |

  Scenario: 2. Check the delivery options are displayed as design on PMED page
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Canesten Thrush Cream 20g" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Canesten Thrush Cream 20g"
    Then The PDP of "Canesten Thrush Cream 20g" opens
    And The delivery options are displayed on PMED page
      | Click and Collect     |
      | Deliver to UK address |
    And The delivery texts are displayed on PMED page
      | (FREE) Collect from over 1500 LloydsPharmacy stores.                                                 |
      | (FREE on orders over £35) Including Isle of Man and Channel Islands. Free delivery on prescriptions. |

  Scenario: 3. Check the product name/description/price/tab is displayed as design on PMED page
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Canesten Thrush Cream 20g" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Canesten Thrush Cream 20g"
    Then The PDP of "Canesten Thrush Cream 20g" opens
    And The product name "Canesten Thrush Cream 20g" is displayed on PMED page
    And The short description "Provides effective treatment and soothing relief of the itching caused by thrush." is displayed on PMED page
    And The Offer Price "£7.49" is displayed on PMED page
    #    And The Was Save Price "Was £7.49 Save £0.40" is displayed on PMED page
    And All the info tabs are displayed and clickable
      | Description          |
      | How to use           |
      | Ingredients          |
      | Reviews              |
      | Deliveries & Returns |

  Scenario: 4. Check the type1 WWHAM page is open as design, subscribe part as well
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Canesten Thrush Cream 20g" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Canesten Thrush Cream 20g"
    Then The PDP of "Canesten Thrush Cream 20g" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    And The "Get the latest offers and product news from Lloyds Pharmacy" subscribe_part is displayed
    And The "Your email address" subscribe_part is displayed
    And The "Subscribe" subscribe_part is displayed

  Scenario: 5. Check the type1 WWHAM page is displayed as design, and the Back button functions well
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Canesten Thrush Cream 20g" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Canesten Thrush Cream 20g"
    Then The PDP of "Canesten Thrush Cream 20g" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    And The question title "Questions from your Pharmacist" is displayed on WWHAM page
    And The Headers are displayed on WWHAM page
      | Patient Details |
      | Medication      |
    And The questions are displayed on WWHAM page
      | Is the medicine just for you?                                                                                                      |
      | What medical conditions will the product be used to treat, and how long have you had the symptoms?                                 |
      | What is the age, in years, of the youngest intended user of this product (please give age in months if younger than 1 year)?       |
      | Is the intended user(s) taking any other medication, including vitamins and herbal remedies?                                       |
      | Does the intended user(s) suffer from any other medical conditions (e.g. diabetes, asthma )?                                       |
      | I confirm that I have read the information in this questionnaire and will follow the advice shown here and in the product leaflet. |
    When The user clicks Back button on WWHAM page
    Then The PDP of "Canesten Thrush Cream 20g" opens

  Scenario: 6. Check the type1 WWHAM page is displayed as design, and can continue to checkout.
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Canesten Thrush Cream 20g" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Canesten Thrush Cream 20g"
    Then The PDP of "Canesten Thrush Cream 20g" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    When The user selects "Yes" from the first dropdown list on WWHAM Page
    And The user inputs "Stomachache for 15 days" in the second question on WWHAM Page
    And The user selects "18" Years and "0" Month from the dropdown list on WWHAM Page
    And The user selects "No" from the fourth dropdown list on WWHAM Page
    And The user selects "No" from the sixth dropdown list on WWHAM Page
    And The user checks on the last checkbox on WWHAM Page
    And The user clicks Continue button on WWHAM Page
    When The user clicks Secure Checkout button on shopcart page
    Then The CheckOut SignIn page opens
    When The user inputs "b2c.username" and "b2c.password" as logon ID and password on CheckOut SignIn page
    And The user clicks "Sign In" button on CheckOut SignIn page
    Then The "Delivery options" page opens

  Scenario: 7. Check the error messages of type1 WWHAM are displayed as per design on WWHAM page - Case 1
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Canesten Thrush Cream 20g" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Canesten Thrush Cream 20g"
    Then The PDP of "Canesten Thrush Cream 20g" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    And The user clicks Continue button on WWHAM Page
    Then The error messages are listed on WWHAM Page
      | Is the medicine just for you? Please select an answer from the drop down menu.                                                                |
      | This field is mandatory                                                                                                                       |
      | Please select the number of years.                                                                                                            |
      | Please select the number of months.                                                                                                           |
      | This field is mandatory                                                                                                                       |
      | Is the intended user(s) taking any other medication, including vitamins and herbal remedies? Please select an answer from the drop down menu. |
      | Does the intended user(s) suffer from any other medical conditions (e.g. asthma, diabetes)? Please select an answer from the drop down menu.  |
      | Please tick the box to confirm that you have read and understood the product information provided.                                            |

  Scenario: 8. Check the error messages of type1 WWHAM are displayed as per design on WWHAM page - Case 2
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Canesten Thrush Cream 20g" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Canesten Thrush Cream 20g"
    Then The PDP of "Canesten Thrush Cream 20g" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    When The user selects "No: Self and others" from the first dropdown list on WWHAM Page
    And The user inputs "Stomachache for 15 days" in the second question on WWHAM Page
    And The user selects "0" Years and "0" Month from the dropdown list on WWHAM Page
    And The user selects "No" from the fourth dropdown list on WWHAM Page
    And The user clicks Continue button on WWHAM Page
    Then The error messages are listed on WWHAM Page
      | This age is not valid. Please enter the age of the youngest intended user.                                                                         |
      | Does the intended user(s) suffer from any other medical conditions (e.g. asthma, diabetes)? Please select an answer from the drop down menu. |
      | Please tick the box to confirm that you have read and understood the product information provided.                                           |

  Scenario: 9. Check the error messages of type1 WWHAM are displayed as per design on WWHAM page - Case 3
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Canesten Thrush Cream 20g" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Canesten Thrush Cream 20g"
    Then The PDP of "Canesten Thrush Cream 20g" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    And The user inputs "Stomachache for 15 days" in the second question on WWHAM Page
    When The user selects "Yes" from the first dropdown list on WWHAM Page
    And The user selects "No" from the sixth dropdown list on WWHAM Page
    And The user checks on the last checkbox on WWHAM Page
    And The user clicks Continue button on WWHAM Page
    Then The error messages are listed on WWHAM Page
      | Please select the number of years.                                                                                                            |
      | Please select the number of months.                                                                                                           |
      | This field is mandatory                                                                                                                       |
      | Is the intended user(s) taking any other medication, including vitamins and herbal remedies? Please select an answer from the drop down menu. |

  Scenario: 10. Check the error messages of type1 WWHAM are displayed as per design on WWHAM page - Case 4
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Canesten Thrush Cream 20g" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Canesten Thrush Cream 20g"
    Then The PDP of "Canesten Thrush Cream 20g" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    When The user selects "0" Years and "0" Month from the dropdown list on WWHAM Page
    And The user inputs "Stomachache for 15 days" in the second question on WWHAM Page
    And The user selects "Yes" from the fourth dropdown list on WWHAM Page
    And The user selects "Yes" from the sixth dropdown list on WWHAM Page
    And The user checks on the last checkbox on WWHAM Page
    And The user clicks Continue button on WWHAM Page
    Then The error messages are listed on WWHAM Page
      | Is the medicine just for you? Please select an answer from the drop down menu. |
      | This age is not valid. Please enter the age of the youngest intended user.           |
      | This field is mandatory                                                        |
      | This field is mandatory                                                        |

  Scenario: 11. Check the type2 WWHAM page is displayed as per design
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "beconase" in the Search text box
    And The user clicks the Search button
    Then The search breadcrumb "Search: beconase" is displayed on search result page
    When The user clicks the image of prodcut "Beconase Hayfever Nasal Spray 180 Sprays"
    Then The PDP of "Beconase Hayfever Nasal Spray 180 Sprays" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    And The question title "Questions from your Pharmacist" is displayed on WWHAM page
    And The Headers are displayed on WWHAM page
      | Patient Details |
      #     | Medication      |
      | Using alli®     |
      | Other Medicines |
      | Consents        |
    And The questions are displayed on WWHAM page
      | Is the medicine just for you?                                                                                                      |
      | What medical conditions will the product be used to treat, and how long have you had the symptoms?                                 |
      | What is the Date of Birth of the youngest intended user of this product?                                                           |
      | Please enter the intended user(s) Height.                                                                                          |
      | Please enter the intended user(s) Weight.                                                                                          |
      | Is the intended user(s) taking any other medication, including vitamins and herbal remedies?                                       |
      | Does the intended user(s) suffer from any other medical conditions (e.g. diabetes, asthma )?                                       |
      | Are you taking any of the following medicines or supplements:                                                                      |
      | I confirm that I have read the information in this questionnaire and will follow the advice shown here and in the product leaflet. |
      | Please supply a daytime contact number as we may need to contact you regarding your alli® order                                    |
    When The user clicks Back button on WWHAM page
    Then The PDP of "Beconase Hayfever Nasal Spray 180 Sprays" opens

  Scenario: 12. Check the type2 WWHAM page is displayed correctly and continue to checkout
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "beconase" in the Search text box
    And The user clicks the Search button
    Then The search breadcrumb "Search: beconase" is displayed on search result page
    When The user clicks the image of prodcut "Beconase Hayfever Nasal Spray 180 Sprays"
    Then The PDP of "Beconase Hayfever Nasal Spray 180 Sprays" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    Given User enters all mandatory input fields and dropdown list from WWHAM page
    When The user selects "Yes" from the first dropdown list on WWHAM Page
    And The user inputs "Stomachache for 15 days" in the second question on WWHAM Page
    And User input height and weight for how long of sympotoms on WWHAM page
      | Meter      | 1  |
      | CentiMeter | 76 |
      | Kg         | 50 |
    #    And The user selects "1" Years and "0" Month for how long of the symptoms on WWHAM Page
    And The user selects "No" from the sixth dropdown list on WWHAM Page
    And The user selects "No" from the eighth dropdown list on WWHAM Page
    And The user checks on the last checkbox on WWHAM Page
    And The user inputs the daytime contact number "02071234567"
    And The user clicks Continue button on WWHAM Page
    When The user clicks Secure Checkout button on shopcart page
    Then The CheckOut SignIn page opens
    Then The user inputs "b2c.username" and "b2c.password" as logon ID and password on CheckOut SignIn page
    And The user clicks "Sign In" button on CheckOut SignIn page
    Then The "Delivery options" page opens

  Scenario: 13. Check the error messages of type2 WWHAM page are displayed - Case 1
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "beconase" in the Search text box
    And The user clicks the Search button
    Then The search breadcrumb "Search: beconase" is displayed on search result page
    When The user clicks the image of prodcut "Beconase Hayfever Nasal Spray 180 Sprays"
    Then The PDP of "Beconase Hayfever Nasal Spray 180 Sprays" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    And The user clicks Continue button on WWHAM Page
    Then The error messages are listed on WWHAM Page
      | Please select an answer from the drop down menu.                                                                                              |
      | This field is mandatory                                                                                                                       |
      | Please select the day.                                                                                                                        |
      | Please select the month.                                                                                                                      |
      | Please select the year.                                                                                                                       |
      | This field is mandatory                                                                                                                       |
      | Please enter a valid number.                                                                                                                  |
      | Please enter a weight in kilograms between 35 and 250                                                                                         |
      | Is the intended user(s) taking any other medication, including vitamins and herbal remedies? Please select an answer from the drop down menu. |
      | Does the intended user(s) suffer from any other medical conditions (e.g. asthma, diabetes)? Please select an answer from the drop down menu.  |
      #      | Please tick the box to confirm that you have read and understood the product information provided.                                            |
      | This field is mandatory                                                                                                                       |
      | This field is mandatory                                                                                                                       |

  Scenario: 14. Check the error messages of type2 WWHAM page are displayed - Case 2
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "beconase" in the Search text box
    And The user clicks the Search button
    Then The search breadcrumb "Search: beconase" is displayed on search result page
    When The user clicks the image of prodcut "Beconase Hayfever Nasal Spray 180 Sprays"
    Then The PDP of "Beconase Hayfever Nasal Spray 180 Sprays" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    And The user inputs "Stomachache for 15 days" in the second question on WWHAM Page
    And The user selects "1" Day and "1" Month and "1980" Year from the dropdown list on WWHAM Page
    And The user selects "Yes" from the sixth dropdown list on WWHAM Page
    And The user selects "Yes" from the eighth dropdown list on WWHAM Page
    And The user clicks Continue button on WWHAM Page
    Then The error messages are listed on WWHAM Page
      | Please select an answer from the drop down menu.      |
      | Please enter a valid number.                          |
      | Please enter a weight in kilograms between 35 and 250 |
      | This field is mandatory                               |
      | This field is mandatory                               |
      | This field is mandatory                               |
      | This field is mandatory                               |

  Scenario: 15. Check the error messages of type2 WWHAM page are displayed - Case 3
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "beconase" in the Search text box
    And The user clicks the Search button
    Then The search breadcrumb "Search: beconase" is displayed on search result page
    When The user clicks the image of prodcut "Beconase Hayfever Nasal Spray 180 Sprays"
    Then The PDP of "Beconase Hayfever Nasal Spray 180 Sprays" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    When The user selects "Yes" from the first dropdown list on WWHAM Page
    And The user inputs "Stomachache for 15 days" in the second question on WWHAM Page
    And The user inputs "1" metre and "80" centimeter as height on WWHAM Page
    And The user inputs "70" kilogram as weight on WWHAM Page
    And The user selects "No" from the sixth dropdown list on WWHAM Page
    And The user selects "No" from the eighth dropdown list on WWHAM Page
    And The user checks on the last checkbox on WWHAM Page
    And The user inputs the daytime contact number "aaaaaa"
    And The user clicks Continue button on WWHAM Page
    Then The error messages are listed on WWHAM Page
      | Please select the day.                    |
      | Please select the month.                  |
      | Please select the year.                   |
      | This field is mandatory                   |
      | Please enter your daytime contact number. |

  Scenario: 16. Check the type3 WWHAM page is displayed correctly and the Back button functions well
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Benylin" in the Search text box
    And The user clicks the Search button
    And The user clicks "Benylin Dry Coughs Original 150ml" on the PLP
    Then The PDP of "Benylin Dry Coughs Original 150ml" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    And The question title "Questions from your Pharmacist" is displayed on WWHAM page
    And The Headers are displayed on WWHAM page
      | Patient Details    |
      | Other Medication   |
      | Medical conditions |
      | Symptoms           |
    And The questions are displayed on WWHAM page
      | Is the medicine just for you?                                                                                                      |
      | What medical conditions will the product be used to treat?                                                                         |
      | How long have you had these symptoms?                                                                                              |
      | What is the age, in years, of the youngest intended user of this product? (Please give age in months if younger than 1 year)       |
      | Is the intended user(s) taking any other medication, including vitamins and herbal remedies?                                       |
      | Does the intended user(s) suffer from any other medical conditions (e.g. asthma, diabetes)?                                        |
      | How many nails are affected?                                                                                                       |
      | Has this been recommended by a doctor/podiatrist?                                                                                  |
      | Is this a repeat purchase?                                                                                                         |
      | Which one is the most like your nail?                                                                                              |
      | Which parts of your nail are affected?                                                                                             |
      | I confirm that I have read the information in this questionnaire and will follow the advice shown here and in the product leaflet. |
    When The user clicks Back button on WWHAM page
    Then The PDP of "Benylin Dry Coughs Original 150ml" opens

  Scenario: 17. Check the type3 WWHAM page is displayed correctly and continue to checkout
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Benylin" in the Search text box
    And The user clicks the Search button
    And The user clicks "Benylin Dry Coughs Original 150ml" on the PLP
    Then The PDP of "Benylin Dry Coughs Original 150ml" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    When The user selects "Yes" from the first dropdown list on WWHAM Page
    And The user selects "1" Years and "0" Month for how long of the symptoms on WWHAM Page
    And The user selects "18" Years and "6" Month for the youngest intended user on WWHAM Page
    And The user inputs "normal" for medical conditions on WWHAM Page
    And The user selects "No" from the sixth dropdown list on WWHAM Page
    And The user selects "No" from the eighth dropdown list on WWHAM Page
    And The user selects "2" nails are affected on WWHAM Page
    And The user selects "Yes" that has been recommended by a doctor on WWHAM Page
    And The user selects "Yes" that it is a repeat purchase on WWHAM Page
    And The user selects "Yes" that the treatment is working on WWHAM Page
    And The user selects "A" that is the most like your nail on WWHAM Page
    And The user selects "the entire nail" that are affected on WWHAM Page
    And The user checks on the last checkbox on WWHAM Page
    And The user clicks Continue button on WWHAM Page
    When The user clicks Secure Checkout button on shopcart page
    Then The CheckOut SignIn page opens
    Then The user inputs "b2c.username" and "b2c.password" as logon ID and password on CheckOut SignIn page
    And The user clicks "Sign In" button on CheckOut SignIn page
    Then The "Delivery options" page opens

  Scenario: 18. Check the error messages of type3 WWHAM page are displayed correctly - Case 1
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Benylin" in the Search text box
    And The user clicks the Search button
    And The user clicks "Benylin Dry Coughs Original 150ml" on the PLP
    Then The PDP of "Benylin Dry Coughs Original 150ml" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    And The user clicks Continue button on WWHAM Page
    Then The error messages are listed on WWHAM Page
      | Is the medicine just for you? Please select an answer from the drop down menu.                                                                |
      | What medical conditions will the product be used to treat?                                                                                    |
      | Please select the number of years.                                                                                                            |
      | Please select the number of months.                                                                                                           |
      | This field is mandatory                                                                                                                       |
      | Please select the number of years.                                                                                                            |
      | Please select the number of months.                                                                                                           |
      | This field is mandatory                                                                                                                       |
      | Is the intended user(s) taking any other medication, including vitamins and herbal remedies? Please select an answer from the drop down menu. |
      | Does the intended user(s) suffer from any other medical conditions (e.g. asthma, diabetes)? Please select an answer from the drop down menu.  |
      | Please enter how many nails are affected?                                                                                                     |
      | Has this been recommended by a doctor/podiatrist?                                                                                             |
      | Is this a repeat purchase?                                                                                                                    |
      | Please select which one is the most like your nail?                                                                                           |
      | Which parts of your nail are affected?                                                                                                        |
      | Please tick the box to confirm that you have read and understood the product information provided.                                            |

  Scenario: 19. Check the error messages of type3 WWHAM page are displayed correctly - Case 2
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Benylin" in the Search text box
    And The user clicks the Search button
    And The user clicks "Benylin Dry Coughs Original 150ml" on the PLP
    Then The PDP of "Benylin Dry Coughs Original 150ml" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    When The user selects "No: Self and others" from the first dropdown list on WWHAM Page
    And The user selects "2" Years and "1" Month for how long of the symptoms on WWHAM Page
    And The user selects "8" Years and "1" Month for the youngest intended user on WWHAM Page
    And The user selects "4" nails are affected on WWHAM Page
    And The user selects "No" that has been recommended by a doctor on WWHAM Page
    And The user selects "No" that it is a repeat purchase on WWHAM Page
    And The user selects "B" that is the most like your nail on WWHAM Page
    And The user selects "the base of the nail" that are affected on WWHAM Page
    And The user checks on the last checkbox on WWHAM Page
    And The user clicks Continue button on WWHAM Page
    Then The error messages are listed on WWHAM Page
      | This field is mandatory                                                                                                                       |
      | What medical conditions will the product be used to treat?                                                                                    |
      | You need to be over 18 years old to be eligible for this product.                                                                             |
      | Is the intended user(s) taking any other medication, including vitamins and herbal remedies? Please select an answer from the drop down menu. |
      | Does the intended user(s) suffer from any other medical conditions (e.g. asthma, diabetes)? Please select an answer from the drop down menu.  |
      | This product is not suitable if more than two nails are affected.                                                                             |
      | If your nail resembles this image, we suggest you visit your GP.                                                                              |

  Scenario: 20. Check the error messages of type3 WWHAM page are displayed correctly - Case 2
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Benylin" in the Search text box
    And The user clicks the Search button
    And The user clicks "Benylin Dry Coughs Original 150ml" on the PLP
    Then The PDP of "Benylin Dry Coughs Original 150ml" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    When The user selects "No: Self and others" from the first dropdown list on WWHAM Page
    And The user selects "0" Years and "0" Month for how long of the symptoms on WWHAM Page
    And The user selects "0" Years and "0" Month for the youngest intended user on WWHAM Page
    And The user selects "4" nails are affected on WWHAM Page
    And The user selects "No" that has been recommended by a doctor on WWHAM Page
    And The user selects "No" that it is a repeat purchase on WWHAM Page
    And The user selects "B" that is the most like your nail on WWHAM Page
    And The user selects "the base of the nail" that are affected on WWHAM Page
    And The user checks on the last checkbox on WWHAM Page
    And The user clicks Continue button on WWHAM Page
    Then The error messages are listed on WWHAM Page
      | This field is mandatory                                                                                                                       |
      | What medical conditions will the product be used to treat?                                                                                    |
      | You need to be over 18 years old to be eligible for this product.                                                                             |
      #  | Not a valid age? Please enter an age.                                                                                                         |
      | Is the intended user(s) taking any other medication, including vitamins and herbal remedies? Please select an answer from the drop down menu. |
      | Does the intended user(s) suffer from any other medical conditions (e.g. asthma, diabetes)? Please select an answer from the drop down menu.  |
      | This product is not suitable if more than two nails are affected.                                                                             |
      | If your nail resembles this image, we suggest you visit your GP.                                                                              |
