package com.example.BookingService.exception;

public class TransactionIdExistsException extends RuntimeException {
    public TransactionIdExistsException() {
        super("Transaction Id already exists for this booking");
    }
}
