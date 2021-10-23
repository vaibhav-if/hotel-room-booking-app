package com.example.BookingService.exception;

public class UpiIdNotFoundException extends RuntimeException {
    public UpiIdNotFoundException() {
        super("Upi Id not found");
    }
}
