package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void sendEmail(String to, String subject, String body) {
        // JavaMailSender
        log.info("EMAIL SENT → to: {}, subject: {}, body: {}", to, subject, body);
    }

    @Override
    public void sendSms(String phoneNumber, String message) {
        // Twilio veya başka SMS sağlayıcı
        log.info("SMS SENT → phone: {}, message: {}", phoneNumber, message);
    }

    @Override
    public void sendPushNotification(Integer userId, String title, String message) {
        // Firebase, OneSignal veya WebPush
        log.info("PUSH NOTIFICATION SENT → userId: {}, title: {}, message: {}", userId, title, message);
    }
}
