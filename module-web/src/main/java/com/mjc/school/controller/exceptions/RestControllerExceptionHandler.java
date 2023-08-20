package com.mjc.school.controller.exceptions;

import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.exceptions.ValidatorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class, ValidatorException.class, NullPointerException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        String eMessage = e.getMessage();
        ZonedDateTime timestamp = ZonedDateTime.now(ZoneId.of("Z"));

        if (e instanceof NotFoundException) {
            HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
            RestControllerExceptionPayload restControllerExceptionPayload = new RestControllerExceptionPayload(
                    eMessage,
                    e,
                    notFoundStatus,
                    timestamp
            );

            return new ResponseEntity(restControllerExceptionPayload, notFoundStatus);
        } else {
            HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
            RestControllerExceptionPayload restControllerExceptionPayload = new RestControllerExceptionPayload(
                    eMessage,
                    e,
                    badRequestStatus,
                    timestamp
            );

            return new ResponseEntity(restControllerExceptionPayload, badRequestStatus);
        }
    }
}
