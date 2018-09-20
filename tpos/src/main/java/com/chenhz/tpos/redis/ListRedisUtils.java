package com.chenhz.tpos.redis;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ListRedisUtils {

    @Resource(name = "redisTemplate")
    private ListOperations<String,Object> listOperations;
}
