package com.xlh.trywithresources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * try-with-resources 是 JDK 7 中一个新的异常处理机制，
 * 它能够很容易地关闭在 try-catch 语句块中使用的资源。
 * 所谓的资源（resource）是指在程序完成后，必须关闭的对象。
 * try-with-resources 语句确保了每个资源在语句结束时关闭。
 * 所有实现了 java.lang.AutoCloseable 接口（其中，它包括实现了 java.io.Closeable 的所有对象），
 * 可以使用作为资源。try-with-resources 声明在 JDK 9 已得到改进。
 * 如果你已经有一个资源是 final 或等效于 final 变量,
 * 您可以在 try-with-resources 语句中使用该变量，而无需在 try-with-resources 语句中声明一个新变量。
 */
public class TryWithResourcesTester {
    /**
     * java9之前写法
     */
//    public static void main(String[] args) throws IOException {
//        System.out.println(readData("test"));
//    }
//
//    static String readData(String message) throws IOException {
//        Reader inputString = new StringReader(message);
//        BufferedReader br = new BufferedReader(inputString);
//        try (BufferedReader br1 = br) {
//            return br1.readLine();
//        }
//    }

    /**
     * java9 写法
     */
    public static void main(String[] args) throws IOException {
        System.out.println(readData("test"));
    }

    static String readData(String message) throws IOException {
        Reader inputString = new StringReader(message);
        BufferedReader br = new BufferedReader(inputString);
        try (br) {
            return br.readLine();
        }
    }
}
