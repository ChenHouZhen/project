package com.chenhz.test.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class TestRandom {

    private static final List<Integer> VALID_PWD_CHARS = new ArrayList<>();


    static {
        IntStream.rangeClosed('0','9').forEach(VALID_PWD_CHARS::add);
        IntStream.rangeClosed('a','z').forEach(VALID_PWD_CHARS::add);
    }





    public static void main(String[] args) {

        // 包含最后的值
        IntStream.rangeClosed(0,5).forEach(System.out::println);

        Line.print();

        // 不包含最后的值
        IntStream.range(0,5).forEach(System.out::println);


        Line.print();

        int passwordLength = 8;
        Random random = new Random();
        for (int i= 0 ;i<5 ;i++){
            random.ints(passwordLength,0,VALID_PWD_CHARS.size())
                    .map(VALID_PWD_CHARS::get)
                    .forEach(s -> System.out.print((char)s));
            System.out.println();
        }

        System.out.println();
        random.ints(10 ,1,100).forEach(s -> System.out.print( s+"  "));


        Line.print();





    }




}
