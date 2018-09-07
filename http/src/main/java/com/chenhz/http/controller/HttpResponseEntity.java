package com.chenhz.http.controller;

public class HttpResponseEntity {

    /**
     * 保留字段，后期扩展
     */
    //private String requestId;
    //private String bizId;
    private int code;
    private String message;

    private static final int ERROR_CODE = 500;

    private static final int OK_CODE= 0;

    public HttpResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private HttpResponseEntity() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static HttpResponseEntity ok(String msg){
        HttpResponseEntity response = new HttpResponseEntity();
        response.setCode(HttpResponseEntity.OK_CODE);
        response.setMessage(msg);
        return response;
    }

    public static HttpResponseEntity error(String msg){
        HttpResponseEntity response = new HttpResponseEntity();
        response.setCode(HttpResponseEntity.ERROR_CODE);
        response.setMessage(msg);
        return response;
    }

    @Override
    public String toString() {
        return "HttpResponseEntity{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
