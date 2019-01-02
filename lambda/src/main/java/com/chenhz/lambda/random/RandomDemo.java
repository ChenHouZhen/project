package com.chenhz.lambda.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomDemo {

    private static final List<Integer> VALID_PWD_CHARS = new ArrayList<>();

    static {
        // 0-9
        IntStream.rangeClosed('0','9').forEach(VALID_PWD_CHARS::add);
        // a-z
        IntStream.rangeClosed('a','z').forEach(VALID_PWD_CHARS::add);

    }

    public static void main(String[] args) {
        int passwordLength = 8;
        System.out.println( "---- 生成密码 ----" );
        Random random = new Random();
        for (int i = 0 ; i<5 ; i++){
            random.ints(passwordLength, 0 ,VALID_PWD_CHARS.size())
                    .map(VALID_PWD_CHARS::get)
                    .forEach(s -> System.out.print((char) s));
            System.out.println();
        }
    }

}
