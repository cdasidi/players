package com.intuit.players.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<String> handlePlayerNotFoundException(PlayerNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        // Log the exception details for debugging purposes
        // logger.error("Exception occurred: ", exception);
        return new ResponseEntity<>("An error occurred: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
