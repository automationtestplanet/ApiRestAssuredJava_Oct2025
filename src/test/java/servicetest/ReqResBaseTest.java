package servicetest;

import in.reqres.services.UsersService;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class ReqResBaseTest {

    private static final String BASE_URI = "https://reqres.in";
    private static final String API_KEY = "reqres_c4dd8dedafed4c89ba31edf4eb997630";
    public RequestSpecification rs;

    @BeforeClass(alwaysRun = true)
    public void requestConfig() {
        rs = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .addHeader("x-api-key", API_KEY)
                .build();
    }
}
