package com.berru.app.cabbookingapplication.exception;

import com.berru.app.cabbookingapplication.exception.BaseException;
import com.berru.app.cabbookingapplication.exception.ErrorDetailDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public class InvalidOrderStateException extends BaseException {

    public InvalidOrderStateException(String message) {
        super(message);
    }

    public InvalidOrderStateException(String message, List<? extends ErrorDetailDto> errors) {
        super(message, errors);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}