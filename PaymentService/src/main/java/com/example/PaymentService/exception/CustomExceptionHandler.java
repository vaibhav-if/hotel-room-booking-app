package com.example.PaymentService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidTransactionIdException.class)
    public ResponseEntity handleInvalidTransactionIdException() {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("message", "Invalid Transaction Id");
        error.put("statusCode", 400);

        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
