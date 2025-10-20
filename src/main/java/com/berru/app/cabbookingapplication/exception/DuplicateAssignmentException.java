package com.berru.app.cabbookingapplication.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class DuplicateAssignmentException extends BaseException {

    public DuplicateAssignmentException(String message) {
        super(message);
    }

    public DuplicateAssignmentException(String message, List<? extends ErrorDetailDto> errors) {
        super(message, errors);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}