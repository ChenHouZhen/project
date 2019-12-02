package com.chenhz.common.demo.wrap;

import java.math.BigDecimal;

public class Float_1 {

    public static void main(String[] args) {
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        System.out.println(a);// 0.100000024
        System.out.println(b);// 0.099999964
        System.out.println(a == b);// false

        // 解决方法
        BigDecimal ba = new BigDecimal("1.0");
        BigDecimal bb = new BigDecimal("0.9");
        BigDecimal c = new BigDecimal("0.8");
        BigDecimal x = ba.subtract(bb);// 0.1
        BigDecimal y = bb.subtract(c);// 0.1
        System.out.println(x.equals(y));// true
    }
}
