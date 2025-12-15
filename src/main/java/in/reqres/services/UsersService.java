package in.reqres.services;

import in.reqres.response.models.CreateUserResponse;
import in.reqres.response.models.ListOfUsers;
import in.reqres.utils.RestMethods;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class UsersService extends RestMethods {

    private final String USER_SERVICE = "/api/users";

    public ListOfUsers getListOfUsersByPageNo(RequestSpecification rs, Map<String, Object> queryParam, int expectedStatusCode) {
        return getWithQueryParams(rs, USER_SERVICE, queryParam, expectedStatusCode)
                .extract().as(ListOfUsers.class);
    }

    public CreateUserResponse createNewUserDetails(RequestSpecification rs, Object requestBody, int expectedStatusCode) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("Accept", "*/*");
        headers.put("Content-Type", "application/json");
        return post(rs, USER_SERVICE, headers, requestBody, expectedStatusCode)
                .extract().as(CreateUserResponse.class);
    }

    public void updateExistingUserDetailsByUserId(RequestSpecification rs, Object requestBody, Map<String, Object> pathParam, int expectedStatusCode) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("Accept", "*/*");
        headers.put("Content-Type", "application/json");
        putWithPathParam(rs, USER_SERVICE, headers, requestBody, pathParam, expectedStatusCode);
    }

    public void updateExistingUserPartialDetailsUserId(RequestSpecification rs, Object requestBody, Map<String, Object> pathParam, int expectedStatusCode) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("Accept", "*/*");
        headers.put("Content-Type", "application/json");
        patchWithPathParam(rs, USER_SERVICE, headers, requestBody, pathParam, expectedStatusCode);
    }

    public void deleteUserDetailsByUserId(RequestSpecification rs, Map<String, Object> pathParam, int expectedStatusCode) {
        deleteWithPathParam(rs, USER_SERVICE, pathParam, expectedStatusCode);
    }
}
