package com.chenhz.lambda.random;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class StatisticsDemo {


    public static void main(String[] args) {
        System.out.println("------DoubleSummaryStatistics------");

        DoubleSummaryStatistics dstats = DoubleStream.of(5.33d,2.23d,5.32d,2.32d,3.51d)
                .collect(DoubleSummaryStatistics::new
                        ,DoubleSummaryStatistics::accept
                        ,DoubleSummaryStatistics::combine);

        System.out.println("MAX："+dstats.getMax()+"  MIN:"+dstats.getMin());

        System.out.println("COUNT："+dstats.getCount() +" SUM:"+dstats.getSum());

        System.out.println("AVG:"+dstats.getAverage());


        System.out.println("-----------DoubleSummaryStatistics-------------");

        List<Rectangle> list = Rectangle.getRectangle();

        DoubleSummaryStatistics ds = list.stream()
                .collect(Collectors.summarizingDouble(Rectangle::getWidth));

        System.out.println("MAX："+ds.getMax()+"  MIN:"+ds.getMin());

        System.out.println("COUNT："+ds.getCount() +" SUM:"+ds.getSum());

        System.out.println("AVG:"+ds.getAverage());

        System.out.println("--------------IntSummaryStatistics-------------------");

        IntSummaryStatistics is = list.stream()
                .collect(Collectors.summarizingInt(Rectangle::getLength));


        System.out.println("MAX："+is.getMax()+"  MIN:"+is.getMin());

        System.out.println("COUNT："+is.getCount() +" SUM:"+is.getSum());

        System.out.println("AVG:"+is.getAverage());

        System.out.println("---------------LongSummaryStatistics------------------");

        LongSummaryStatistics ls = list.stream()
                .collect(Collectors.summarizingLong(Rectangle::getId));

        System.out.println("Max:"+ls.getMax()+", Min:"+ls.getMin());
        System.out.println("Count:"+ls.getCount()+", Sum:"+ls.getSum());
        System.out.println("Average:"+ls.getAverage());

    }
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Rectangle{
    private long id;

    private int length;

    private double width;

    public static List<Rectangle> getRectangle() {
        List<Rectangle> list = new ArrayList<>();
        list.add(new Rectangle(100l, 213, 114.23d));
        list.add(new Rectangle(200l, 233, 134.34d));
        list.add(new Rectangle(300l, 243, 144.32d));
        list.add(new Rectangle(400l, 253, 154.12d));
        return list;
    }

}