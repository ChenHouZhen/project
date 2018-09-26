package com.chenhz.tpos.item.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ListRedisUtils {

    @Resource(name = "redisTemplate")
    private ListOperations<String,Object> listOperations;


    @Autowired
    private RedisTemplateUtils redisTemplateUtils;

    /**
     * 从左向右存压栈
     */
    public Long leftPush(String k,String v){
        Long len = listOperations.leftPush(k,v);
        return len;
    }

    /**
     * 从右向左存压栈
     */
    public Long rightPush(String k,String v){
        Long len = listOperations.rightPush(k,v);
        return len;
    }

    /**
     * 获取大小
     */
    public Long size(String k){
        return listOperations.size(k);
    }

    /**
     *范围检索,根据下标，返回[start,end]的List
     */
    public List<Object> range(String k, long start , long end){
        return listOperations.range(k,start,end);
    }

    /**
     * 移除k中值为v的num个,返回删除的个数；如果没有这个元素则返回0
     */
    public Long remove(String k,long num, Object v){
        return listOperations.remove(k,num,v);
    }

    /**
     *检索
     */
    public Object index(String k,long index){
        return listOperations.index(k,index);
    }

    /**
     *裁剪,删除除了[start,end]以外的所有元素
     */
    public void trim(String k,long start,long end){
        listOperations.trim(k,start,end);
    }

    /**
     * 将源key的队列的右边的一个值删除，然后塞入目标key的队列的左边，返回这个值
     */
    public Object rightPopAndLeftPush(String sourceKey,String destinationKey){
        return listOperations.rightPopAndLeftPush(sourceKey,destinationKey);
    }

}
