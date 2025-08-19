package com.horizon.backend.exception;

import com.horizon.backend.enums.ErrorAttribute;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePersonNotFound(RuntimeException ex){
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex){
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex){
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    public ResponseEntity<Map<String, Object>> buildError(HttpStatus status, String message){
        Map<String, Object> error = new HashMap<>();
        error.put(ErrorAttribute.TIMESTAMP.getKey(), LocalDate.now());
        error.put(ErrorAttribute.STATUS.getKey(), HttpStatus.NOT_FOUND.value());
        error.put(ErrorAttribute.ERROR.getKey(), "Internal Server Error");
        error.put(ErrorAttribute.MESSAGE.getKey(), message);
        return new ResponseEntity<>(error, status);
    }
}
