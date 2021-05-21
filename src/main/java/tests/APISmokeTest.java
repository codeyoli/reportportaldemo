package tests;

import base.APITestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utility.LOG;

public class APISmokeTest extends APITestBase {

    @Test(priority = 1)
    public void register_one_user() {

        // Test Data
        String payload = getNewUserPayload();

        // Test Step
        LOG.step("Sending POST request to the /user/register endpoint");
        Response response = RestAssured.given()
                .contentType("Application/json")
                .body(payload)
                .post("/user/register");

        LOG.json("Received response with status code: " + response.getStatusCode());
        String responseBody = response.getBody().prettyPrint();
        LOG.json(responseBody);
        String token = extractData(response.getBody().asString(), "$.token");
        System.out.println("Registration token: " + token);
        setUserToken(token);

        // Test Assertion
        Assert.assertEquals(response.getStatusCode(), 201);
    }

}
