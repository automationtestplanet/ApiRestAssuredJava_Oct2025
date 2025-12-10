package in.reqres.services;

import in.reqres.utils.RestMethods;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class RegisterService extends RestMethods {
    private final String REGISTER_SERVICE = "/api/register";

    public void createUser(RequestSpecification rs, Object requestBody, int expectedStatusCode){
        Map<String, Object> headers = new HashMap<>();
        headers.put("Accept", "*/*");
        headers.put("Content-Type", "application/json");
        post(rs, REGISTER_SERVICE, headers, requestBody,expectedStatusCode);
    }
}
