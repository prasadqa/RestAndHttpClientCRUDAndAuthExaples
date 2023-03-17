package com.httpclient.api;

import io.restassured.path.json.JsonPath;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class BasicAuthHttpClient {

    @Test
    public void basiAuthHandleTest(){
        //credentials
        CredentialsProvider credentials=  new BasicCredentialsProvider();
        credentials.setCredentials(new AuthScope("httpbin.org",80),new UsernamePasswordCredentials("user","passwd"));

        //set up
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credentials).build();

        //get call
        HttpGet getRequest = new HttpGet("http://httpbin.org/basic-auth/user/passwd");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(getRequest);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(response.getStatusLine().getStatusCode());

        HttpEntity httpEntity = response.getEntity();
        String responseBody = null;

        try {
            responseBody = EntityUtils.toString(httpEntity);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Body "+responseBody );
        JsonPath jp = new JsonPath(responseBody);
        System.out.println(jp.prettyPrint());
    }


}
