package com.chenhz.common.demo.wrap;

public class Integer_1 {

    public static void main(String[] args) {
        Integer x = 3; Integer y = 3;
        System.out.println(x == y);// true
        Integer a = new Integer(3);
        Integer b = new Integer(3);
        System.out.println(a == b);//false
        System.out.println(a.equals(b));//true
    }
}
