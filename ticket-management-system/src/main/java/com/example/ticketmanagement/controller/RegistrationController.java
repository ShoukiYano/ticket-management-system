package com.example.ticketmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ticketmanagement.entity.User;
import com.example.ticketmanagement.repository.UserRepository;

@Controller
public class RegistrationController {

    // UserRepositoryを自動的に注入する
    @Autowired
    private UserRepository userRepository;

    // PasswordEncoderを自動的に注入する
    @Autowired
    private PasswordEncoder passwordEncoder;

    // ユーザー登録フォームを表示するためのGETリクエストを処理
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // モデルに新しいUserオブジェクトを追加
        return "register"; // register.htmlを表示
    }

    // ユーザーを登録するためのPOSTリクエストを処理
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // パスワードをエンコード
        user.setRole("USER"); // ユーザーの役割を設定
        userRepository.save(user); // ユーザーを保存
        return "redirect:/login"; // ログインページにリダイレクト
    }
}
