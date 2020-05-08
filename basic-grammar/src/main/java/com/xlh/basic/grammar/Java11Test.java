package com.xlh.basic.grammar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java11Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        var abc = "111";
        System.out.println(abc);

//        var lambda = () -> System.out.println("Pity!");
        var myList = new ArrayList<Map<String, List<Integer>>>();

        var request = HttpRequest.newBuilder().uri(URI.create("https://www.baidu.com"))
                .GET()
                .build();
        var client = HttpClient.newHttpClient();

        /**
         * HttpClient 既可以使用异步，又可以使用同步的方式。同步请求会一直阻塞直至当前线程响应返回，
         * BodyHandlers 定义了response body 的类型，如string, byte-array 或者 file。
         */
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        // 异步方式为调用sendAsync，该方法通过返回CompletableFuture 来执行异步操作。
        request = HttpRequest.newBuilder().uri(URI.create("https://www.baidu.com"))
                .GET().build();

        client = HttpClient.newHttpClient();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
//        System.out.println();

        // POST
        request = HttpRequest.newBuilder()
                .uri(URI.create("https://postman-echo.com/post"))
                .header("Content-Type", "text/plain")
                .POST(HttpRequest.BodyPublishers.ofString("Hi there!"))
                .build();
        client = HttpClient.newHttpClient();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());      // 200

        // BASIC-AUTH
        request = HttpRequest.newBuilder()
                .uri(URI.create("https://postman-echo.com/basic-auth"))
                .build();
        client = HttpClient.newBuilder()
                .authenticator(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("postman", "password".toCharArray());
                    }
                })
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());      // 200

        var list = List.of("A", "B", "C");
        var copy = List.copyOf(list);
        System.out.println(list == copy);   // true

        list = new ArrayList<String>();
        copy = List.copyOf(list);
        System.out.println(list == copy);   // false

        var map = Map.of("A", 1, "B", 2);
        System.out.println(map);    // {B=2, A=1}

        // Streams
        Stream.ofNullable(null).count();

        Stream.of(1, 2, 3, 2, 1)
                .dropWhile(n -> n < 3)
                .collect(Collectors.toList());  // [3, 2, 1]

        Stream.of(1, 2, 3, 2, 1)
                .takeWhile(n -> n < 3)
                .collect(Collectors.toList());  // [1, 2]

        // Strings
        " ".isBlank();                // true
        " Foo Bar ".strip();          // "Foo Bar"
        " Foo Bar ".stripTrailing();  // " Foo Bar"
        " Foo Bar ".stripLeading();   // "Foo Bar "
        "Java".repeat(3);             // "JavaJavaJava"
        "A\nB\nC".lines().count();    // 3

        // InputStreams
        var classLoader = ClassLoader.getSystemClassLoader();
        var inputStream = classLoader.getResourceAsStream("myFile.txt");
        var tempFile = File.createTempFile("myFileCopy", "txt");
        try (var outputStream = new FileOutputStream(tempFile)) {
            inputStream.transferTo(outputStream);
        }

    }
}
