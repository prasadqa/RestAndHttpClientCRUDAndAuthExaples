package com.httpclient.api;


import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class OAuth1 {

    public static void OAuth1Test(){

        // oauth string
        String consumerKey = "HGgqWV2t6YnEhhBvuDnAcYlli";
        String consumerSecret = "LTqWrkWEkcW7FsZO93gq1Z9IfnED8LSY4cpCBAiQGY8AXrLloQ";
        String accessToken = "220976784-2eQZmLlaEPxeNb3Wywy24dXldUlq1ge29afKA2AR";
        String accessTokenSecret = "SgXmzqweFoaSilrWLCwYXCYKme4Rk8oXlK4rtE1CUrJN7";

        // Oauth

        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(consumerKey,consumerSecret);
        consumer.setTokenWithSecret(accessToken,accessTokenSecret);

        // Post request

        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost postRequest = new HttpPost("https://api.twitter.com/1.1/statuses/update.json?status=Naveen");

        //OAuth1 to our post request

        try {
            consumer.sign(postRequest);
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        }
        // execute the request

        try {
            response = httpClient.execute(postRequest);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response.getStatusLine().getStatusCode());

        HttpEntity httpEntity = response.getEntity();
        String responseEntity = null;

        try {
            responseEntity = EntityUtils.toString(httpEntity);
            System.out.println(responseEntity);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        OAuth1Test();
    }

}
