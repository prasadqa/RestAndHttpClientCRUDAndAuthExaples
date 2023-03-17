package com.rest.api.get;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class GetBddAPI {


@Test
public void getAPITest1(){
    given().log().all()
            .when().log().all()
            .get("http://ergast.com/api/f1/2017/circuits.json").
            then().log().all()
            .assertThat().
            body("MRData.CircuitTable.Circuits.circuitId",hasSize(20));
}
@Test
    public void getAPITest2(){
    Response res =
            given().log().all()
                    .when().log().all()
                    .get("http://ergast.com/api/f1/2017/circuits.json");
    int resCode = res.getStatusCode();
    Assert.assertEquals(resCode,200);
    System.out.println(res.prettyPrint());
}
@Test
    public void getAPITest3(){
    RestAssured.baseURI = "http://ergast.com";
    given().log().all()
            .when().log().all()
            .get("/api/f1/2017/circuits.json")
            .then().log().all()
            .assertThat()
            .statusCode(200)
            .and()
            .contentType(ContentType.JSON)
            .and()
            .header("Content-Length",equalTo("4552"));

}
@Test
    public void getAPITest4(){
    given().log().all()
            .param("text","test")
            .when().log().all()
            .get("http://md5.jsontest.com")
            .then().log().all()
            .assertThat()
            .body("md5",equalTo("098f6bcd4621d373cade4e832627b4f6"));
}
@DataProvider(name="circuitData")
    public Object[][] getCircuteYearInfo(){
    return new Object[][]{
            {"2017",20},
            {"2016",21},
            {"1966",9}
    };
}
@Test(dataProvider = "circuitData")
    public void numberOfCircuteInfoTest(String yearName,int circutesNum){

    given().log().all()
            .pathParam("raceSeason",yearName)
            .when().log().all()
            .get("http://ergast.com/api/f1/{raceSeason}/circuits.json")
            .then().log().all()
            .assertThat()
            .body("MRData.CircuitTable.Circuits.circuitId",hasSize(circutesNum));

}

}
