package com.catalogue.authentication.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class ApiException {
    private final List<String> message;
    private final HttpStatus httpStatus;
    // private final ZonedDateTime timestamp = ZonedDateTime.now();
    // private List<String> detailedMessages;

}