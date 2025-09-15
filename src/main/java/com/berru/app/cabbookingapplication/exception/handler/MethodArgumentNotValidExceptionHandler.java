package com.berru.app.cabbookingapplication.exception.handler;

import com.berru.app.cabbookingapplication.exception.IBaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MethodArgumentNotValidExceptionHandler extends BaseBindExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<IBaseException> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return processBindingResult(ex.getBindingResult());
    }
}