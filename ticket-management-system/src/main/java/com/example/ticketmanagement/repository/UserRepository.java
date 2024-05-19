// ユーザーリポジトリのファイル
package com.example.ticketmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository; // JPAリポジトリを使うためのインポート

import com.example.ticketmanagement.entity.User; // ユーザーエンティティを使うためのインポート

// ユーザーを扱うためのリポジトリインターフェース
public interface UserRepository extends JpaRepository<User, Long> {
    // ユーザー名でユーザーを探すメソッド
    User findByUsername(String username);
}