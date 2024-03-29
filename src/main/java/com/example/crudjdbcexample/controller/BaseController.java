package com.example.crudjdbcexample.controller;

import com.example.crudjdbcexample.models.errors.ErrorResponse;
import com.example.crudjdbcexample.models.errors.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    protected ResponseEntity<?> buildResponse(Object data, HttpStatus httpStatus){
        return new ResponseEntity<>(data , httpStatus);
    }
    protected ResponseEntity<?> buildResponse(HttpStatus httpStatus){
        return new ResponseEntity<>(httpStatus);
    }

    protected ErrorResponse buildErrorResponse(ServiceException serviceException){
        return ErrorResponse.builder()
                .code(serviceException.getErrorCode())
                .message(serviceException.getMessage())
                .build();
    }

    protected ResponseEntity<?> buildSuccessResponse(Object data){
        return new ResponseEntity<>(data , HttpStatus.OK);
    }}
