package servicetest;

import in.reqres.response.models.Data;
import in.reqres.response.models.ListOfUsers;
import in.reqres.services.UsersService;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class UserServiceTest {

    private static final String BASE_URI = "https://reqres.in";
    private static final String API_KEY = "reqres_c4dd8dedafed4c89ba31edf4eb997630";

    private final UsersService userService = new UsersService();
    private RequestSpecification rs;

    @BeforeClass
    public void requestConfig(){
        rs = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .addHeader("x-api-key", API_KEY)
                .build();
    }

    @Test
    public void getListOfUsersByPageNumberTest() {
        Map<String, Object> queryParms = Map.of("page", 2);
        var listOfUsers = userService.getListOfUsersByPageNo(rs, queryParms, 200);
        Assert.assertEquals(listOfUsers.getPage(), 2, "Validate PageNum");
        Assert.assertEquals(listOfUsers.getData().get(0).getId(), 7, "Validate First Record Data Id");
        List<Data> dataList = listOfUsers.getData();
        for (Data eachData : dataList) {
            Assert.assertTrue(eachData.getId() > 0, "Each Data id should be a positive integer");
        }
    }

    @Test
    public void getListOfUsers_page1_shouldReturnUsers() {
        Map<String, Object> queryParms = Map.of("page", 1);
        ListOfUsers listOfUsers = userService.getListOfUsersByPageNo(rs, queryParms, 200);

        Assert.assertEquals(listOfUsers.getPage(), 1, "Expected page 1");
        Assert.assertTrue(listOfUsers.getPerPage() > 0, "per_page should be positive");
        Assert.assertFalse(listOfUsers.getData().isEmpty(), "Data list should not be empty for page 1");
        Assert.assertNotNull(listOfUsers.getSupport(), "Support object should be present");

        // Validate ids are positive
        for (Data d : listOfUsers.getData()) {
            Assert.assertTrue(d.getId() > 0, "User id must be positive");
        }
    }

    @Test
    public void getListOfUsers_missingPageParam_defaultsToPage1() {
        Map<String, Object> queryParms = Map.of();
        ListOfUsers listOfUsers = userService.getListOfUsersByPageNo(rs, queryParms, 200);

        Assert.assertEquals(listOfUsers.getPage(), 1, "Missing page param should default to page 1");
    }

    @Test
    public void getListOfUsers_nonExistentPage_returnsEmptyData() {
        Map<String, Object> queryParms = Map.of("page", 1000);
        ListOfUsers listOfUsers = userService.getListOfUsersByPageNo(rs, queryParms, 200);

        Assert.assertEquals(listOfUsers.getPage(), 1000, "Page in response should match requested page");
        Assert.assertNotNull(listOfUsers.getData(), "Data list should be present (possibly empty)");
        Assert.assertTrue(listOfUsers.getData().isEmpty(), "Non-existent page should return empty data list");
    }

    @Test
    public void getListOfUsers_perPageConsistency() {
        Map<String, Object> queryParms = Map.of("page", 1);
        ListOfUsers listOfUsers = userService.getListOfUsersByPageNo(rs, queryParms, 200);

        int perPage = listOfUsers.getPerPage();
        int dataSize = listOfUsers.getData() == null ? 0 : listOfUsers.getData().size();

        // dataSize should never exceed perPage; perPage should be positive
        Assert.assertTrue(perPage > 0, "per_page must be positive");
        Assert.assertTrue(dataSize <= perPage, "Returned data size must be <= per_page");
    }
}
