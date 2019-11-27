package com.chenhz.common.demo.gc;

//Exception in thread "main" java.lang.StackOverflowError

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StackOverFlow {

    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackOverFlow stack = new StackOverFlow();
        try {
            stack.stackLeak();
        }catch (Throwable e){
            log.error("stack length :"+stack.stackLength);
            throw e;
        }

    }
}
