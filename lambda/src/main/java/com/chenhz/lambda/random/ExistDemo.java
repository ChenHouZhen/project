package com.chenhz.lambda.random;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExistDemo {

    static List<Book> listObj;

    static {
        listObj = new ArrayList<>();
        {
            listObj.add(new Book("Core Java", 200));
            listObj.add(new Book("Core Java", 200));
            listObj.add(new Book("Core Java", 150));
            listObj.add(new Book("Learning Freemarker", 150));
            listObj.add(new Book("Spring MVC", 300));
            listObj.add(new Book("Spring MVC", 300));
        }
    }


    public static void main(String[] args) {

        Integer existPrice = 200;

        Integer noExistPrice = 999;

        List<Integer> prices = new ArrayList<>();
        {
            prices.add(200);
            prices.add(999);
            prices.add(300);
            prices.add(100000);
        }

        System.out.println("----------- 判断是否存在列表中 -----------------");

        System.out.println(listObj.stream().map(Book::getPrice).anyMatch(item -> item.equals(existPrice)));

        System.out.println(listObj.stream().map(Book::getPrice).anyMatch(item -> item.equals(noExistPrice)));


        System.out.println("----------- 交集 -----------------");

        // 交集 List<Integer> prices
        System.out.println(listObj.stream().map(Book::getPrice).filter(prices::contains).collect(Collectors.toList()));


        System.out.println("----------- 差集 -----------------");
        System.out.println("----------- listObject - prices -----------------");

        // 差集
        System.out.println(listObj.stream().map(Book::getPrice).filter(item -> !prices.contains(item)).collect(Collectors.toList()));

        System.out.println("----------- prices - listObject -----------------");
        List<Integer> objPrices = listObj.stream().map(Book::getPrice).collect(Collectors.toList());
        System.out.println(prices.stream().filter(item -> !objPrices.contains(item)).collect(Collectors.toList()));



        System.out.println("----------- 并集 -----------------");
        // 并集
        List<Integer> listAll = listObj.parallelStream().map(Book::getPrice).collect(Collectors.toList());
        List<Integer> listAll2 = prices.parallelStream().collect(Collectors.toList());
        listAll.addAll(listAll2);
        System.out.println(listAll);

        System.out.println("----------- 去重并集 -----------------");
        listAll  = listAll.stream().distinct().collect(Collectors.toList());
        System.out.println(listAll);

    }


}
