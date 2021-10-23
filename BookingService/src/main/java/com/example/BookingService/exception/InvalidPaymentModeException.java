package com.example.BookingService.exception;

public class InvalidPaymentModeException extends RuntimeException {
    public InvalidPaymentModeException() {
        super("Invalid mode of payment");
    }
}
