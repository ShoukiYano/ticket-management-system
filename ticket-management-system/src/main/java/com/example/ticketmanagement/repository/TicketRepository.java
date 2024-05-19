// チケットリポジトリのファイル
package com.example.ticketmanagement.repository;

import java.util.List; // リストを使うためのインポート

import org.springframework.data.jpa.repository.JpaRepository; // JPAリポジトリを使うためのインポート

import com.example.ticketmanagement.entity.Ticket; // チケットエンティティを使うためのインポート
import com.example.ticketmanagement.entity.User; // ユーザーエンティティを使うためのインポート

// チケットを扱うためのリポジトリインターフェース
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // 特定のユーザーのチケットを探すメソッド
    List<Ticket> findByOwner(User owner);
}
