package com.rest.api.ResSpec;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class ResponceSpecBuiltExp {


    /* configure common validation accros the all test cases */
    ResponseSpecification resSpec_200_Ok = new ResponseSpecBuilder()
            .expectContentType(ContentType.JSON)
            .expectStatusCode(200)
            .expectHeader("Server","cloudflare").build();

    ResponseSpecification resSpec_400_BADRequest = new ResponseSpecBuilder().expectStatusCode(400).build();
    ResponseSpecification resSpec_401_AUTH_fail = new ResponseSpecBuilder().expectStatusCode(401).build();

    @Test
    public void ResponseSpecTest(){

        RestAssured.baseURI = "https://gorest.co.in";

        given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer eac398d6164c2a975c1c5635bef2b9815631adc326914940887fad13bc230f4c")
                .when().log().all()
                .get("/public/v2/users")
                .then().log().all()
                .assertThat().spec(resSpec_200_Ok);
    }

    @Test
    public void specAuth_fail(){

        RestAssured.baseURI = "https://gorest.co.in";

        given()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer 12323434")
                .when().log().all()
                .get("/public/v2/users")
                .then().log().all()
                .assertThat().spec(resSpec_401_AUTH_fail);

    }


}
