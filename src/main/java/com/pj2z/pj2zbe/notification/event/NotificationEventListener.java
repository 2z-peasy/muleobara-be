package com.pj2z.pj2zbe.notification.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationEventListener {

    @Async
    @EventListener
    public void handle(NotificationEvent event) {

    }
}
