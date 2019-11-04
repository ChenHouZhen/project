package com.chenhz.lambda.random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class test {

    public static void main(String[] args) {

        String a = "3";

        String[] aa = a.split("");
        int len = aa.length;

        log.info("len ==> {}",len);
        int sum = 0;

        for (String aaa: aa) {
            len--;
            int ab = Integer.valueOf(aaa) * av(len);
            sum += ab;
        }
        System.out.println(sum);
    }

    private static int av(int v){
        int sum = 2;
        if (v == 0){
            return 1;
        }
        for (int i = 0 ;i<v-1;i++) {
            sum *= 2;
        }
        return sum;
    }


    private static String get(int sum){
        return "";
    }



}
