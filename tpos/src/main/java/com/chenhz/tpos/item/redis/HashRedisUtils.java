package com.chenhz.tpos.item.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class HashRedisUtils {

    @Resource(name = "redisTemplate")
    private HashOperations<String,String,Object> hashOperations;

    @Autowired
    private RedisTemplateUtils redisTemplateUtils;

    public void del(String k , String... mk){
        hashOperations.delete(k,mk);
    }

    public boolean hasKey(String k,String mk){
        return hashOperations.hasKey(k,mk);
    }

    public Object get(String k,String mk){
        return hashOperations.get(k,mk);
    }

    public Map<String,Object> entries(String k){
        return hashOperations.entries(k);
    }

    public long increment(String k ,String mk,long increment){
        return hashOperations.increment(k,mk,increment);
    }

    public Set<String> keys(String k){
        return hashOperations.keys(k);
    }

    public List<Object> values(String k){
        return hashOperations.values(k);
    }

    public void put(String k,String mk ,String v){
        hashOperations.put(k,mk,v);
    }


}
