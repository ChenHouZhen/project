package com.chenhz.tpos.controller;

import com.chenhz.common.entity.R;
import com.chenhz.tpos.redis.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author CHZ
 * @create 2018/9/20
 */
@RestController
@RequestMapping("/redis")
@Api(tags = "Redis 操作接口")
public class RedisController {

    private ValueRedisUtils value;

    private HashRedisUtils hash;

    private ListRedisUtils list;

    private SetRedisUtils set;

    private ZSetRedisUtils zSet;

    @Autowired
    public void setValue(ValueRedisUtils value) {
        this.value = value;
    }
    @Autowired
    public void setHash(HashRedisUtils hash) {
        this.hash = hash;
    }
    @Autowired
    public void setList(ListRedisUtils list) {
        this.list = list;
    }
    @Autowired
    public void setSet(SetRedisUtils set) {
        this.set = set;
    }
    @Autowired
    public ZSetRedisUtils getzSet() {
        return zSet;
    }
    //-------------------- Value -----------------------------

    @PostMapping("V/save/val")
    @ApiOperation("Redis 保存字面值")
    public R saveVal(String key,String val){
        value.set(key,val);
        return R.ok();
    }

    @GetMapping("V/get/{k}")
    @ApiOperation("Redis 获取字面值")
    public R getValByK(@PathVariable String k){
        Object o = value.get(k);
        return R.ok().put("val",o);
    }

    @PostMapping("V/save/val/expire")
    @ApiOperation("Redis 测试过期时长")
    public R saveVE(String key,String val,@RequestParam(value = "expire",defaultValue = "1000",required = false) long expire){
        value.set(key,val,expire);
        return R.ok();
    }

    @PostMapping("V/incr/{key}/{increment}")
    @ApiOperation("Redis 测试步长增长")
    public R incr(@PathVariable("key") String key,@PathVariable(name = "increment") long val){
        long result = value.incr(key,val);
        return R.ok().put("val",result);
    }

    @PostMapping("V/expire/{key}")
    @ApiOperation("过期失效")
    public R expire(@PathVariable("key") String key){
        boolean result = value.expire(key);
        if (!result){
            return R.error("键不存在");
        }
        return R.ok();
    }

    @GetMapping("V/exists/{key}")
    @ApiOperation("判断是否存在")
    public R exists(@PathVariable("key") String key){
        boolean result = value.exists(key);
        return R.ok().put("exists", result);
    }


    //-------------------- Map -----------------------------

    @PostMapping("M/save/{k}/{mk}/{val}")
    @ApiOperation("Hash 新增")
    public R saveMVal(@PathVariable("k") String key,@PathVariable("mk") String mkey ,@PathVariable("val") String val){
        hash.put(key,mkey,val);
        return R.ok();
    }

    @GetMapping("M/get/{k}/{mk}")
    @ApiOperation("Hash 获取")
    public R getMByKey(@PathVariable("k") String key,@PathVariable("mk") String mkey ){
        Object val = hash.get(key,mkey);
        return R.ok().put("val",val);
    }

    @GetMapping("M/get/K/{k}")
    @ApiOperation("Hash 获取全部KEY")
    public R getMKeys(@PathVariable("k") String key){
        Set<String> result = hash.keys(key);
        return R.ok().put("keys",result);
    }

    @GetMapping("M/get/V/{k}")
    @ApiOperation("Hash 获取全部VALUE")
    public R getMValues(@PathVariable("k") String key){
        List<Object> result = hash.values(key);
        return R.ok().put("keys",result);
    }

    @GetMapping("M/entries/{k}")
    @ApiOperation("Hash 获取全部VALUE")
    public R getMkkeyValues(@PathVariable("k") String key){
        Map<String,Object> result = hash.entries(key);
        return R.ok().put("keys",result);
    }

    @GetMapping("M/exists/{k}/{mk}")
    @ApiOperation("Hash 获取")
    public R getMexists(@PathVariable("k") String key,@PathVariable("mk") String mkey){
        boolean result = hash.hasKey(key,mkey);
        return R.ok().put("exists",result);
    }
}
