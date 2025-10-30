package com.berru.app.cabbookingapplication.exception;

import org.springframework.http.HttpStatus;
import java.util.List;

public class DuplicateRatingException extends BaseException {

    public DuplicateRatingException(String message) {
        super(message);
    }

    public DuplicateRatingException(String message, List<? extends ErrorDetailDto> errors) {
        super(message, errors);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}