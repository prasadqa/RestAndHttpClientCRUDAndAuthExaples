package com.rest.api.validateSchema;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;


import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class schemaValidation {

    @Test
    public void postSchemaValidator(){
        RestAssured.baseURI = "https://gorest.co.in";

        given().log().all()
                .header("Authorization","Bearer 18b814df0f529895867c216216110d862e8107f0ba6487add24f76904410a952")
                .contentType(ContentType.JSON)
                .body(new File("/Users/challaprasad/Documents/APIAutomation/RestAssured/src/test/java/com/rest/api/userDetails.json"))
                .when().log().all()
                .post("/public/v2/users")
                .then().log().all()
                .assertThat()
                .statusCode(201)
                .and()
                .body(matchesJsonSchema(new File("/Users/challaprasad/Documents/APIAutomation/RestAssured/src/test/java/resource/userSchema.json")));
    }
    @Test
    public void getSchemaValidator(){
        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer 18b814df0f529895867c216216110d862e8107f0ba6487add24f76904410a952")
                .when().log().all()
                .get("public/v2/users/?id=881585")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .body(matchesJsonSchema(new File("/Users/challaprasad/Documents/APIAutomation/RestAssured/src/test/java/resource/getSchema.json")));

    }


}
