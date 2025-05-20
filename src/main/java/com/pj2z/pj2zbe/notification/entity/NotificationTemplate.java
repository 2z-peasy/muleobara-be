package com.pj2z.pj2zbe.notification.entity;

import com.pj2z.pj2zbe.notification.entity.enums.NotificationCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notification_templates")
@Getter
@Setter
public class NotificationTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    private NotificationCategory category;

    @Column(name = "title", length = 25, nullable = false)
    private String title;

    @Column(name = "message_template", columnDefinition = "TEXT", nullable = false)
    private String messageTemplate;

    @Column(name = "link_to")
    private String linkTo;
}
