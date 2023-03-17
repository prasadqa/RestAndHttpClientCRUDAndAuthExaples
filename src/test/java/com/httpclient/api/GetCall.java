package com.httpclient.api;


import com.jayway.jsonpath.Configuration;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetCall {

    @Test
    public void getCallTest(){
        //set
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 1) Create get request with rul
        HttpGet getRequest =  new HttpGet("https://gorest.co.in/public/v2/users");
        // 2) add headers
        getRequest.addHeader("Authorization","Bearer 18b814df0f529895867c216216110d862e8107f0ba6487add24f76904410a952");
        // 3) Execute API
        try {
            response = httpClient.execute(getRequest);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 4) Get the status code
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Status code"+statusCode);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
        // 5) Get the response body
        HttpEntity httpEntity =  response.getEntity();
        String responseEntity = null;
        try {
            responseEntity = EntityUtils.toString(httpEntity);
        } catch (ParseException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("Response Entity"+ responseEntity);
        JsonPath js = new JsonPath(responseEntity);
        System.out.println(js.getString("id"));
        System.out.println(js.prettyPrint());

        Object document = Configuration.defaultConfiguration().jsonProvider().parse(responseEntity);
        List<String> result = com.jayway.jsonpath.JsonPath.read(document,"$..name");
        System.out.println(result.get(0));



    }

}
