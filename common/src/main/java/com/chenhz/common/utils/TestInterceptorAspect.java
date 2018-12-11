package com.chenhz.common.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * TODO 测试完毕删除
 * 拦截器
 */
@Aspect
@Component
public class TestInterceptorAspect {

    static Logger logger = LoggerFactory.getLogger(TestInterceptorAspect.class);
    //ThreadLocal 维护变量 避免同步
    //ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
    ThreadLocal<Long> startTime = new ThreadLocal<>();// 开始时间

    /**
     * map1存放方法被调用的次数O
     */
    ThreadLocal<Map<String, Long>> map1 = new ThreadLocal<>();

    /**
     * map2存放方法总耗时
     */
    ThreadLocal<Map<String, Long>> map2 = new ThreadLocal<>();

    /**
     * 定义一个切入点. 解释下：
     * <p>
     * ~ 第一个 * 代表任意修饰符及任意返回值. ~ 第二个 * 定义在web包或者子包 ~ 第三个 * 任意方法 ~ .. 匹配任意数量的参数.
     */
    static final String pCutStr = "execution(* com.hq.*..*.*(..))";

    @Pointcut(value = pCutStr)
    public void logPointcut() {
    }

    @Around("logPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {


        //初始化 一次
        if (map1.get() == null) {
            map1.set(new HashMap<>());

        }

        if (map2.get() == null) {
            map2.set(new HashMap<>());
        }

        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            if (result == null) {
                //如果切到了 没有返回类型的void方法，这里直接返回
                return null;
            }
            long end = System.currentTimeMillis();

            logger.info("===================");
            String tragetClassName = joinPoint.getSignature().getDeclaringTypeName();
            String MethodName = joinPoint.getSignature().getName();

            // 参数
            Object[] args = joinPoint.getArgs();
            int argsSize = args == null ? 0 : args.length;
            String argsTypes = "";
            String typeStr = joinPoint.getSignature().getDeclaringType().toString().split(" ")[0];
            String returnType = joinPoint.getSignature().toString().split(" ")[0];
            logger.info("类/接口:" + tragetClassName + "(" + typeStr + ")");
            logger.info("方法:" + MethodName);
            logger.info("参数个数:" + argsSize);
            logger.info("返回类型:" + returnType);
            if (argsSize > 0) {
                // 拿到参数的类型
                for (Object object : args) {
                    //有些参数直接传null
                    argsTypes += (object == null ? "null" : object.getClass().getTypeName().toString() + " ");
                }
                logger.info("参数类型：" + argsTypes);
            }

            Long total = end - start;
            logger.info("耗时: " + total + " ms!");
            String classPath = tragetClassName + "." + MethodName;

            if (map1.get().containsKey(classPath)) {
                Long count = map1.get().get(classPath);
                //先移除，在增加
                map1.get().remove(classPath);
                map1.get().put(classPath, count + 1);

                count = map2.get().get(classPath);
                map2.get().remove(classPath);
                map2.get().put(classPath, count + total);
            } else {

                map1.get().put(classPath, 1L);
                map2.get().put(classPath, total);
            }

            return result;

        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            logger.info("====around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : "
                    + e.getMessage());
            throw e;
        }

    }

    /**
     * 对Controller下面的方法执行前进行切入，初始化开始时间
     *
     * @param jp
     */
    @Before(value = "execution(* com.hq.*.api.controller.*.*(..))")
    public void beforMehhod(JoinPoint jp) {
        startTime.set(System.currentTimeMillis());
    }

    /**
     * 对Controller下面的方法执行后进行切入，统计方法执行的次数和耗时情况
     * 注意，这里的执行方法统计的数据不止包含Controller下面的方法，也包括环绕切入的所有方法的统计信息
     *
     * @param jp
     */
    @AfterReturning(value = "execution(* com.hq.*.api.controller.*.*(..))")
    public void afterMehhod(JoinPoint jp) {
        long end = System.currentTimeMillis();
        long total = end - startTime.get();
        String methodName = jp.getSignature().getName();
        logger.info("连接点方法为：" + methodName + ",执行总耗时为：" + total + "ms");

        //重新new一个map
        Map<String, Long> map = new HashMap<>();

        //从map2中将最后的 连接点方法给移除了，替换成最终的，避免连接点方法多次进行叠加计算
        //由于map2受ThreadLocal的保护，这里不支持remove，因此，需要单开一个map进行数据交接
        for (Map.Entry<String, Long> entry : map2.get().entrySet()) {
            if (entry.getKey().equals(methodName)) {
                map.put(methodName, total);
            } else {
                map.put(entry.getKey(), entry.getValue());
            }
        }

        Map<String, Long> totalMap = new HashMap<>();
        totalMap.put("dao", 0L);
        totalMap.put("service", 0L);
        totalMap.put("controller", 0L);
        String daoPattern = "com.hq.*.core.dao.*";
        String servicePattern = "com.hq.*.core.service.*";
        String controllerPattern = "com.hq.*.api.controller.*";
        for (Map.Entry<String, Long> entry : map1.get().entrySet()) {
            for (Map.Entry<String, Long> entry2 : map.entrySet()) {
                if (entry.getKey().equals(entry2.getKey())) {
                    System.err.println(entry.getKey() + ",被调用次数：" + entry.getValue() + ",综合耗时：" + entry2.getValue() + "ms");

                    if (entry.getKey().contains("com.baomidou.mybatisplus.mapper.BaseMapper") || Pattern.matches(daoPattern, entry.getKey())) {
                        totalMap.put("dao", (totalMap.get("dao") + entry2.getValue()));
                    } else if (Pattern.matches(servicePattern, entry.getKey())) {
                        totalMap.put("service", (totalMap.get("service") + entry2.getValue()));
                    } else if (Pattern.matches(controllerPattern, entry.getKey())) {
                        totalMap.put("controller", (totalMap.get("controller") + entry2.getValue()));
                    }
                }
            }
        }

        System.err.println("耗时汇总===================");
        System.err.println("controller层耗时：" + (totalMap.get("controller") - totalMap.get("service") > 0 ? (totalMap.get("controller") - totalMap.get("service") + "ms") : "0ms"));
        System.err.println("service层耗时：" + (totalMap.get("service") - totalMap.get("dao") > 0 ? (totalMap.get("service") - totalMap.get("dao") + "ms") : "0ms"));
        System.err.println("dao层耗时：" + (totalMap.get("dao") + "ms"));
        System.err.println("总耗时：" + (totalMap.get("controller") + "ms"));
    }

}
