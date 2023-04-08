package com.salatin.demouser.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    private final static String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @ExceptionHandler(value = {
        BadCredentialsException.class,
    })
    public ResponseEntity<ApiExceptionObject> handleLimitedPermissionException(
        RuntimeException e
    ) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        return new ResponseEntity<>(getApiExceptionObject(e.getMessage(), status), status);
    }

    private ApiExceptionObject getApiExceptionObject(String message, HttpStatus status) {
        return new ApiExceptionObject(
            message,
            status,
            LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN))
        );
    }
}
