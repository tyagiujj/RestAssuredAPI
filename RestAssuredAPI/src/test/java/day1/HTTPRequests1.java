package day1;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;
public class HTTPRequests1 {
	int id;
    @Test(priority = 1)
    void GetUser() {
        given()
        .when()
        .get("https://reqres.in/api/unknown/2")
        .then()
        .statusCode(200)
        .body("page", equalTo(null))
        .log().all();   
    }
    @Test(priority=2,dependsOnMethods= {"GetUser"})
    void CreateUser() {
    	 HashMap<String, String> data = new HashMap<>();
    	 data.put("name","Sachin");
    	 data.put("job", "Software Testing");
    	 id=given()
         .contentType("application/json")
         .body(data)
         .when()
         .post("https://reqres.in/api/users")
         .jsonPath().getInt("id");
         ;
         //.then()  // Fixed missing parentheses
         //.statusCode(201)
         //.log().all();	
    }
    @Test(priority=3,dependsOnMethods= {"CreateUser"})
    void UpdateUser() {
    	HashMap<String, String> data = new HashMap<>();
        data.put("name", "ujjwal Tyagi");
        data.put("job", "SDET");
        given()
        .contentType("application/json")
        .body(data)
        .when()
        .put("https://reqres.in/api/users/"+id)
        .then()
        .statusCode(200)
        .log().all();
    }
    @Test(priority=4,dependsOnMethods= {"UpdateUser"})
    void DeleteUser() {
	given()
    	.when()
    	.delete("https://reqres.in/api/users/"+id)
    	.then()
    	.statusCode(204)
    	.log().all();  }   }
