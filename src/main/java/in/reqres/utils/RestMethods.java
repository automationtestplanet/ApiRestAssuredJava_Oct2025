package in.reqres.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class RestMethods {

    public ValidatableResponse get(RequestSpecification rs, String endPoint, int expectedStatusCode) {
        return RestAssured.given().spec(rs)
                .when().get(endPoint).then().statusCode(expectedStatusCode);
    }

    public ValidatableResponse getWithQueryParams(RequestSpecification rs, String endPoint, Map<String, Object> queryParms, int expectedStatusCode) {
        return RestAssured.given().spec(rs).queryParams(queryParms)
                .when().get(endPoint).then().statusCode(expectedStatusCode);
    }

    public ValidatableResponse getWithQueryParamsAndHeaders(RequestSpecification rs, String endPoint, Map<String, Object> queryParms, Map<String, Object> headers, int expectedStatusCode) {
        return RestAssured.given().spec(rs).queryParams(queryParms).headers(headers)
                .when().get(endPoint).then().statusCode(expectedStatusCode);
    }

    public ValidatableResponse post(RequestSpecification rs, String endPoint, Map<String, Object> headers, Object requestBody, int expectedStatusCode) {
        return RestAssured.given().spec(rs)
                .headers(headers)
                .body(requestBody)
                .when().post(endPoint).then().statusCode(expectedStatusCode);
    }

    public ValidatableResponse put(RequestSpecification rs, String endPoint, Map<String, Object> headers, Object requestBody, int expectedStatusCode) {
        return RestAssured.given().spec(rs)
                .headers(headers)
                .body(requestBody)
                .when().put(endPoint).then().statusCode(expectedStatusCode);
    }

    public ValidatableResponse putWithPathParam(RequestSpecification rs, String endPoint, Map<String, Object> headers, Object requestBody, Map<String, Object> pathParams, int expectedStatusCode) {
        String pathParamName = "";
        for (String eachKey : pathParams.keySet()) {
            pathParamName = eachKey;
        }
        return RestAssured.given().spec(rs)
                .pathParams(pathParams)
                .headers(headers)
                .body(requestBody)
                .when().put(endPoint + "/{" + pathParamName + "}").then().statusCode(expectedStatusCode);
    }

    public ValidatableResponse patch(RequestSpecification rs, String endPoint, Map<String, Object> headers, Object requestBody, Map<String, Object> pathParams, int expectedStatusCode) {
        return RestAssured.given().spec(rs)
                .headers(headers)
                .pathParams(pathParams)
                .body(requestBody)
                .when().patch(endPoint).then().statusCode(expectedStatusCode);
    }

    public ValidatableResponse patchWithPathParam(RequestSpecification rs, String endPoint, Map<String, Object> headers, Object requestBody, Map<String, Object> pathParams, int expectedStatusCode) {
        String pathParamName = "";
        for (String eachKey : pathParams.keySet()) {
            pathParamName = eachKey;
        }
        return RestAssured.given().spec(rs)
                .headers(headers)
                .pathParams(pathParams)
                .body(requestBody)
                .when().patch(endPoint + "/{" + pathParamName + "}").then().statusCode(expectedStatusCode);
    }

    public ValidatableResponse deleteWithPathParam(RequestSpecification rs, String endPoint, Map<String, Object> pathParams, int expectedStatusCode) {
        String pathParamName = "";
        for (String eachKey : pathParams.keySet()) {
            pathParamName = eachKey;
        }
        return RestAssured.given().spec(rs)
                .pathParams(pathParams)
                .when().delete(endPoint + "/{" + pathParamName + "}").then().statusCode(expectedStatusCode);
    }


}
