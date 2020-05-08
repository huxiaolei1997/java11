package com.xlh.optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalTester {
    public static void main(String[] args) {
        // stream() 方法
        List<Optional<String>> list = Arrays.asList(
            Optional.empty(),
            Optional.of("A"),
            Optional.empty(),
            Optional.of("B")
        );

        List<String> filteredList = list.stream().flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
                .collect(Collectors.toList());

        List<String> fillteredListJava9 = list.stream().flatMap(Optional::stream).collect(Collectors.toList());

        System.out.println(filteredList);
        System.out.println(fillteredListJava9);

        // ifPresentOrElse() 方法
        Optional<Integer> optional = Optional.of(1);

        optional.ifPresentOrElse( x -> System.out.println("Value: " + x),() ->
                System.out.println("Not Present."));

        optional = Optional.empty();

        optional.ifPresentOrElse( x -> System.out.println("Value: " + x),() ->
                System.out.println("Not Present."));


        // or() 方法
        Optional<String> optional1 = Optional.of("Mahesh");
        Supplier<Optional<String>> supplierString = () -> Optional.of("Not Present");
        optional1 = optional1.or( supplierString);
        optional1.ifPresent( x -> System.out.println("Value: " + x));
        optional1 = Optional.empty();
        optional1 = optional1.or( supplierString);
        optional1.ifPresent( x -> System.out.println("Value: " + x));
    }
}
