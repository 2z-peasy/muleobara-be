package com.pj2z.pj2zbe.notification.event;

import lombok.Getter;

import java.util.Map;

@Getter
public class NotificationEvent {
    private final Long receiverId;
    private final String templateCode;
    private final Map<String, String> params;

    public NotificationEvent(Long receiverId, String templateCode, Map<String, String> params) {
        this.receiverId = receiverId;
        this.templateCode = templateCode;
        this.params = params;
    }

}
