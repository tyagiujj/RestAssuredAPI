package day3;

import static io.restassured.RestAssured.given;
import java.util.Map;
import org.testng.annotations.Test;
import io.restassured.response.Response;
public class CookiesDemo {
    //@Test
    void testCookies() {

        given()

        .when()
        .get("https://www.google.com/")

        .then()
        //.cookie("AEC","AVcja2e3pJVUO4Cdzt0ewylZe8IFqYvQA7FrFYI19WlDSBTz5DhvLWb0WQ; expires=Fri, 08-Aug-2025 08:48:13 GMT; path=/; domain=.google.com; Secure; HttpOnly; SameSite=lax\r\n"
        //        + "Set-Cookie: NID=521=hXOFW8da-7eoj_XB_c4wpHh9AGhAvywQCj5jdfGzpmG4rp6C1oTLztg18C6uy9iVRGXcvQGU9u21Cf-xkKjzgaw5sCUDABnwxxegX2oL9s620G5m7MCLFlQ6OWOYjXlCY5kmqD7JbR-genMe1zAC6dUDM6jtLSlt2-kKppKU6YJRoci7e5ZtEd2WAzpxzkdw9YPvRl_qVg9fSw; expires=Mon, 11-Aug-2025 08:48:13 GMT; path=/; domain=.google.com; HttpOnly")
        .log().all();
    }
    @Test(priority=2)
    void testCookiesinfo() {

        Response res = given()

        .when()
        .get("https://www.google.com/");

        //get Single cookie info
        //String cookies_value = res.getCookie("AEC");
        //System.out.println("Value of cookie is ====>" + cookies_value);

        //getAll cookie info
        Map<String, String> cookies_values = res.getCookies();
        //System.out.println(cookies_values.keySet());
        for (String k : cookies_values.keySet()) {
            String cookies_value = res.getCookie(k);
            System.out.println(k + "             " + cookies_value);
        }
    }
}
