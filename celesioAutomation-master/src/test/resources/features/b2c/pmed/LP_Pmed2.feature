@b2c @b2c_pmed2
Feature: Check the PMED and WWHAM Page Functionality Part 2

  Scenario: 21. Check the type4 WWHAM page is displayed as design
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Otrivine" in the Search text box
    And The user clicks the Search button
    Then The search breadcrumb "Search: Otrivine" is displayed on search result page
    When The user clicks the image of prodcut "Otrivine Antistin Eye Drops 10ml"
    Then The PDP of "Otrivine Antistin Eye Drops 10ml" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    And The question title "Questions from your Pharmacist" is displayed on WWHAM page
    And The Headers are displayed on WWHAM page
      | Patient Details    |
      | Other Medication   |
      | Medical conditions |
    And The questions are displayed on WWHAM page
      | Is the medicine just for you?                                                                                                      |
      | What is the age in years and months of the intended user of this product?                                                          |
      | Please indicate your gender.                                                                                                       |
      | Do you have shaved scalp?                                                                                                          |
      | Is the intended user(s) taking any other medication, including vitamins and herbal remedies?                                       |
      | Does the intended user(s) suffer from any other medical conditions (e.g. asthma, diabetes)?                                        |
      | I confirm that I have read the information in this questionnaire and will follow the advice shown here and in the product leaflet. |
    When The user clicks Back button on WWHAM page
    Then The PDP of "Otrivine Antistin Eye Drops 10ml" opens

  Scenario: 22. Check the type4 WWHAM page is displayed as design, and can continue to checkout
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Otrivine" in the Search text box
    And The user clicks the Search button
    Then The search breadcrumb "Search: Otrivine" is displayed on search result page
    When The user clicks the image of prodcut "Otrivine Antistin Eye Drops 10ml"
    Then The PDP of "Otrivine Antistin Eye Drops 10ml" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    When The user selects "Yes" from the first dropdown list on WWHAM Page
    And The user selects "18" Years and "0" Month from the dropdown list on WWHAM Page
    And The user selects "Male" of gender from the dropdown list on WWHAM Page
    And The user selects "No" of the shaved scalp from the dropdown list on WWHAM Page
    And The user selects "No" from the sixth dropdown list on WWHAM Page
    And The user selects "No" from the eighth dropdown list on WWHAM Page
    And The user checks on the last checkbox on WWHAM Page
    And The user clicks Continue button on WWHAM Page
    When The user clicks Secure Checkout button on shopcart page
    Then The CheckOut SignIn page opens
    Then The user inputs "b2c.username" and "b2c.password" as logon ID and password on CheckOut SignIn page
    And The user clicks "Sign In" button on CheckOut SignIn page
    Then The "Delivery options" page opens

  Scenario: 23. Check the error messages of type4 WWHAM page are displayed as per design - Case 1
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Otrivine" in the Search text box
    And The user clicks the Search button
    Then The search breadcrumb "Search: Otrivine" is displayed on search result page
    When The user clicks the image of prodcut "Otrivine Antistin Eye Drops 10ml"
    Then The PDP of "Otrivine Antistin Eye Drops 10ml" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    And The user clicks Continue button on WWHAM Page
    Then The error messages are listed on WWHAM Page
      | Is the medicine just for you? Please select an answer from the drop down menu.                                                                |
      | Select years of the intended user of this product.                                                                                            |
      | Select months of the intended user of this product.                                                                                           |
      | Please indicate your gender.                                                                                                                  |
      | Do you have a shaved scalp?                                                                                                                   |
      | Is the intended user(s) taking any other medication, including vitamins and herbal remedies? Please select an answer from the drop down menu. |
      | Does the intended user(s) suffer from any other medical conditions (e.g. asthma, diabetes)? Please select an answer from the drop down menu.  |
      | Please tick the box to confirm that you have read and understood the product information provided.                                            |

  Scenario: 24. Check the error messages of type4 WWHAM page are displayed as per design - Case 2
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Otrivine" in the Search text box
    And The user clicks the Search button
    Then The search breadcrumb "Search: Otrivine" is displayed on search result page
    When The user clicks the image of prodcut "Otrivine Antistin Eye Drops 10ml"
    Then The PDP of "Otrivine Antistin Eye Drops 10ml" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    When The user selects "No: Self and others" from the first dropdown list on WWHAM Page
    And The user selects "10" Years and "6" Month from the dropdown list on WWHAM Page
    And The user selects "Female" of gender from the dropdown list on WWHAM Page
    And The user selects "Yes" of the shaved scalp from the dropdown list on WWHAM Page
    And The user selects "Yes" from the sixth dropdown list on WWHAM Page
    And The user selects "Yes" from the eighth dropdown list on WWHAM Page
    And The user checks on the last checkbox on WWHAM Page
    And The user clicks Continue button on WWHAM Page
    Then The error messages are listed on WWHAM Page
      | This field is mandatory                                                     |
      | You need to be between 18 and 65 years old to be eligible for this product. |
      | This product is not suitable if you have a shaved scalp.                    |
      | This field is mandatory                                                     |
      | This field is mandatory                                                     |
  
  Scenario: 25. Check the error messages of type4 WWHAM page are displayed as per design - Case 2
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "Otrivine" in the Search text box
    And The user clicks the Search button
    Then The search breadcrumb "Search: Otrivine" is displayed on search result page
    When The user clicks the image of prodcut "Otrivine Antistin Eye Drops 10ml"
    Then The PDP of "Otrivine Antistin Eye Drops 10ml" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    When The user selects "No: Self and others" from the first dropdown list on WWHAM Page
    And The user inputs "For John" in the second question on WWHAM Page
    And The user selects "70" Years and "0" Month from the dropdown list on WWHAM Page
    And The user selects "Female" of gender from the dropdown list on WWHAM Page
    And The user selects "No" of the shaved scalp from the dropdown list on WWHAM Page
    And The user selects "No" from the sixth dropdown list on WWHAM Page
    And The user selects "No" from the eighth dropdown list on WWHAM Page
    And The user checks on the last checkbox on WWHAM Page
    And The user clicks Continue button on WWHAM Page
    Then The error messages are listed on WWHAM Page
      | You need to be between 18 and 65 years old to be eligible for this product. |

  Scenario: 26. Check the type7 WWHAM page is displayed as per design
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "feminax" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Feminax Ultra Maximum Strength 9 Tablets"
    Then The PDP of "Feminax Ultra Maximum Strength 9 Tablets" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    And The question title "Questions from your Pharmacist" is displayed on WWHAM page
    And The Headers are displayed on WWHAM page
      | Patient Details |
    And The questions are displayed on WWHAM page
      | Is this medicine just for you?                                                                                                     |
      | Where are you travelling? Please provide accurate details of all destinations/Areas on the trip                                    |
      | Have you been advised by your GP/Nurse/Travel clinic to take this medication?                                                      |
      | When are you planning to travel?                                                                                                   |
      | How long are you travelling for?                                                                                                   |
      | Have you been prescribed/ are you planning to take any other anti-malarials?                                                       |
      | Are you allergic to proguanil hydrochloride?                                                                                       |
      | Have you ever experienced, or do you currently have, any kidney problems or liver problems (hepatic or renal impairment)?          |
      | Do you take any anti-coagulant medication such as Warfarin?                                                                        |
      | Do you take anatacids regularly?                                                                                                   |
      | Are you pregnant/ breast-feeding?                                                                                                  |
      | I confirm that I have read the information in this questionnaire and will follow the advice shown here and in the product leaflet. |
    When The user clicks Back button on WWHAM page
    Then The PDP of "Feminax Ultra Maximum Strength 9 Tablets" opens

  Scenario: 27. Check the type7 WWHAM page is displayed as per design and can continue to checkout
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "feminax" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Feminax Ultra Maximum Strength 9 Tablets"
    Then The PDP of "Feminax Ultra Maximum Strength 9 Tablets" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    When The user selects "Yes" from the first dropdown list on WWHAM Page
    And The user inputs "UK" for travel destination on WWHAM Page
    And The user selects "No" from the fourth dropdown list on WWHAM Page
    And The user selects "8" Day "1" Month "2015" Year for travel date on WWHAM Page
    And The user selects "2" Years "2" Months and "2" Days for travel duration on WWHAM Page
    And The user selects "No" from the seventh dropdown list on WWHAM Page
    And The user selects "No" from the eighth dropdown list on WWHAM Page
    And The user selects "No" from the nineth dropdown list on WWHAM Page
    And The user selects "No" from the tenth dropdown list on WWHAM Page
    And The user selects "No" from the twelveth dropdown list on WWHAM Page
    And The user selects "No" from the thirteenth dropdown list on WWHAM Page
    And The user checks on the last checkbox on WWHAM Page
    And The user clicks Continue button on WWHAM Page
    When The user clicks Secure Checkout button on shopcart page
    Then The CheckOut SignIn page opens
    Then The user inputs "b2c.username" and "b2c.password" as logon ID and password on CheckOut SignIn page
    And The user clicks "Sign In" button on CheckOut SignIn page
    Then The "Delivery options" page opens

  Scenario: 28. Check the error messages of type7 WWHAM page are displayed as per design - Case 1
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "feminax" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Feminax Ultra Maximum Strength 9 Tablets"
    Then The PDP of "Feminax Ultra Maximum Strength 9 Tablets" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    And The user clicks Continue button on WWHAM Page
    Then The error messages are listed on WWHAM Page
      | Is the medicine just for you? Please select an answer from the drop down menu.                                            |
      | This field is mandatory                                                                                                   |
      | Please select an answer from the drop down menu.                                                                          |
      | Please select the day.                                                                                                    |
      | Please select the month.                                                                                                  |
      | Please select the year.                                                                                                   |
      | This field is mandatory                                                                                                   |
      | Please select the number of years.                                                                                        |
      | Please select the number of months.                                                                                       |
      | Please select the number of days.                                                                                         |
      | This field is mandatory                                                                                                   |
      | Have you been prescribed/ are you planning to take any other anti-malarials?                                              |
      | Please select an answer from the drop down menu.                                                                          |
      | Have you ever experienced, or do you currently have, any kidney problems or liver problems (hepatic or renal impairment)? |
      | Do you take any anti-coagulant medication such as Warfarin?                                                               |
      | Do you take antacids regularly?                                                                                           |
      | Are you pregnant/ breast-feeding?                                                                                         |
      | Please tick the box to confirm that you have read and understood the product information provided.                        |

  Scenario: 29. Check the error messages of type7 WWHAM page are displayed as per design - Case 2
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "feminax" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Feminax Ultra Maximum Strength 9 Tablets"
    Then The PDP of "Feminax Ultra Maximum Strength 9 Tablets" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    When The user selects "No: Self and others" from the first dropdown list on WWHAM Page
    And The user selects "No" from the fourth dropdown list on WWHAM Page
    And The user selects "0" Years "0" Months and "0" Days for travel duration on WWHAM Page
    And The user selects "Yes" from the seventh dropdown list on WWHAM Page
    And The user selects "Yes" from the eighth dropdown list on WWHAM Page
    And The user selects "Yes" from the nineth dropdown list on WWHAM Page
    And The user selects "Yes" from the tenth dropdown list on WWHAM Page
    And The user selects "Yes" from the twelveth dropdown list on WWHAM Page
    And The user selects "Yes" from the thirteenth dropdown list on WWHAM Page
    And The user clicks Continue button on WWHAM Page
    Then The error messages are listed on WWHAM Page
      #      | Is the medicine just for you? Please select an answer from the drop down menu.                                                                       |
      | This field is mandatory                                                                                                                              |
      | This field is mandatory                                                                                                                              |
      | Please select the day.                                                                                                                               |
      | Please select the month.                                                                                                                             |
      | Please select the year.                                                                                                                              |
      | This field is mandatory                                                                                                                              |
 #     | Please select at least 1 day(s).                                                                                                                     |
      | This product is not suitable if you are allergic to proguanil hydrocholride - please consult your GP.                                                |
      | This product is not suitable if you have ever experienced, or you do currently have, any kidney problems or liver problems - please consult your GP. |
      | If 'Yes', please provide exact details.                                                                                                              |
      | Please tick the box to confirm that you have read and understood the product information provided.                                                   |

  Scenario: 30. Check the type10 WWHAM page is displayed as per design
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "beechams" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Beechams All In One Liquid 240ml"
    Then The PDP of "Beechams All In One Liquid 240ml" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    And The question title "Questions from your Pharmacist" is displayed on WWHAM page
    And The Headers are displayed on WWHAM page
      | Patient Details    |
      | Medical Conditions |
      | Medication         |
    And The questions are displayed on WWHAM page
      | What is your Date of Birth?                                                                                                        |
      | Please confirm that you are buying Levonelle® for your own use                                                                     |
      | Have you had unproteced sex within the last 72 hours?                                                                              |
      | Was your last period late, lighter, shorter than usual, unusual in any way or are you already pregnant?                            |
      | Are you taking any other medication including any herbal remedies?                                                                 |
      | Do you suffer from any form of small bowel disease such as Crohn's disease?                                                        |
      | Do you have any severe liver problems?                                                                                             |
      | Have you ever taken levonorgestrel previously?                                                                                     |
      | Have you ever had an allergy or other reaction to leveonorgestrel?                                                                 |
      | I confirm that I have read the information in this questionnaire and will follow the advice shown here and in the product leaflet. |
    When The user clicks Back button on WWHAM page
    Then The PDP of "Beechams All In One Liquid 240ml" opens

  Scenario: 31. Check the type10 WWHAM page is displayed as per design and can continue to checkout
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "beechams" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Beechams All In One Liquid 240ml"
    Then The PDP of "Beechams All In One Liquid 240ml" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    And The user selects "1" Day "1" Month "2000" Year for the birthday on WWHAM Page
    And The user ticks the checkbox of the second question on WWHAM Page
    And The user selects "No" from the third dropdown list on WWHAM Page
    And The user selects "No" from the fourth dropdown list on WWHAM Page
    And The user selects "No" from the sixth dropdown list on WWHAM Page
    And The user selects "No" from the seventh dropdown list on WWHAM Page
    And The user selects "No" from the eighth dropdown list on WWHAM Page
    And The user selects "No" from the nineth dropdown list on WWHAM Page
    And The user selects "No" from the tenth dropdown list on WWHAM Page
    And The user checks on the last checkbox on WWHAM Page
    And The user clicks Continue button on WWHAM Page
    When The user clicks Secure Checkout button on shopcart page
    Then The CheckOut SignIn page opens
    Then The user inputs "b2c.username" and "b2c.password" as logon ID and password on CheckOut SignIn page
    And The user clicks "Sign In" button on CheckOut SignIn page
    Then The "Delivery options" page opens

  Scenario: 32. Check the error message of type10 WWHAM page is displayed as per design - Case 1
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "beechams" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Beechams All In One Liquid 240ml"
    Then The PDP of "Beechams All In One Liquid 240ml" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    And The user clicks Continue button on WWHAM Page
    And The error messages are listed on WWHAM Page
      | Please select the day.                                                                                                        |
      | Please select the month.                                                                                                      |
      | Please select the year.                                                                                                       |
      | This field is mandatory                                                                                                       |
      | Please confirm that you are buying this item for your own use                                                                 |
      | Please confirm if you have had unprotected sex within the last 72 hours                                                       |
      | Please confirm whether your last period was late, lighter, shorter than usual, unusual in any way or are you already pregnant |
      | Please enter any other medication the intended user is currently taking                                                       |
      | Please select an answer to tell us if you suffer from any form of small bowel disease such as Crohn's Disease?                |
      | Please select an answer to tell us if you have any severe liver problems                                                      |
      | Please select an answer to tell us if you have ever taken levonorgestrel previuosly                                           |
      | Please select an answer to tell us if you have ever had an allergy or other reaction to levonorgestrel                        |
      | Please tick the box to confirm that you have read and understood the product information provided.                            |

  Scenario: 33. Check the error messages of type10 WWHAM page are displayed as per design - Case 2
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "beechams" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Beechams All In One Liquid 240ml"
    Then The PDP of "Beechams All In One Liquid 240ml" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    And The user selects "1" Day "1" Month "2000" Year for the birthday on WWHAM Page
    And The user ticks the checkbox of the second question on WWHAM Page
    And The user selects "Yes" from the third dropdown list on WWHAM Page
    And The user selects "Yes" from the fourth dropdown list on WWHAM Page
    And The user selects "Yes" from the sixth dropdown list on WWHAM Page
    And The user selects "Yes" from the seventh dropdown list on WWHAM Page
    And The user selects "Yes" from the eighth dropdown list on WWHAM Page
    And The user selects "Yes" from the nineth dropdown list on WWHAM Page
    And The user selects "Yes" from the tenth dropdown list on WWHAM Page
    And The user checks on the last checkbox on WWHAM Page
    And The user clicks Continue button on WWHAM Page
    And The error messages are listed on WWHAM Page
      | Please seek advice from a healthcare professional                           |
      | Please seek advice from a healthcare professional                           |
      | Please state if you are taking any other medication, if no please select no |
      | Please seek advice from a healthcare professional                           |
      | Please seek advice from a healthcare professional                           |
      | Please seek advice from a healthcare professional                           |
      | Please seek advice from a healthcare professional                           |

  Scenario: 34. Check the type12 WWHAM page is displayed as per design, and the Back button functions well
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "sominex" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Sominex 8 One a Night Tablets"
    Then The PDP of "Sominex 8 One a Night Tablets" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    And The question title "Questions from your Pharmacist" is displayed on WWHAM page
    And The Headers are displayed on WWHAM page
      | Patient Details    |
      | Medical Conditions |
      | Medication         |
    And The questions are displayed on WWHAM page
      | Is this item just for you?                                                                                                                                                                                                                                                                                                                                                  |
      | What is the date of birth of the youngest intended user of the product?                                                                                                                                                                                                                                                                                                     |
      | Is the intended user taking any other medication?                                                                                                                                                                                                                                                                                                                           |
      | Does the intended user suffer from any of the following medical conditions:                                                                                                                                                                                                                                                                                                 |
      | Hypertension(Blood Pressure)                                                                                                                                                                                                                                                                                                                                                |
      | Diabetes                                                                                                                                                                                                                                                                                                                                                                    |
      | An unstable heart condition                                                                                                                                                                                                                                                                                                                                                 |
      | Are you allergic or sensitive to Nicotine?                                                                                                                                                                                                                                                                                                                                  |
      | Are you aware of other options available to you such as Nicotine Replacement Therapy (e.g. gu, patches, inhalator, lozenges), the NHS Stop Smoking Service or the Lloydspharmacy Online Doctor where alternative treatments are available? If you would like to know more about these options then please visit your local LloydsPharmacy to discuss these options further. |
      | I confirm that I have read the information in this questionnaire and will follow the advice shown here and in the product leaflet.                                                                                                                                                                                                                                          |
    When The user clicks Back button on WWHAM page
    Then The PDP of "Sominex 8 One a Night Tablets" opens

  Scenario: 35. Check the type12 WWHAM page is displayed as per design and can continue to checkout
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "sominex" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Sominex 8 One a Night Tablets"
    Then The PDP of "Sominex 8 One a Night Tablets" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    When The user selects "Yes" from the first dropdown list on WWHAM Page
    And The user selects "8" Day "1" Month "1980" Year for the birthday of youngest user on WWHAM Page
    And The user selects "No" from the third dropdown list on WWHAM Page
    And The user selects "No" from the fifth dropdown list on WWHAM Page
    And The user selects "No" from the sixth dropdown list on WWHAM Page
    And The user selects "No" from the seventh dropdown list on WWHAM Page
    And The user selects "No" from the eighth dropdown list on WWHAM Page
    And The user selects "No" from the nineth dropdown list on WWHAM Page
    And The user checks on the last checkbox on WWHAM Page
    And The user clicks Continue button on WWHAM Page
    When The user clicks Secure Checkout button on shopcart page
    Then The CheckOut SignIn page opens
    Then The user inputs "b2c.username" and "b2c.password" as logon ID and password on CheckOut SignIn page
    And The user clicks "Sign In" button on CheckOut SignIn page
    Then The "Delivery options" page opens

  Scenario: 36. Check the error messages of type 12 WWHAM page are displayed as per design - Case 1
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "sominex" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Sominex 8 One a Night Tablets"
    Then The PDP of "Sominex 8 One a Night Tablets" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    And The user clicks Continue button on WWHAM Page
    Then The error messages are listed on WWHAM Page
      | Is the medicine just for you? Please select an answer from the drop down menu.                                                                |
      | Please select the day.                                                                                                                        |
      | Please select the month.                                                                                                                      |
      | Please select the year.                                                                                                                       |
      | This field is mandatory                                                                                                                       |
      | Is the intended user(s) taking any other medication, including vitamins and herbal remedies? Please select an answer from the drop down menu. |
      | This field is mandatory                                                                                                                       |
      | This field is mandatory                                                                                                                       |
      | This field is mandatory                                                                                                                       |
      | This field is mandatory                                                                                                                       |
      | This field is mandatory                                                                                                                       |
      | Please tick the box to confirm that you have read and understood the product information provided.                                            |

  Scenario: 37. Check the error messages of type 12 WWHAM page are displayed as per design - Case 2
    Given The user is on Lloyds Pharmacy home page
    When The user inputs "sominex" in the Search text box
    And The user clicks the Search button
    When The user clicks the image of prodcut "Sominex 8 One a Night Tablets"
    Then The PDP of "Sominex 8 One a Night Tablets" opens
    When The user clicks Add to basket on PMED page
    Then The WWHAM Page opens
    When The user selects "No: Self and others" from the first dropdown list on WWHAM Page
    And The user selects "Yes" from the third dropdown list on WWHAM Page
    And The user selects "Yes" from the fifth dropdown list on WWHAM Page
    And The user selects "Yes" from the sixth dropdown list on WWHAM Page
    And The user selects "Yes" from the seventh dropdown list on WWHAM Page
    And The user selects "Yes" from the eighth dropdown list on WWHAM Page
    And The user selects "Yes" from the nineth dropdown list on WWHAM Page
    And The user clicks Continue button on WWHAM Page
    Then The error messages are listed on WWHAM Page
      | Please select the day.                                                                             |
      | Please select the month.                                                                           |
      | Please select the year.                                                                            |
      | This field is mandatory                                                                            |
      | This field is mandatory                                                                            |
      | Please tick the box to confirm that you have read and understood the product information provided. |
