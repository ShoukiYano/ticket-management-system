package com.example.ticketmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    // ホームページにアクセスしたときにチケットリストページにリダイレクトするGETリクエストを処理
    @GetMapping
    public String home() {
        return "redirect:/tickets"; // チケットリストページにリダイレクト
    }
}
