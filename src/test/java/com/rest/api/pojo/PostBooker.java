package com.rest.api.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostBooker {



    @Test
         public void PostBookerTest1(){



    CreatePojo creat = new CreatePojo(888662,"Deepesh Bhattacharya","bhattacharya_deepesh@harris.net","female","active");
    ObjectMapper mapper = new ObjectMapper();
    String userjson = null;

        try
        {
            userjson = mapper.writeValueAsString(creat);
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(userjson);

        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer 18b814df0f529895867c216216110d862e8107f0ba6487add24f76904410a952")
                .body(userjson)
                .when().log().all()
                .post("/public/v2/users")
                .then()
                .assertThat()
                .statusCode(201)
                .and()
                .body("name",equalTo(creat.getName()))
                .body("status",equalTo(creat.getStatus()));

}


}
