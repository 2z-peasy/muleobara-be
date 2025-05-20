package com.pj2z.pj2zbe.notification.entity;

import com.pj2z.pj2zbe.notification.entity.enums.NotificationCategory;
import com.pj2z.pj2zbe.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //굳이 user객채를 불러오지 않았다.
    @Column(name = "user_id", nullable = false)
    private Long userId;

    // 템플릿 코드 (예: "MISSION_COMPLETE")
    @Column(name = "template_code", length = 50)
    private String templateCode;

    @Column(name = "title", length = 25, nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private NotificationCategory category;

    // 파라미터를 JSON 형태로 저장 (ex: {"user_name":"dohi", "quantity":"5"})
    @Column(name = "message_params", columnDefinition = "JSON")
    private String messageParams;

    // 최종 사용자에게 보여줄 메시지 (템플릿을 조합한 결과)
    @Column(name = "rendered_message", columnDefinition = "TEXT")
    private String renderedMessage;

    @Column(name = "read_YN", nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationYN readYN = NotificationYN.N;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public enum NotificationYN{
        Y,N
    }
}
