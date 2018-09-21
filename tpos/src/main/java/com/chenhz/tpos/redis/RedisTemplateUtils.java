package com.chenhz.tpos.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RedisTemplateUtils {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    /**
     * 默认过期时长，单位：秒
     */
    public static final long DEFAULT_EXPIRE = 60 * 60 *24 ;

    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1;

    public void renameKey(String oldK,String newK){
        redisTemplate.rename(oldK,newK);
    }

    public void del(String k){
        redisTemplate.delete(k);
    }

    public void dels(String... ks){
        Set<String> kSet = Stream.of(ks).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    public void expireKAt(String k, Date date){
        redisTemplate.expireAt(k,date);
    }


    /**
     * 失效
     * @param key
     */
    public boolean setExpire(String key) {
        return redisTemplate.expire(key, -1, TimeUnit.SECONDS);
    }

}
