package com.catalogue.authentication.exception;

import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ApiExceptionHandler {

        @ExceptionHandler(ApiRequestException.class)
        public ResponseEntity<?> handleApiRequestException(
                        ApiRequestException e) {
                return ExceptionResponseEntity.getExceptionResponse(
                                Collections.singletonList(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(NullPointerException.class)
        public ResponseEntity<?> handleNullPointerException(
                        NullPointerException e) {
                return ExceptionResponseEntity.getExceptionResponse(
                                Collections.singletonList(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(UsernameNotFoundException.class)
        public ResponseEntity<?> handleUsernameNotFoundException(
                        UsernameNotFoundException e) {
                return ExceptionResponseEntity.getExceptionResponse(
                                Collections.singletonList(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(MissingRequestHeaderException.class)
        public ResponseEntity<?> handleServletRequestBindingException(
                        MissingRequestHeaderException e) {
                return ExceptionResponseEntity.getExceptionResponse(Collections.singletonList("Please provide "
                                + e.getHeaderName() + " with your request"), HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
        public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(
                        HttpRequestMethodNotSupportedException e) {
                return ExceptionResponseEntity.getExceptionResponse(
                                Collections.singletonList(e.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
        }

        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<?> handleAccessDeniedException(
                        AccessDeniedException e) {
                return ExceptionResponseEntity.getExceptionResponse(
                                Collections.singletonList(e.getMessage()), HttpStatus.FORBIDDEN);
        }

        @ExceptionHandler(ConstraintViolationException.class)
        ResponseEntity<?> handleConstraintViolation(ConstraintViolationException e) {
                List<String> messages = e
                                .getConstraintViolations().stream()
                                .map(err -> err.unwrap(ConstraintViolation.class))
                                .map(ConstraintViolation::getMessage).toList();
                return ExceptionResponseEntity.getExceptionResponse(messages, HttpStatus.NOT_FOUND);

        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
                List<String> messages = e
                                .getBindingResult().getAllErrors().stream()
                                .map(err -> err.unwrap(ConstraintViolation.class))
                                .map(ConstraintViolation::getMessage)
                                .collect(Collectors.toList());
                return ExceptionResponseEntity.getExceptionResponse(messages, HttpStatus.NOT_FOUND);
        }
}