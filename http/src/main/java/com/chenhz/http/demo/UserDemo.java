package com.chenhz.http.demo;

import com.chenhz.http.enums.MethodType;
import com.chenhz.http.main.HttpRequest;
import org.junit.Test;

/**
 * @author CHZ
 * @create 2018/9/10
 */
public class UserDemo {

    @Test
    public void getUser(){
        HttpRequest httpRequest = new HttpRequest("");
        httpRequest.setMethod(MethodType.GET);
        httpRequest.setEncoding("utf-8");
    }
}
