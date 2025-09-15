package com.berru.app.cabbookingapplication.exception.handler;

import com.berru.app.cabbookingapplication.exception.BaseException;
import com.berru.app.cabbookingapplication.exception.IBaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<IBaseException> handleBaseException(IBaseException ex) {
        return new ResponseEntity<>(ex, ex.getHttpStatus());
    }
}