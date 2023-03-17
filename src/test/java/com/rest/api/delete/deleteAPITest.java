package com.rest.api.delete;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class deleteAPITest {
    @Test
    public void deleteAPITest1(){

        String id = "886689";
        RestAssured.baseURI = "https://gorest.co.in";
        given()
                .header("Authorization","Bearer 18b814df0f529895867c216216110d862e8107f0ba6487add24f76904410a952")
                .when()
                .delete("/public/v2/users/"+id)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .extract().path("id");





    }



}
