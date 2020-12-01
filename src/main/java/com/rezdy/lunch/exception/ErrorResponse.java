package com.rezdy.lunch.exception;

/**
 * Used by {@linkplain LunchExceptionHandler}
 *
 */
public class ErrorResponse {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public ErrorResponse setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
