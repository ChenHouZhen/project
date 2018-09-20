package com.chenhz.tpos.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(tags = "测试接口")
public class TestController {

    @GetMapping("one")
    @ApiOperation("测试返回字符")
    public String test(){
        return "success tpos module";
    }

//    @PostMapping
//    public

}
