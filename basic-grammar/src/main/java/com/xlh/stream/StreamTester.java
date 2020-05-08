package com.xlh.stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Java 9 改进的 Stream API 添加了一些便利的方法，使流处理更容易，并使用收集器编写复杂的查询。
 * Java 9 为 Stream 新增了几个方法：dropWhile、takeWhile、ofNullable，为 iterate 方法新增了一个重载方法。
 */
public class StreamTester {
    public static void main(String[] args) {
        /**
         * takeWhile() 方法使用一个断言作为参数，返回给定 Stream 的子集直到断言语句第一次返回 false。
         * 如果第一个值不满足断言条件，将返回一个空的 Stream。
         * takeWhile() 方法在有序的 Stream 中，takeWhile 返回从开头开始的尽量多的元素；
         * 在无序的 Stream 中，takeWhile 返回从开头开始的符合 Predicate 要求的元素的子集。
         */
        System.out.println("takeWhile: ");
        Stream.of("a", "b", "c", "", "e", "f").takeWhile(s -> !s.isEmpty()).forEach(System.out::print);

        System.out.println();
        System.out.println("dropWhile: ");

        /**
         * dropWhile 方法和 takeWhile 作用相反的，使用一个断言作为参数，直到断言语句第一次返回 true 才返回给定 Stream 的子集。
         */
        Stream.of("a", "b", "c", "", "e", "f").dropWhile(s -> !s.isEmpty()).forEach(System.out::print);

        System.out.println();
        System.out.println("iterate: ");

        /**
         * 方法允许使用初始种子值创建顺序（可能是无限）流，并迭代应用指定的下一个方法。
         * 当指定的 hasNext 的 predicate 返回 false 时，迭代停止。
         */
        IntStream.iterate(3, x -> x < 10, x -> x + 3).forEach(System.out::println);

        System.out.println();
        System.out.println("ofNullable: ");

        long count = Stream.ofNullable(100).count();
        System.out.println(count);

        count = Stream.ofNullable(null).count();
        System.out.println(count);
    }
}
