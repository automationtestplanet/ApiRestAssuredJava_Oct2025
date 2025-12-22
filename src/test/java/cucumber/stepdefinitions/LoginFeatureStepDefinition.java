package cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;

public class LoginFeatureStepDefinition {

    @Given("the application to launch")
    public void the_application_to_launch() {
        System.out.println("Application is launched");
    }


    @Given("the user is on the Login Page")
    public void the_user_is_on_the_Login_Page() {
        System.out.printf("User is on Login Page");
    }

    @When("the user enter UserName {string} and Password {string}")
    public void the_user_enter_UserName_TestUser1_and_Password_TestPasword1(String userName, String password) {
        System.out.println("User enters Username: " + userName + " and Password: " + password);
    }

    @And("the user click the Login button")
    public void the_user_click_the_Login_button() {
        System.out.println("User clicks the Login button");
    }

    @Then("the user should login to the Application")
    public void the_user_should_login_to_the_Application() {
        System.out.println("User successfully logged in to the application");
    }

    @And("the user name should be displayed on the Home Page")
    public void the_user_name_should_be_displayed_on_the_Home_Page() {
        System.out.println("User name is displayed on the Home Page");
    }

    @But("the user avtar should not contain actual image")
    public void the_user_atar_should_not_contain_actual_image() {
        System.out.println("User avatar does not contain actual image");
    }
}
