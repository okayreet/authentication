package com.catalogue.authentication.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionResponseEntity {

    public static ResponseEntity<?> getExceptionResponse(
            List<String> message, HttpStatus httpStatus) {

        ApiException apiException = new ApiException(message, httpStatus);
        return new ResponseEntity<>(apiException, httpStatus);
    }
}