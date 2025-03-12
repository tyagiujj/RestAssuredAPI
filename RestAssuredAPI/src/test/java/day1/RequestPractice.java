package day1;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;

public class RequestPractice {
	String token;
	int userId = 2;

	@Test(priority=1)
	void GetUsers() {
		given()
		.when()
			.get("https://reqres.in/api/users/" + userId)
		.then()
			.statusCode(200)
			.log().all();
	}
	@Test(priority=2)
	void POST() {
		HashMap<String, String> data = new HashMap<>();
		data.put("email", "ujjwaltyagi9700@gmail.com");
		data.put("password", "tyagi@1234");
		token = given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/login")
		.then()
			.statusCode(400)
			.log().all()
			.extract().jsonPath().getString("token");
	
		System.out.println("Token: " + token);
		
	}
	@Test(priority=3)
	void UpdateUser() {
		HashMap<String, String> data = new HashMap<>();
		data.put("name", "Ujjwal Tyagi");
		data.put("job", "Software Tester");
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.put("https://reqres.in/api/users/" + userId)
		.then()
			.statusCode(200)
			.log().all()
			.body("name", equalTo("Ujjwal Tyagi")) 
			.body("job", equalTo("Software Tester"))
			//.log().all();
			.log().headers();
	}
	@Test(priority=4)
	void DeleteUser() {
		given()
		.when()
			.delete("https://reqres.in/api/users/" + userId)
		.then()
			.statusCode(204)
			.log().all();
	}
}
