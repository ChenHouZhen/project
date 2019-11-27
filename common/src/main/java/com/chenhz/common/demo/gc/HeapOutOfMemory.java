package com.chenhz.common.demo.gc;

import com.chenhz.common.entity.User;

import java.util.ArrayList;
import java.util.List;

public class HeapOutOfMemory {

    //Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded


    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        while (true){
            users.add(new User());
        }
    }
}
