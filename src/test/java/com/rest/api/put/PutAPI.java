package com.rest.api.put;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.pojo.CreatePojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PutAPI {

    @Test
    public void putAPITest(){
        CreatePojo user = new CreatePojo(886689,"Ghanashyam Patil","bhattacharya_deepesh1234@harris.net","female","inactive");
        ObjectMapper mapper = new ObjectMapper();
        String userJson = null;
        try {
            userJson = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(userJson);

        RestAssured.baseURI = "https://gorest.co.in";
        given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer 18b814df0f529895867c216216110d862e8107f0ba6487add24f76904410a952")
                .body(userJson)
                .when().log().all()
                .put("/public/v2/users/886689")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .body("gender",equalTo(user.getGender()))
                .body("id",equalTo(user.getId()))
                .body("email",equalTo(user.getEmail()));


    }

}
