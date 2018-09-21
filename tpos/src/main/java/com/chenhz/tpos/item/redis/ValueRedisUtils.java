package com.chenhz.tpos.item.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class ValueRedisUtils {

    @Resource(name = "redisTemplate")
    private ValueOperations<String,Object> valueOperations;

    @Autowired
    private RedisTemplateUtils redisTemplateUtils;

    public  Object get(String k){
        return valueOperations.get(k);
    }

    public void set(String k,Object v){
        valueOperations.set(k,v);
    }

    public void set(String k,Object v,long expire){
        valueOperations.set(k,v,expire,TimeUnit.SECONDS);
    }

    public long incr(String k){
        return incr(k,1L);
    }

    public long decr(String k){
        return incr(k,-1L);
    }

    public long incr(String k,long increment){
        return valueOperations.increment(k,increment);
    }


    /**
     * @param k
     * @return
     * true : 存在
     * false : 不存在
     */
    public boolean exists(String k){
        return this.get(k) != null ;
    }

    /**
     *
     * @param k
     * @return
     *
     * true : 删除成功
     * false: 键不存在 或 出错
     */
    public boolean expire(String k){
        return redisTemplateUtils.setExpire(k);
    }


}
