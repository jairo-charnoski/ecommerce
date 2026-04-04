package com.charnoski.ecommerce.controllers.handlers;

import com.charnoski.ecommerce.dto.CustomError;
import com.charnoski.ecommerce.services.exceptions.DatabaseException;
import com.charnoski.ecommerce.services.exceptions.ResouceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResouceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResouceNotFoundException exception,
                                                        HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(Instant.now(), status.value(),
                exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(customError);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> database(DatabaseException exception,
                                                        HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError customError = new CustomError(Instant.now(), status.value(),
                exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(customError);
    }

}
