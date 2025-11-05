package com.berru.app.cabbookingapplication.service;

public interface NotificationService {

    void sendEmail(String to, String subject, String body);

    void sendSms(String phoneNumber, String message);

    void sendPushNotification(Integer userId, String title, String message);
}