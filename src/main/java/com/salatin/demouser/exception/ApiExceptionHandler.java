package com.salatin.demouser.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    private final static String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @ExceptionHandler(value = {
        BadCredentialsException.class,
    })
    public ResponseEntity<ApiExceptionObject> handleBadCredentialsException(
        RuntimeException e
    ) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        return new ResponseEntity<>(getApiExceptionObject(e.getMessage(), status), status);
    }

    @ExceptionHandler(value = {
        UserAlreadyExistsException.class,
    })
    public ResponseEntity<ApiExceptionObject> handleUserAlreadyExistsException(
        RuntimeException e
    ) {
        HttpStatus status = HttpStatus.CONFLICT;
        return new ResponseEntity<>(getApiExceptionObject(e.getMessage(), status), status);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiExceptionObject> handleBadRequestException(
        MethodArgumentNotValidException e
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorMessages = e.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.joining(", "));
        return new ResponseEntity<>(getApiExceptionObject(errorMessages, status), status);
    }

    private ApiExceptionObject getApiExceptionObject(String message, HttpStatus status) {
        return new ApiExceptionObject(
            message,
            status,
            LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN))
        );
    }
}
