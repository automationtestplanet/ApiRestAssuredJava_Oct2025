Feature: Login Feature to test the Login functionality of the Application

  Background: this background launches the application
    Given the application to launch

  @LoginWIthValidCredentials @SmokeTest @SanityTest @RegressionTest
  Scenario: Login with valid credentials
    Given the user is on the Login Page
    When the user enter UserName "TestUser1" and Password "TestPasword1"
    And the user click the Login button
    Then the user should login to the Application
    And the user name should be displayed on the Home Page
    But the user avtar should not contain actual image

  @LoginWIthInValidCredentials @SanityTest @RegressionTest
  Scenario: Login with invalid credentials
    Given the user is on the Login Page
    When the user enter UserName "InvalidTestUser1" and Password "InvalidTestPasword1"
    And the user click the Login button
    Then the user should not login to the Application

  @LoginWIthMultpleCredentials @RegressionTest
  Scenario Outline: Login with valid credentials for different users
    Given the user is on the Login Page
    When the user enter UserName "<UserName>" and Password "<Password>"
    And the user click the Login button
    Then the user should login to the Application
    And the user name should be displayed on the Home Page
    But the user avtar should not contain actual image

    Examples:
      | UserName  | Password     |
      | TestUser1 | TestPasword1 |
      | TestUser2 | TestPasword2 |
      | TestUser3 | TestPasword3 |
      | TestUser4 | TestPasword4 |


  @LoginWIthDataTable @SanityTest @RegressionTest
  Scenario: Login with invalid credentials at step level using Data Table
    Given the user is on the Login Page
    When the user enter UserName and Password
      | UserName         | Password            |
      | InvalidTestUser1 | InvalidTestPasword1 |
      | InvalidTestUser2 | InvalidTestPasword2 |
      | InvalidTestUser3 | InvalidTestPasword3 |
      | InvalidTestUser4 | InvalidTestPasword4 |
    And the user click the Login button
    Then the user should not login to the Application