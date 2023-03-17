package com.rest.api.post;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class postBDD {

@Test
    public void postTest1(){
/*with out json file */
    RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    given().log().all()
            .contentType(ContentType.JSON)
            .body("{\n" +
                    "    \"username\" : \"admin\",\n" +
                    "    \"password\" : \"password123\"\n" +
                    "}")
            .when()
            .post("/auth")
            .then()
            .assertThat()
            .statusCode(200);

}

/*with json file data will get from json file */

    @Test
    public void postTest2(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        String tokenID= given().log().all()
                .contentType(ContentType.JSON)
                .body(new File("/Users/challaprasad/Documents/APIAutomation/RestAssured/src/test/java/com/rest/api/credential.json"))
                .when().log().all()
                .post("/auth")
                .then().log().all()
                .extract()
                .path("token");
        System.out.println("token id "+tokenID);
        Assert.assertNotNull(tokenID);

    }

    @Test
    public void bookingTest(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        int tokenID = given().log().all()
                .contentType(ContentType.JSON)
                .body(new File("/Users/challaprasad/Documents/APIAutomation/RestAssured/src/test/java/com/rest/api/BookingDetails.json"))
                .when()
                .post("/booking")
                .then()
                .extract()
                .path("bookingid");
        System.out.println("token id "+tokenID);

    }



}
