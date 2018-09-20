package com.chenhz.tpos.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class HashRedisUtils {

    @Resource(name = "redisTemplate")
    private HashOperations<String,String,Object> hashOperations;
}
