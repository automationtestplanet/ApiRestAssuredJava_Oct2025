package cucumber.stepdefinitions;

import in.reqres.request.models.CreateUser;
import in.reqres.response.models.CreateUserResponse;
import in.reqres.response.models.Data;
import in.reqres.response.models.ListOfUsers;
import in.reqres.response.models.UpdateUserResponse;
import in.reqres.services.UsersService;
import in.reqres.utils.ReadDataFromExternalFiles;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class UserServiceStepDefinition {

    private UsersService userService;
    private static final String BASE_URI = "https://reqres.in";
    private static final String API_KEY = "reqres_c4dd8dedafed4c89ba31edf4eb997630";
    private RequestSpecification rs;
    ReadDataFromExternalFiles readData = new ReadDataFromExternalFiles();
    private ListOfUsers listOfUsers;
    private CreateUser createUserRequest;
    private CreateUserResponse createUserResponse;
    private UpdateUserResponse updateUserResponse;
    private String deleteUserResponse;

    @Given("the UserService API endpoint")
    public void the_userService_API_endpoint() {
        userService = new UsersService();
        rs = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .addHeader("x-api-key", API_KEY)
                .build();
    }

    @When("the user performs GET  with query parameter {string} and value {string}")
    public void the_user_performs_get_with_query_parameter_and_value(String queryParamName, String queryParamValue) {
        Map<String, Object> queryParms = Map.of(queryParamName, queryParamValue);
        listOfUsers = userService.getListOfUsersByPageNo(rs, queryParms, 200);

    }

    @Then("the list of users is returned wth page number {int}")
    public void the_list_of_users_is_returned_wth_page_number(Integer pageNumber) {
        Assert.assertEquals(listOfUsers.getPage(), pageNumber, "Validate PageNum");
    }

    @Then("the data should contain id greater than {int}")
    public void the_data_should_contain_id_greater_than(Integer expectedId) {
        List<Data> dataList = listOfUsers.getData();
        for (Data eachData : dataList) {
            Assert.assertTrue(eachData.getId() > 0, "Each Data id should be a positive integer");
        }
    }

    @When("the user performs the POST request with request body {string}")
    public void the_user_performs_the_post_request_with_request_body(String requestBodyPath) {
        createUserRequest = readData.readDataFromJsonFile(System.getProperty("user.dir") + requestBodyPath);
        createUserResponse = userService.createNewUserDetails(rs, createUserRequest, 201);
    }

    @Then("the user is created successfully")
    public void the_user_is_created_successfully() {
        Assert.assertEquals(createUserResponse.getName(), createUserRequest.getName(), "Validate Name");
        Assert.assertEquals(createUserResponse.getJob(), createUserRequest.getJob(), "Validate Job");
        Assert.assertNotNull(createUserResponse.getId(), "Id should not be null");
    }

    @When("the user performs the PUT request with user id {string}, name {string} and job {string} through request body {string}")
    public void the_user_performs_the_put_request_with_user_id_name_and_job_through_request_body(String id, String name, String job, String requestBodyPath) {
        String updateUserRequestBody = readData.readDataFromJsonFileAsString(System.getProperty("user.dir") + requestBodyPath);
        if (updateUserRequestBody == null) {
            Assert.fail("Failed to read CreateUserParameterData.json as string");
            return;
        }
        String requestBody = String.format(updateUserRequestBody, name, job);
        Map<String, Object> pathParms = Map.of("id", id);
        updateUserResponse = userService.updateExistingUserDetailsByUserId(rs, requestBody, pathParms, 200);
    }

    @Then("the user name {string} and job {string} updated successfully")
    public void the_user_name_and_job_updated_successfully(String name, String job) {
        Assert.assertEquals(updateUserResponse.getName(), name, "Validate Name");
        Assert.assertEquals(updateUserResponse.getJob(), job, "Validate Job");
    }

    @When("the user performs the PUT request with user id {string} and job {string} through request body {string}")
    public void the_user_performs_the_put_request_with_user_id_and_job_through_request_body(String id, String job, String requestBodyPath) {
        String updateUserRequestBody = readData.readDataFromJsonFileAsString(System.getProperty("user.dir") + requestBodyPath);
        if (updateUserRequestBody == null) {
            Assert.fail("Failed to read CreateUserParameterData.json as string");
            return;
        }
        String requestBody = String.format(updateUserRequestBody, job);
        Map<String, Object> pathParms = Map.of("id", id);
        updateUserResponse = userService.updateExistingUserPartialDetailsUserId(rs, requestBody, pathParms, 200);
    }

    @Then("the user job {string} is patched successfully")
    public void the_user_job_is_patched_successfully(String job) {
        Assert.assertEquals(updateUserResponse.getJob(), job, "Validate Job");
    }

    @When("the user performs the DELETE request with user id {string}")
    public void the_user_performs_the_delete_request_with_user_id(String id) {
        Map<String, Object> pathParms = Map.of("id", id);
        deleteUserResponse = userService.deleteUserDetailsByUserId(rs, pathParms, 204);

    }

    @Then("the user is deleted successfully")
    public void the_user_is_deleted_successfully() {
        Assert.assertEquals(deleteUserResponse, "", "Response must be empty");
    }
}
