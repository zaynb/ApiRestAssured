
package tests;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class FirstTest {

	@BeforeSuite
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void getAllPosts() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/posts")
                .then()
                .extract().response();

        Assert.assertEquals(200, response.statusCode());
      
    }
    @Test
    public void getPostWithId() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/posts/1")
                .then()
                .extract().response();
        Assert.assertEquals(200, response.statusCode());
    }
	  public  static String requestBody = "{\n" +
	            "  \"title\": \"foo\",\n" +
	            "  \"body\": \"bar\",\n" +
	            "  \"userId\": \"1\" \n}";

    @Test
    public void postRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .extract().response();

        Assert.assertEquals(201, response.statusCode());
       Assert.assertEquals("foo", response.jsonPath().getString("title"));
       Assert.assertEquals("bar", response.jsonPath().getString("body"));
      Assert.assertEquals("1", response.jsonPath().getString("userId"));
    }
    
    public  static String requestBody2 = "";

@Test
public void postRequestwithemptybody() {
    Response response = given()
            .header("Content-type", "application/json")
            .and()
            .body(requestBody2)
            .when()
            .post("/posts")
            .then()
            .extract().response();

    Assert.assertEquals(201, response.statusCode());

}
}


