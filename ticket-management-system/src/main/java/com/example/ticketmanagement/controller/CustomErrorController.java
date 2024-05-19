package com.example.ticketmanagement.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // エラーメッセージを設定する場合
        request.setAttribute("errorMessage", "不明なエラーが発生しました。");
        return "error";
    }
}
