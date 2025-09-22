package com.study.coursemanager.controller.exeptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.study.coursemanager.services.exeptions.BusinessException;
import com.study.coursemanager.services.exeptions.InvalidUserDataExeption;
import com.study.coursemanager.services.exeptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExeptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFoundExepetion(Exception ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(Instant.now(),
                status.value(),
                "User not found",
                ex.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }
    @ExceptionHandler(InvalidUserDataExeption.class)
    public ResponseEntity<StandardError> handleInvalidUserData(InvalidUserDataExeption ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(
                Instant.now(),
                status.value(),
                "Bad Request",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(standardError);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Data Integrity Violation",
                "A user with the given email or username already exists.",
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> handleJsonParseErrors(
            HttpMessageNotReadableException ex, HttpServletRequest request) {

        String error = "Invalid request body";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        String message = "Malformed JSON or invalid data type.";

        if (ex.getCause() instanceof InvalidFormatException invalidFormat) {
            String field = invalidFormat.getPath().get(0).getFieldName();
            message = "Invalid value for field '" + field + "'. Expected type: "
                    + invalidFormat.getTargetType().getSimpleName();
        }

        StandardError standardError = new StandardError(
                Instant.now(),
                status.value(),
                error,
                message,
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(standardError);
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardError> handleBusinessException(BusinessException ex , HttpServletRequest request) {
        String error = "Invalid parameters";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError standardError = new StandardError(
                Instant.now(),
                status.value(),
                error,
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(standardError);
    }


}
