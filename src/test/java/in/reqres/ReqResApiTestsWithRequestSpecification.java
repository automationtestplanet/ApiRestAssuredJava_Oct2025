package in.reqres;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReqResApiTestsWithRequestSpecification {

    RequestSpecification rs;

    @BeforeTest
    public void requestConfig(){
        rs = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in")
                .addHeader("x-api-key","reqres_c4dd8dedafed4c89ba31edf4eb997630")
                .build();
    }


    @Test
    public void getListOfUsersTest() {
        Response response = RestAssured.given().spec(rs).queryParam("page", 2)
                .when().get("/api/users").then().statusCode(200).extract().response();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Validate Expected Status code");
        Assert.assertEquals(Integer.parseInt(response.jsonPath().get("page").toString()), 2, "Validate PageNum");
        Assert.assertEquals( Integer.parseInt(response.jsonPath().get("data[0].id").toString()),7, "Validate First Record Data Id");

        List<Map<String,Object>> dataList= response.jsonPath().getList("data");
        System.out.println(dataList);

        dataList.forEach(eachMap-> {
            Assert.assertNotNull(eachMap.get("id"),"Each Data id should not be null");
        });
    }

    @Test
    public void getListOfUsersTest2() {
        Map<String,Integer> queryParams = new HashMap<>();
        queryParams.put("page",2);


        Response response = RestAssured.given(rs).queryParams(queryParams)
                .when().get("/api/users").then().statusCode(200).extract().response();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Validate Expected Status code");
        Assert.assertEquals(Integer.parseInt(response.jsonPath().get("page").toString()), 2, "Validate PageNum");
        Assert.assertEquals( Integer.parseInt(response.jsonPath().get("data[0].id").toString()),7, "Validate First Record Data Id");

        List<Map<String,Object>> dataList= response.jsonPath().getList("data");
        System.out.println(dataList);

        dataList.forEach(eachMap-> {
            Assert.assertNotNull(eachMap.get("id"),"Each Data id should not be null");
        });
    }


    @Test
    public void createUserTest() {
        String requestBOdy = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";



        Response response = RestAssured.given().header("x-api-key", "reqres_c4dd8dedafed4c89ba31edf4eb997630")
                .header("Accept","*/*")
                .header("Content-Type","application/json")
//                .header(ContentType.JSON)
                .body(requestBOdy)
                .when().post("https://reqres.in/api/users").then().statusCode(201).extract().response();
        response.prettyPrint();
        Assert.assertEquals(response.jsonPath().get("name"), "morpheus", "Validate Name");
        Assert.assertEquals(response.jsonPath().get("job"), "leader", "Validate Job");
        Assert.assertNotNull(response.jsonPath().get("id"),"Id should not be null");
    }

    @Test
    public void updateUserTest() {
        String requestBOdy = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"Automation Tester\"\n" +
                "}";

        Response response = RestAssured.given()
                .pathParam("id",2)
                .header("x-api-key", "reqres_c4dd8dedafed4c89ba31edf4eb997630")
                .header("Accept","*/*")
                .header("Content-Type","application/json")
                .body(requestBOdy)
                .when().put("https://reqres.in/api/users/{id}").then().statusCode(200).extract().response();
        response.prettyPrint();
        Assert.assertEquals(response.jsonPath().get("name"), "morpheus", "Validate Name");
        Assert.assertEquals(response.jsonPath().get("job"), "Automation Tester", "Validate Job");
    }

    @Test
    public void partialUpdateUserTest() {
        String requestBOdy = "{\n" +
                "    \"job\": \"QA Engineer\"\n" +
                "}";

        Response response = RestAssured.given()
                .pathParam("id",2)
                .header("x-api-key", "reqres_c4dd8dedafed4c89ba31edf4eb997630")
                .header("Accept","*/*")
                .header("Content-Type","application/json")
                .body(requestBOdy)
                .when().patch("https://reqres.in/api/users/{id}").then().statusCode(200).extract().response();

        response.prettyPrint();

        Assert.assertEquals(response.jsonPath().get("job"), "QA Engineer", "Validate Job");
    }

    @Test
    public void deleteUserTest() {
        Response response = RestAssured.given()
                .pathParam("id",2)
                .header("x-api-key", "reqres_c4dd8dedafed4c89ba31edf4eb997630")
                .when().delete("https://reqres.in/api/users/{id}").then().statusCode(204).extract().response();

        Assert.assertEquals(response.asString(),"","Response must be empty");
    }
}

