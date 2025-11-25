package com.berru.app.cabbookingapplication.service.impl;

import com.berru.app.cabbookingapplication.entity.Booking;
import com.berru.app.cabbookingapplication.enums.CancelledBy;
import com.berru.app.cabbookingapplication.service.BookingNotificationService;
import com.berru.app.cabbookingapplication.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingNotificationServiceImpl implements BookingNotificationService {

    private final NotificationService notificationService;

    @Override
    public void notifyBookingCreated(Booking booking) {

        notificationService.sendEmail(
                booking.getUser().getEmail(),
                "Booking Created",
                "Your booking request is pending confirmation."
        );

        notificationService.sendPushNotification(
                booking.getDriver().getUser().getId(),
                "New Booking Request",
                "You have a new booking request from " + booking.getUser().getFirstName()
        );
    }

    @Override
    public void notifyBookingAccepted(Booking booking) {
        notificationService.sendPushNotification(
                booking.getUser().getId(),
                "Booking Confirmed",
                "Your booking has been accepted by your driver."
        );
    }

    @Override
    public void notifyBookingCompleted(Booking booking) {

        notificationService.sendEmail(
                booking.getUser().getEmail(),
                "Ride Completed",
                "Thank you for riding with us!"
        );

        notificationService.sendPushNotification(
                booking.getDriver().getUser().getId(),
                "Ride Completed",
                "You completed the ride."
        );
    }

    @Override
    public void notifyBookingCancelled(Booking booking, CancelledBy cancelledBy) {

        switch (cancelledBy) {
            case CUSTOMER -> notificationService.sendPushNotification(
                    booking.getDriver().getUser().getId(),
                    "Booking Cancelled",
                    "The customer cancelled the booking."
            );

            case DRIVER -> notificationService.sendPushNotification(
                    booking.getUser().getId(),
                    "Booking Cancelled",
                    "The driver cancelled the booking."
            );

            case ADMIN -> {
                notificationService.sendPushNotification(
                        booking.getUser().getId(),
                        "Booking Cancelled",
                        "Your booking was cancelled by an admin."
                );
                notificationService.sendPushNotification(
                        booking.getDriver().getUser().getId(),
                        "Booking Cancelled",
                        "This booking was cancelled by an admin."
                );
            }
        }
    }
}
