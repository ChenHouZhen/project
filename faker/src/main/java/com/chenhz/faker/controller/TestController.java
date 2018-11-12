package com.chenhz.faker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author CHZ
 * @create 2018/9/5
 */
@Controller
@RequestMapping("test")
public class TestController {

    @RequestMapping("/index")
    public String index(){
        return "10";
    }

    @GetMapping("one")
    @ResponseBody
    public String test(){
        return "success";
    }
}
