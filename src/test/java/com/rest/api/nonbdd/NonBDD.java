package com.rest.api.nonbdd;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class NonBDD {

    @Test
    public void getNonBDDTest(){

        RestAssured.baseURI = "https://gorest.co.in";
        RequestSpecification request = RestAssured.given();
        request.header("Authorization","Bearer 18b814df0f529895867c216216110d862e8107f0ba6487add24f76904410a952");
        Response res = request.get("/public/v2/users");

        System.out.println(res.prettyPrint());
        System.out.println(res.getStatusCode());
        System.out.println(res.getStatusLine());
        System.out.println(res.getHeader("Server"));


    }
    @Test
    public void getNonBDDQueryParam(){
        RestAssured.baseURI = "https://gorest.co.in";
        RequestSpecification request = RestAssured.given();
        request.header("Authorization","Bearer 18b814df0f529895867c216216110d862e8107f0ba6487add24f76904410a952");
        request.queryParam("name","ravi shesham");
        Response response = request.get("/public/v2/users/");

        JsonPath jp = response.jsonPath();
        System.out.println(jp.getString("name"));


    }
    @Test
    public void getNonBDDHashMulQueryParam(){
        RestAssured.baseURI = "https://gorest.co.in";
        RequestSpecification request = RestAssured.given();
        request.header("Authorization","Bearer 18b814df0f529895867c216216110d862e8107f0ba6487add24f76904410a952");
//        Map<String,String> params = new HashMap<String,String>();
//        params.put("name","ravi shesham");
//        params.put("status","inactive");


//        request.queryParam(params);
        request.queryParam("name","ravi shesham");
        request.queryParam("status","inactive");


        Response response = request.get("/public/v2/users/");
        response.then().log().all();

        JsonPath jp = response.jsonPath();
        System.out.println(jp.getString("name"));
        Assert.assertEquals(jp.getString("name"),"ravi shesham");


    }
    @Test
    public void getNonBDDHashMapQueryParam(){
        RestAssured.baseURI = "https://gorest.co.in";
        RequestSpecification request = RestAssured.given();
        request.header("Authorization","Bearer 18b814df0f529895867c216216110d862e8107f0ba6487add24f76904410a952");
        Map<String,String> params = new HashMap<String,String>();
        params.put("name","ravi shesham");
        params.put("status","inactive");
//        request.queryParam(params);


        Response response = request.get("/public/v2/users/");
        response.then().log().all();

        JsonPath jp = response.jsonPath();
        System.out.println(jp.getString("name"));


    }

}
