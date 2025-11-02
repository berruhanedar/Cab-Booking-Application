package com.berru.app.cabbookingapplication.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class InvalidBookingStateException extends BaseException {


    public InvalidBookingStateException(String message) {
        super(message);
    }

    public InvalidBookingStateException(String message, List<? extends ErrorDetailDto> errors) {
        super(message, errors);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}