![Salmon](/salmonlogo.png)
================================
Salmon Test Automation Framework
================================
This project is a template for Salmon's test automation framework, which provides structured and standard way of 
creating automated test scripts for GUI and API level tests across salmon projects  

This is a reusable automation framework that blends together Selenium WebDriver and Cucumber JVM (incorporating Gherkin and the BDD 'Given, When Then' testing construct) 

The framework incorporates design principle of BDD (Behaviour driven development) which promotes
 writing acceptance tests by describing behaviour of application under test in simple english language from
 the perspective of its stakeholders. 
 Having test written in Natural language helps the Project Team 
 (Product Owners, Business Analysts, Development and QA team) to understand and track the requirements
 
Supports Custom Page Object model which represents the screens of AUT as a series of objects and 
encapsulates the fields represented by a page which ultimately avoids duplication and improves code maintainability and readability.
    


Tools & libraries
=================
The test automation framework is comprised of following tools and libraries  

*Cucumber-JVM:- BDD Framework  
*Custom Page Object Pattern and utility functions  
*Selenium WebDriver : - Browser automation framework  
*JAVA: - Programming language  
*TestNg: - TestNg Java testing framework  
*Maven: - Build tool  
*Jenkins: - Continuous Integration  
*Lombok: - Java utility api  
*PicoContainer: - Dependency Injection    
*Git: - Version Control  
*Github (optional): - Git repository hosted server (Descoped for this project due to security)  
*Intellij Or Eclipse: - Integrated Development Environment  
*AssertJ: - Test assertions (Hamcrest is descoped for this project)  
*Loggers: - Simple Logging Facade for Java  
*Joda-Time: - Java Date time Api  
*SonarQube (optional): - Code Quality and Code Coverage (FindBugs is added as Sonar is not setup for this project)  
*DbUtils Mysql (optional): - Java Database utility api  
*Rest-Assured (optional): - Restful Api framework     
*PhantomJsDriver, GhostDriver: - Full web stack No browser required supporting headless testing



Contact information
===================
Overall Test & Test management support
------------------------------------------
Nick Gee
--------
Head of Testing  
ngee@salmon.com  


Test Automation framework support
------------------------------------------
Gaurav Karvir
--------------
Lead Test Automation Architect  
gkarvir@salmon.com  



Machine Configuration
====================
Configure Ubuntu / Windows and setup: -   
*Java 7  
*Git  
*Maven  


Get the latest Source Code
===========================
Open Terminal or command line
cd to the desired folder where the test automation source code needs to be checkout

Run command
git clone https://pluto.salmon.ltd.uk:7057/git/celesiocucumbertesting.git

This will download the latest template source code


IDE Configuration
==================
Intellij plugins  
----------------
Configure and Install Following Plugins  
File >> Setting >> Plugins >> Browser Repositories>

*Cucumber for Java
*Gherkin
*lombok
*Git Integration
*GitHub
*Maven Integration


Eclipse plugins  
----------------
Configure and Install Following Plugins  
Help>>Install new software
*Cucumber for Java
http://cucumber.github.com/cucumber-eclipse/update-site  

*lombok  
Download lombok.jar from http://projectlombok.org/download.html
Copy the Jar to eclipse installation directory
right click the Jar>Open with Open JDK or Oracle JDK Java 7 Runtime
Follow the Installation Steps    

*TestNg  

*Git Integration  

*m2e-findbugs  

Plugin configuration for Cucumber Feature
Open Run Configurations
Select Cucumber Feature and create one new configuration
Project: salmonAutomationFramework
Feature: src/salmonAutomationFramework/src/test/resources/features
Glue:  com.salmon.test.step_definitions
Reports:  monochrome, pretty

