package com.pj2z.pj2zbe.notification.Client;

import com.pj2z.pj2zbe.notification.entity.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class NotificationClient {

    public  boolean sendNotification(Notification notification){
        try {
            log.info("send notification");
            notification.setSentAt(LocalDateTime.now());
            return true;
        }catch (Exception e){
            log.error("send notification error");
            return false;
        }
    }
}
