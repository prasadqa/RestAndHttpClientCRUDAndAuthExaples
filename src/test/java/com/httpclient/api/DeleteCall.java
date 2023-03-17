package com.httpclient.api;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteCall {


    @Test
    public void deleteUserTest() {

        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 1. create a delete request with url:
        HttpDelete deleteRequest = new HttpDelete("https://gorest.co.in/public/v2/users/888651");

        // 2. add headers:
        deleteRequest.addHeader("Authorization", "Bearer 18b814df0f529895867c216216110d862e8107f0ba6487add24f76904410a952");

        // 3. execute the api:
        try {
            response = httpClient.execute(deleteRequest);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 4. get the status code:
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode, 200);

        // 5. get the response body:
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

    }
}
