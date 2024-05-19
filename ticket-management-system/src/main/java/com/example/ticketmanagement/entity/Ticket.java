// チケットクラスのファイル
package com.example.ticketmanagement.entity;

import jakarta.persistence.Entity; // JPAのエンティティを表す
import jakarta.persistence.GeneratedValue; // ID生成戦略を指定
import jakarta.persistence.GenerationType; // ID生成戦略のタイプ
import jakarta.persistence.Id; // 主キーを指定
import jakarta.persistence.ManyToOne; // 多対一の関係を指定
import lombok.Data; // ゲッター、セッター、その他を自動生成

@Entity // これはデータベースのテーブルに対応するクラス
@Data // 自動でゲッターやセッターを作る
public class Ticket {
    @Id // 主キーを指定
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主キーを自動生成
    private Long id;

    private String title; // チケットのタイトル
    private String description; // チケットの説明
    private String status; // チケットのステータス（例えば、「未処理」、「進行中」、「完了」など）
    private String assignedTo; // チケットが誰に割り当てられているか

    @ManyToOne // 多対一の関係を指定（1人のユーザーが複数のチケットを持てる）
    private User owner; // チケットの所有者（ユーザー）
}