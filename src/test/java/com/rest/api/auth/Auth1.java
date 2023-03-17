package com.rest.api.auth;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Auth1 {

    @Test
    public void basicAuth(){
        given().log().all()
                .auth()
                .preemptive()
                .basic("admin","admin")
                .when()
                .get("https://the-internet.herokuapp.com/basic_auth")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void tokenAuth(){
        RestAssured.baseURI = "https://gorest.co.in";

          given().log().all()
                .pathParam("id","736987")
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer eac398d6164c2a975c1c5635bef2b9815631adc326914940887fad13bc230f4c")
                .when()
                .get("/public/v2/users/{id}")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .header("Server","cloudflare");

    }

}
