package com.xlh.diamondoperator;


/**
 * 钻石操作符是在 java 7 中引入的，可以让代码更易读，但它不能用于匿名的内部类。
 * 在 java 9 中， 它可以与匿名的内部类一起使用，从而提高代码的可读性。
 */
public class DiamondOperator {
    public static void main(String[] args) {
        /**
         * java9 之前写法
         */
//        Handler<Integer> intHandler = new Handler<Integer>(1) {
//            @Override
//            public void handle() {
//                System.out.println(content);
//            }
//        };
//        intHandler.handle();
//        Handler<? extends Number> intHandler1 = new Handler<Number>(2) {
//            @Override
//            public void handle() {
//                System.out.println(content);
//            }
//        };
//        intHandler1.handle();
//        Handler<?> handler = new Handler<Object>("test") {
//            @Override
//            public void handle() {
//                System.out.println(content);
//            }
//        };
//        handler.handle();

        /**
         * java9写法
         */
        Handler<Integer> intHandler = new Handler<>(1) {
            @Override
            public void handle() {
                System.out.println(content);
            }
        };
        intHandler.handle();
        Handler<? extends Number> intHandler1 = new Handler<>(2) {
            @Override
            public void handle() {
                System.out.println(content);
            }
        };
        intHandler1.handle();
        Handler<?> handler = new Handler<>("test") {
            @Override
            public void handle() {
                System.out.println(content);
            }
        };

        handler.handle();
    }
}

abstract class Handler<T> {
    public T content;

    public Handler(T content) {
        this.content = content;
    }

    abstract void handle();
}
