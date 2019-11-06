package com.chenhz.common.demo;

/**
 * 打印二进制存储的 float、double 类型
 */
public class Binary {

    public static void main(String[] args) {
        Float data = 0.75f;
        int b=Float.floatToIntBits(data);
        System.out.println(Integer.toBinaryString(b));
    }
}
