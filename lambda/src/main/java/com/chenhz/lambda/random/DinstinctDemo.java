package com.chenhz.lambda.random;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DinstinctDemo {


    public static void main(String[] args) {

        System.out.println("---- 字符串去重 ---- ");

        List<String> list = Arrays.asList("AA","BB","CC","BB","AA","AA");

        long len = list.stream().distinct().count();

        System.out.println("去重后字符串的长度为："+len);

        String output = list.stream().distinct().collect(Collectors.joining(","));

        System.out.println(output);


        System.out.println("----  对象去重----- ");

        List<Book> listObj = new ArrayList<>();
        {
            listObj.add(new Book("Core Java", 200));
            listObj.add(new Book("Core Java", 200));
            listObj.add(new Book("Core Java", 150));
            listObj.add(new Book("Learning Freemarker", 150));
            listObj.add(new Book("Spring MVC", 300));
            listObj.add(new Book("Spring MVC", 300));
        }

        long lenObj = listObj.stream().distinct().count();

        System.out.println("去重后Book对象的长度为："+lenObj);

        listObj.stream().distinct().forEach(b -> System.out.println(b.getName() +" ,"+b.getPrice()));



        System.out.println("----  按对象属性去重----- ");

        long lenObj2 = listObj.stream().distinct().count();

        System.out.println("按对象属性去重后Book对象的长度为："+lenObj2);

        listObj.stream().filter(distinctByKey(b -> b.getName())).forEach(b -> System.out.println(b.getName() +" ,"+b.getPrice()));



    }

    static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Book{

    private String name;

    private int price;

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }

        final Book book = (Book) obj;
        if (this == book){
            return true;
        } else {
            return this.name.equals(book.name) && this.price == book.price;
        }
    }

    @Override
    public int hashCode() {
        int hashno = 7;
        hashno = 13 * hashno + (name == null ? 0 : name.hashCode());
        return hashno;
    }
}