// カスタムユーザーディテールサービスのファイル
package com.example.ticketmanagement.service;

import java.util.ArrayList; // リストを使うためのインポート

import org.springframework.beans.factory.annotation.Autowired; // 自動的に依存関係を注入するためのインポート
import org.springframework.security.core.userdetails.UserDetails; // ユーザー詳細を使うためのインポート
import org.springframework.security.core.userdetails.UserDetailsService; // ユーザーディテールサービスを使うためのインポート
import org.springframework.security.core.userdetails.UsernameNotFoundException; // ユーザーが見つからない場合の例外を使うためのインポート
import org.springframework.stereotype.Service; // サービスクラスであることを示すインポート

import com.example.ticketmanagement.entity.User; // ユーザーエンティティを使うためのインポート
import com.example.ticketmanagement.repository.UserRepository; // ユーザーリポジトリを使うためのインポート

@Service // このクラスはサービスクラスであることを示すアノテーション
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired // 自動的に依存関係を注入するアノテーション
    private UserRepository userRepository;

    // ユーザー名でユーザー詳細をロードするメソッド
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username); // ユーザー名でユーザーを見つける
        if (user == null) { // ユーザーが見つからなかった場合
            throw new UsernameNotFoundException("User not found"); // ユーザーが見つからない例外を投げる
        }
        // ユーザー詳細を作成して返す
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}