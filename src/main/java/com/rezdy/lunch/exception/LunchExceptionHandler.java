package com.rezdy.lunch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rezdy.lunch.controller.LunchController;

import java.time.format.DateTimeParseException;

/**
 * Used by Spring when the {@linkplain LunchController} receives a mal-formatted date parameter
 *
 */
@RestControllerAdvice
public class LunchExceptionHandler {

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(DateTimeParseException exception) {
        return new ResponseEntity<>(new ErrorResponse().setErrorMessage(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
