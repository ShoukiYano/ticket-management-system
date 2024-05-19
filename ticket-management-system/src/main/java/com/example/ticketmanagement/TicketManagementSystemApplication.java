package com.example.ticketmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // このアノテーションは、Spring Bootのアプリケーションとして動くようにするためのもの
public class TicketManagementSystemApplication {

    // メインメソッド：アプリケーションをスタートする
    public static void main(String[] args) {
        SpringApplication.run(TicketManagementSystemApplication.class, args); // Spring Bootのアプリケーションを起動する
    }
}
