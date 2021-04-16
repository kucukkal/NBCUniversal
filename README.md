## Demo Challenge

#### Instructions
1. Complete the project setup as listed below
2. Complete the Excerise
3. Email a synopsis of your work and the link to your git repo containing the completed exercise to: sqedemonstrationchallenge@nbcuni.com


#### Expectations
We will be evaluating
1. Naming conventions
2. Code readability
3. Code encapsulation
4. Code structure and organization
5. Quality of test cases
6. Variety  of testing types (examples: boundary, happy path, negative, etc) 


#### Technologies
1. Java
2. Selenium
3. TestNG
4. Any other technologies you see fit.
5. Please do not use a BDD framework.

#### Project Setup
1. Clone this project to your git account in a public repo
2. Setup the project in your IDE
3. Open the index.html file from src/test/resource/files in a browser
4. Copy the url from the browser and update the url value in src/test/resource/config.properties to be the copied url.
5. In src/test/resources update the config.properties file platform for your OS.
6. From command line run mvn clean install -U -DskipTests
7. Make sure you can run the DemoTest and chrome launches.  You may need to update the chromedriver in /src/test/resources/chromedriver/ to the version that works with your browser
   https://chromedriver.chromium.org/


#### Exercise
1. Use the site at the index.html
2. There are helper locators provided for you in the src/test/resource/files/locators.txt file.
3. In the Test Cases section below:
  - List all of the test cases you think are necessary to test the sample page
  - Note any defects or issues observed
4. Code up a few examples of:
  - At least one happy path case placing an order
  - At least one error case
5. When complete please check your code into your public git repo

#### Test Cases

Addendum:
1) Build the project using Maven mvn clean install -U -DskipTests
2) You can run the test using the testng.xml file in an IDE
3) ExtentReport is used for reporting, scrrenshots are saved in "screenShot" folder and Extent html report is saved in "results" folder.
4) Two test groups; Smoke and Defects, have been created. The positive tests (happy paths) are added to "Smoke" group and all the ones that find one issue/problem with the software are added to "Defect" group.
The test cases in "Smoke" group:
1) SuccessfulNoTopingOrder - Checks if a small pizza without any topping can be ordered successfully
2)  SuccessfulOneTopingOrder - Checks if a small pizza with one topping can be ordered successfully
3)   SuccessfulTwoTopingOrder - Checks if a large pizza with two toppings can be ordered successfully
4) SuccessfulMultipePizzaOrder -  Checks if three large pizza with two toppings can be ordered successfully
5) MissingCustomerInformation - Checks if the error messages are displayed when necessary fields are missing (name and phone number) and also checks if the order can be completed after closing the popup and entering values to the missing fields 
The defects/issues as test cases in "Defect" group:
1) ChoosingBothPaymentOptions- Chooses both payment option and checks if  a large pizza with double toppings  can be given successfully
2) MissingPaymentOptions-Does not choose any payment information and checks if  a large pizza with double toppings  can be given successfully  
3) OrderingNoPizza - enters "0" for the Quantity and check if a  large pizza with double toppings  can be given successfully
4) CheckingValuesAfterReset: The values are populated first checks if the default values are restored and all the field are cleared following clicking the  Reset button
5) CheckingTheNameofCustomer- checks if a large pizza with double topping can be ordered successfully when the customer name contains  special characters
6) CheckingThePhoneNumberOfcustomer -checks if a successful order can be given with a phone number containing special characters and/or letters or missing digits or having extra digits
7) CheckingTheToppingForNoToppingPizza - choose one topping for a no-topping pizza and checks if a successful order can be given
8)CheckingTheMissingToppingForOneToppingPizza -  does not choose any topping for an one topping pizza and checks if a successful order can be given
9)  CheckingTheMissingToppingsForTwoToppingPizza - does not choose any topping for an one topping pizza and checks if a successful order can be given

Note: All the test in "Defect" group are designed to pass to show the expected result (unsuccessful order) but they both fail since the software is designed as it is.


