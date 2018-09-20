package com.chenhz.tpos.redis;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Redis 切面
 */
@Aspect
@Component
public class RedisAspect {

    private Logger log = LoggerFactory.getLogger(RedisAspect.class);

    @Value("${sample.redis.open: false}")
    private boolean open;

    @Around("execution(* com.chenhz.tpos.redis.*(..))")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        if (open){
            try {
                result = point.proceed();
            } catch (Throwable e) {
                log.error("redis error" , e);
                e.printStackTrace();
            }
        }
        return result;
    }
}