File >> Setting >>  
Search for Annotation Processing  
(Java Compiler ... Annotation Processing>> Enable the check box


Import Project into Intellij
----------------------------
File>Import Project>
Browse to SalmonAutomationFramework



Import Project into Eclipse
--------------------------
File>Import>Maven>Existing Maven Projects>Next>
Browse to SalmonAutomationFramework
Ensure pom.xml is found
Finish

open terminal
cd to test root directory
run "mvn clean eclipse:eclipse" 



Framework Setup steps
============================
URL,  Browser Configuration, Test Suites to Run
Open "pom.xml" 
Scroll to Profile section : - Choose desired profile e.g "dev" for running locally

            <!-- Development environment @ my local machine -->
                    <profile>
                        <id>dev</id>
                        <activation>
                            <activeByDefault>true</activeByDefault>
                        </activation>
                        <properties>
                            <!-- Application under test-->
                            <site.url>http://www.lloydspharmacy.com/</site.url>
                            <!-- Service under test-->
                            <api.url>http://www.dulux.com.sg</api.url>
                            <!-- AUT has default desired port-->
                            <site.port></site.port>
                            <!-- AUT has default base path-->
                            <site.basepath></site.basepath>
                            <!--platform to run e.g linux64, mac32, win32, win64-->
                            <platform>linux64</platform>
                            <!--Desired browser to run e.g firefox,chrome,iexplore phantomjs-->
                            <browser>firefox</browser>
                            <!--To Run parallel Test suite specify the type of Run Files which can be run in parallel -->
                            <testToRun>**/*ATSuite.class</testToRun>
            
                            <!--Database Params -->
                            <jdbcUrl>jdbc:mysql://localhost:3306/sonar</jdbcUrl>
                            <jdbcDriver>com.mysql.jdbc.Driver</jdbcDriver>
                            <jdbcUser>sonar</jdbcUser>
                            <jdbcPwd>sonar</jdbcPwd>
            
                        </properties>


Compile Build or Run Tests
==========================

Command Line

cd to root ot salmonAutomationFramework project directory

To clean and compile the build
-----------------------------
mvn clean install -DskipTests

To run all tests parallel
------------------------
mvn clean install  

OR

mvn clean install -P dev  

*Note -P dev is default profile hence doesn't need to be specified for every run 

To run a single test with tags
------------------------

mvn clean install -Dcucumber.options="--tags @gui --tags ~@api" -P single

** Note "~" before tag means this specific tag will not run


Jenkins
======

mvn clean install  -P jenkins

** Create 3 string parameters in jenkins 
with the below conventions which will be passed on as an argument to the above maven command  

Key: cucumber.options Default Value: --tags @gui, @api     
Key: driverhost  Default Value: 192.168.216.76  
Key: driverport  Default Value: 4444  


Report
======

Local report
-------------
A report will be generated at /target/cucumber-report/index.html

Jenkins report
--------------
The report will be available as part of configured Jenkins test build  
**Cucumber plugin for Jenkins needs to be installed





Getting Started
===========================


Feature Files
-------------------------------------------------------------------
These files contains the acceptance criteria which are written in Gherkin Language and contains various scenarios.  
The feature files are tagged with "@tagname" to group common feature files 

File Extension:  *.feature    
Location: "/home/dev/src/salmonAutomationFramework/src/test/resources/features"      
Directory:  Separate directories for GUI and API tests, Group common features files in a 
single directory    
File Conventions:Meaning full name "WebRegister.feature"
Example:   
@gui
Feature: REGISTER:- As a new customer of Lloydspharmacy I would like to register 
Scenario:Perform a New registration for a customer
    Given I navigate to the Lloydspharmacy "HOME" page


Page Objects
-------------------------------------------------------------------
PageObjects are used to store the WebElements for a Web Page.
A good practice is to have a separate class for every single WebPage.
To avoid duplication for multiple pages which have common web page elements a Parent class can be created 
and the child class can then inherit.  
Every Page  class extends "PageObject.class" to make use of the WebDriver Object and utility functions.  
In case of Parent and Child Class, Parent class extends PageObject class and child class extends Parent class      
   
Location: /home/dev/src/salmonAutomationFramework/src/test/java/com/salmon/test/pageobjects
Directory structure: Group common Page Objects classes in a single directory e.g Login Functionality Classes in Login Directory      
File Conventions:Every Class file ends with Page.class (Homepage.class)  

Example:   

public class HomeSamplePage extends PageObject {

    private By headerSignInLink = By.cssSelector("#headerSignInLink a");

    public void clickSignInLink() {
        waitForExpectedElement(headerSignInLink).click();
    }
}


Step Definitions
--------------------------------------------------------------------
Every steps defined in Feature file needs to be implemented in Step Definitions Class

Location: /home/dev/src/salmonAutomationFramework/src/test/java/com/salmon/test/step_definitions 
Directory structure: Separate directories for GUI and API tests, Group common step definition files in a 
                     single directory    
File Conventions:Every Class file ends with Steps.class (LoginSteps.class)  

Example:  

public class HomePageSteps {

   private HomeSamplePage homePage =  new HomeSamplePage();

    @And("^i click on Sign In on the Home Page$")
    public void i_click_on_Sign_In_on__the_Home_Page() throws Throwable {
        homePage.clickSignInLink();
    }
}

Run Test Suite
--------------------------------------------------------------------
Test Suites are used to run a group of Tests which are tagged and represented in form of Feature files & Scenarios

Location: /home/dev/src/salmonAutomationFramework/src/test/java/com/salmon/test
File Conventions:Every Class file ends with Suite.class (RunWebATSuite.class)  


    @CucumberOptions(features = "target/test-classes", tags = {"@gui"}, 
         monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report/runwebat",
        "json:target/cucumber-report/runwebat/cucumber.json"
        })
        
        public class RunWebATSuite extends AbstractTestNGCucumberTests {
        }

Where: -  
features: represent the location of feature files from the compiled build  
tags:  multiple tags can be specified by comma separated denotation, if a specific tag needs to be excluded then this 
        can be specified by "~" . e.g "~@api" feature files tagged with "~api" will not be run as a part of Test Suite.  
format: html and json reports are created. if a TesSuite is renamed then change the reporting directory name for both reports   

Other ways to run the tests or Test Suite
---------------------------------------------
*command line using Maven:-  mvn clean install -P dev  
*IDE Plugins: - Eclipse or Intellij via TestNg plugin or Maven plugin or Cucumber-Java plugin  
*IDE TestNg Suite xml: - file located at "src/test/resources" TestNGRunTestSuite.xml (Right click and run as TestNg)