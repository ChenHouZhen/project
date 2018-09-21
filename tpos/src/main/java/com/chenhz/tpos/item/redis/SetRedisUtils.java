package com.chenhz.tpos.item.redis;

import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SetRedisUtils {

    @Resource(name = "redisTemplate")
    private SetOperations<String,Object> setOperations;
}
