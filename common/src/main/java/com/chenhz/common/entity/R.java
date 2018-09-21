package com.chenhz.common.entity;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", 200);
        put("msg", "success");

    }

    public static R error() {
        return error(500, "系统异常");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", map);
        R r = new R();
        r.putAll(jsonObject);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        if (!(key.equals("code") || key.equals("msg"))) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(key, value);
            key = "data";
            value = jsonObject;
        }
        super.put(key, value);
        return this;
    }

    public static void main(String[] args) {
        System.out.println("ok :"+JSON.toJSON(R.ok()));
        System.out.println("error :"+JSON.toJSON(R.error()));
        Map<String,Object> r = new HashMap<>();
        r.put("name","小小");
        r.put("age",12);
        System.out.println("map :"+JSON.toJSON(R.ok(r)));

        System.out.println("put : " +JSON.toJSON(R.ok().put("name","达达").put("age",14)));

    }


}
