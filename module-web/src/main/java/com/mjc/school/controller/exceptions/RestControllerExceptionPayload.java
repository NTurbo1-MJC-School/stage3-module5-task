package com.mjc.school.controller.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class RestControllerExceptionPayload {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public RestControllerExceptionPayload(
            String message,
            Throwable throwable,
            HttpStatus httpStatus,
            ZonedDateTime timestamp
    ) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
