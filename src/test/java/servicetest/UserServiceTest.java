package servicetest;

import in.reqres.request.models.CreateUser;
import in.reqres.response.models.Data;
import in.reqres.response.models.ListOfUsers;
import in.reqres.services.UsersService;
import in.reqres.utils.ReadDataFromExternalFiles;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class UserServiceTest extends ReqResBaseTest {

    private final UsersService userService = new UsersService();
    private final ReadDataFromExternalFiles readData = new ReadDataFromExternalFiles();

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
        Assert.assertTrue(perPage > 0, "per_page must be positive");
        Assert.assertTrue(dataSize <= perPage, "Returned data size must be <= per_page");
    }


    @Test
    public void createNewUserTestWithRequestBodyAsString() {
        String requestBody = "{\"name\": \"morpheus\", \"job\": \"leader\"}";
        var createUserResponse = userService.createNewUserDetails(rs, requestBody, 201);
        Assert.assertEquals(createUserResponse.getName(), "morpheus", "Validate Name");
        Assert.assertEquals(createUserResponse.getJob(), "leader", "Validate Job");
        Assert.assertNotNull(createUserResponse.getId(), "Id should not be null");
    }

    @Test
    public void createNewUserTestWithRequestBodyAsModelClass() {
        CreateUser createUser = new CreateUser();
        createUser.setName("morpheus");
        createUser.setJob("leader");
        var createUserResponse = userService.createNewUserDetails(rs, createUser, 201);
        Assert.assertEquals(createUserResponse.getName(), createUser.getName(), "Validate Name");
        Assert.assertEquals(createUserResponse.getJob(), createUser.getJob(), "Validate Job");
        Assert.assertNotNull(createUserResponse.getId(), "Id should not be null");
    }

    @Test
    public void createNewUserTestWithRequestBodyFromJson() {
        CreateUser createUser = readData.readDataFromJsonFile(System.getProperty("user.dir") + "/src/test/resources/CreateUser.json");
        var createUserResponse = userService.createNewUserDetails(rs, createUser, 201);
        Assert.assertEquals(createUserResponse.getName(), createUser.getName(), "Validate Name");
        Assert.assertEquals(createUserResponse.getJob(), createUser.getJob(), "Validate Job");
        Assert.assertNotNull(createUserResponse.getId(), "Id should not be null");
    }

    @Test
    public void createNewUserTestWithRequestBodyFromJsonAsStingParams() {
        String createUser = readData.readDataFromJsonFileAsString(System.getProperty("user.dir") + "/src/test/resources/CreateUserParameterData.json");
        if (createUser == null) {
            Assert.fail("Failed to read CreateUserParameterData.json as string");
            return;
        }
        String requestBody = String.format(createUser, "morpheus", "leader");
        var createUserResponse = userService.createNewUserDetails(rs, requestBody, 201);
        Assert.assertEquals(createUserResponse.getName(), "morpheus", "Validate Name");
        Assert.assertEquals(createUserResponse.getJob(), "leader", "Validate Job");
        Assert.assertNotNull(createUserResponse.getId(), "Id should not be null");
    }
}
