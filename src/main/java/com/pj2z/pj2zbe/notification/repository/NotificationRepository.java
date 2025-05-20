package com.pj2z.pj2zbe.notification.repository;

import com.pj2z.pj2zbe.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
