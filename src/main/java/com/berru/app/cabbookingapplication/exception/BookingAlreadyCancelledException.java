package com.berru.app.cabbookingapplication.exception;

import org.springframework.http.HttpStatus;
import java.util.List;

public class BookingAlreadyCancelledException extends BaseException {

    public BookingAlreadyCancelledException(String message) {
        super(message);
    }

    public BookingAlreadyCancelledException(String message, List<? extends ErrorDetailDto> errors) {
        super(message, errors);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT; // 409 Conflict mantıklı
    }
}