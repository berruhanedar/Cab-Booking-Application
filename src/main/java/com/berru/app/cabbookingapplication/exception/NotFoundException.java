package com.berru.app.cabbookingapplication.exception;

import com.berru.app.cabbookingapplication.exception.BadRequestException;

public class NotFoundException extends BadRequestException {

    public NotFoundException(String message) {
        super(message);
    }
}
