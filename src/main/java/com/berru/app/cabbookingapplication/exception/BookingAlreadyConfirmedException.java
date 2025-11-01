package com.berru.app.cabbookingapplication.exception;

import org.springframework.http.HttpStatus;
import java.util.List;

public class BookingAlreadyConfirmedException extends BaseException {

    public BookingAlreadyConfirmedException(String message) {
        super(message);
    }

    public BookingAlreadyConfirmedException(String message, List<? extends ErrorDetailDto> errors) {
        super(message, errors);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}