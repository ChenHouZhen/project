package com.chenhz.tpos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author CHZ
 * @create 2018/9/5
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("one")
    public String test(){
        return "success tpos module";
    }

}
