package com.pj2z.pj2zbe.notification.event;

import com.pj2z.pj2zbe.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationEventListener {
    private final NotificationService notificationService;

    @Async
    @EventListener
    public void handle(NotificationEvent event) {
        notificationService.sendNotification(event.getReceiverId(), event.getTemplateCode(), event.getParams());
    }
}
