// ユーザークラスのファイル
package com.example.ticketmanagement.entity;

import jakarta.persistence.Entity; // JPAのエンティティを表す
import jakarta.persistence.GeneratedValue; // ID生成戦略を指定
import jakarta.persistence.GenerationType; // ID生成戦略のタイプ
import jakarta.persistence.Id; // 主キーを指定
import jakarta.persistence.Table; // テーブル名を指定
import lombok.Data; // ゲッター、セッター、その他を自動生成

@Entity // これはデータベースのテーブルに対応するクラス
@Table(name = "app_user") // テーブル名を「app_user」にする
@Data // 自動でゲッターやセッターを作る
public class User {
    @Id // 主キーを指定
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主キーを自動生成
    private Long id;

    private String username; // ユーザー名
    private String password; // パスワード
    private String role; // ユーザーの役割（例えば、管理者か普通のユーザーか）
}