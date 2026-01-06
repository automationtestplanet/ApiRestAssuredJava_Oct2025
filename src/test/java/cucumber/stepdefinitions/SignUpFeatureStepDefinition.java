package cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignUpFeatureStepDefinition {

    @Given("the user is on the SignUp Page")
    public void the_user_is_on_the_sign_up_page() {
        System.out.println("the user is on the SignUp Page");
    }

    @When("the user enter FirstName {string}, LastName {string}, Email {string}")
    public void the_user_enter_first_name_last_name_email(String firstName, String lastName, String email) {
        System.out.println("the user enter FirstName " + firstName + ", LastName " + lastName + ", Email " + email);
    }

    @When("the user click the SignUp button")
    public void the_user_click_the_sign_up_button() {
        System.out.println("the user click the SignUp button");
    }

    @Then("the user should sign up to the Application")
    public void the_user_should_sign_up_to_the_application() {
        System.out.println("the user should sign up to the Application");
    }
}
