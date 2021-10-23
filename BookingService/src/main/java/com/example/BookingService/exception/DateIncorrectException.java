package com.example.BookingService.exception;

public class DateIncorrectException extends RuntimeException {
    public DateIncorrectException() {
        super("fromDate cannot be greater than toDate");
    }
}
