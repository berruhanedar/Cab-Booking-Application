package com.berru.app.cabbookingapplication.exception;

import com.berru.app.cabbookingapplication.exception.BaseException;
import com.berru.app.cabbookingapplication.exception.ErrorDetailDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public class BadRequestException extends BaseException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, List<? extends ErrorDetailDto> errors) {
        super(message, errors);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}