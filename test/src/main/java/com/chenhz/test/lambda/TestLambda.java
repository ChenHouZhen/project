package com.chenhz.test.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestLambda {

    private static final List<Integer> VALID_PWD_CHARS = new ArrayList<>();

    private static List<Integer> intVal;

    static {
        IntStream.rangeClosed('0','9').forEach(VALID_PWD_CHARS::add);
        IntStream.rangeClosed('a','z').forEach(VALID_PWD_CHARS::add);

        intVal = Arrays.asList(1,2,3,4,5);

    }



    public static void main(String[] args) {

        //求平均数
        Double result = intVal.stream().collect(Collectors.averagingInt(i -> i));
        System.out.println("ave :"+result);



    }
}
