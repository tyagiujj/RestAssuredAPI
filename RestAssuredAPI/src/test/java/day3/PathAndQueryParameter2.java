package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class PathAndQueryParameter2 {
	//https://reqres.in/api/users?page=2
	@Test(priority=1)
	void testQueryandpath() {
	given()
	.pathParam("mypath","users")
	.queryParam("page", 2)
	.when()
	.get("https://reqres.in/api/{mypath}")
	.then()
	.statusCode(200)
	 .body("page", equalTo(2))
	//.log().all()
	//.log().headers();
	//.log().cookies();
	 .log().body();
	}}
