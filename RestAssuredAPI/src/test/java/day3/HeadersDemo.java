package day3;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {
    @Test(priority = 1)
    void testHeaders() {
        given()
        .when()
            .get("https://www.google.com/")
        .then()
            .header("Content-Type", "text/html; charset=ISO-8859-1")
            .and()
            .header("Content-Encoding", "gzip")
            .and()
            .header("Server", "gws");
    }
    @Test(priority = 2)
    void GetHeaders() {
    	
        Response res=given()
        .when()
            .get("https://www.google.com/");
        //get Single Header Info
      // String Header_Value= res.getHeader("Content-Type");
       //System.out.println("The value of Content Header Type is : " + Header_Value);
        
        //get All Header Info
       Headers MyHeaders =res.getHeaders();
       for(Header hd: MyHeaders) {
    	   System.out.println(hd.getName()+"           "+hd.getValue());
       }
        
    }
}
