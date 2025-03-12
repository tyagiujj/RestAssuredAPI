package day1;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;
import org.testng.annotations.Test;

/*given()
 content,type, set, cookies,add auth,add para, set headers, info etc...
when()
get, post, put, delete
then()
validate status code, extract response, extract headers cookies response body...
*/
public class HTTPRequests {
	int id;
    @Test(priority=1)
    void getUser() {
        given()
        
        .when()
        .get("https://reqres.in/api/users?page=2")
        
        .then()
        .statusCode(200)
        .body("page", equalTo(2))
        .log().all();
    }

    @Test(priority=2)
    void createUsers() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "ujjwal");
        data.put("job", "qa");
        
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
    @Test(priority=3,dependsOnMethods= {"createUsers"})
    void UpdateUser() {
    	HashMap<String, String> data = new HashMap<>();
        data.put("name", "ujjwal Tyagi");
        data.put("job", "Testing");
        
        given()
        .contentType("application/json")
        .body(data)
        
        .when()
        .put("https://reqres.in/api/users/"+id)
   
        .then()
        .statusCode(200)
        .log().all();
    }
    @Test(priority=4)
    void DeleteUsers() {
    	given()
    	
    	.when()
    	.delete("https://reqres.in/api/users/"+id)
    	
    	.then()
    	.statusCode(204)
    	.log().all();
    	
    }
}
