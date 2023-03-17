package com.httpclient.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.rest.api.pojo.CreatePojo;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class PutCall {

    // create a user -- POST
    // get the user id from the response
    // use the update api and append the user id in url
    // update the pojo with any data
    // hit the put call api
    // get the response from the put api

    @Test
    public void updateCallTest(){
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // post call
        HttpPost postRequest = new HttpPost("https://gorest.co.in/public/v2/users");
        postRequest.addHeader("Authorization", "Bearer 18b814df0f529895867c216216110d862e8107f0ba6487add24f76904410a952");
        postRequest.setHeader("Content-Type", "application/json");
        postRequest.setHeader("accept", "application/json");

        CreatePojo creat = new CreatePojo(888662,"Deepesh Bhattacharya","bhattacharya_deepesh437@harris.net","female","active");
        ObjectMapper mapper = new ObjectMapper();
        String userJson = null;

        try {
            userJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(creat);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("first user json "+userJson);

        StringEntity payloadEntity = null;

        try {
            payloadEntity = new StringEntity(userJson);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //hit the api

        postRequest.setEntity(payloadEntity);

        try {
            response = httpClient.execute(postRequest);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(response.getStatusLine().getStatusCode());
        HttpEntity httpEntity = response.getEntity();
        String responseBody = null;
        try {
            responseBody = EntityUtils.toString(httpEntity);
            System.out.println(responseBody);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
///********************end if the post call ******************//

        // get the id from the post response:
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(responseBody);
        List<String> result = JsonPath.read(document,"$.id");
        System.out.println(result.get(0));

        // ****************************starting of PUT call ********************
        String userId = result.get(0);
        String putUri = "https://gorest.co.in/public/v2/users"+userId;

        HttpPut putRequest = new HttpPut(putUri);
        // add headers:
        putRequest.addHeader("Authorization", "Bearer 18b814df0f529895867c216216110d862e8107f0ba6487add24f76904410a952");
        putRequest.setHeader("Content-Type", "application/json");
        putRequest.addHeader("accept", "application/json");

        //update string
        creat.setStatus("inactive");

        // convert Java pojo to json -- Serialization -- JACKSON API
        mapper = new ObjectMapper();
        userJson = null;
        try {
            userJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(creat);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(userJson);

        payloadEntity = null;
        try {
            payloadEntity = new StringEntity(userJson);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 4. add the json body to the request:
        putRequest.setEntity(payloadEntity);

        // 5. hit the API/execute the API:

        try {
            response = httpClient.execute(putRequest);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 6. get the status code:
        System.out.println(response.getStatusLine().getStatusCode());
        // Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);

        // 7. get the response body:
        httpEntity = response.getEntity();
        responseBody = null;
        try {
            responseBody = EntityUtils.toString(httpEntity);
            System.out.println(responseBody);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get the statuc from the post response:

        document = Configuration.defaultConfiguration().jsonProvider().parse(responseBody);
        result = JsonPath.read(document, "$..status");
        System.out.println("current user status is: ==> " + result.get(0));

        String updatedUserStatus = result.get(0);

        Assert.assertEquals(updatedUserStatus, creat.getStatus());




    }
}
