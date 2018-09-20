package com.chenhz.tpos.controller;

import com.chenhz.tpos.redis.ValueRedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CHZ
 * @create 2018/9/20
 */
@RestController
@RequestMapping("/redis")
@Api(tags = "Redis 操作接口")
public class RedisController {

    private ValueRedisUtils value;

    @Autowired
    public void setValue(ValueRedisUtils value) {
        this.value = value;
    }

    @PostMapping("/save/value")
    @ApiOperation("Redis 保存字面值")
    public void saveVal(String key,String val){
        value.set(key,val);
    }

    @GetMapping("/get/value")
    @ApiOperation("Redis 获取字面值")
    public String getVal(String key){
        return value.get(key).toString();
    }


}
