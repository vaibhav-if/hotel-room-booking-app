package com.example.BookingService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookingIdMismatchException.class)
    public ResponseEntity handleBookingIdMismatchException() {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("message", "Booking id in path variable and request body do not match");
        error.put("statusCode", 400);

        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CardDetailsNotFoundException.class)
    public ResponseEntity handleCardDetailsNotFoundException() {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("message", "Card details not found");
        error.put("statusCode", 400);

        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateIncorrectException.class)
    public ResponseEntity handleDateIncorrectException() {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("message", "From-Date cannot be greater than To-Date");
        error.put("statusCode", 400);

        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidBookingIdException.class)
    public ResponseEntity handleInvalidBookingIdException() {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("message", "Invalid Booking Id");
        error.put("statusCode", 400);

        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPaymentModeException.class)
    public ResponseEntity handleInvalidPaymentModeException() {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("message", "Invalid mode of payment");
        error.put("statusCode", 400);

        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UpiIdNotFoundException.class)
    public ResponseEntity handleUpiIdNotFoundException() {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("message", "Upi Id not found");
        error.put("statusCode", 400);

        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionIdExistsException.class)
    public ResponseEntity handleTransactionIdExistsException() {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("message", "Transaction Id already exists for this booking");
        error.put("statusCode", 400);

        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
