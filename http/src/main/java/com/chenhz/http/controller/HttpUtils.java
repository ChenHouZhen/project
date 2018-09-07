package com.chenhz.http.controller;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private static final String charset = "UTF-8";

    /**
     * application/json
     */
    public static final ContentType APPLICATION_JSON=ContentType.APPLICATION_JSON;

    /**
     * application/x-www-form-urlencoded
     */
    public static final ContentType APPLICATION_FORM_URLENCODED = ContentType.APPLICATION_FORM_URLENCODED;


    public HttpResponseEntity get(URI url){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String responseStr = "";
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            System.out.println("response.getStatusLine():"+response.getStatusLine());
            System.out.println("response.getStatusLine().getStatusCode():"+response.getStatusLine().getStatusCode());
            if (entity != null){
                responseStr=EntityUtils.toString(entity);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return HttpResponseEntity.ok(responseStr);
    }

    public HttpResponseEntity get(String host, String path, Map<String,Object> params) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder().setScheme("http")
                .setHost(host)
                .setPath(path);
        if (params != null){
            for (Map.Entry<String,Object> e: params.entrySet()) {
                if (!StringUtils.isEmpty(e.getValue())){
                    uriBuilder.setParameter(e.getKey(),e.getValue().toString());
                }
            }
        }
        URI uri = uriBuilder.build();
        return get(uri);
    }

    public HttpResponseEntity get(String ip,int port,String path,Map<String,Object> params) throws URISyntaxException {
        return get(ip+":"+port,path,params);
    }

    public HttpResponseEntity post(URI uri, ContentType contentType,Map<String,Object> params) throws UnsupportedEncodingException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);
        HttpEntity httpEntity = getHttpEntity(contentType,charset,params);
        String responseStr = "";
        try {
            httpPost.setEntity(httpEntity);
            System.out.println("executing request: "+httpPost.getURI());
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try{
                HttpEntity entity = response.getEntity();
                if (entity!=null){
                    System.out.println("--------------------------");
                    responseStr=EntityUtils.toString(entity,charset);
                    System.out.println("==========================");
                }
            } finally {
                response.close();
            }

        }catch (IOException e) {
            e.printStackTrace();
            return HttpResponseEntity.error(e.getMessage());
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return HttpResponseEntity.ok(responseStr);
    }

    public HttpResponseEntity post(String host, String path, ContentType contentType ,Map<String,Object> params) throws URISyntaxException, UnsupportedEncodingException {
        URI uri = new URIBuilder().setScheme("http")
                .setHost(host)
                .setPath(path)
                .build();
        return post(uri,contentType,params);
    }

    public HttpResponseEntity post(String ip,int port,String path,ContentType contentType,Map<String,Object> params) throws URISyntaxException, UnsupportedEncodingException {
        return post(ip+":"+port,path,contentType,params);
    }

    private HttpEntity getHttpEntity(ContentType contentType,String charset,Map<String,Object> params) throws UnsupportedEncodingException {

        if (contentType.equals(ContentType.APPLICATION_JSON)){
            StringEntity stringEntity = new StringEntity(JSONObject.toJSON(params).toString(),charset);
            stringEntity.setContentType("application/json");
            stringEntity.setContentEncoding(charset);
            return stringEntity;
        }
        List<NameValuePair> formParams = new ArrayList<>();
        if (params !=null){
            for (Map.Entry<String,Object> e:params.entrySet()) {
                if (!StringUtils.isEmpty(e.getValue())){
                    formParams.add(new BasicNameValuePair(e.getKey(),e.getValue().toString()));
                }
            }
        }
        UrlEncodedFormEntity httpEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
        return httpEntity;
    }
/*
    public static void main(String[] args) {
      HttpUtils httpUtils = new HttpUtils();
        Map<String,Object> params = new HashMap<>();
        params.put("token","eec55c4d9df2466fbca6ef2d21cb739f");
        try {
            HttpResponseEntity r = httpUtils.get("localhost",8083,"springboot-receipt/api/message/getNoReadMsgNum",params);
            JSONObject json = JSONObject.fromObject(r);
            //JSONObject data = json.getJSONObject("data");
            //System.out.println("num:"+data.get("num"));
            System.out.println(r);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
 *//*      params.put("userId",23L);
       params.put("title","消息标题");
       params.put("content","消息内容");
       params.put("type",1);
        try {
            try {
                httpUtils.post("localhost",8083,"springboot-receipt/api/message/saveNewMsg",HttpUtils.APPLICATION_JSON,params);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }*//*

  *//*      params.put("name","小明");
        params.put("age",12);
        params.put("path","page");
        try {
            httpUtils.post("localhost",8083,"springboot-receipt/test/post",HttpUtils.APPLICATION_FORM_URLENCODED,params);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*//*

    }*/
}
