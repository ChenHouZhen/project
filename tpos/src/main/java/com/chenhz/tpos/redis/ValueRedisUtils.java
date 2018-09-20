package com.chenhz.tpos.redis;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ValueRedisUtils {

    @Resource(name = "redisTemplate")
    private ValueOperations<String,Object> valueOperations;
}
