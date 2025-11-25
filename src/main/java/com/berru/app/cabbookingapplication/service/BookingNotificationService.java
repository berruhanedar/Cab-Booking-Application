package com.berru.app.cabbookingapplication.service;

import com.berru.app.cabbookingapplication.entity.Booking;
import com.berru.app.cabbookingapplication.enums.CancelledBy;

public interface BookingNotificationService {

    void notifyBookingCreated(Booking booking);

    void notifyBookingAccepted(Booking booking);

    void notifyBookingCompleted(Booking booking);

    void notifyBookingCancelled(Booking booking, CancelledBy cancelledBy);
}
