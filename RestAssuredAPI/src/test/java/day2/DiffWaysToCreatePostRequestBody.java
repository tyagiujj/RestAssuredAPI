package day2;

import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DiffWaysToCreatePostRequestBody {

    String userId;  // Changed from int to String to store the extracted ID correctly

    // 1) Post request body using org.json library
    @Test(priority = 1)
    void testPostUsingJsonLibrary() {
        JSONObject data = new JSONObject();
        data.put("name", "KUMAR");
        data.put("job", "leader");

        userId = given()
                    .contentType("application/json")
                    .body(data.toString())
                .when()
                    .post("https://reqres.in/api/users")
                .then()
                    .statusCode(201)
                    .body("name", equalTo("KUMAR"))
                    .body("job", equalTo("leader"))
                    .log().all()
                .extract()
                    .path("id"); // Extracting ID as a String

        System.out.println("Created User ID: " + userId);
    }

    // 2) Deleting the created user
    @Test(priority = 2, dependsOnMethods = "testPostUsingJsonLibrary")
    void testDelete() {
        given()
        .when()
            .delete("https://reqres.in/api/users/" + userId) // Use the extracted ID
        .then()
            .statusCode(204)
            .log().all();
    }
}
