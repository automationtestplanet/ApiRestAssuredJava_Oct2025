@UserServiceTestCases
Feature: User Service Test Cases

  @GetListOfUsers
  Scenario: GET list of Users test
    Given the UserService API endpoint
    When the user performs GET  with query parameter "page" and value "2"
    Then the list of users is returned wth page number 2
    And the data should contain id greater than 0

  @CreateUser
  Scenario: POST the User test
    Given the UserService API endpoint
    When the user performs the POST request with request body "/src/test/resources/CreateUser.json"
    Then the user is created successfully

  @UpdateUser
  Scenario: UPDATE the User test
    Given the UserService API endpoint
    When the user performs the PUT request with user id "2", name "Raj" and job "Automation Tester" through request body "/src/test/resources/UpdateUserWithParameterData.json"
    Then the user name "Raj" and job "Automation Tester" updated successfully

  @PartialUpdateUser
  Scenario: PATCH the User test
    Given the UserService API endpoint
    When the user performs the PUT request with user id "2" and job "QA Engineer" through request body "/src/test/resources/PartialUpdateUserWithParameterData.json"
    Then the user job "QA Engineer" is patched successfully

  @DeleteUser
  Scenario: DELETE the User test
    Given the UserService API endpoint
    When the user performs the DELETE request with user id "2"
    Then the user is deleted successfully