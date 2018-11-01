package com.chenhz.common.utils;

import java.util.UUID;

public class UUIDGenerate {

    public static String create(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static void main(String[] args) {
        System.out.println(create());
    }
}
