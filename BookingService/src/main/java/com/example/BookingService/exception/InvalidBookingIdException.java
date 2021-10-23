package com.example.BookingService.exception;

public class InvalidBookingIdException extends RuntimeException {
    public InvalidBookingIdException() {
        super("Invalid Booking Id");
    }
}
