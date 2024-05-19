package com.example.ticketmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // ログインページを表示するためのGETリクエストを処理
    @GetMapping("/login")
    public String login() {
        return "login"; // login.htmlを表示
    }
}
