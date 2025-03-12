package day1;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import java.util.HashMap;
import io.restassured.response.Response;
public class HTTPSLocalHost {
    String userId; 
    @Test(priority = 1)
    void GetRequest() {
        given()
        .when()
        .get("http://localhost:3000/students")
        .then()
        .log().all(); }
    @Test(priority = 2)
    void CreateUser() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name", "ujjwal");
        data.put("age", "25");
        data.put( "grade","Engineering");
        data.put("email","ujjwaltyagi9700@gmail.com");
        data.put("phone", "9756360213");
        data.put("city","Bangalore");
        Response response = given()
            .contentType("application/json")
            .body(data)
        .when()
        .post("http://localhost:3000/students")
        .then()
        .log().all()
       // .log().headers()
        .statusCode(201)
        .extract().response();
        userId = response.jsonPath().getString("id"); }
    @Test(priority = 3, dependsOnMethods = "CreateUser")
    void DeleteUser() {
        given()
        .when()
        .delete("http://localhost:3000/students/" + userId)
        .then()
        .statusCode(200)
        .log().all();}}
