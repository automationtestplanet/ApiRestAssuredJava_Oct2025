Feature: SignUp feature to test the SignUp functionality of the Application


  @SignUpWithValidDetails @SmokeTest @SanityTest @RegressionTest
  Scenario: SignUp with valid details
    Given the user is on the SignUp Page
    When the user enter FirstName "John", LastName "Doe", Email "user1@gmail.com"
    And the user click the SignUp button
    Then the user should sign up to the Application