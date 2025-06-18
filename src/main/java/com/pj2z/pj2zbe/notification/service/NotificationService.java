package com.pj2z.pj2zbe.notification.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pj2z.pj2zbe.notification.entity.Notification;
import com.pj2z.pj2zbe.notification.entity.NotificationTemplate;
import com.pj2z.pj2zbe.notification.repository.NotificationRepository;
import com.pj2z.pj2zbe.notification.repository.NotificationTemplateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationTemplateRepository notificationTemplateRepository;

// 아직 구상중 하나도
    public void sendNotification(Long userId, String templateCode, Map<String, String> params) {
        // 예시 템플릿 로딩 (실제 구현에서는 Redis나 enum 또는 DB에서 가져올 수 있음)
        NotificationTemplate template = getTemplateByCode(templateCode);

        // 템플릿에 파라미터 적용
        String renderedMessage = renderTemplate(template, params);

        sendNotificationLog(userId,templateCode,params,renderedMessage);

    }

    private void sendNotificationLog(Long userId, String templateCode, Map<String, String> params, String renderedMessage){
        // 알림 저장 로직 (실제로는 Repository 사용)
        Notification notification = Notification.builder()
                .userId(userId)
                .templateCode(templateCode)
                .messageParams(convertToJson(params))
                .renderedMessage(renderedMessage)
                .build();

        notificationRepository.save(notification);
    }

    private NotificationTemplate getTemplateByCode(String templateCode) {
        NotificationTemplate template = notificationTemplateRepository.findByCode(templateCode)
                .orElseThrow(() -> new IllegalArgumentException("템플릿 코드가 존재하지 않습니다: " + templateCode));
        return template;

    }

    private String renderTemplate(NotificationTemplate template, Map<String, String> params) {
        String result = template.getMessageTemplate();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            result = result.replace("${" + entry.getKey() + "}", entry.getValue());
        }
        return result;
    }

    private String convertToJson(Map<String, String> params) {
        try {
            return new ObjectMapper().writeValueAsString(params);
        } catch (Exception e) {
            return "{}";
        }
    }
}
