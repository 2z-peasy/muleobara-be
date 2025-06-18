package com.pj2z.pj2zbe.notification.event;

import com.pj2z.pj2zbe.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationEventListener {
    private final NotificationService notificationService;
/*
사용예시
    @Autowired
    ApplicationEventPublisher eventPublisher;
    eventPublisher.publishEvent(new NotificationEvent(user.getId(), "MISSION_REFRESH"));

 */
    @Async
    @EventListener
    public void handle(NotificationEvent event) {
        try {
            notificationService.sendNotification(event.getReceiverId(), event.getTemplateCode(), event.getParams());
        } catch (IllegalArgumentException e) {
            log.warn("알림 전송실패: {}", e.getMessage());
        }
    }
}
