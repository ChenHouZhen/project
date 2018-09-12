package com.chenhz.http.utils;

import com.chenhz.http.main.HttpResponse;

import java.util.Map;

public class UnmarshallerContext {
    private int httpStatus;
    private Map<String, String> responseMap;
    private HttpResponse httpResponse;

    public UnmarshallerContext() {
    }

    public Integer integerValue(String key) {
        String value = (String)this.responseMap.get(key);
        return null != value && 0 != value.length() ? Integer.valueOf(value) : null;
    }

    public String stringValue(String key) {
        return (String)this.responseMap.get(key);
    }

    public Long longValue(String key) {
        String value = (String)this.responseMap.get(key);
        return null != value && 0 != value.length() ? Long.valueOf((String)this.responseMap.get(key)) : null;
    }

    public Boolean booleanValue(String key) {
        String value = (String)this.responseMap.get(key);
        return null != value && 0 != value.length() ? Boolean.valueOf((String)this.responseMap.get(key)) : null;
    }

    public Float floatValue(String key) {
        String value = (String)this.responseMap.get(key);
        return null != value && 0 != value.length() ? Float.valueOf((String)this.responseMap.get(key)) : null;
    }

    public Double doubleValue(String key) {
        String value = (String)this.responseMap.get(key);
        return null != value && 0 != value.length() ? Double.valueOf((String)this.responseMap.get(key)) : null;
    }

    public int lengthValue(String key) {
        String value = (String)this.responseMap.get(key);
        return null != value && 0 != value.length() ? Integer.valueOf((String)this.responseMap.get(key)) : 0;
    }

    public int getHttpStatus() {
        return this.httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Map<String, String> getResponseMap() {
        return this.responseMap;
    }

    public void setResponseMap(Map<String, String> responseMap) {
        this.responseMap = responseMap;
    }

    public HttpResponse getHttpResponse() {
        return this.httpResponse;
    }

    public void setHttpResponse(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }
}
