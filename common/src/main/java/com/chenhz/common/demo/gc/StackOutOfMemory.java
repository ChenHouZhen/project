package com.chenhz.common.demo.gc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StackOutOfMemory {

    private void doStop(){
        while (true){

        }
    }

    void stackLeakByThread(){
        while (true){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    log.info(">>>>> 开启线程");
                    doStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        StackOutOfMemory stack = new StackOutOfMemory();
        stack.stackLeakByThread();
    }
}
