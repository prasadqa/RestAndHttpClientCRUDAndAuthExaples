package com.httpclient.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.pojo.CreatePojo;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class PostCall {

    @Test
    public void postCallAPITest(){
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //creat request
        HttpPost postRequest = new HttpPost("https://gorest.co.in/public/v2/users");
        postRequest.addHeader("Authorization", "Bearer 18b814df0f529895867c216216110d862e8107f0ba6487add24f76904410a952");
        postRequest.setHeader("Content-Type", "application/json");
        postRequest.addHeader("accept", "application/json");
        //create pojo
        CreatePojo creat = new CreatePojo(888662,"Deepesh Bhattacharya","bhattacharya_deepesh435@harris.net","female","active");
        ObjectMapper mapper = new ObjectMapper();
        String userJson = null;

        try {
            userJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(creat);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("first user json "+userJson);
        // request execute
        StringEntity userEntity = null;

        try {
            userEntity = new StringEntity(userJson);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // add body
        postRequest.setEntity(userEntity);
        //execute request
        try {
            response = httpClient.execute(postRequest);
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response.getStatusLine().getStatusCode());
        HttpEntity httpEntity =  response.getEntity();
        String reponseBody = null;

        try {
            reponseBody = EntityUtils.toString(httpEntity);
        } catch (ParseException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(reponseBody);



    }
}
