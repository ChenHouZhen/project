package com.chenhz.tpos.item.redis;

import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ZSetRedisUtils {

    @Resource(name = "redisTemplate")
    private ZSetOperations<String,Object> zSetOperations;
}
