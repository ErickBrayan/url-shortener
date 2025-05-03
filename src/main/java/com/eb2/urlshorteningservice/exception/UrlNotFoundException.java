package com.eb2.urlshorteningservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UrlNotFoundException extends RuntimeException {

    private final HttpStatus status;
    private final String statusCode;

    public UrlNotFoundException(String message, HttpStatus httpStatus, String statusCode) {
        super(message);
        this.status = httpStatus;
        this.statusCode = statusCode;
    }
}
