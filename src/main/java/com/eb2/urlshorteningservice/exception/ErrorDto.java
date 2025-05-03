package com.eb2.urlshorteningservice.exception;

import org.springframework.http.HttpStatus;

public record ErrorDto(String message, HttpStatus httpStatus, String statusCode) {
}
