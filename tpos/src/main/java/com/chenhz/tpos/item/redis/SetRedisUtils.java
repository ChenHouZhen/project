package com.chenhz.tpos.item.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

@Component
public class SetRedisUtils {

    @Resource(name = "redisTemplate")
    private SetOperations<String,Object> setOperations;


    @Autowired private RedisTemplateUtils redisTemplateUtils;


    /**
     *如果不存在这个字符串，将字符串存入set集合，返回存入元素的个数；
     *如果存在这个字符串就不操作，返回0；
     */
    public Long add(String k,Object... v){
        return setOperations.add(k,v);
    }

    /**
     * 列出key的所有set集合
     */
    public Set<Object> members(String k){
        return setOperations.members(k);
    }

    public Long size(String k){
        return setOperations.size(k);
    }
    /**
     * 差集
     */
    public Set<Object> difference(String key,String otherKey){
        return setOperations.difference(key,otherKey);
    }

    /**
     * 交集
     */
    public Set<Object> intersect(String key,String otherKey){
        return setOperations.intersect(key,otherKey);
    }

/*
    public Set<Object> intersect(String key, Collection<String> c){
        return setOperations.intersect(key,c);
    }
*/

    /**
     * 并集
     */
    public Set<Object> union(String key,String otherKey){
        return setOperations.union(key,otherKey);
    }

    /**
     * 并集，并存入destKey集合
     */
    public Long unionAndStore(String key,String otherKey,String destKey){
        return setOperations.unionAndStore(key,otherKey,destKey);
    }
}
