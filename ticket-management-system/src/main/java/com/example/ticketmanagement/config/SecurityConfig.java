package com.example.ticketmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.ticketmanagement.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // ユーザー情報を管理するサービス
    private final CustomUserDetailsService userDetailsService;

    // コンストラクタでサービスを受け取る
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // セキュリティの設定をするメソッド
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/register", "/login", "/css/**", "/js/**").permitAll() // これらのURLは誰でもアクセスOK
                .anyRequest().authenticated() // 他のURLはログインしないとダメ
            )
            .formLogin((form) -> form
                .loginPage("/login") // ログインページのURL
                .defaultSuccessUrl("/tickets", true) // ログイン成功後に行くページ
                .permitAll() // ログインページは誰でもアクセスOK
            )
            .logout((logout) -> logout
                .logoutUrl("/logout") // ログアウトのURL
                .logoutSuccessUrl("/login?logout") // ログアウトした後に行くページ
                .permitAll() // ログアウトも誰でもOK
            );
        return http.build(); // 設定をまとめる
    }

    // ログインのやり方を設定するメソッド
    @Bean
    public AuthenticationManager customAuthenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // ユーザー情報とパスワードの暗号化を設定
        return authManagerBuilder.build(); // 設定をまとめる
    }

    // パスワードを暗号化するやつを作るメソッド
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // パスワードを暗号化するやつ
    }
}
