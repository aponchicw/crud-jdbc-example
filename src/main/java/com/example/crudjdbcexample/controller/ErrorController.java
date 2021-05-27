package com.example.crudjdbcexample.controller;

import com.example.crudjdbcexample.models.errors.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController extends BaseController {
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> errors(ServiceException e) {
        return buildResponse(buildErrorResponse(e), e.getHttpStatus());
    }
}
