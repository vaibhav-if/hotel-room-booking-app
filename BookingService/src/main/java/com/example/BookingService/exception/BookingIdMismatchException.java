package com.example.BookingService.exception;

public class BookingIdMismatchException extends RuntimeException {
    public BookingIdMismatchException() {
        super("Booking id in path variable and request body do not match");
    }
}
