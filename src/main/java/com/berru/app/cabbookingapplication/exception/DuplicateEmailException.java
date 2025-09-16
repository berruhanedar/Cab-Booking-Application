package com.berru.app.cabbookingapplication.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class DuplicateEmailException extends BaseException {
    public DuplicateEmailException(String message) {
        super(message);
    }

    public DuplicateEmailException(String message, List<? extends ErrorDetailDto> errors) {
        super(message, errors);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
