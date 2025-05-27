package com.pj2z.pj2zbe.notification.entity;

import com.pj2z.pj2zbe.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "device_token")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeviceToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "device_token", nullable = false)
    private String deviceToken;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private DeviceToken(User user, String deviceToken) {
        this.user = user;
        this.deviceToken = deviceToken;
        this.createdAt = LocalDateTime.now();
    }

    public static DeviceToken of(User user, String deviceToken) {
        DeviceToken token = new DeviceToken(user, deviceToken);
        //유효성검증필요
        return token;
    }
}
