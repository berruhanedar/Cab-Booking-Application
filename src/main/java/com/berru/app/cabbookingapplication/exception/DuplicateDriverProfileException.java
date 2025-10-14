package com.berru.app.cabbookingapplication.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class DuplicateDriverProfileException extends BaseException {

    public DuplicateDriverProfileException(String message) {
        super(message);
    }

    public DuplicateDriverProfileException(String message, List<? extends ErrorDetailDto> errors) {
        super(message, errors);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
