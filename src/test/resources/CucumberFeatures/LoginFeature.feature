Feature: Login Feature to test the Login functionality of the Application

  Background: this background launches the application
    Given the application to launch

  Scenario: Login with valid credentials
    Given the user is on the Login Page
    When the user enter UserName "TestUser1" and Password "TestPasword1"
    And the user click the Login button
    Then the user should login to the Application
    And the user name should be displayed on the Home Page
    But the user avtar should not contain actual image


  Scenario Outline: Login with valid credentials for different users
    Given the user is on the Login Page
    When the user enter UserName "<UserName>" and Password "<Password>"
    And the user click the Login button
    Then the user should login to the Application
    And the user name should be displayed on the Home Page
    But the user avtar should not contain actual image

    Examples:
      | UserName  | Password 	   |
      | TestUser1 | TestPasword1 |
      | TestUser2 | TestPasword2 |
      | TestUser3 | TestPasword3 |
      | TestUser4 | TestPasword4 |